package com.abastos.service;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.abastos.model.Contenido;
import com.abastos.model.ContenidoProducto;
import com.abastos.model.Oferta;
import com.abastos.model.Producto;
import com.abastos.service.impl.OfertaServiceImpl;
import com.abastos.service.impl.ProductoServiceImpl;

public class ProductoServiceTest {
	private static Logger logger = LogManager.getLogger(ProductoServiceTest.class);
	private ProductoService productoService;
	private OfertaService ofertaSer;
	@Before
	public void setUp() throws Exception {
		productoService = new ProductoServiceImpl();
		ofertaSer = new OfertaServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindBy() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();

		productoCriteria.setIdOrigen('I');
		productoCriteria.setIdTienda(1L);
		productoCriteria.setOferta(true);
		
	


		assertNotEquals(Collections.EMPTY_LIST,productoService.findBy(productoCriteria, "es"));

		logger.traceExit();
	}
	@Test
	public void testFindByCategoriaPrecioDesde() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();

		productoCriteria.setIdCategoria(46);
		productoCriteria.setIdTienda(1L);
		productoCriteria.setPredioDesde(10d);


		assertNotEquals(Collections.EMPTY_LIST,productoService.findBy(productoCriteria, "es"));

		logger.traceExit();
	}
	@Test
	public void testFindByOfertaPrecioHasta() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();

		productoCriteria.setIdTienda(1L);
		productoCriteria.setOferta(false);
		productoCriteria.setPrecioHasta(10d);

		
		assertNotEquals(Collections.EMPTY_LIST,productoService.findBy(productoCriteria, "es"));

		logger.traceExit();
	}
	@Test
	public void testFindByOfertaOrigen() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();

		productoCriteria.setIdTienda(1L);
		productoCriteria.setOferta(false);
		productoCriteria.setIdOrigen('N');
		
		assertNotEquals(Collections.EMPTY_LIST,productoService.findBy(productoCriteria, "es"));

		logger.traceExit();
	}
	@Test
	public void testFindByCategoria() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();
		productoCriteria.setIdCategoria(46);
		productoCriteria.setIdTienda(1L);
	
		assertNotEquals(Collections.EMPTY_LIST,productoService.findBy(productoCriteria, "es"));

		logger.traceExit();
	}

	@Test
	public void testFindByOrigen() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();
		productoCriteria.setIdOrigen('N');
		productoCriteria.setIdTienda(1L);
		productoCriteria.setOferta(false);
		assertNotEquals(Collections.EMPTY_LIST,productoService.findBy(productoCriteria, "es"));

		logger.traceExit();
	}

	@Test
	public void testFindByTienda() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();
		productoCriteria.setIdTienda(1L);
		productoCriteria.setOferta(false);

		assertNotEquals(Collections.EMPTY_LIST,productoService.findBy(productoCriteria, "es"));

		logger.traceExit();
	}

	@Test
	public void testFindByOferta() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();
		productoCriteria.setIdTienda(1L);
		productoCriteria.setOferta(false);

		assertNotEquals(Collections.EMPTY_LIST,productoService.findBy(productoCriteria, "es"));

		logger.traceExit();
	}

	@Test
	public void testFindByPrecioHasta() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();
		productoCriteria.setIdTienda(1L);
		productoCriteria.setPrecioHasta(50d);
		productoCriteria.setOferta(false);

		assertNotEquals(Collections.EMPTY_LIST,productoService.findBy(productoCriteria, "es"));

		logger.traceExit();
	}

	@Test
	public void testFindByPrecioDesde() throws Exception{
		logger.traceEntry();
		ProductoCriteria productoCriteria = new ProductoCriteria();
		productoCriteria.setIdTienda(1L);
		productoCriteria.setPredioDesde(10d);
		productoCriteria.setOferta(false);
		assertNotEquals(Collections.EMPTY_LIST,productoService.findBy(productoCriteria, "es"));

		logger.traceExit();
	}

	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();

		assertNotNull(productoService.findById(6L, "es"));

		logger.traceExit();
	}

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
		oferta = ofertaSer.findById(2L);

		oferta.setDescuentoFijo(1d);
		producto.setOferta(oferta);
		assertNotNull(productoService.create(producto));
		logger.trace("test correcto");

		logger.traceExit();


	}

	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();
		Producto producto = new Producto();

		Oferta oferta;


		producto = productoService.findById(2L,  "en");

		producto.setNombre("prueba001");
		producto.setPrecio(12d);
		producto.getContenidoProducto().get(0).getContenido().setNombre("imagenPrueba");
		producto.getProductoIdioma().get(0).setCaractProduct("prueba0001");
		assertNotNull(productoService.update(producto, "es"));

		logger.traceExit();
	}
	@Ignore
	@Test
	public void testDelete() throws Exception{
		logger.traceEntry();

		assertTrue(productoService.delete(1L));

		logger.traceExit();
	}

	@Test
	public void testUpdateStock() throws Exception {
		logger.traceEntry();

		productoService.updateStock(12, 100L);

		logger.traceExit();
	}

}
