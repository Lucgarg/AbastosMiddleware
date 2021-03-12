package com.abastos.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.abastos.model.LineaLista;
import com.abastos.model.Lista;
import com.abastos.service.impl.ListaServiceImpl;

public class ListaServiceTest {
	private static Logger logger = LogManager.getLogger(ListaServiceTest.class);
	private ListaService listaService;

	@Before
	public void setUp() throws Exception {
		listaService = new ListaServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();

		assertNotNull(listaService.findById(1L));

		logger.traceExit();
	}

	@Test
	public void testFindByIdParticular() throws Exception{
		logger.traceEntry();

		assertNotNull(listaService.findByIdParticular(1L));

		logger.traceExit();
	}

	@Test
	public void testCreate() throws Exception{
		logger.traceEntry();
		Lista lista = new Lista();

		LineaLista linList = new LineaLista();
		LineaLista linList1 = new LineaLista();
		linList.setIdProducto(1L);
		linList.setNombreProducto("prueba00045");
		linList.setPrecio(12d);
		lista.add(linList);
		linList1.setIdProducto(7L);
		linList1.setNombreProducto("prueba004");
		linList1.setPrecio(12d);
		lista.add(linList1);
		lista.setIdParticular(10L);
		lista.setNombre("prueba0005");

		assertNotNull(listaService.create(lista));

		logger.traceExit();
	}

	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();
		Lista lista;

		lista = listaService.findById(10L);	
		lista.setNombre("prueba118");
		assertNotNull(listaService.update(lista));

		logger.traceExit();

	}

	@Test
	public void testDelete() throws Exception{
		logger.traceEntry();

		logger.traceEntry();
		assertTrue(listaService.delete(4L));

		logger.traceExit();

	}
	@Test
	public void testDeleteByIdParticular() throws Exception{
		logger.traceEntry();

		assertTrue(listaService.deleteByIdParticular(1L));

		logger.traceExit();
	}

}
