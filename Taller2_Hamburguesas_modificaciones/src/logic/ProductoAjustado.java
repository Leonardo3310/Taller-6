package logic;

public class ProductoAjustado implements Producto {
	private ProductoMenu base;
	private int precio = 0;
	private String factura = "";
	private int calorias = 0;
	
	public ProductoAjustado(ProductoMenu base){
		this.base = base;
	}
	
	public void agregarIngrediente(Ingrediente ingrediente) {
		precio += ingrediente.getCostoAdicional();
		String formatPrint = "%-25.25s %14.14s";
		factura += "\n" + String.format(formatPrint, "-->Con " + ingrediente.getNombre(), "+" + Integer.toString(ingrediente.getCostoAdicional()));
		calorias += ingrediente.getCalorias();
	}
	
	public void quitarIngrediente(Ingrediente ingrediente) {
		String formatPrint = "%-25.25s %14.14s";
		factura += "\n" + String.format(formatPrint, "-->Sin " + ingrediente.getNombre(), "+0");
		calorias -= ingrediente.getCalorias();
	}
	
	@Override
	public int getPrecio() {
		return base.getPrecio() + precio;
	}
	
	@Override
	public String getNombre() {
		return base.getNombre();
	}
	
	@Override
	public String generarTextoFactura() {
		return base.generarTextoFactura() + factura;
	}

	@Override
	public int getCalorias() {
		return base.getCalorias() + calorias;
	}
}
