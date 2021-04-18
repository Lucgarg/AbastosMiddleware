package com.abastos.service.impl;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.cache.Cache;
import com.abastos.cache.impl.CacheManagerImpl;
import com.abastos.dao.CategoriaDAO;
import com.abastos.dao.jdbc.CategoriaDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Categoria;
import com.abastos.service.CategoriaService;
import com.abastos.service.DataException;
import com.abastos.service.utils.CacheNames;

public class CategoriaServiceImpl implements CategoriaService{
	private static Logger logger = LogManager.getLogger(CategoriaServiceImpl.class);
	private CategoriaDAO categoriaDAO = null;
	public CategoriaServiceImpl() {
		categoriaDAO = new CategoriaDAOImpl();
	
	}

	@Override
	public List<Categoria> findRoot(String idioma) throws DataException {
		logger.info("Empezando categoria findRoot...");
		Cache cacheCateg = CacheManagerImpl.getInstance().get(CacheNames.CATEGORIA);
		List<Categoria> categoria=  (List<Categoria>)cacheCateg.get(idioma);
		if(categoria != null) {
			logger.info("cache hit");
		}
		else { 
			logger.info("cache miss");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
	
		try {
			connection.setAutoCommit(false);
			categoria = categoriaDAO.findRoot(connection, idioma);
			commit = true;
			
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		cacheCateg.put(idioma, categoria);
		}
		
		return categoria;
	}

	@Override
	public Categoria findById(Integer idCategoria, String idioma) throws DataException {
		logger.info("Empezando findId...");
		Cache cacheCateg = CacheManagerImpl.getInstance().get(CacheNames.CATEGORIA);
		MultiKey mk  = new MultiKey(idCategoria, idioma);
		Categoria categoria=  (Categoria)cacheCateg.get(mk);
		if(categoria != null) {}
		else {
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;

		try {
			connection.setAutoCommit(false);
			categoria = categoriaDAO.findById(connection, idCategoria, idioma);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		cacheCateg.put(mk, connection);
		
		}
		return categoria;
	}

	@Override
	public List<Categoria> findByIdPadre(Integer idCatPadre, String idioma) throws DataException {
		logger.info("Empezando findId...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<Categoria> categoria  = null;
		try {
			connection.setAutoCommit(false);
			categoria = categoriaDAO.findByIdPadre(connection, idCatPadre, idioma);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return categoria;
	}

	@Override
	public List<Categoria> findByTienda(Long idTienda, String idioma) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
