package com.abastos.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.service.impl.PuntuacionProductoServiceImpl;

public class PuntuacionProductoServiceTest {
	private static Logger logger = LogManager.getLogger(PuntuacionProductoServiceTest.class);
	private PuntuacionProductoService puntProducto;
	@Before
	public void setUp() throws Exception {
		puntProducto = new PuntuacionProductoServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();

		logger.trace("Por producto");
		assertNotNull(puntProducto.findByIdParticular(1L));


		logger.trace("Por Particular");
		assertNotNull(puntProducto.findByIdProducto(1L));

		logger.traceExit();

	}

	@Test
	public void testFindMedia() throws Exception{
		logger.traceEntry();

		assertNotNull(puntProducto.findMedia(1L));

		logger.traceExit();


	}

	@Test
	public void testCreate() throws Exception{
		logger.traceEntry();

		puntProducto.create(1L, 27L, 3);

		logger.traceExit();

	}

	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();

		puntProducto.update(1L, 2L, 4);

		logger.traceExit();
	}

	@Test
	public void testDelete() throws Exception{
		logger.traceEntry();

		assertTrue(puntProducto.delete(1L, 22L));

		logger.traceExit();
	}

}
