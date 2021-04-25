package com.abastos.service.impl;

import java.sql.Connection;

import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.abastos.dao.EmpresaDAO;
import com.abastos.dao.jdbc.EmpresaDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Empresa;
import com.abastos.service.DataException;
import com.abastos.service.EmpresaService;
import com.abastos.service.exceptions.ConfirmationRegistrationException;
import com.abastos.service.exceptions.IncorrectPasswordException;
import com.abastos.service.exceptions.ServiceException;
import com.abastos.service.exceptions.UserNotFoundException;

public class EmpresaServiceImpl implements EmpresaService{
	private static Logger logger = LogManager.getLogger(EmpresaServiceImpl.class);
	private static final StrongPasswordEncryptor ENCRYPTOR = new StrongPasswordEncryptor();
	private EmpresaDAO empresaDAO;

	public EmpresaServiceImpl() {
		empresaDAO = new EmpresaDAOImpl();

	}
	@Override
	public Empresa findById(Long idEmpresa) throws DataException {
		logger.info("findById...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Empresa empresa  = null;
		try {
			connection.setAutoCommit(false);
			empresa = empresaDAO.findById(connection, idEmpresa);
			commit = true;

		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return empresa;
	}

	@Override
	public Empresa login(String email, String alias, String password) throws DataException, ServiceException,
	UserNotFoundException, IncorrectPasswordException {
		String usuario = email !=null? email:alias;
		logger.info(new StringBuilder().append("Iniciando sesión con ")
				.append(usuario).toString());
		Empresa empresa = null;
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		try {

			connection.setAutoCommit(false); 
			empresa = email != null? empresaDAO.findByEmail(connection, email):
				empresaDAO.findByAlias(connection, alias);

			if(empresa == null) {

				throw new UserNotFoundException(usuario); 

			}else {

				if (!ENCRYPTOR.checkPassword(password, empresa.getContrasena())) {
					throw new IncorrectPasswordException();
				} 	
				if(empresa.getDataAlta() == null) {
					throw new ConfirmationRegistrationException();
				}
			}
			commit = true;		

			logger.info("Sesión iniciada");
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		} finally {
			ConnectionManager.closeConnection(connection, commit);

		}
		return empresa;	
	}

	@Override
	public Empresa registrar(Empresa empresa) throws DataException{
		logger.info( new StringBuilder().append("Registrando usuario ")
				.append(empresa.getCorreoElectronico()).toString());
		String encryptedPassword = ENCRYPTOR.encryptPassword(empresa.getContrasena());
		empresa.setContrasena(encryptedPassword);
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Empresa empres = null;
		try {

			connection.setAutoCommit(false); 
			empres = empresaDAO.create(connection, empresa);
			commit = true;			

			logger.info(new StringBuilder().append("Usuario ")
					.append(empresa.getCorreoElectronico())
					.append(" registrado").toString());
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		} finally {
			ConnectionManager.closeConnection(connection, commit);

		}
		return empres;
	}

	@Override
	public Empresa update(Empresa empresa) throws DataException {
		logger.info("Actualizando empresa...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Empresa empres  = null;
		try {
			connection.setAutoCommit(false);
			empres = empresaDAO.update(connection, empresa);
			commit = true;
			logger.info(new StringBuilder().append("Empresa actualizada ").append(empresa.getCorreoElectronico()).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return empres;
	}
	@Override
	public boolean updateAlta(Long idEmpresa) throws DataException {
		logger.info("Actualizando empresa...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean result = false;
		try {
			connection.setAutoCommit(false);
			result = empresaDAO.updateAlta(connection, idEmpresa);
			commit = true;
			logger.info(new StringBuilder().append("Actualizando empresa..").append(idEmpresa).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return result;
	}

	@Override
	public boolean delete(Long idEmpresa) throws DataException {
		logger.info("Eliminando empresa...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean empres = false;
		try {
			connection.setAutoCommit(false);
			empres = empresaDAO.softDelete(connection, idEmpresa);
			commit = true;
			logger.info(new StringBuilder().append("Empresa eliminada ").append(idEmpresa).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return empres;
	}




}
