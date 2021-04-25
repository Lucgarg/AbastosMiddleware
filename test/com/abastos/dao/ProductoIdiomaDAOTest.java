package com.abastos.dao;

import static org.junit.Assert.assertNotEquals;


import java.sql.Connection;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.abastos.dao.jdbc.ProductoIdiomaDAOImpl;
import com.abastos.dao.util.ConnectionManager;

public class ProductoIdiomaDAOTest {
	private static Logger logger = LogManager.getLogger(ProductoIdiomaDAOTest.class);
	private ProductoIdiomaDAO productoIdiomaDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		productoIdiomaDAO = new ProductoIdiomaDAOImpl();
		connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		ConnectionManager.closeConnection(connection, false);
	}

	@Test
	public void testFindBy() throws Exception{
		logger.traceEntry();
		assertNotEquals(Collections.EMPTY_LIST,productoIdiomaDAO.findBy(connection, 1L));
		logger.traceExit();
	}

}
