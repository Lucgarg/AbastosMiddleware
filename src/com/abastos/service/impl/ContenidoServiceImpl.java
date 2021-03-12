package com.abastos.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.ContenidoDAO;
import com.abastos.dao.jdbc.ContenidoDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Contenido;
import com.abastos.service.ContenidoService;
import com.abastos.service.DataException;

public class ContenidoServiceImpl implements ContenidoService{
	private static Logger logger = LogManager.getLogger(ContenidoServiceImpl.class);
	private ContenidoDAO contenidoDAO;
	public ContenidoServiceImpl() {
		contenidoDAO = new ContenidoDAOImpl();

	}

	@Override
	public Contenido findById(Long id) throws DataException {
		logger.info("Iniciando findById...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Contenido contenido= null;
		try {
			connection.setAutoCommit(false);
			contenido = contenidoDAO.findById(connection, id);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return contenido;
	}
	@Override
	public Contenido findByIdTipo( Long id) throws DataException{
		logger.info("Iniciando findByIdTipo...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Contenido contenido= null;
		try {
			connection.setAutoCommit(false);
			contenido = contenidoDAO.findByIdTipo(connection, id);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return contenido;
	}
	@Override
	public Contenido create(Contenido cont) throws DataException {
		logger.info("Creando contenido...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Contenido contenido= null;
		try {
			connection.setAutoCommit(false);
			contenido = contenidoDAO.create(connection, cont);
			commit = true;
			logger.info(new StringBuilder().append("Contenido creado ").append(contenido.getId()).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return contenido;
	}

	@Override
	public Contenido update(Contenido cont) throws DataException {
		logger.info("Actualizando contenido...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Contenido contenido= null;
		try {
			connection.setAutoCommit(false);
			contenido = contenidoDAO.update(connection, cont);
			commit = true;
			logger.info(new StringBuilder("Contenido actualizado...").append(contenido.getId()).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return contenido;
	}

	@Override
	public boolean hardDelete(Long idContenido) throws DataException {
		logger.info("Eliminando contenido...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean contenido= false;
		try {
			connection.setAutoCommit(false);
			contenido = contenidoDAO.hardDelete(connection, idContenido);
			commit = true;
			logger.info(new StringBuilder().append("Contenido eliminado ").append(idContenido).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return contenido;
	}

}
