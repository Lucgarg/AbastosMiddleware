package com.abastos.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.model.ContenidoProducto;
import com.abastos.service.impl.ContenidoProductoServiceImpl;

public class ContenidoProductoServiceTest {
	private static Logger logger = LogManager.getLogger(ContenidoProductoServiceTest.class);
	private ContenidoProductoService contenidoProductoService;

	@Before
	public void setUp() throws Exception {
		contenidoProductoService = new ContenidoProductoServiceImpl();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testContenidoProductoServiceImpl() {

	}

	@Test
	public void testFindByImagenes()throws Exception {
		logger.traceEntry();

		assertNotEquals(Collections.EMPTY_LIST,contenidoProductoService.findByImagenes(10L));

		logger.traceExit();
	}

}
