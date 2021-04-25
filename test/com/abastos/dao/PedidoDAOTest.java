package com.abastos.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.dao.jdbc.PedidoDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.LineaPedido;
import com.abastos.model.Pedido;

public class PedidoDAOTest {
	private static Logger logger = LogManager.getLogger(PedidoDAOTest.class);
	private PedidoDAO pedidoDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		pedidoDAO = new PedidoDAOImpl();
		connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		ConnectionManager.closeConnection(connection, false);
	}

	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();

		assertNotNull(pedidoDAO.findById(connection,1L));

		logger.traceExit();
	}

	@Test
	public void testFindByIdParticular() throws Exception{
		logger.traceEntry();

		assertNotEquals(Collections.EMPTY_LIST,pedidoDAO
				.findByIdParticular(connection,1L));

		logger.traceExit();
	}

	@Test
	public void testCreate() throws Exception{
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
		linPed.setIdOferta(7L);
		linPed.setIdProducto(15L);
		linPed.setPrecio(13d);
		linPed.setPrecioFinal(111.0);
		linPed.setIdTienda(1L);
		pedido.add(linPed);
		
		linPed1.setDescuentoPcn(50d);
		linPed1.setIdTipoOferta(3);
		linPed1.setNumeroUnidades(6);
		linPed1.setNombreProducto("prueba001");
		linPed1.setIdOferta(7L);
		linPed1.setIdProducto(18L);
		linPed1.setIdProdOferta(15L);
		linPed1.setPrecio(130d);
		linPed1.setPrecioFinal(111.0);
		linPed1.setIdTienda(1L);
		pedido.add(linPed1);
	
		pedido.setIdParticular(1L);
		pedido.setAplicarDescuento(true);
		pedido.setId(1L);
		pedido.setIdEstado('S');
		pedido.setPrecioTotal(111.0);
		assertNotNull(pedidoDAO.create(connection, pedido));

	}



	@Test
	public void testUpdateEstado() throws Exception{
		logger.traceEntry();

		assertTrue(pedidoDAO.updateEstado(connection,'S', 1l));

		logger.traceExit();
	}

}
