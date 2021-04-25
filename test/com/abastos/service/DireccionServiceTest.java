package com.abastos.service;

import static org.junit.Assert.assertNotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.model.Direccion;
import com.abastos.model.DireccionDto;
import com.abastos.service.impl.DireccionServiceImpl;

public class DireccionServiceTest {
	private static Logger logger = LogManager.getLogger(DireccionServiceTest.class);
	private DireccionService direccionService;

	@Before
	public void setUp() throws Exception {
		direccionService = new DireccionServiceImpl();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDireccionServiceImpl() {

	}

	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();

		assertNotNull(direccionService.findById(10L));

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

		assertNotNull(direccionService.createDireccion(direccion));

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
		assertNotNull(direccionService.createDireccion(diDto));

		logger.traceExit();
	}

	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();
		Direccion direccion = new Direccion();

		direccion = direccionService.findById(10L);
		direccion.setCalle("prueba00001update");
		direccion.setCodigoPostal("prueba0001");
		direccion.setIdLocalidad(1L);
		direccion.setPiso("1D");
		direccion.setTipoDireccion(2);
		assertNotNull(direccionService.update(direccion));

		logger.traceExit();
	}



}
