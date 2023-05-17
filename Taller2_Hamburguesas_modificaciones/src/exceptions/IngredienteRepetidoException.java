package exceptions;

public class IngredienteRepetidoException extends HamburguesaException {
	
	private String IngredienteNombre;
	
	public IngredienteRepetidoException(String nombreingrediente)
	{
		super("Ingrediente repetido "+ nombreingrediente);
		this.IngredienteNombre = nombreingrediente;
	}
	
	public String getNombreIngrediente() 
	{
        return IngredienteNombre;
    }
}
