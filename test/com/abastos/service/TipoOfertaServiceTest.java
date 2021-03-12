package com.abastos.service;

import static org.junit.Assert.assertNotEquals;

import java.util.Collections;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.service.impl.TipoOfertaServiceImpl;

public class TipoOfertaServiceTest {
	private static Logger logger = LogManager.getLogger(TipoOfertaServiceTest.class);
	private TipoOfertaService tipoOfertaService;



	@Before
	public void setUp() throws Exception {
		tipoOfertaService = new TipoOfertaServiceImpl();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAll() throws Exception {
		logger.traceEntry();
	

			assertNotEquals(Collections.EMPTY_LIST,tipoOfertaService.findAll());
		
		logger.traceExit();
	}

}
