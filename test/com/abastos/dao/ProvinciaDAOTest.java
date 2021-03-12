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

import com.abastos.dao.jdbc.ContenidoDAOImpl;
import com.abastos.dao.jdbc.ProvinciaDAOImpl;
import com.abastos.dao.util.ConnectionManager;

public class ProvinciaDAOTest {
	private static Logger logger = LogManager.getLogger(ProvinciaDAOTest.class);
	private ProvinciaDAO provinciaDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		provinciaDAO = new ProvinciaDAOImpl();
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
	
			assertNotNull(provinciaDAO.findById(connection,1L));
	
		logger.traceExit();
	}

	@Test
	public void testFindByIdComunidad() throws Exception{
		logger.traceEntry();
		
			
			assertNotEquals(Collections.EMPTY_LIST,provinciaDAO.findByIdComunidad(connection,1L));
	
		logger.traceExit();
	
	}

	@Test
	public void testFindBy() throws Exception{
		logger.traceEntry();
	
			
			assertNotEquals(Collections.EMPTY_LIST,provinciaDAO.findBy(connection,1L));
	
		logger.traceExit();
	}


}
