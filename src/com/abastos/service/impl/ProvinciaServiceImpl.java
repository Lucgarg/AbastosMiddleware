package com.abastos.service.impl;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.cache.Cache;
import com.abastos.cache.impl.CacheManagerImpl;
import com.abastos.dao.ProvinciaDAO;
import com.abastos.dao.jdbc.ProvinciaDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Provincia;
import com.abastos.service.DataException;
import com.abastos.service.ProvinciaService;
import com.abastos.service.utils.CacheNames;

public class ProvinciaServiceImpl implements ProvinciaService {
	private static Logger logger = LogManager.getLogger(ProvinciaServiceImpl.class);
	private ProvinciaDAO provinciaDAO = null;
	public ProvinciaServiceImpl() {
		provinciaDAO = new ProvinciaDAOImpl();
	}
	@Override
	public Provincia findById(Long idProvincia) throws DataException {
		logger.info("Iniciando findById");

		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Provincia provincia  = null;
		try {
			connection.setAutoCommit(false);
			provincia = provinciaDAO.findById(connection, idProvincia);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return provincia;
	}

	@Override
	public List<Provincia> findByIdComunidad(Long idComunidad) throws DataException {
		logger.info("Iniciando findByIdComunidad");
		Cache cacheProvincia = CacheManagerImpl.getInstance().get(CacheNames.PROVINCIA);
		List<Provincia> provincia=  (List<Provincia>)cacheProvincia.get(idComunidad);
		if(provincia != null) {
			logger.info("cache hit");
		}
		else {
			logger.info("cache miss");
			Connection connection = ConnectionManager.getConnection();
			boolean commit = false;

			try {
				connection.setAutoCommit(false);
				provincia = provinciaDAO.findByIdComunidad(connection, idComunidad);
				commit = true;
			}catch(SQLException se) {
				logger.error(se);
				throw new DataException(se);
			}
			finally {
				ConnectionManager.closeConnection(connection, commit);
			}
			cacheProvincia.put(idComunidad, provincia);
		}
		return provincia;
	}

	@Override
	public List<Provincia> findBy(Long idComunidad) throws DataException {
		logger.info("Iniciando findBy");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<Provincia> provincia  = null;
		try {
			connection.setAutoCommit(false);
			provincia = provinciaDAO.findBy(connection, idComunidad);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return provincia;
	}

}
