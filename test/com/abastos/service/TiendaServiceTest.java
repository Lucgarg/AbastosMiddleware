package com.abastos.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.abastos.model.Contenido;
import com.abastos.model.DireccionDto;
import com.abastos.model.Tienda;
import com.abastos.service.exceptions.MailException;
import com.abastos.service.exceptions.ServiceException;
import com.abastos.service.impl.TiendaServiceImpl;

public class TiendaServiceTest {
	private static Logger logger = LogManager.getLogger(TiendaServiceTest.class);

	private TiendaService tiendaService;

	@Before
	public void setUp() throws Exception {

		tiendaService  = new TiendaServiceImpl();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();

		assertNotNull(tiendaService.findById(1L));

		logger.traceExit();

	}
	@Ignore
	@Test
	public void testFindByIdEmpresa() throws Exception{
		logger.traceEntry();

		assertNotEquals(Collections.EMPTY_LIST,tiendaService.findByIdEmpresa(1L));

		logger.traceExit();
	}

	@Test
	public void testFindByCriteria() throws Exception{
		logger.traceEntry();
		TiendaCriteria tiendaCrit = new TiendaCriteria();

		tiendaCrit.setIdEmpresa(1L);
		logger.info(tiendaCrit.hashCode());
		
		tiendaService.findByCriteria(tiendaCrit, 1, 5);
		 tiendaCrit = new TiendaCriteria();

		tiendaCrit.setIdEmpresa(1L);
		logger.info(tiendaCrit.hashCode());
		tiendaService.findByCriteria(tiendaCrit, 1, 5);

		assertNotEquals(Collections.EMPTY_LIST,	tiendaService.findByCriteria(tiendaCrit, 1, 5));

		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByCriteriaNombreLocalidad() throws Exception{
		logger.traceEntry();
		TiendaCriteria tiendaCrit = new TiendaCriteria();

		tiendaCrit.setNombre("Ali");
		tiendaCrit.setIdLocalidad(1L);

		assertNotEquals(Collections.EMPTY_LIST,tiendaService.findByCriteria(tiendaCrit));

		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByCriteriaCategoriaEnvio() throws Exception{
		logger.traceEntry();
		TiendaCriteria tiendaCrit = new TiendaCriteria();

		tiendaCrit.setCategoria(8);
		tiendaCrit.setEnvioDomicilio(false);

		assertNotEquals(Collections.EMPTY_LIST,tiendaService.findByCriteria(tiendaCrit));

		logger.traceExit();
		
	}
	@Ignore
	@Test
	public void testFindByCriteriaNombre() throws Exception{
		logger.traceEntry();
		TiendaCriteria tiendaCrit = new TiendaCriteria();
		tiendaCrit.setNombre("Ali");

		assertNotEquals(Collections.EMPTY_LIST,tiendaService.findByCriteria(tiendaCrit));

		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByCriteriaLocalidad() throws Exception {
		logger.traceEntry();
		TiendaCriteria tiendaCrit = new TiendaCriteria();
		tiendaCrit.setIdLocalidad(1L);


		assertNotEquals(Collections.EMPTY_LIST,tiendaService.findByCriteria(tiendaCrit));

		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByCriteriaCategoria() throws Exception {
		logger.traceEntry();
		TiendaCriteria tiendaCrit = new TiendaCriteria();
		tiendaCrit.setCategoria(8);


		assertNotEquals(Collections.EMPTY_LIST,tiendaService.findByCriteria(tiendaCrit));

		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByCriteriaEnvio() throws Exception{
		logger.traceEntry();
		TiendaCriteria tiendaCrit = new TiendaCriteria();
		tiendaCrit.setEnvioDomicilio(true);


		assertNotEquals(Collections.EMPTY_LIST,tiendaService.findByCriteria(tiendaCrit));

		logger.traceExit();
	}
	@Ignore
	@Test
	public void testCreate() throws Exception{
		logger.traceEntry();
		Tienda tienda = new Tienda();
		tienda.setCategoria(1);
		tienda.setEmail("prueba12000@gmail.com");
		tienda.setEnvioDomicilio(true);
		tienda.setIdEmpresa(1L);
		tienda.setNombre("Maria del  Gondar Prol");
		tienda.setNumeroTelefono("986785645");
		DireccionDto direccionDto = new DireccionDto();
		direccionDto.setCalle("prueba006");
		direccionDto.setCodigoPostal("45344");
		direccionDto.setIdLocalidad(8L);
		direccionDto.setIdTipoDireccion(1);
		direccionDto.setPiso("bajoPrueba");
		direccionDto.setNumero(12);
		tienda.setDireccionDto(direccionDto);


		tienda = tiendaService.create(tienda);
		logger.info(tienda);

		logger.traceExit();
	}
	@Ignore
	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();
		Tienda tienda = new Tienda();

		tienda = tiendaService.findById(2L);
		tienda.setNombre("pruebaUpdate0001");
		assertNotNull(tiendaService.update(tienda));

		logger.traceExit();
	}
	@Ignore
	@Test
	public void testDelete() throws Exception{
		logger.traceEntry();
		
			assertTrue(tiendaService.delete(1L));
		
		logger.traceExit();
	}



}
