package logic;

public class ProductoMenu implements Producto{
	private String nombre;
	private int precioBase;
	private int calorias;
	
	public ProductoMenu(String nombre, int precioBase, int calorias){
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.calorias = calorias;
	}
	
	@Override
	public String getNombre() {
		return nombre;
	}
	
	@Override
	public int getPrecio() {
		return precioBase;
	}
	
	@Override
	public String generarTextoFactura() {
		String formatPrint = "%-25.25s %14.14s";
		return String.format(formatPrint, nombre, Integer.toString(precioBase));
	}

	@Override
	public int getCalorias() {
		return calorias;
	}
}
