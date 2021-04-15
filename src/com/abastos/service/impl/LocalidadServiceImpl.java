package com.abastos.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.cache.EhCache;
import com.abastos.cache.impl.CacheManagerImpl;
import com.abastos.dao.LocalidadDAO;
import com.abastos.dao.jdbc.LocalidadDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.ComunidadAutonoma;
import com.abastos.model.Localidad;
import com.abastos.service.DataException;
import com.abastos.service.LocalidadService;
import com.abastos.service.utils.CacheNames;

public class LocalidadServiceImpl implements LocalidadService {
	private static Logger logger = LogManager.getLogger(LocalidadServiceImpl.class);
	private LocalidadDAO localidadDAO = null;
	public LocalidadServiceImpl() {
		localidadDAO = new LocalidadDAOImpl();
	}
	@Override
	public List<Localidad> findByIdProvincia(Long idProvincia) throws DataException {
		logger.info("Iniciando findByIdProvincia...");
		EhCache cacheLocalidad = CacheManagerImpl.getInstance().get(CacheNames.LOCALIDAD);
		List<Localidad> localidad=  (List<Localidad>)cacheLocalidad.get(idProvincia);
		if(localidad != null) {
			logger.info("cache hit");
		}
		else {
			logger.info("cache miss");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
	
		try {
			connection.setAutoCommit(false);
			localidad = localidadDAO.findByIdProvincia(connection, idProvincia);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		cacheLocalidad.put(idProvincia, localidad);
		}
		return localidad;
	}

	@Override
	public Localidad findByIdLocalidad(Long idLocalidad) throws DataException {
		logger.info("Iniciando findByIdLocalidad...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Localidad localidad  = null;
		try {
			connection.setAutoCommit(false);
			localidad = localidadDAO.findByIdLocalidad(connection, idLocalidad);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return localidad;
	}

	@Override
	public List<Localidad> findByTiendas(Long idProvincia) throws DataException {
		logger.info("Iniciando findByTiendas...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<Localidad> localidad  = null;
		try {
			connection.setAutoCommit(false);
			localidad = localidadDAO.findByTiendas(connection, idProvincia);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return localidad;
	}

}
