package exceptions;

public class PriceException extends HamburguesaException{
	
	private int precio ;

    public PriceException(int precio) {
        super("El precio de su pedido fue mayor de 150k: " + String.valueOf(precio) + " $");
        this.precio = precio;
    }

    public int getPrecioProducto() {
        return precio;
    }
}

