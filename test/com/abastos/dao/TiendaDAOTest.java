package com.abastos.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.abastos.dao.jdbc.TiendaDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.DireccionDto;
import com.abastos.model.Producto;
import com.abastos.model.Tienda;
import com.abastos.service.TiendaCriteria;

public class TiendaDAOTest {
	private static Logger logger = LogManager.getLogger(TiendaDAOTest.class);
	private TiendaDAO tiendaDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		tiendaDAO = new TiendaDAOImpl();
		connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		ConnectionManager.closeConnection(connection, false);
	}
	@Ignore
	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();

		assertNotNull(tiendaDAO.findById(connection,1L));

		logger.traceExit();

	}
	@Ignore
	@Test
	public void testFindByIdEmpresa() throws Exception{
		logger.traceEntry();

		assertNotEquals(Collections.EMPTY_LIST,tiendaDAO
				.findByIdEmpresa(connection,1L));

		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByCriteria() throws Exception{
		logger.traceEntry();
		TiendaCriteria tiendaCrit = new TiendaCriteria();

		tiendaCrit.setCategoria(8);
		tiendaCrit.setEnvioDomicilio(true);
		tiendaCrit.setIdLocalidad(1L);

		int index = 1;

		int total = Integer.MAX_VALUE;

		Results<Tienda>  listTienda = null;
		while(index < total) {
			listTienda = tiendaDAO.findByCriteria(connection, tiendaCrit, index, 10);
			for(Tienda p : listTienda.getPage()) {
				logger.info(p.getId());
			}
			total = listTienda.getTotal();		
			index += 10;

		}
		assertEquals(Collections.EMPTY_LIST,listTienda);
		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByCriteriaNombreLocalidad() throws Exception{
		logger.traceEntry();
		TiendaCriteria tiendaCrit = new TiendaCriteria();

		tiendaCrit.setNombre("Ali");
		tiendaCrit.setIdLocalidad(1L);

		int index = 1;

		int total = Integer.MAX_VALUE;

		Results<Tienda>  listTienda = null;
		while(index < total) {
			listTienda = tiendaDAO.findByCriteria(connection, tiendaCrit, index, 10);
			for(Tienda p : listTienda.getPage()) {
				logger.info(p.getId());
			}
			total = listTienda.getTotal();		
			index += 10;

		}
		assertEquals(Collections.EMPTY_LIST,listTienda);
		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByCriteriaCategoriaEnvio() throws Exception{
		logger.traceEntry();
		TiendaCriteria tiendaCrit = new TiendaCriteria();

		tiendaCrit.setCategoria(8);
		tiendaCrit.setEnvioDomicilio(false);

		int index = 1;

		int total = Integer.MAX_VALUE;

		Results<Tienda>  listTienda = null;
		while(index < total) {
			listTienda = tiendaDAO.findByCriteria(connection, tiendaCrit, index, 10);
			for(Tienda p : listTienda.getPage()) {
				logger.info(p.getId());
			}
			total = listTienda.getTotal();		
			index += 10;

		}
		assertEquals(Collections.EMPTY_LIST,listTienda);
		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByCriteriaNombre() throws Exception{
		logger.traceEntry();
		TiendaCriteria tiendaCrit = new TiendaCriteria();
		tiendaCrit.setNombre("Ali");

		int index = 1;

		int total = Integer.MAX_VALUE;

		Results<Tienda>  listTienda = null;
		while(index < total) {
			listTienda = tiendaDAO.findByCriteria(connection, tiendaCrit, index, 10);
			for(Tienda p : listTienda.getPage()) {
				logger.info(p.getId());
			}
			total = listTienda.getTotal();		
			index += 10;

		}
		assertEquals(Collections.EMPTY_LIST,listTienda);
		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByCriteriaLocalidad() throws Exception {
		logger.traceEntry();
		TiendaCriteria tiendaCrit = new TiendaCriteria();
		tiendaCrit.setIdLocalidad(1L);


		int index = 1;

		int total = Integer.MAX_VALUE;

		Results<Tienda>  listTienda = null;
		while(index < total) {
			listTienda = tiendaDAO.findByCriteria(connection, tiendaCrit, index, 10);
			for(Tienda p : listTienda.getPage()) {
				logger.info(p.getId());
			}
			total = listTienda.getTotal();		
			index += 10;

		}
		assertEquals(Collections.EMPTY_LIST,listTienda);
		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByCriteriaCategoria() throws Exception {
		logger.traceEntry();
		TiendaCriteria tiendaCrit = new TiendaCriteria();
		tiendaCrit.setCategoria(8);


		int index = 1;

		int total = Integer.MAX_VALUE;

		Results<Tienda>  listTienda = null;
		while(index < total) {
			listTienda = tiendaDAO.findByCriteria(connection, tiendaCrit, index, 10);
			for(Tienda p : listTienda.getPage()) {
				logger.info(p.getId());
			}
			total = listTienda.getTotal();		
			index += 10;

		}
		assertEquals(Collections.EMPTY_LIST,listTienda);
		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByCriteriaEnvio() throws Exception{
		logger.traceEntry();
		TiendaCriteria tiendaCrit = new TiendaCriteria();
		tiendaCrit.setEnvioDomicilio(true);


		int index = 1;

		int total = Integer.MAX_VALUE;

		Results<Tienda>  listTienda = null;
		while(index < total) {
			listTienda = tiendaDAO.findByCriteria(connection, tiendaCrit, index, 10);
			for(Tienda p : listTienda.getPage()) {
				logger.info(p.getId());
			}
			total = listTienda.getTotal();		
			index += 10;

		}
		assertEquals(Collections.EMPTY_LIST,listTienda);
		logger.traceExit();
	}

	@Test
	public void testCreate() throws Exception{
		logger.traceEntry();
		Tienda tienda = new Tienda();
		tienda.setCategoria(1);
		tienda.setEmail("lugarcia132@gmail.com");
		tienda.setEnvioDomicilio(true);
		tienda.setIdEmpresa(1L);
		tienda.setNombre("Maria del Carmen Gondar Prol");
		tienda.setNumeroTelefono("986785645");
		DireccionDto direccionDto = new DireccionDto();
		direccionDto.setCalle("prueba006");
		direccionDto.setCodigoPostal("45344");
		direccionDto.setIdLocalidad(8L);
		direccionDto.setIdTipoDireccion(1);
		direccionDto.setPiso("bajoPrueba");
		direccionDto.setNumero(12);
		tienda.setDireccionDto(direccionDto);


		tienda = tiendaDAO.create(connection,tienda);


		logger.traceExit();
	}
	@Ignore
	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();
		Tienda tienda = new Tienda();

		tienda = tiendaDAO.findById(connection,2L);
		tienda.setNombre("pruebaUpdate0001");
		assertNotNull(tiendaDAO.update(connection,tienda));

		logger.traceExit();
	}
	@Ignore
	@Test
	public void testDelete() throws Exception{
		logger.traceEntry();
		
			assertTrue(tiendaDAO.softDelete(connection,1L));
		
		logger.traceExit();
	}


	

}
