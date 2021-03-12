package com.abastos.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.dao.jdbc.LocalidadDAOImpl;
import com.abastos.dao.util.ConnectionManager;

public class LocalidadDAOTest {
	private static Logger logger = LogManager.getLogger(LocalidadDAOTest.class);
	private LocalidadDAO localidadDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		localidadDAO = new LocalidadDAOImpl();
		connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		ConnectionManager.closeConnection(connection, false);
	}

	@Test
	public void testFindByIdProvincia() throws Exception{
		logger.traceEntry();

		assertNotEquals(Collections.EMPTY_LIST,localidadDAO.findByIdProvincia(connection,1L));

		logger.traceExit();

	}

	@Test
	public void testFindByIdLocalidad() throws Exception{
		logger.traceEntry();


		assertNotNull(localidadDAO.findByIdLocalidad(connection,1L));

		logger.traceExit();
	}

	@Test
	public void testFindByTiendas() throws Exception {
		logger.traceEntry();

		assertNotEquals(Collections.EMPTY_LIST,localidadDAO.findByTiendas(connection,3L));

		logger.traceExit();
	}

}
