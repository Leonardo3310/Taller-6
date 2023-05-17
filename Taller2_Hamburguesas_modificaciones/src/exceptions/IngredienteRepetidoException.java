package exceptions;

public class IngredienteRepetidoException extends HamburguesaException {
	
	private ingredienteNombre;
	
	public IngredienteRepetidoException(String nombreingrediente)
	{
		super("Ingrediente repetido "+ nombreingrediente);
		this.ingredienteNombre = nombreingrediente;
	}
	
	public String getNombreIngrediente() 
	{
        return nombreIngrediente;
    }
}
