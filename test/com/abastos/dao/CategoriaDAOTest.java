package com.abastos.dao;

import static org.junit.Assert.assertEquals;
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

import com.abastos.dao.jdbc.CategoriaDAOImpl;
import com.abastos.dao.util.ConnectionManager;

public class CategoriaDAOTest {
	private static Logger logger = LogManager.getLogger(CategoriaDAOTest.class);
	private CategoriaDAO categoriaDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		categoriaDAO = new CategoriaDAOImpl();
		connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		ConnectionManager.closeConnection(connection, false);
	}

	@Test
	public void testFindRoot() throws Exception{
		logger.traceEntry();

		assertNotEquals(Collections.EMPTY_LIST,categoriaDAO.findRoot(connection,"en"));


		logger.traceExit();
	}

	@Test
	public void testFindByIdPadre() throws Exception{
		logger.traceEntry();

		assertNotEquals(Collections.EMPTY_LIST,categoriaDAO.findByIdPadre(connection, 1, "en"));

		logger.traceExit();
	}

	@Test
	public void testFindById() throws Exception {
		logger.traceEntry();

		assertNotNull(categoriaDAO.findById(connection, 1, "es"));

		logger.traceExit();
	}

	@Test
	public void testFindByCategoriaHoja() throws Exception{
		logger.traceEntry();

		assertNotNull(categoriaDAO.findByCategoriaHoja(connection, 1, "es"));

		logger.traceExit();
	}

	@Test
	public void testFindByTienda() {
	
	}



}
