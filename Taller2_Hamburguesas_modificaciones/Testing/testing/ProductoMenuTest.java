package testing;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import logic.Combo;
import logic.ProductoAjustado;
import logic.ProductoMenu;

public class ProductoMenuTest {

	
	
	private ProductoMenu productomenu;
	private ProductoMenu productomenu2;
	
	@BeforeEach
	public void setUp()
	{
		productomenu = new ProductoMenu("pera", 300, 50);
		productomenu2 = new ProductoMenu("pera", 300, 50);
	}
	
	
	

	@Test
	@Order(1)
	@DisplayName("Generar texto factura")
	void testFactura() 
	{
		
		String formatPrint = "%-25.25s %14.14s";
		String Expected = String.format(formatPrint, "pera", Integer.toString(300));
		assertEquals("Factura dispar", Expected, productomenu.generarTextoFactura());
		
	}

	@Test
	@Order(2)
	@DisplayName("Get Calorias")
	void testCalorias() 
	{
		assertEquals("calorias dispares", 50, productomenu.getCalorias());
	}

}
