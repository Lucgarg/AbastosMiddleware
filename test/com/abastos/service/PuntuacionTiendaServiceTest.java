package com.abastos.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.abastos.model.PuntuacionMediaTienda;
import com.abastos.model.PuntuacionTienda;
import com.abastos.service.impl.PuntuacionTiendaServiceImpl;

public class PuntuacionTiendaServiceTest {
	private static Logger logger = LogManager.getLogger(PuntuacionTiendaServiceTest.class);
	private PuntuacionTiendaService puntuacion;
	@Before
	public void setUp() throws Exception {
		puntuacion = new PuntuacionTiendaServiceImpl();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindByIdTienda() throws Exception {
		logger.traceEntry();

		logger.trace("Por idParticular...");

		assertNotEquals(Collections.EMPTY_LIST,puntuacion.findByIdTienda(1L, null));


		logger.trace("Por idTienda...");

		assertNotEquals(Collections.EMPTY_LIST,puntuacion.findByIdTienda(null, 1L));


		logger.trace("Por IdTienda y IdParticular...");

		assertNotEquals(Collections.EMPTY_LIST,puntuacion.findByIdTienda(1L, 1L));

		logger.traceExit();

	}

	@Test
	public void testFindMedia() throws Exception{
		logger.traceEntry();

		assertNotNull(puntuacion.findMedia(1L));


	}

	@Test
	public void testCreate() throws Exception{
		logger.traceEntry();
		PuntuacionTienda punt = new PuntuacionTienda();
		punt.setIdPerfilParticular(1L);
		punt.setIdTienda(27L);
		punt.setValoracionAtncCliente(1);
		punt.setValoracionPrecio(4);
		punt.setValoracionServDomicilio(3);

		puntuacion.create(punt);

		logger.traceExit();
	}

	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();
		PuntuacionTienda punt = new PuntuacionTienda();
		punt.setIdPerfilParticular(1L);
		punt.setIdTienda(2L);
		punt.setValoracionAtncCliente(1);
		punt.setValoracionPrecio(4);
		punt.setValoracionServDomicilio(3);


		assertTrue(puntuacion.update(punt));

		logger.traceExit();
	}

	@Test
	public void testDelete()throws Exception {
		logger.traceEntry();

		assertTrue(puntuacion.delete(1L, 1L));

		logger.traceExit();
	}

}
