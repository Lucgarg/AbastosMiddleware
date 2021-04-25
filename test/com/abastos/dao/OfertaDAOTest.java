package com.abastos.dao;

import static org.junit.Assert.assertNotNull;
import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import org.junit.Test;

import com.abastos.dao.jdbc.OfertaDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.dao.util.DataUtils;
import com.abastos.model.Oferta;

public class OfertaDAOTest {
	private static Logger logger = LogManager.getLogger(OfertaDAOTest.class);
	private OfertaDAO ofertaDAO;
	private Connection connection;
	@Before
	public void setUp() throws Exception {
		ofertaDAO = new OfertaDAOImpl();
		connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		ConnectionManager.closeConnection(connection, false);
	}
	
	@Test
	public void testFindById() throws Exception {
		logger.traceEntry();


		assertNotNull(ofertaDAO.findById(connection, 2L));

		logger.traceExit();
	}



	@Test
	public void testCreate() throws Exception{
		logger.traceEntry();
		Oferta oferta = new Oferta();

		oferta.setNumerador(4);
		oferta.setDescuentoFijo(12d);
		oferta.setIdTipoOferta(1);
		oferta.setNombreOferta("prueba0002");
		oferta.setNombreTipoOfer("prueba004");
		oferta.setIdEmpresa(1L);
		oferta.setFechaDesde(DataUtils.formatDate("14-04-2021 12:02:02"));
		oferta.setFechaHasta(DataUtils.formatDate("12-09-2022 12:02:02"));
		assertNotNull(ofertaDAO.create(connection,oferta));



		logger.traceExit();
	}

	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();
		Oferta oferta = new Oferta();

		oferta = ofertaDAO.findById(connection, 2L);
		oferta.setDescuentoFijo(123d);
		oferta.setIdProdOferta(null);
		assertNotNull(ofertaDAO.update(connection, oferta));

		logger.traceExit();

	}

}
