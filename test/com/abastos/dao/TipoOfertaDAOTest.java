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

import com.abastos.dao.jdbc.ContenidoDAOImpl;
import com.abastos.dao.jdbc.TipoOfertaDAOImpl;
import com.abastos.dao.util.ConnectionManager;

public class TipoOfertaDAOTest {
	private static Logger logger = LogManager.getLogger(TipoOfertaDAOTest.class);
	private TipoOfertaDAO tipOfertaDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		tipOfertaDAO = new TipoOfertaDAOImpl();
		connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		ConnectionManager.closeConnection(connection, false);
	}

	@Test
	public void testFindAll()throws Exception {
		logger.traceEntry();
		

		assertNotEquals(Collections.EMPTY_LIST,tipOfertaDAO.findAll(connection));
	
	logger.traceExit();
	}

}
