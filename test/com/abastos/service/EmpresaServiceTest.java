package com.abastos.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.abastos.model.DireccionDto;
import com.abastos.model.Empresa;
import com.abastos.service.exceptions.IncorrectPasswordException;
import com.abastos.service.exceptions.MailException;
import com.abastos.service.exceptions.ServiceException;
import com.abastos.service.exceptions.UserNotFoundException;
import com.abastos.service.impl.EmpresaServiceImpl;

public class EmpresaServiceTest {
	private static Logger logger = LogManager.getLogger(EmpresaServiceTest.class);
	private EmpresaService empresaService;
	

	@Before
	public void setUp() throws Exception {
		empresaService = new EmpresaServiceImpl();
	
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testFindById() throws Exception{
		logger.traceEntry();
	
			assertNotNull(empresaService.findById(1L));
	
		logger.traceExit();
	}
	@Test
	public void testLogin()throws Exception {
		logger.trace("Testing logging....");
		//password incorrecta
		try {
		empresaService.login(null, "Sara", "45678910");

		} catch(IncorrectPasswordException e) {
			logger.trace("test correcto...");
		}
		//usuario incorrecto
		try {
			empresaService.login(null, "Saa", "45678910");

		} catch(UserNotFoundException e) {
			logger.trace("test correcto...");
		}
		//usuario y password correctas
		
			assertNotNull(empresaService.login(null, "Sara", "12345678910"));
		
			logger.trace("test correcto...");
		
			logger.traceExit();

	}
	
	@Test
	public void testRegistrar() throws Exception{
		logger.traceEntry();
		Empresa empresa = new Empresa();
		DireccionDto diDto = new DireccionDto();
		diDto.setCalle("prueba");
		diDto.setCodigoPostal("prueba02");
		diDto.setComunidadAutonoma("prueba0002");
		diDto.setIdLocalidad(1L);
		diDto.setIdLocalidad(1L);
		diDto.setNumero(12);
		diDto.setPiso("3d");
		diDto.setIdTipoDireccion(1);
		empresa.setAlias("sar");
		empresa.setApellidos("prueba001");
		empresa.setCif("prueba 002");
		empresa.setContrasena("prueba001");
		empresa.setCorreoElectronico("lugarcia132@gmail.com");
		empresa.setRazonSocial("prueba00045");
		empresa.setDireccion(diDto);
		empresa.setNombre("raquel");
		
			empresaService.registrar(empresa);
	
		logger.traceExit();
	}
	
	@Test
	public void testUpdate() throws Exception {
		logger.traceEntry();
		Empresa empresa;
	
			empresa = empresaService.login(null, "RoberTizona", "3456Rob");
			empresa.setAlias("pruebas");
			empresa.getDireccion().setCalle("prueba");
			assertNotNull(empresaService.update(empresa));
	
		logger.traceExit();
	}
	@Test
	public void testUpdateAlta() throws Exception{
		logger.traceEntry();
	
			empresaService.updateAlta(1L);
	
		logger.traceExit();
	}
	@Test
	public void testDelete() throws Exception{
		logger.traceEntry();
		
			assertTrue(empresaService.delete(1L));
		
		logger.traceExit();
	}

}
