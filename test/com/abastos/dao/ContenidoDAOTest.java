package com.abastos.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.dao.jdbc.ContenidoDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Contenido;


public class ContenidoDAOTest {
	private static Logger logger = LogManager.getLogger(ContenidoDAOTest.class);
	private ContenidoDAO contenidoDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		contenidoDAO = new ContenidoDAOImpl();
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

		assertNotNull(contenidoDAO.findById(connection,1L));

		logger.traceExit();
	}

	@Test
	public void testFindByIdTipo() throws Exception{
		logger.traceEntry();

		assertNotNull(contenidoDAO.findByIdTipo(connection, 1L));


		logger.traceExit();
	}

	@Test
	public void testCreate() throws Exception {
		logger.traceEntry();
		Contenido contenido = new Contenido();
		contenido.setNombre("prueba0002");
		contenido.setTipoContenido(1);

		assertNotNull(contenidoDAO.create(connection, contenido).getId());

		logger.traceExit();
	}

	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();
		Contenido contenido = new Contenido();
		contenido.setNombre("prueba0002");
		contenido.setId(1L);
		contenido.setTipoContenido(1);

		assertNotNull(contenidoDAO.update(connection, contenido));

		logger.traceExit();
	}

	@Test
	public void testHardDelete() throws Exception {
		Contenido contenido = new Contenido();
		logger.traceEntry();
		contenido.setNombre("prueba0002");
		contenido.setId(1L);
		contenido.setTipoContenido(1);

		assertTrue(contenidoDAO.hardDelete(connection, 25L));

		logger.traceExit();
	}

}
