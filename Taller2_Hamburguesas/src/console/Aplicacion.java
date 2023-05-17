package console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import logic.Producto;
import logic.ProductoAjustado;
import logic.ProductoMenu;
import logic.Restaurante;
import logic.Ingrediente;
import logic.Pedido;

public class Aplicacion {
	Restaurante restaurante = new Restaurante();
	
	public void mostrarMenu() {
		System.out.println("Opciones de la aplicacion\n");
		System.out.println("1. Mostrar el Menu");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar un elemento al pedido");
		System.out.println("4. Cerrar el pedido y guardar la factura");
		System.out.println("5. Consultar la informacion de un pedido dado su ID");
		System.out.println("6. Salir de la aplicación\n");
	}
	
	public void ejecutarAplicacion() {
		System.out.println("RESTAURANTE\n\n");
		boolean continuar = true;
		while(continuar) {
			try {
				mostrarMenu();
				int opcion = Integer.parseInt(input("Por favor seleccione una opcion"));
				if(opcion == 1) {
					mostrarMenuRestaurante();
				}else if(opcion == 2) {
					iniciarPedido();
				}else if(opcion == 3) {
					agregarElementoAPedido();
				}else if(opcion == 4) {
					cerrarPedido();
				}else if(opcion == 5) {
					consultarPedidoPorID();
				}else if(opcion == 6) {
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}else {
					System.out.println("Por favor seleccione una opción válida.");
				}
				mostrarPedidoActual();
				if(continuar) {
					System.out.println("\n\nPresione Enter para continuar...");
					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					reader.readLine();
				}
			}catch (NumberFormatException e){
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
	
	private void mostrarMenuRestaurante() {
		ArrayList<Producto> menuBase = restaurante.getMenuBase();
		ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
		String formatPrint = "%-3.3s %-25.25s %5.5s";
		int index = 1;
		System.out.println("\nMENU\n");
		System.out.format(formatPrint, "#", "Producto", "Valor");
		System.out.println();
		for(Producto producto : menuBase) {
			System.out.format(formatPrint, Integer.toString(index) + "- ", producto.getNombre(), producto.getPrecio());
		    System.out.println();
			index++;
		}
		index = 1;
		System.out.println("\n\nIngredientes Extra\n");
		System.out.format(formatPrint, "#", "Ingrediente", "Valor");
		System.out.println();
		for(Ingrediente ingrediente : ingredientes) {
			System.out.format(formatPrint, Integer.toString(index) + "- ", ingrediente.getNombre(), ingrediente.getCostoAdicional());
			System.out.println();
			index++;
		}
	}
	
	private void iniciarPedido() {
		if(restaurante.getPedidoEnCurso() == null) {
			System.out.println("\n\nINICIAR PEDIDO\n");
			String nombreCliente = input("Nombre del Cliente");
			String direccionCliente = input("Direccion del cliente");
			restaurante.iniciarPedido(nombreCliente, direccionCliente);
			System.out.println("El ID del pedido es: " + restaurante.getPedidoEnCurso().getIdPedido());
		}else {
			System.out.println("Tiene un pedido en curso.");
		}
	}
	
	private void agregarElementoAPedido() {
		if(restaurante.getPedidoEnCurso() == null) {
			System.out.println("No se ha iniciado ningun pedido.");
		}else {
			System.out.println("\n\nAGREGAR ELEMENTO A PEDIDO\n");
			int elemento = Integer.parseInt(input("Numero del elemento"));
			Producto producto = restaurante.getMenuBase().get(elemento - 1);
			if(producto instanceof ProductoMenu) {
				ProductoMenu productoMenu = (ProductoMenu) producto;
				System.out.println("\nDesea Agregar/Quitar ingrediente a " + producto.getNombre() + "\n\n1- Si\n2- No\n");
				int agregarQuitar = Integer.parseInt(input("R/"));
				if(agregarQuitar == 1) {
					ProductoAjustado productoAjustado = new ProductoAjustado(productoMenu);
					boolean continuar = true;
					while(continuar) {
						int indexIngrediente = Integer.parseInt(input("\nNumero del ingrediente"));
						Ingrediente ingrediente = restaurante.getIngredientes().get(indexIngrediente - 1);
						System.out.println("\nAgregar/Quitar " + ingrediente.getNombre() + "\n\n1- Agregar\n2- Quitar\n");
						agregarQuitar =Integer.parseInt(input("R/"));
						if(agregarQuitar == 1) {
							productoAjustado.agregarIngrediente(ingrediente);
						}else {
							productoAjustado.quitarIngrediente(ingrediente);
						}
						System.out.println("\n\nDesea Agregar/Quitar otro ingrediente a " + producto.getNombre() + "\n\n1- Si\n2- No\n");
						int opcion = Integer.parseInt(input("R/"));
						if(opcion == 2) {
							continuar = false;
						}
					}
					producto = productoAjustado;
				}
			}
			restaurante.getPedidoEnCurso().agregarProducto(producto);
			System.out.println("\nSe agrego " + producto.getNombre() + " al pedido");
		}
	}
	
	private void cerrarPedido() {
		if(restaurante.getPedidoEnCurso() == null) {
			System.out.println("No se ha iniciado ningun pedido.");
		}else {
			restaurante.cerrarYGuardarPedido();
		}
	}
	
	private void consultarPedidoPorID() {
		String id = input("ID del pedido");
		Pedido pedido = restaurante.getPedidoID(id);
		if(pedido == null) {
			System.out.println("No existe un pedido con ese ID.");
		}else {
			System.out.println(pedido.consultarPedido());
		}
	}
	
	private void mostrarPedidoActual() {
		if(restaurante.getPedidoEnCurso() == null) {
			System.out.println("\n\nNo hay ningun pedido en curso.");
		}else {
			System.out.println("\n\nPedido en curso: " + restaurante.getPedidoEnCurso().getIdPedido());
		}
	}
	
	public String input(String mensaje) {
		try {
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}catch (IOException e){
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.ejecutarAplicacion();
	}
}
