package com.abastos.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


import java.sql.Connection;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.dao.jdbc.LineaListaDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.LineaLista;

public class LineaListaDAOImplTest {
	private static Logger logger = LogManager.getLogger(LineaListaDAOImplTest.class);
	private LineaListaDAO lineaListaDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		lineaListaDAO = new LineaListaDAOImpl();
		connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		ConnectionManager.closeConnection(connection, false);
	}

	@Test
	public void testFindByIdListaDeseos() throws Exception {
		logger.traceEntry();

		assertNotEquals(Collections.EMPTY_LIST,lineaListaDAO.findByIdListaDeseos(connection,4L));

		logger.traceExit();
	}

	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();

		logger.trace("Por idLista");
		assertNotEquals(Collections.EMPTY_LIST,lineaListaDAO.findById(connection,1L, null));


		logger.trace("Por idProducto");
		assertNotEquals(Collections.EMPTY_LIST,lineaListaDAO.findById(connection,null, 1L));


		logger.trace("Por idLista y idProducto");
		assertNotEquals(Collections.EMPTY_LIST,lineaListaDAO.findById(connection,1L, 1L));

		logger.traceExit();
	}

	@Test
	public void testCreate() throws Exception{
		logger.traceEntry();
		LineaLista linList = new LineaLista();

		linList.setIdProducto(21L);
		linList.setId(2L);
		linList.setNombreProducto("prueba0005");
		linList.setPrecio(22d);
		lineaListaDAO.create(connection,linList);

		logger.traceExit();
	}

	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();
		LineaLista linList = new LineaLista();

		linList = lineaListaDAO.findById(connection,1L, 11L);

		assertTrue(lineaListaDAO.update(connection,linList));

		logger.traceExit();
	}

	@Test
	public void testDelete() throws Exception{
		logger.traceEntry();

		logger.trace("Por idLista");


		assertTrue(lineaListaDAO.delete(connection,6L, null));


		logger.trace("Por idProducto");


		assertTrue(lineaListaDAO.delete(connection,null, 8L));


		logger.trace("Por idProducto y idLista");


		assertTrue(lineaListaDAO.delete(connection,7L, 44L));

		logger.traceExit();
	}

	

}
