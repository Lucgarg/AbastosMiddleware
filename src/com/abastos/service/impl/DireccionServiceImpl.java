package com.abastos.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.DireccionDAO;
import com.abastos.dao.jdbc.DireccionDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Direccion;
import com.abastos.model.DireccionDto;
import com.abastos.service.DataException;
import com.abastos.service.DireccionService;


public class DireccionServiceImpl implements DireccionService {
	private static Logger logger = LogManager.getLogger(DireccionServiceImpl.class);
	private DireccionDAO direccionDAO;
	
	public DireccionServiceImpl() {
		direccionDAO = new DireccionDAOImpl();
	}

	@Override
	public Direccion findById(Long idDireccion) throws DataException {
		logger.info("Iniciando findById...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Direccion direccion = null;
		try {
			connection.setAutoCommit(false);
			direccion = direccionDAO.findById(connection, idDireccion);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return direccion;
	}

	@Override
	public Direccion createDireccion(Direccion direccion) throws DataException {
		logger.info("Creando direccion...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		try {
			connection.setAutoCommit(false);
			direccion = direccionDAO.createDireccion(connection, direccion);
			commit = true;
			logger.info(new StringBuilder().append("Dirección creada").append(direccion.getId()).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return direccion;
	}

	@Override
	public Direccion createDireccion(DireccionDto direccion) throws DataException {
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Direccion direcc = null;
		try {
			connection.setAutoCommit(false);
			direcc = direccionDAO.createDireccion(connection, direccion);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return direcc;
	}

	@Override
	public Direccion update(Direccion direccion) throws DataException {
		logger.info("Actualizando direccion...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Direccion direcc = null;
		try {
			connection.setAutoCommit(false);
			direcc = direccionDAO.update(connection, direccion);
			commit = true;
			logger.info(new StringBuilder().append("Direccion actualizada ").append(direccion.getId()).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return direcc;
	}

	@Override
	public boolean hardDelete(Long idDireccion) throws DataException {
		logger.info("Actualizando direccion...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean direcc = false;
		try {
			connection.setAutoCommit(false);
			direcc = direccionDAO.hardDelete(connection, idDireccion);
			commit = true;
			logger.info(new StringBuilder().append("Direccion actualizada...").append(idDireccion).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return direcc;
	}

}
