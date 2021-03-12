package com.abastos.service;

import static org.junit.Assert.assertNotNull;


import static org.junit.Assert.assertTrue;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import org.junit.Test;


import com.abastos.model.DireccionDto;
import com.abastos.model.Particular;
import com.abastos.service.exceptions.IncorrectPasswordException;

import com.abastos.service.exceptions.UserNotFoundException;
import com.abastos.service.impl.ParticularServiceImpl;

public class ParticularServiceTest {
	private static Logger logger = LogManager.getLogger(ParticularServiceTest.class);
	private ParticularService particularService;

	@Before
	public void setUp() throws Exception {
		particularService = new ParticularServiceImpl();

	}
	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();

		assertNotNull(particularService.findById(6L));

		logger.traceExit();
	}
	@Test
	public void testLogin() throws Exception{
		logger.traceEntry();
		//password incorrecta
		try {
			logger.trace("Con password incorrecta...");
			particularService.login(null, "AlexPerez", "45678910");
		} catch(IncorrectPasswordException e) {
			logger.trace("test correcto...");

		}
		//usuario incorrecto
		try {
			logger.trace("Con usuario incorrecto...");
			particularService.login(null, "ls", "1234@Alp");

		} catch(UserNotFoundException e) {
			logger.trace("test correcto...");
		}
		//usuario y password correctas

		logger.trace("Con usuario y password correcta...");
		assertNotNull(particularService.login(null, "AlexPerez", "1234@Alp"));
		logger.trace("test valido...");
		logger.traceExit();
	}

	@Test
	public void testRegistrar() throws Exception{
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

		assertNotNull(particularService.registrar(particular));


		logger.traceExit();
	}

	@Test
	public void testFindPuntos() throws Exception{
		logger.traceEntry();

		assertNotNull(particularService.findPuntos(1L));

		logger.traceExit();
	}

	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();
		Particular particular;

		particular = particularService.login(null, "AlexPerez", "1234@Alp");
		particular.setAlias("pruebas00034");
		assertNotNull(particularService.update(particular));


		logger.traceExit();
	}
	@Test
	public void testUpdateAlta() throws Exception{
		logger.traceEntry();

		assertTrue(particularService.updateAlta(1L));


		logger.traceExit();
	}
	@Test
	public void testUpdatePuntos() throws Exception{
		logger.traceEntry();

		assertTrue(particularService.updatePuntos(2L, 100));

	}

	@Test
	public void testDelete() throws Exception{
		logger.traceEntry();

		assertTrue(particularService.delete(5L));

		logger.traceExit();

	}



}
