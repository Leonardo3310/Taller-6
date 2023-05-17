package logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private int numeroPedidos;
	private String idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private List<Producto> productos = new ArrayList<>();
	
	public Pedido(String nombreCliente, String direccionCliente, int numeroPedidos) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.numeroPedidos = numeroPedidos;
	}
	
	public String getIdPedido() {
		idPedido = DateTimeFormatter.BASIC_ISO_DATE.format(LocalDateTime.now()) + String.format("%03d" , numeroPedidos);
		return idPedido;
	}
	
	public void agregarProducto(Producto nuevoItem) {
		productos.add(nuevoItem);
	}

	private int getPrecioNetoPedido() {
		int precioNeto = 0;
		for(Producto producto : productos) {
			int precio = producto.getPrecio();
			precioNeto += precio;
		}
		return precioNeto;
	}
	
	private int getPrecioIVAPedido() {
		int precioIVA = 0;
		for(Producto producto : productos) {
			int precio = producto.getPrecio();
			precioIVA += Math.round(precio * 0.19);
		}
		return precioIVA;
	}
	
	private int getPrecioTotalPedido() {
		return getPrecioNetoPedido() + getPrecioIVAPedido();
	}
	
	private int getTotalCalorias() {
		int calorias = 0;
		for(Producto producto : productos) {
			calorias += producto.getCalorias();
		}
		return calorias;
	}
	
	private String generarTextoFactura() {
		String formatPrint = "%-25.25s %14.14s";
		String factura = "RESTAURANTE\n\nFactura de venta\n\n";
		factura += String.format(formatPrint, "ID Factura:", idPedido) + "\n";
		factura += String.format(formatPrint, "Cliente:", nombreCliente) + "\n";
		factura += String.format(formatPrint, "Direccion:", direccionCliente) + "\n\n";
		factura += String.format(formatPrint, "Descripcion", "Valor") + "\n";
		for(Producto producto : productos) {
			factura += producto.generarTextoFactura() + "\n";
		}
		factura += "\n" + String.format(formatPrint, "Total calorias:", Integer.toString(getTotalCalorias()));
		factura += "\n\n" + String.format(formatPrint, "Valor Neto:", Integer.toString(getPrecioNetoPedido()));
		factura += "\n" + String.format(formatPrint, "Valor IVA:", Integer.toString(getPrecioIVAPedido()));
		factura += "\n\n" + String.format(formatPrint, "Valor Total:", Integer.toString(getPrecioTotalPedido()));
		return factura;
	}
	
	public String consultarPedido() {
		return generarTextoFactura();
	}
	
	public void guardarFactura(File file) {
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(generarTextoFactura());
			bw.close();
			System.out.println("\n\n" + generarTextoFactura());
			System.out.println("\n\nLa factura se guardo con exito en la direccion: data/factura.txt\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	 public boolean equals(Object objeto) {
		 boolean equal = false;
		 if (objeto instanceof Pedido) {
			 Pedido pedidoTemporal = (Pedido) objeto;
			 if(this.productos.containsAll(pedidoTemporal.productos)) {
				 equal = true;
			 }
		 }
		 return equal;
	 }
}
