package com.abastos.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Collections;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.service.impl.ComunidadAutonomaServiceImpl;

public class ComunidadAutonomaServiceTest {
	private static Logger logger = LogManager.getLogger(ComunidadAutonomaServiceTest.class);
	private ComunidadAutonomaService comunidadService;

	@Before
	public void setUp() throws Exception {
		comunidadService = new ComunidadAutonomaServiceImpl();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testComunidadAutonomaServiceImpl() {

	}

	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();

		assertNotNull(comunidadService.findById(1L));




		logger.traceExit();
	}

	@Test
	public void testFindByIdPais() throws Exception{
		logger.traceEntry();

		assertNotEquals(Collections.EMPTY_LIST,comunidadService.findByIdPais(1L));

		logger.traceExit();
	}

	@Test
	public void testFindByTienda() throws Exception{
		logger.traceEntry();


		assertNotEquals(Collections.EMPTY_LIST,comunidadService.findByTienda(1));

		logger.traceExit();
	}

}
