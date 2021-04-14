package com.abastos.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ehcache.CacheManager;

import com.abastos.cache.impl.CacheManagerImpl;
import com.abastos.dao.OfertaDAO;
import com.abastos.dao.jdbc.OfertaDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Oferta;
import com.abastos.service.DataException;
import com.abastos.service.OfertaService;
import com.abastos.service.utils.CacheNames;

public class OfertaServiceImpl implements OfertaService{
	private static Logger logger = LogManager.getLogger(OfertaServiceImpl.class);
	private OfertaDAO ofertaDAO;
	public OfertaServiceImpl() {
		ofertaDAO = new OfertaDAOImpl();
	}

	@Override
	public Oferta findById(Long idOferta) throws DataException {
		logger.info("Iniciando findById...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Oferta oferta  = null;
		try {
			connection.setAutoCommit(false);
			oferta = ofertaDAO.findById(connection, idOferta);
			commit = true;

		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return oferta;
	}
	@Override
	public List<Oferta> findByIdEmpresa(Long idEmpresa) throws DataException {
		logger.info("Iniciando findByIdEmpresa...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<Oferta> oferta  = null;
		try {
			connection.setAutoCommit(false);
			oferta = ofertaDAO.findByIdEmpresa(connection, idEmpresa);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return oferta;
	}

	@Override
	public Oferta create(Oferta oferta) throws DataException {
		logger.info("Creando oferta...");
		CacheManagerImpl.getInstance().remove(CacheNames.PRODUCTO_OFERTA);
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Oferta ofert  = null;
		try {
			connection.setAutoCommit(false);
			ofert = ofertaDAO.create(connection, oferta);
			commit = true;
			logger.info(new StringBuilder().append("Oferta creada ")
					.append(ofert.getId()).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return ofert;
	}


	@Override
	public Oferta update(Oferta oferta) throws DataException {
		logger.info("Actualizando oferta...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Oferta ofert  = null;
		try {
			connection.setAutoCommit(false);
			ofert = ofertaDAO.update(connection, oferta);
			commit = true;
			logger.info(new StringBuilder().append("Oferta actualizada ")
					.append(oferta.getId()).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return ofert;
	}

	

	
}


