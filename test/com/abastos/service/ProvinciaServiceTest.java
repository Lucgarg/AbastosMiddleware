package com.abastos.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Collections;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.service.impl.ProvinciaServiceImpl;

public class ProvinciaServiceTest {
	private static Logger logger = LogManager.getLogger(ProvinciaServiceTest.class);
	private ProvinciaService provinciaService;
	
	@Before
	public void setUp() throws Exception {
		provinciaService = new ProvinciaServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();
	
			assertNotNull(provinciaService.findById(1L));
	
		logger.traceExit();
	}

	@Test
	public void testFindByIdComunidad() throws Exception{
		logger.traceEntry();
		
			
			assertNotEquals(Collections.EMPTY_LIST,provinciaService.findByIdComunidad(1L));
	
		logger.traceExit();
	
	}

	@Test
	public void testFindBy() throws Exception{
		logger.traceEntry();
	
			
			assertNotEquals(Collections.EMPTY_LIST,provinciaService.findBy(1L));
	
		logger.traceExit();
	}

}
