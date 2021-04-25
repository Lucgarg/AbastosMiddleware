package com.abastos.dao;

import static org.junit.Assert.assertNotEquals;
import java.sql.Connection;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.dao.jdbc.LineaPedidoDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.LineaPedido;

public class LineaPedidoDAOTest {
	private static Logger logger = LogManager.getLogger(LineaPedidoDAOTest.class);
	private LineaPedidoDAO linPedDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		linPedDAO = new LineaPedidoDAOImpl();
		connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		ConnectionManager.closeConnection(connection, false);
	}

	@Test
	public void testFindByIdPedido()throws Exception {
		logger.traceEntry();

		assertNotEquals(Collections.EMPTY_LIST,linPedDAO.findByIdPedido(connection, 1L));

		logger.traceExit();
	}

	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();

		assertNotEquals(Collections.EMPTY_LIST,linPedDAO.findById(connection, 1L, 1L));

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
		lineaPedido.setPrecioFinal(12.0);
		lineaPedido.setIdTienda(1L);
		linPedDAO.create(connection, lineaPedido);

		logger.traceExit();
	}



}
