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

import com.abastos.dao.jdbc.PaisDAOImpl;
import com.abastos.dao.util.ConnectionManager;

public class PaisDAOTest {
	private static Logger logger = LogManager.getLogger(PaisDAOTest.class);
	private PaisDAO paisDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		paisDAO = new PaisDAOImpl();
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

		assertNotNull(paisDAO.findById(connection,1));

		logger.traceExit();
	}

	@Test
	public void testFindByAll() throws Exception{
		logger.traceEntry();

		assertNotEquals(Collections.EMPTY_LIST,paisDAO.findByAll(connection));

		logger.traceExit();
	}


}
