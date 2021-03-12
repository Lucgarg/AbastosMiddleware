package com.abastos.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;


import java.sql.Connection;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.dao.jdbc.ComunidadAutonomaDAOImpl;
import com.abastos.dao.util.ConnectionManager;

public class ComunidadAutonomaDAOTest {
	private static Logger logger = LogManager.getLogger(ComunidadAutonomaDAOTest.class);
	private Connection connection;
	private ComunidadAutonomaDAO comunidadDAO;
	@Before
	public void setUp() throws Exception {
		comunidadDAO = new ComunidadAutonomaDAOImpl();
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

		assertNotNull(comunidadDAO.findById(connection, 1L));

		logger.traceExit();
	}

	@Test
	public void testFindByIdPais() throws Exception{
		logger.traceEntry();

		assertNotEquals(Collections.EMPTY_LIST,comunidadDAO.findByIdPais(connection, 1L));

		logger.traceExit();
	}

	@Test
	public void testFindByTienda()throws Exception {
		logger.traceEntry();


		assertNotEquals(Collections.EMPTY_LIST,comunidadDAO.findByTienda(connection, 1));

		logger.traceExit();
	}

}
