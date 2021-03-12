package com.abastos.dao;

import static org.junit.Assert.assertNotNull;


import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.dao.jdbc.DireccionDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Direccion;
import com.abastos.model.DireccionDto;

public class DireccionDAOTest {
	private static Logger logger = LogManager.getLogger(DireccionDAOTest.class);
	private DireccionDAO direccionDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		direccionDAO = new DireccionDAOImpl();
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

		assertNotNull(direccionDAO.findById(connection,1L));

		logger.traceExit();
	}

	@Test
	public void testCreateDireccion()throws Exception {
		logger.traceEntry();
		Direccion direccion = new Direccion();
		direccion.setCalle("prueba001");
		direccion.setCodigoPostal("003");
		direccion.setIdLocalidad(1L);
		direccion.setPiso("p0901");
		direccion.setTipoDireccion(1);
		direccion.setNumero(2);

		assertNotNull(direccionDAO.createDireccion(connection, direccion).getId());

		logger.traceExit();
	}

	@Test
	public void testCreateDireccionDireccionDto() throws Exception{
		logger.traceEntry();
		DireccionDto diDto = new DireccionDto();
		diDto.setCalle("prueba");
		diDto.setCodigoPostal("prueba02");
		diDto.setComunidadAutonoma("prueba0002");
		diDto.setIdLocalidad(1L);
		diDto.setIdLocalidad(1L);
		diDto.setNumero(12);
		diDto.setIdTipoDireccion(1);
		diDto.setPiso("12D");
		assertNotNull(direccionDAO.createDireccion(connection,diDto).getId());

		logger.traceExit();
	}

	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();
		Direccion direccion = new Direccion();

		direccion = direccionDAO.findById(connection,1L);
		direccion.setCalle("prueba00001update");
		direccion.setCodigoPostal("prueba0001");
		direccion.setIdLocalidad(1L);
		direccion.setPiso("1D");
		direccion.setTipoDireccion(2);
		assertNotNull(direccionDAO.update(connection,direccion));

		logger.traceExit();
	}


}
