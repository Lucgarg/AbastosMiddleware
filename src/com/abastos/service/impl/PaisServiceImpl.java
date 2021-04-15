package com.abastos.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.cache.EhCache;
import com.abastos.cache.impl.CacheManagerImpl;
import com.abastos.dao.PaisDAO;
import com.abastos.dao.jdbc.PaisDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Pais;
import com.abastos.model.Provincia;
import com.abastos.service.DataException;
import com.abastos.service.PaisService;
import com.abastos.service.utils.CacheNames;

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
		EhCache cachePais = CacheManagerImpl.getInstance().get(CacheNames.PAIS);
		List<Pais> pais=  (List<Pais>)cachePais.get(CacheNames.PAIS);
		if(pais != null) {
			logger.info("cache hit");
		}
		else {
			logger.info("cache miss");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		 pais  = null;
		
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
		cachePais.put(CacheNames.PAIS, pais);
		}
		return pais;
	}

}
