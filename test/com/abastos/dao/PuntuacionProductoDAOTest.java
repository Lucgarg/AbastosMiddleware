package com.abastos.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.dao.jdbc.PuntuacionProductoDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.service.PuntuacionProductoServiceTest;

public class PuntuacionProductoDAOTest {
	private static Logger logger = LogManager.getLogger(PuntuacionProductoServiceTest.class);
	private PuntuacionProductoDAO puntuacionDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		puntuacionDAO = new PuntuacionProductoDAOImpl();
		connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		ConnectionManager.closeConnection(connection, false);
	}

	@Test
	public void testFindByIdProducto()throws Exception {
		logger.traceEntry();
		assertNotNull(puntuacionDAO.findByIdProducto(connection, 2L));
		logger.traceExit();
		
	}

	@Test
	public void testFindByIdParticular()throws Exception {
		logger.traceEntry();
		assertNotNull(puntuacionDAO.findByIdParticular(connection,2L));
		logger.traceExit();
	}

	@Test
	public void testFindMedia() throws Exception{
		logger.traceEntry();

		assertNotNull(puntuacionDAO.findMedia(connection,1L));

		logger.traceExit();


	}

	@Test
	public void testCreate() throws Exception{
		logger.traceEntry();

		puntuacionDAO.create(connection,1L, 27L, 3);

		logger.traceExit();

	}

	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();

		puntuacionDAO.update(connection,1L, 2L, 4);

		logger.traceExit();
	}

	@Test
	public void testDelete() throws Exception{
		logger.traceEntry();

		assertTrue(puntuacionDAO.delete(connection,1L, 22L));

		logger.traceExit();
	}

}
