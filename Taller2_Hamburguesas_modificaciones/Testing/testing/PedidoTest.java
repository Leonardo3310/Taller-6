package testing;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import logic.Pedido;
import logic.ProductoAjustado;
import logic.ProductoMenu;

public class PedidoTest {

	private Pedido pedidotest; 
	private ProductoAjustado productotest;
	private ProductoMenu productomenu = new ProductoMenu("pera", 300, 50);
	
	@BeforeEach
	public void setUp()
	{
		pedidotest = new Pedido("alfonso", "bogota", 2);
		productotest = new ProductoAjustado(productomenu);
		
		
		
	}
	
	
	@Test
	@Order(1)
	@DisplayName("Agregar item a combo")
	void testAgregar() 
	{
		//pedidotest.agregarProducto(productomenu);
		assertTrue((productotest.getPrecio()+pedidotest.getCostico() <= 150000));
		//assertEquals("No son iguales", definido,  );
	}

	@Test
	@Order(2)
	@DisplayName("Generar texto factura")
	void testFactura() 
	{
		
		String formatPrint = "%-25.25s %14.14s";
		String Expected = String.format(formatPrint, "corral", Integer.toString(300));
		//assertEquals("Factura dispar", Expected, combotest.generarTextoFactura());
		
	}

	@Test
	@Order(3)
	@DisplayName("Get Calorias")
	void testCalorias() 
	{
		//assertEquals("calorias dispares", 50, combotest.getCalorias());
	}

}
