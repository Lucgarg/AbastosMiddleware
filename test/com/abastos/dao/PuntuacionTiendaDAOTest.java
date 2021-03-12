package com.abastos.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.dao.jdbc.PuntuacionTiendaDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.PuntuacionTienda;

public class PuntuacionTiendaDAOTest {
	private static Logger logger = LogManager.getLogger(PuntuacionTiendaDAOTest.class);
	private PuntuacionTiendaDAO puntuacionDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		
		puntuacionDAO = new PuntuacionTiendaDAOImpl();
		connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		ConnectionManager.closeConnection(connection, false);
	}

	@Test
	public void testFindByIdTienda()throws Exception {
		logger.traceEntry();
		assertNotEquals(Collections.EMPTY_LIST,puntuacionDAO
				.findByIdTienda(connection,1L));
		logger.traceExit();
	}

	@Test
	public void testFindByIdParticular()throws Exception {
		logger.traceEntry();
		assertNotEquals(Collections.EMPTY_LIST,puntuacionDAO
				.findByIdParticular(connection,1L));
		logger.traceExit();
	}

	@Test
	public void testFindMedia() throws Exception{
		logger.traceEntry();

		assertNotNull(puntuacionDAO.findMedia(connection,1L));


	}

	@Test
	public void testCreate() throws Exception{
		logger.traceEntry();
		PuntuacionTienda punt = new PuntuacionTienda();
		punt.setIdPerfilParticular(1L);
		punt.setIdTienda(27L);
		punt.setValoracionAtncCliente(1);
		punt.setValoracionPrecio(4);
		punt.setValoracionServDomicilio(3);

		puntuacionDAO.create(connection,punt);

		logger.traceExit();
	}

	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();
		PuntuacionTienda punt = new PuntuacionTienda();
		punt.setIdPerfilParticular(1L);
		punt.setIdTienda(2L);
		punt.setValoracionAtncCliente(1);
		punt.setValoracionPrecio(4);
		punt.setValoracionServDomicilio(3);


		assertTrue(puntuacionDAO.update(connection, punt));

		logger.traceExit();
	}

	@Test
	public void testDelete()throws Exception {
		logger.traceEntry();

		assertTrue(puntuacionDAO.delete(connection, 1L, 1L));

		logger.traceExit();
	}

}
