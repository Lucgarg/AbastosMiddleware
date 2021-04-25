package com.abastos.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.dao.util.DataUtils;
import com.abastos.model.Oferta;
import com.abastos.service.impl.OfertaServiceImpl;

public class OfertaServiceTest {
	private static Logger logger = LogManager.getLogger(OfertaServiceTest.class);

	private OfertaService oferServ;
	@Before
	public void setUp() throws Exception {
		oferServ = new OfertaServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();

		
		//oferta con fecha valida
		assertNotNull(oferServ.findById(1L));

		logger.traceExit();
	}
	@Test
	public void testFindByIdEmpresa() throws Exception{
		logger.traceEntry();


		assertNotNull(oferServ.findByIdEmpresa(1L));
	

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
		oferta.setFechaHasta(DataUtils.formatDate("12-09-2021 12:02:02"));
		assertNotNull(oferServ.create(oferta));



		logger.traceExit();

	}

	@Test
	public void testUpdate() throws Exception{
		logger.traceEntry();
		Oferta oferta = new Oferta();

		oferta = oferServ.findById(2L);
		oferta.setDescuentoFijo(123d);
		oferta.setIdProdOferta(null);
		assertNotNull(oferServ.update(oferta));

		logger.traceExit();

	}

}
