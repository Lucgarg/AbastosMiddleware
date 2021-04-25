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

import com.abastos.dao.jdbc.DireccionDtoDAOImpl;
import com.abastos.dao.util.ConnectionManager;

public class DireccionDtoDAOTest {
	private static Logger logger = LogManager.getLogger(DireccionDtoDAOTest.class);
	private DireccionDtoDAO direcDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		direcDAO = new DireccionDtoDAOImpl();
		connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		ConnectionManager.closeConnection(connection, false);
	}

	@Test
	public void testFindByIdEmp() throws Exception{
		logger.traceEntry();


		assertNotNull(direcDAO.findByIdEmp(connection, 1L));

		logger.traceExit();
	}

	@Test
	public void testFindByIdPart() throws Exception{
		logger.traceEntry();


		assertNotEquals(Collections.EMPTY_LIST, direcDAO.findByIdPart(connection, 1L));

		logger.traceExit();

	}

	@Test
	public void testFindByIdTienda()throws Exception {
		logger.traceEntry();

		assertNotNull(direcDAO.findByIdTienda(connection,1L));


		logger.traceExit();
	}

}
