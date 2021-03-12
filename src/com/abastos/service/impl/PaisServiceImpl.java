package com.abastos.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.PaisDAO;
import com.abastos.dao.jdbc.PaisDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Pais;
import com.abastos.model.Provincia;
import com.abastos.service.DataException;
import com.abastos.service.PaisService;

public class PaisServiceImpl implements PaisService{
	private static Logger logger = LogManager.getLogger(PaisServiceImpl.class);
	private PaisDAO paisDAO = null;
	public PaisServiceImpl() {
		paisDAO = new PaisDAOImpl();
	}
	@Override
	public Pais findById(Integer idPais) throws DataException {
		logger.info("Iniciando findById...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Pais pais  = null;
		try {
			connection.setAutoCommit(false);
			pais = paisDAO.findById(connection, idPais);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return pais;
	}

	@Override
	public List<Pais> findByAll() throws DataException {
		logger.info("Iniciando findByAll...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<Pais> pais  = null;
		try {
			connection.setAutoCommit(false);
			pais = paisDAO.findByAll(connection);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return pais;
	}

}
