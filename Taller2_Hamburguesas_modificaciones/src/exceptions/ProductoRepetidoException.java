package exceptions;

public class ProductoRepetidoException extends HamburguesaException {
	
    private String nombreProducto;

    public ProductoRepetidoException(String nombreProducto) {
        super("Producto repetido: " + nombreProducto);
        this.nombreProducto = nombreProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }
}
