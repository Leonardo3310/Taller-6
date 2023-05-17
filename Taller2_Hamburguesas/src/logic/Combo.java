package logic;

public class Combo implements Producto {
	private int descuento;
	private String nombreCombo;
	private int precio = 0;
	
	public Combo(String nombreCombo, int descuento){
		this.nombreCombo = nombreCombo;
		this.descuento = descuento;
	}
	
	public void agregarItemACombo(Producto itemCombo) {
		precio += itemCombo.getPrecio();
	}
	
	@Override
	public int getPrecio() {
		return precio * descuento / 100;
	}
	
	@Override
	public String getNombre() {
		return nombreCombo;
	}
	
	@Override
	public String generarTextoFactura() {
		String formatPrint = "%-25.25s %14.14s";
		return String.format(formatPrint, nombreCombo, Integer.toString(precio));
	}
}
