package com.abastos.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.Collections;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.abastos.dao.jdbc.OfertaDAOImpl;
import com.abastos.dao.jdbc.ProductoDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Contenido;
import com.abastos.model.ContenidoProducto;
import com.abastos.model.Oferta;
import com.abastos.model.Producto;
import com.abastos.service.ProductoCriteria;

public class ProductoDAOTest {
	private static Logger logger = LogManager.getLogger(ProductoDAOTest.class);
	private ProductoDAO productoDAO;
	private OfertaDAO ofertaDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		ofertaDAO = new OfertaDAOImpl();
		productoDAO = new ProductoDAOImpl();
		connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		ConnectionManager.closeConnection(connection, false);
	}

	@Test
	public void testFindBy() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();


		productoCriteria.setIdTienda(1L);



		int index = 1;

		int total = Integer.MAX_VALUE;

		Results<Producto>  listProducts = null;
		while(index < total) {
			listProducts = productoDAO.findBy(connection, productoCriteria, "es", index, 10);
			for(Producto p : listProducts.getPage()) {
				logger.info(p.getId());
			}
			total = listProducts.getTotal();		
			index += 10;

		}
		assertEquals(Collections.EMPTY_LIST,listProducts);
		logger.traceExit();
	}
	@Ignore
	@Test
	public void testfindByProducTOfert() throws Exception{
		logger.traceEntry();
		
		assertEquals(Collections.EMPTY_LIST,  productoDAO.findByProductOfert(connection, "es"));
		logger.traceExit();
	}
	@Ignore
	public void testFindByCategoriaPrecioDesde() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();

		productoCriteria.setIdCategoria(46);
		productoCriteria.setIdTienda(1L);
		productoCriteria.setPredioDesde(10d);


		int index = 1;

		int total = Integer.MAX_VALUE;

		Results<Producto>  listProducts = null;
		while(index < total) {
			listProducts = productoDAO.findBy(connection, productoCriteria, "es", index, 10);
			for(Producto p : listProducts.getPage()) {
				logger.info(p.getId());
			}
			total = listProducts.getTotal();		
			index += 10;

		}
		assertEquals(Collections.EMPTY_LIST,listProducts);
		logger.traceExit();

	}
	@Ignore
	@Test
	public void testFindByOfertaPrecioHasta() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();

		productoCriteria.setIdTienda(1L);
		productoCriteria.setOferta(false);
		productoCriteria.setPrecioHasta(10d);


		int index = 1;

		int total = Integer.MAX_VALUE;

		Results<Producto>  listProducts = null;
		while(index < total) {
			listProducts = productoDAO.findBy(connection, productoCriteria, "es", index, 10);
			for(Producto p : listProducts.getPage()) {
				logger.info(p.getId());
			}
			total = listProducts.getTotal();		
			index += 10;

		}
		assertEquals(Collections.EMPTY_LIST,listProducts);
		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByOfertaOrigen() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();

		productoCriteria.setIdTienda(1L);
		productoCriteria.setOferta(false);
		productoCriteria.setIdOrigen('N');


		int index = 1;

		int total = Integer.MAX_VALUE;

		Results<Producto>  listProducts = null;
		while(index < total) {
			listProducts = productoDAO.findBy(connection, productoCriteria, "es", index, 10);
			for(Producto p : listProducts.getPage()) {
				logger.info(p.getId());
			}
			total = listProducts.getTotal();		
			index += 10;

		}
		assertEquals(Collections.EMPTY_LIST,listProducts);
		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByCategoria() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();
		productoCriteria.setIdCategoria(46);
		productoCriteria.setIdTienda(1L);


		int index = 1;

		int total = Integer.MAX_VALUE;

		Results<Producto>  listProducts = null;
		while(index < total) {
			listProducts = productoDAO.findBy(connection, productoCriteria, "es", index, 10);
			for(Producto p : listProducts.getPage()) {
				logger.info(p.getId());
			}
			total = listProducts.getTotal();		
			index += 10;

		}
		assertEquals(Collections.EMPTY_LIST,listProducts);
		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByOrigen() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();
		productoCriteria.setIdOrigen('N');
		productoCriteria.setIdTienda(1L);
		productoCriteria.setOferta(false);
		
		int index = 1;
		
		int total = Integer.MAX_VALUE;
	
		Results<Producto>  listProducts = null;
		while(index < total) {
			 listProducts = productoDAO.findBy(connection, productoCriteria, "es", index, 10);
			 for(Producto p : listProducts.getPage()) {
				 logger.info(p.getId());
			 }
			total = listProducts.getTotal();		
			index += 10;

		}
		assertEquals(Collections.EMPTY_LIST,listProducts);
		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByTienda() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();
		productoCriteria.setIdTienda(1L);
		productoCriteria.setOferta(false);

		
		int index = 1;
		
		int total = Integer.MAX_VALUE;
	
		Results<Producto>  listProducts = null;
		while(index < total) {
			 listProducts = productoDAO.findBy(connection, productoCriteria, "es", index, 10);
			 for(Producto p : listProducts.getPage()) {
				 logger.info(p.getId());
			 }
			total = listProducts.getTotal();		
			index += 10;

		}
		assertEquals(Collections.EMPTY_LIST,listProducts);
		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByOferta() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();
		productoCriteria.setIdTienda(1L);
		productoCriteria.setOferta(false);

		
		int index = 1;
		
		int total = Integer.MAX_VALUE;
	
		Results<Producto>  listProducts = null;
		while(index < total) {
			 listProducts = productoDAO.findBy(connection, productoCriteria, "es", index, 10);
			 for(Producto p : listProducts.getPage()) {
				 logger.info(p.getId());
			 }
			total = listProducts.getTotal();		
			index += 10;

		}
		assertEquals(Collections.EMPTY_LIST,listProducts);
		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByPrecioHasta() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();
		productoCriteria.setIdTienda(1L);
		productoCriteria.setPrecioHasta(50d);
		productoCriteria.setOferta(false);

		
		int index = 1;
		
		int total = Integer.MAX_VALUE;
	
		Results<Producto>  listProducts = null;
		while(index < total) {
			 listProducts = productoDAO.findBy(connection, productoCriteria, "es", index, 10);
			 for(Producto p : listProducts.getPage()) {
				 logger.info(p.getId());
			 }
			total = listProducts.getTotal();		
			index += 10;

		}
		assertEquals(Collections.EMPTY_LIST,listProducts);
		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindByPrecioDesde() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();
		productoCriteria.setIdTienda(1L);
		productoCriteria.setPredioDesde(10d);
		productoCriteria.setOferta(false);
		
		int index = 1;
		
		int total = Integer.MAX_VALUE;
	
		Results<Producto>  listProducts = null;
		while(index < total) {
			 listProducts = productoDAO.findBy(connection, productoCriteria, "es", index, 10);
			 for(Producto p : listProducts.getPage()) {
				 logger.info(p.getId());
			 }
			total = listProducts.getTotal();		
			index += 10;

		}
		assertEquals(Collections.EMPTY_LIST,listProducts);
		logger.traceExit();
	}
	@Ignore
	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();

		assertNotNull(productoDAO.findById(connection, 6L, "es"));

		logger.traceExit();
	}
	@Ignore
	@Test
	public void testCreate() throws Exception{
		logger.traceEntry();
		Producto producto = new Producto();
		producto.setCaracteristicas("pruebas0001");
		Contenido contenido = new Contenido();
		contenido.setNombre("prueba0002");
		contenido.setTipoContenido(1);
		Contenido contenido1 = new Contenido();
		contenido1.setNombre("prueba");
		contenido1.setTipoContenido(3);
		ContenidoProducto contP = new ContenidoProducto();
		contP.setIdTipoFoto('1');
		contP.setContenido(contenido);
		contP.setContenido(contenido1);
		ContenidoProducto contP1 = new ContenidoProducto();
		contP1.setIdTipoFoto('1');
		contP1.setContenido(contenido);
		contP1.setContenido(contenido1);
		producto.add(contP1);
		producto.add(contP);
		producto.setIdCategoria(1);
		producto.setIdTienda(1L);
		producto.setPrecio(12d);
		producto.setStock(12);
		producto.setTipoOrigen('N');
		producto.setNombre("prueba1250");
		Oferta oferta;
		oferta = ofertaDAO.findById(connection, 2L);

		oferta.setDescuentoFijo(1d);
		producto.setOferta(oferta);
		producto.setPrecioFinal(12.0);
		assertNotNull(productoDAO.create(connection, producto));
		logger.trace("test correcto");

		logger.traceExit();


	}
	@Ignore
	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();
		Producto producto = new Producto();

		Oferta oferta;


		producto = productoDAO.findById(connection, 2L,  "en");

		producto.setNombre("prueba001");
		producto.setPrecio(12d);
		producto.getContenidoProducto().get(0).getContenido().setNombre("imagenPrueba");
		producto.getProductoIdioma().get(0).setCaractProduct("prueba0001");
		assertNotNull(productoDAO.update(connection, producto, "es"));

		logger.traceExit();
	}
	@Ignore
	@Test
	public void testDelete() throws Exception{
		logger.traceEntry();

		assertTrue(productoDAO.softDelete(connection, 1L));

		logger.traceExit();
	}
	@Ignore
	@Test
	public void testUpdateStock() throws Exception {
		logger.traceEntry();

		productoDAO.updateStock(connection, 100L, 12);

		logger.traceExit();
	}

}
