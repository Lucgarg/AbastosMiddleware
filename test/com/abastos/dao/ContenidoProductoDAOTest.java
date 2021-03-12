package com.abastos.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.dao.jdbc.ContenidoProductoDAOImpl;
import com.abastos.dao.util.ConnectionManager;

public class ContenidoProductoDAOTest {
	private static Logger logger = LogManager.getLogger(ContenidoProductoDAOTest.class);
	private ContenidoProductoDAO contProdcDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		
		contProdcDAO = new ContenidoProductoDAOImpl();
		connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		ConnectionManager.closeConnection(connection, false);
	}

	

	@Test
	public void testFindByImagenes()throws Exception {
		logger.traceEntry();

		assertNotEquals(Collections.EMPTY_LIST,contProdcDAO.findByImagenes(connection, 10L));

		logger.traceExit();
	}

}
