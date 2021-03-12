package com.abastos.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.abastos.dao.ParticularDAO;
import com.abastos.dao.jdbc.ParticularDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Empresa;
import com.abastos.model.Oferta;
import com.abastos.model.Particular;
import com.abastos.service.ContenidoService;
import com.abastos.service.DataException;
import com.abastos.service.MailService;
import com.abastos.service.MailServiceImpl;
import com.abastos.service.ParticularService;
import com.abastos.service.exceptions.ConfirmationRegistrationException;
import com.abastos.service.exceptions.IncorrectPasswordException;
import com.abastos.service.exceptions.ServiceException;
import com.abastos.service.exceptions.UserNotFoundException;
import com.abastos.service.utils.VelocityUtils;

public class ParticularServiceImpl implements ParticularService {
	private static Logger logger = LogManager.getLogger(ParticularServiceImpl.class);
	private static final StrongPasswordEncryptor ENCRYPTOR = new StrongPasswordEncryptor();
	private ParticularDAO particularDAO;
	private ContenidoService contenidoService;
	private MailService mailService;
	private Map<String, Object> velo;
	public ParticularServiceImpl() {
		particularDAO = new ParticularDAOImpl();
		contenidoService = new ContenidoServiceImpl();
		mailService = new MailServiceImpl();
		velo = new HashMap<String, Object>();
	}
	@Override
	public Particular findById(Long idParticular) throws DataException {
		Connection connection = ConnectionManager.getConnection();
		logger.info("findById particular...");
		boolean commit = false;
		Particular particular  = null;
		try {
			connection.setAutoCommit(false);
			particular = particularDAO.findById(connection, idParticular);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return particular;
	}
	@Override
	public Integer findPuntos(Long idParticular) throws DataException {
		logger.info("Iniciando findPuntos..");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Integer particular = null;
		try {
			connection.setAutoCommit(false);
			particular = particularDAO.findPuntos(connection, idParticular);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return particular;
	}

	@Override
	public Particular login(String email, String alias, String password) throws DataException, ServiceException,
	UserNotFoundException, IncorrectPasswordException {
		String usuario = email != null? email:alias;
		logger.info(new StringBuilder().append("Iniciando sesión con ").append(usuario).toString());
		Particular particular = null;
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;

		try {

			connection.setAutoCommit(false); 
			particular = email != null? particularDAO.findByEmail(connection, email):
				particularDAO.findByAlias(connection, alias);

			if(particular == null) {
				throw new UserNotFoundException(usuario); 
			}else {

				if (!ENCRYPTOR.checkPassword(password, particular.getContrasena())) {
					throw new IncorrectPasswordException();
				} 
				if(particular.getDataAlta() == null) {
					throw new ConfirmationRegistrationException(new StringBuilder().append(usuario)
							.append(" no ha confirmado su registro").toString());
				}
			}

			commit = true;		

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		} finally {
			ConnectionManager.closeConnection(connection, commit);

		}
		return particular;	
	}

	@Override
	public Particular registrar(Particular parti) throws DataException, ServiceException {
		logger.info(new StringBuilder().append("Registrando usuario ").append(parti.getEmail()).toString());
		Particular particular = null;
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		String encryptedPassword = ENCRYPTOR.encryptPassword(parti.getContrasena());
		parti.setContrasena(encryptedPassword);
		try {
			connection.setAutoCommit(false); 
			particular = particularDAO.create(connection, parti);
			velo.put("user", parti);
			velo.put("enlace", "www.prueba.com/abastos");
			mailService.sendMail(velo,3L, parti.getEmail());
			logger.info("Usuario creado");
			commit = true;			

			logger.info(new StringBuilder().append("Usuario ").append(particular.getEmail())
					.append(" registrado").toString());
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		} finally {
			ConnectionManager.closeConnection(connection, commit);

		}
		return particular;
	}

	@Override
	public Particular update(Particular parti) throws DataException {
		Connection connection = ConnectionManager.getConnection();
		logger.info("actualizando particular...");
		boolean commit = false;
		Particular particular = null;
		try {
			connection.setAutoCommit(false);
			particular = particularDAO.update(connection, parti);
			commit = true;
			logger.info(new StringBuilder().append("particular actualizado ").append(parti.getId()).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return particular;
	}
	@Override
	public boolean updatePuntos(Long idParticular, Integer puntos) throws DataException {
		logger.info("Actualizando puntos del particular...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean result = false;
		try {
			connection.setAutoCommit(false);
			result = particularDAO.updatePuntos(connection, idParticular, puntos);
			commit = true;
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
	public boolean updateAlta(Long idParticular) throws DataException {
		Connection connection = ConnectionManager.getConnection();
		logger.info(new StringBuilder().append("Confirmando el registro del particular ").append(idParticular).toString());
		boolean commit = false;
		boolean result = false;
		try {
			connection.setAutoCommit(false);
			result = particularDAO.updateAlta(connection, idParticular);
			commit = true;
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
	public boolean delete(Long idParticular) throws DataException {
		Connection connection = ConnectionManager.getConnection();
		logger.info("Eliminando particular... ");
		boolean commit = false;
		boolean particular = false;
		try {
			connection.setAutoCommit(false);
			particular = particularDAO.softDelete(connection, idParticular);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return particular;
	}








}
