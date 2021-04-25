package com.abastos.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Collections;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.service.impl.LocalidadServiceImpl;

public class LocalidadServiceTest {
	private static Logger logger = LogManager.getLogger(LocalidadServiceTest.class);
	private LocalidadService localidadService;

	@Before
	public void setUp() throws Exception {
		localidadService  = new LocalidadServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindByIdProvincia() throws Exception{
		logger.traceEntry();

			assertNotEquals(Collections.EMPTY_LIST,localidadService.findByIdProvincia(1L));
	
		logger.traceExit();
		
	}

	@Test
	public void testFindByIdLocalidad() throws Exception{
		logger.traceEntry();
	
		
			assertNotNull(localidadService.findByIdLocalidad(1L));
	
		logger.traceExit();
	}

	@Test
	public void testFindByTiendas() throws Exception {
		logger.traceEntry();
	
			assertNotEquals(Collections.EMPTY_LIST,localidadService.findByTiendas(3L));
	
		logger.traceExit();
	}

}
