package logic;

public class ProductoMenu implements Producto{
	private String nombre;
	private int precioBase;
	
	public ProductoMenu(String nombre, int precioBase){
		this.nombre = nombre;
		this.precioBase = precioBase;
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
}
