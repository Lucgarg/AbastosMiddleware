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

import com.abastos.dao.jdbc.ParticularDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.DireccionDto;
import com.abastos.model.Particular;

public class ParticularDAOTest {
	private static Logger logger = LogManager.getLogger(ParticularDAOTest.class);
	private ParticularDAO particularDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		particularDAO = new ParticularDAOImpl();
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

		assertNotNull(particularDAO.findById(connection, 2L));

		logger.traceExit();
	}

	@Test
	public void testFindByEmail() throws Exception{
		logger.traceEntry();
		assertNotNull(particularDAO.findByEmail(connection, "lugarcia132@gmail.com"));
		logger.traceExit();
	}

	@Test
	public void testFindByAlias() throws Exception{
		logger.traceEntry();
		assertNotNull(particularDAO.findByAlias(connection, "AlexPerez"));
		logger.traceExit();
	}

	@Test
	public void testFindPuntos() throws Exception{
		logger.traceEntry();
		particularDAO.findPuntos(connection, 1L);
		logger.traceExit();
	}

	@Test
	public void testCreate() throws Exception {
		logger.traceEntry();
		DireccionDto diDto = new DireccionDto();
		Particular particular = new Particular();
		diDto.setCalle("prueba");
		diDto.setCodigoPostal("prueba02");
		diDto.setComunidadAutonoma("prueba0002");
		diDto.setIdLocalidad(1L);
		diDto.setIdLocalidad(1L);
		diDto.setNumero(12);
		diDto.setPiso("3d");
		diDto.setIdTipoDireccion(1);
		particular.setAlias("sar");
		particular.setApellidos("prueba001");
		particular.setContrasena("prueba001");
		particular.setEmail("sarasequeironeda93@gmail.com");
		particular.add(diDto);
		particular.setNombre("Raquel");

		assertNotNull(particularDAO.create(connection,particular));


		logger.traceExit();
	}

	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();
		Particular particular;

		particular = particularDAO.findByAlias(connection, "AlexPerez");
		particular.setAlias("pruebas00034");
		assertNotNull(particularDAO.update(connection, particular));


		logger.traceExit();
	}

	@Test
	public void testUpdatePuntos()throws Exception {
		logger.traceEntry();

		assertTrue(particularDAO.updatePuntos(connection, 2L, 100));
	}

	@Test
	public void testUpdateAlta() throws Exception{
		logger.traceEntry();

		assertTrue(particularDAO.updateAlta(connection, 1L));


		logger.traceExit();
	}

	@Test
	public void testSoftDelete() throws Exception{
		logger.traceEntry();

		assertTrue(particularDAO.softDelete(connection, 5L));

		logger.traceExit();
	}

}
