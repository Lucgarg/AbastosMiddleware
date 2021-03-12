package com.abastos.service;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.abastos.model.LineaPedido;
import com.abastos.model.Pedido;
import com.abastos.service.exceptions.MailException;
import com.abastos.service.exceptions.ServiceException;
import com.abastos.service.impl.LineaPedidoServiceImpl;
import com.abastos.service.impl.PedidoServiceImpl;

public class PedidoServiceTest {
	private static Logger logger = LogManager.getLogger(PedidoServiceTest.class);
	private PedidoService pedServ;
	private LineaPedidoService linServ;
	@Before
	public void setUp() throws Exception {

		pedServ = new PedidoServiceImpl();
		linServ = new LineaPedidoServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	public void testCalcPrecioOferta() throws Exception{
		LineaPedido linPed = new LineaPedido();
		LineaPedido linPed1 = new LineaPedido();
		Pedido pedido = new Pedido();
		// con oferta tipo comprar y llevate
	
			linPed.setNumerador(2);
			linPed.setDenominador(3);
			linPed.setDescuentoFijo(1d);
			linPed.setIdTipoOferta(2);
			linPed.setNumeroUnidades(12);
			linPed.setNombreProducto("prueba001");
			linPed.setIdOferta(1L);
			linPed.setIdProducto(20L);
			linPed.setPrecio(13d);
			pedido.add(linPed);
			//48  145,5
			linPed1.setDescuentoPcn(50d);
			linPed1.setIdTipoOferta(3);
			linPed1.setNumeroUnidades(6);
			linPed1.setNombreProducto("prueba001");
			linPed1.setIdOferta(1L);
			linPed1.setIdProducto(18L);
			linPed1.setIdProdOferta(20L);
			linPed1.setPrecio(130d);
			pedido.add(linPed1);
			//780
			pedido.setIdParticular(1L);
			pedido.setAplicarDescuento(true);
			pedido.setId(1L);
			pedido.setIdEstado('S');
			assertEquals(915.5,pedServ.create(pedido).getPrecioTotal(),0.01);
		

	}
	
	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();
		
			assertNotNull(pedServ.findById(1L));
	
		logger.traceExit();
	}
	
	@Test
	public void testFindByIdParticular() throws Exception{
		logger.traceEntry();
		
			assertNotEquals(Collections.EMPTY_LIST,pedServ.findByIdParticular(1L));
		
		logger.traceExit();
	}
	
	
	@Test
	public void testUpdateEstado() throws Exception{
		logger.traceEntry();
		
			assertTrue(pedServ.updateEstado('S', 1l));
	
		logger.traceExit();
	}

}
