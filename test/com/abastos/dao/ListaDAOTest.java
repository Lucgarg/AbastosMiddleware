package com.abastos.dao;

import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertTrue;
import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.dao.jdbc.ListaDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.LineaLista;
import com.abastos.model.Lista;


public class ListaDAOTest {
	private static Logger logger = LogManager.getLogger(ListaDAOTest.class);
	private ListaDAO listaDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		listaDAO = new ListaDAOImpl();
		connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		ConnectionManager.closeConnection(connection, false);
	}

	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();

		assertNotNull(listaDAO.findById(connection,1L));

		logger.traceExit();
	}

	@Test
	public void testFindByIdParticular()throws Exception {
		logger.traceEntry();

		assertNotNull(listaDAO.findByIdParticular(connection,1L));

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

		assertNotNull(listaDAO.create(connection,lista).getId());

		logger.traceExit();
	}

	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();
		Lista lista;

		lista = listaDAO.findById(connection,10L);	
		lista.setNombre("prueba118");
		assertNotNull(listaDAO.update(connection,lista));

		logger.traceExit();
	}

	@Test
	public void testDelete()throws Exception {
		logger.traceEntry();

		logger.traceEntry();
		assertTrue(listaDAO.delete(connection,4L));

		logger.traceExit();
	}

	@Test
	public void testDeleteByIdParticular() throws Exception{
		logger.traceEntry();

		assertTrue(listaDAO.deleteByIdParticular(connection,1L));

		logger.traceExit();
	}

}
