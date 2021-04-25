package com.abastos.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.ContenidoProductoDAO;
import com.abastos.dao.jdbc.ContenidoProductoDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.ContenidoProducto;
import com.abastos.service.ContenidoProductoService;
import com.abastos.service.DataException;

public class ContenidoProductoServiceImpl implements ContenidoProductoService{
	private static Logger logger = LogManager.getLogger(ContenidoProductoServiceImpl.class);
	private ContenidoProductoDAO contenidoProductoDAO;
	public ContenidoProductoServiceImpl() {
		contenidoProductoDAO = new ContenidoProductoDAOImpl();
	}

	@Override
	public List<ContenidoProducto> findByImagenes(Long idProducto) throws DataException {
		logger.info("Iniciando findByImagenes...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<ContenidoProducto> contenidoProducto  = null;
		try {
			connection.setAutoCommit(false);
			contenidoProducto = contenidoProductoDAO.findByImagenes(connection, idProducto);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return contenidoProducto;
	}

}
