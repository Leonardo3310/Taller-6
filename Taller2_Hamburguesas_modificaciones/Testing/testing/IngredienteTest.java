package testing;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import logic.Ingrediente;


public class IngredienteTest {

	private Ingrediente ingrediente;
	private Ingrediente expected;
	
	@BeforeEach
	public void setUp()
	{
		expected = new Ingrediente("maiz tierno", 2000, 90);
		ingrediente = new Ingrediente("maiz tierno", 2000, 90);
		
	}
	
	
	@Test
	@Order(1)
	@DisplayName("Prueba costo adicional")
	void testAgregar() 
	{
		
		assertEquals("No son iguales", expected.getCostoAdicional(), ingrediente.getCostoAdicional());
		
	}

	@Test
	@Order(2)
	@DisplayName("Get nombre")
	void testFactura() 
	{
		
		assertEquals("No son iguales", expected.getNombre(), ingrediente.getNombre());
	}

	@Test
	@Order(3)
	@DisplayName("Get Calorias")
	void testCalorias() 
	{
		assertEquals("calorias dispares", expected.getCalorias(), ingrediente.getCalorias());
	}

}
