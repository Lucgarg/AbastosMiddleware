package com.abastos.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.model.DireccionDto;
import com.abastos.service.impl.ContenidoProductoServiceImpl;
import com.abastos.service.impl.DireccionDTOServiceImpl;

public class DireccionDTOServiceTest {
	private static Logger logger = LogManager.getLogger(DireccionDTOServiceTest.class);
	private DireccionDTOService  direccion;


	@Before
	public void setUp() throws Exception {
		direccion = new DireccionDTOServiceImpl();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindByIdEmp() throws Exception{
		logger.traceEntry();


		assertNotNull(direccion.findByIdEmp(1L));

		logger.traceExit();
	}

	@Test
	public void testFindByIdPart() throws Exception{
		logger.traceEntry();


		assertNotEquals(Collections.EMPTY_LIST, direccion.findByIdPart(1L));

		logger.traceExit();

	}

	@Test
	public void testFindByIdTienda()throws Exception {
		logger.traceEntry();

		assertNotNull(direccion.findByIdTienda(1L));


		logger.traceExit();
	}

}
