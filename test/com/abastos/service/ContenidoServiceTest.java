package com.abastos.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.model.Contenido;
import com.abastos.service.impl.ContenidoServiceImpl;

public class ContenidoServiceTest {
	private static Logger logger = LogManager.getLogger(ContenidoServiceTest.class);
	private ContenidoService contenidoService;

	@Before
	public void setUp() throws Exception {
		contenidoService = new ContenidoServiceImpl();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testContenidoServiceImpl() {

	}

	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();

		assertNotNull(contenidoService.findById(1L));

		logger.traceExit();

	}

	@Test
	public void testFindByIdTipo()throws Exception {
		logger.traceEntry();

		assertNotNull(contenidoService.findByIdTipo(1L));


		logger.traceExit();
	}

	@Test
	public void testCreate() throws Exception{
		logger.traceEntry();
		Contenido contenido = new Contenido();
		contenido.setNombre("prueba0002");
		contenido.setTipoContenido(1);

		assertNotNull(contenidoService.create(contenido));

		logger.traceExit();
	}

	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();
		Contenido contenido = new Contenido();
		contenido.setNombre("prueba0002");
		contenido.setId(1L);
		contenido.setTipoContenido(1);

		assertNotNull(contenidoService.update(contenido));

		logger.traceExit();
	}

	@Test
	public void testHardDelete() throws Exception{
		Contenido contenido = new Contenido();
		logger.traceEntry();
		contenido.setNombre("prueba0002");
		contenido.setId(1L);
		contenido.setTipoContenido(1);

		assertTrue(contenidoService.hardDelete(25L));

		logger.traceExit();
	}

}
