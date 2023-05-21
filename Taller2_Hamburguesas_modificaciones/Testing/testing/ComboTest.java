package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import logic.Combo;
import logic.Producto;
import logic.ProductoAjustado;
import logic.ProductoMenu;

public class ComboTest {

	private Combo combotest;
	private ProductoAjustado productotest;
	private ProductoMenu productomenu = new ProductoMenu("pera", 300, 50);
	private Combo definido = new Combo ("corral", 7);
	
	@BeforeEach
	public void setUp()
	{
		combotest = new Combo("corral", 7);
		productotest = new ProductoAjustado(productomenu);
		definido.agregarItemACombo(productotest);
		combotest.agregarItemACombo(productotest);
		
	}
	
	
	@Test
	@Order(1)
	@DisplayName("Agregar item a combo")
	void testAgregar() 
	{
		
		assertEquals("No son iguales", definido.getPrecio(), combotest.getPrecio() );
		assertEquals("No son iguales", definido.getCalorias(), combotest.getCalorias() );
	}

	@Test
	@Order(2)
	@DisplayName("Generar texto factura")
	void testFactura() 
	{
		
		String formatPrint = "%-25.25s %14.14s";
		String Expected = String.format(formatPrint, "corral", Integer.toString(300));
		assertEquals("Factura dispar", Expected, combotest.generarTextoFactura());
		
	}

	@Test
	@Order(3)
	@DisplayName("Get Calorias")
	void testCalorias() 
	{
		assertEquals("calorias dispares", 50, combotest.getCalorias());
	}

}
