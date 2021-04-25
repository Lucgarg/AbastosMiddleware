package com.abastos.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.util.Collections;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.model.LineaLista;
import com.abastos.service.impl.LineaListaServiceImpl;

public class LineaListaServiceTest {
	private static Logger logger = LogManager.getLogger(LineaListaServiceTest.class);
	private LineaListaService linListService;

	@Before
	public void setUp() throws Exception {
		linListService = new LineaListaServiceImpl();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindByIdListaDeseos() throws Exception{

		logger.traceEntry();

		assertNotEquals(Collections.EMPTY_LIST,linListService.findByIdListaDeseos(4L));

		logger.traceExit();
	}

	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();

		logger.trace("Por idLista");
		assertNotEquals(Collections.EMPTY_LIST,linListService.findById(1L, null));


		logger.trace("Por idProducto");
		assertNotEquals(Collections.EMPTY_LIST,linListService.findById(null, 1L));


		logger.trace("Por idLista y idProducto");
		assertNotEquals(Collections.EMPTY_LIST,linListService.findById(1L, 1L));

		logger.traceExit();
	}

	@Test
	public void testCreate() throws Exception{
		logger.traceEntry();
		LineaLista linList = new LineaLista();

		linList.setIdProducto(21L);
		linList.setId(2L);
		linList.setNombreProducto("prueba0005");
		linList.setPrecio(22d);
		linListService.create(linList);

		logger.traceExit();
	}

	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();
		LineaLista linList = new LineaLista();

		linList = linListService.findById(1L, 11L);

		assertTrue(linListService.update(linList));

		logger.traceExit();
	}

	@Test
	public void testDelete() throws Exception{
		logger.traceEntry();

		logger.trace("Por idLista");


		assertTrue(linListService.delete(6L, null));


		logger.trace("Por idProducto");


		assertTrue(linListService.delete(null, 8L));


		logger.trace("Por idProducto y idLista");


		assertTrue(linListService.delete(7L, 44L));

		logger.traceExit();

	}

}
