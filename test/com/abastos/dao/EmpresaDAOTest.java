package com.abastos.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.dao.jdbc.EmpresaDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.DireccionDto;
import com.abastos.model.Empresa;

public class EmpresaDAOTest {
	private static Logger logger = LogManager.getLogger(EmpresaDAOTest.class);
	private EmpresaDAO empresaDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		empresaDAO = new EmpresaDAOImpl();
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

		assertNotNull(empresaDAO.findById(connection, 1L));

		logger.traceExit();
	}

	@Test
	public void testFindByEmail() throws Exception {
		logger.traceEntry();
		assertNotNull(empresaDAO.findByEmail(connection, "prueba2@gmail.com"));
		logger.traceExit();
	}

	@Test
	public void testFindByAlias() throws Exception{
		logger.traceEntry();
		assertNotNull(empresaDAO.findByAlias(connection, "Sara"));
		logger.traceExit();
	}

	@Test
	public void testCreate() throws Exception{
		logger.traceEntry();
		Empresa empresa = new Empresa();
		DireccionDto diDto = new DireccionDto();
		diDto.setCalle("prueba");
		diDto.setCodigoPostal("prueba02");
		diDto.setComunidadAutonoma("prueba0002");
		diDto.setIdLocalidad(1L);
		diDto.setIdLocalidad(1L);
		diDto.setNumero(12);
		diDto.setPiso("3d");
		diDto.setIdTipoDireccion(1);
		empresa.setAlias("sar");
		empresa.setApellidos("prueba001");
		empresa.setCif("prueba 002");
		empresa.setContrasena("prueba001");
		empresa.setCorreoElectronico("lugarcia132@gmail.com");
		empresa.setRazonSocial("prueba00045");
		empresa.setDireccion(diDto);
		empresa.setNombre("raquel");

		assertNotNull(empresaDAO.create(connection, empresa).getId());

		logger.traceExit();
	}

	@Test
	public void testUpdate() throws Exception {
		logger.traceEntry();
		Empresa empresa;

		empresa = empresaDAO.findByAlias(connection, "Sara");
		empresa.setAlias("pruebas");
		empresa.getDireccion().setCalle("prueba");

		assertNotNull(empresaDAO.update(connection, empresa));

		logger.traceExit();
	}

	@Test
	public void testUpdateAlta() throws Exception{
		logger.traceEntry();

		empresaDAO.updateAlta(connection, 1L);

		logger.traceExit();
	}

	@Test
	public void testSoftDelete() throws Exception {
		logger.traceEntry();

		assertTrue(empresaDAO.softDelete(connection, 1L));

		logger.traceExit();
	}

}
