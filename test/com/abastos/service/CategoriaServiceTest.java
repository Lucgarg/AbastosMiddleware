package com.abastos.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.model.Categoria;
import com.abastos.service.impl.CategoriaServiceImpl;
import com.abastos.service.impl.PaisServiceImpl;

public class CategoriaServiceTest {
	private static Logger logger = LogManager.getLogger(CategoriaServiceTest.class);
	private CategoriaService categoriaService;

	@Before
	public void setUp() throws Exception {
		categoriaService = new CategoriaServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCategoriaServiceImpl() {

	}

	@Test
	public void testFindRoot() throws Exception{
		logger.traceEntry();
		
			assertNotEquals(Collections.EMPTY_LIST,categoriaService.findRoot("en"));
		
	
		logger.traceExit();
	}

	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();
		
			assertNotNull(categoriaService.findById(1, "es"));
		
		logger.traceExit();
	}

	@Test
	public void testFindByIdPadre() throws Exception{
		logger.traceEntry();
	
			assertNotEquals(Collections.EMPTY_LIST,categoriaService.findByIdPadre(1, "en"));
		
		logger.traceExit();
	}



}
