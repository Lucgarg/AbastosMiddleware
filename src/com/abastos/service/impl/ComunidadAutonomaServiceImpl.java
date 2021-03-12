package com.abastos.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.ComunidadAutonomaDAO;
import com.abastos.dao.jdbc.ComunidadAutonomaDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.ComunidadAutonoma;
import com.abastos.model.Localidad;
import com.abastos.service.ComunidadAutonomaService;
import com.abastos.service.DataException;

public class ComunidadAutonomaServiceImpl implements ComunidadAutonomaService{
	private static Logger logger = LogManager.getLogger(ComunidadAutonomaServiceImpl.class);
	private ComunidadAutonomaDAO comunidadAutonomaDAO = null;
	public ComunidadAutonomaServiceImpl() {
		comunidadAutonomaDAO = new ComunidadAutonomaDAOImpl();
		
	}

	@Override
	public ComunidadAutonoma findById(Long idComunidad) throws DataException {
		logger.info("Iniciando findById...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		ComunidadAutonoma comunidadAutonoma  = null;
		try {
			connection.setAutoCommit(false);
			comunidadAutonoma = comunidadAutonomaDAO.findById(connection, idComunidad);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return comunidadAutonoma;
	}

	@Override
	public List<ComunidadAutonoma> findByIdPais(Long idPais) throws DataException {
		logger.info("Iniciando findById...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<ComunidadAutonoma> comunidadAutonoma  = null;
		try {
			connection.setAutoCommit(false);
			comunidadAutonoma = comunidadAutonomaDAO.findByIdPais(connection, idPais);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return comunidadAutonoma;
	}

	@Override
	public List<ComunidadAutonoma> findByTienda(Integer idPais) throws DataException {
		logger.info("Iniciando findByTienda...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<ComunidadAutonoma> comunidadAutonoma  = null;
		try {
			connection.setAutoCommit(false);
			comunidadAutonoma = comunidadAutonomaDAO.findByTienda(connection, idPais);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return comunidadAutonoma;
	}

}
