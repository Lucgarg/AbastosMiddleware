package com.abastos.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abastos.cache.impl.CacheManagerImpl;
import com.abastos.model.Pais;
import com.abastos.service.impl.PaisServiceImpl;
import com.abastos.service.utils.CacheNames;

public class PaisServiceTest {
	private static Logger logger = LogManager.getLogger(PaisServiceTest.class);
	private PaisService paisService;

	@Before
	public void setUp() throws Exception {
	paisService = new PaisServiceImpl();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();
		
			assertNotNull(paisService.findById(1));
	
		logger.traceExit();
	}

	@Test
	public void testFindByAll() throws Exception{
		logger.traceEntry();
		paisService.findByAll();
		List<Pais> paises=  (List<Pais>)CacheManagerImpl.getInstance()
				.get(CacheNames.PAIS).get(CacheNames.PAIS);
		for(Pais p : paises) {
			logger.info(p);
		}
		Thread.sleep(20200);
		logger.info("segunda tanda");
		paises=  (List<Pais>)CacheManagerImpl.getInstance()
				.get(CacheNames.PAIS).get(CacheNames.PAIS);
		for(Pais p : paises) {
			logger.info(p);
		}
			assertNotEquals(Collections.EMPTY_LIST,paisService.findByAll());
		
		logger.traceExit();
	}

}
