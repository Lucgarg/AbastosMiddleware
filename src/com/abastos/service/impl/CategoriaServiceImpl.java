package com.abastos.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.CategoriaDAO;
import com.abastos.dao.jdbc.CategoriaDAOImpl;
import com.abastos.dao.jdbc.TipoOfertaDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Categoria;
import com.abastos.service.CategoriaService;
import com.abastos.service.DataException;

public class CategoriaServiceImpl implements CategoriaService{
	private static Logger logger = LogManager.getLogger(CategoriaServiceImpl.class);
	private CategoriaDAO categoriaDAO = null;
	public CategoriaServiceImpl() {
		categoriaDAO = new CategoriaDAOImpl();
	
	}

	@Override
	public List<Categoria> findRoot(String idioma) throws DataException {
		logger.info("Empezando categoria findRoot...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<Categoria> categoria  = null;
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
		return categoria;
	}

	@Override
	public Categoria findById(Integer idCategoria, String idioma) throws DataException {
		logger.info("Empezando findId...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Categoria categoria  = null;
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
