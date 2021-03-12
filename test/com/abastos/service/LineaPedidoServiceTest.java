package com.abastos.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.abastos.model.LineaPedido;
import com.abastos.service.exceptions.NegativePriceDiscountException;
import com.abastos.service.exceptions.ServiceException;
import com.abastos.service.impl.LineaListaServiceImpl;
import com.abastos.service.impl.LineaPedidoServiceImpl;

public class LineaPedidoServiceTest {
	private static Logger logger = LogManager.getLogger(LineaPedidoServiceTest.class);
	private LineaPedidoService linServ;

	@Before
	public void setUp() throws Exception {
		linServ = new LineaPedidoServiceImpl();

	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testCalcPrecioSinDescuento() throws Exception {
		LineaPedido lineaPedido = new LineaPedido();
		logger.traceEntry();

		lineaPedido.setNumeroUnidades(12);
		lineaPedido.setNombreProducto("prueba001");
		lineaPedido.setPrecio(1.3d);
		lineaPedido.setIdProducto(1L);
		assertEquals(15.6, linServ.calcPrecio(lineaPedido), 0.01); 

		logger.traceExit();
	}


	@Test
	public void testCalcPrecioDescuentoPCN() throws Exception {
		LineaPedido lineaPedido = new LineaPedido();
		logger.trace("por descuento numerador 2 denomiandor 3...");

		lineaPedido.setNumeroUnidades(6);
		lineaPedido.setDescuentoPcn(12d);
		lineaPedido.setIdTipoOferta(2);
		lineaPedido.setNumerador(2);
		lineaPedido.setDenominador(3);
		lineaPedido.setNombreProducto("prueba001");
		lineaPedido.setPrecio(130d);
		lineaPedido.setIdProducto(1L);
		lineaPedido.setIdOferta(1L);
		assertEquals(748.8, linServ.calcPrecio(lineaPedido), 0.01); 

		logger.trace("por descuento numerador 4 denominador 6...");

		lineaPedido.setNumeroUnidades(6);
		lineaPedido.setDescuentoPcn(12d);
		lineaPedido.setIdTipoOferta(2);
		lineaPedido.setNumerador(4);
		lineaPedido.setDenominador(6);
		lineaPedido.setNombreProducto("prueba001");
		lineaPedido.setPrecio(130d);
		lineaPedido.setIdProducto(1L);
		lineaPedido.setIdOferta(1L);
		assertEquals(748.8, linServ.calcPrecio(lineaPedido), 0.01); 

		logger.traceExit();
	}
	@Test
	public void testCalcPrecioDescuentoFijo() throws Exception {
		LineaPedido lineaPedido = new LineaPedido();


		lineaPedido.setNumeroUnidades(12);
		lineaPedido.setDescuentoFijo(0.2d);
		lineaPedido.setIdTipoOferta(2);
		lineaPedido.setNumerador(2);
		lineaPedido.setDenominador(3);
		lineaPedido.setNombreProducto("prueba001");
		lineaPedido.setPrecio(1.3d);
		lineaPedido.setIdProducto(1L);
		lineaPedido.setIdOferta(1L);
		assertEquals(14.8, linServ.calcPrecio(lineaPedido), 0.01); 

		logger.traceExit();
	}


	@Test
	public void testFindByIdPedido()throws Exception{
		logger.traceEntry();

		assertNotEquals(Collections.EMPTY_LIST,linServ.findByIdPedido(1L));

		logger.traceExit();
	}

	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();

		assertNotEquals(Collections.EMPTY_LIST,linServ.findById(1L, 1L));

		logger.traceExit();
	}

	@Test
	public void testCreate() throws Exception{
		logger.traceEntry();
		LineaPedido lineaPedido = new LineaPedido();
		lineaPedido.setDenominador(3);
		lineaPedido.setDescuentoFijo(12d);
		lineaPedido.setIdOferta(1L);
		lineaPedido.setIdPedido(1L);
		lineaPedido.setIdTipoOferta(2);
		lineaPedido.setPrecio(12d);
		lineaPedido.setNumerador(2);
		lineaPedido.setNombreProducto("prueba002");
		lineaPedido.setNumeroUnidades(12);
		lineaPedido.setIdProducto(16L);

		linServ.create(lineaPedido);

		logger.traceExit();


	}

}
