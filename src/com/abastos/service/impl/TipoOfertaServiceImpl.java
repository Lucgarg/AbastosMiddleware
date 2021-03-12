package com.abastos.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.TipoOfertaDAO;
import com.abastos.dao.jdbc.TipoOfertaDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.TipoOferta;
import com.abastos.service.DataException;
import com.abastos.service.TipoOfertaService;

public class TipoOfertaServiceImpl implements TipoOfertaService{
	private TipoOfertaDAO tipoOfertaDAO= null;
	private static Logger logger = LogManager.getLogger(TipoOfertaServiceImpl.class);
	public TipoOfertaServiceImpl() {
		tipoOfertaDAO = new TipoOfertaDAOImpl();
	}
	@Override
	public List<TipoOferta> findAll() throws DataException {
		logger.info("Iniciando findAll...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<TipoOferta> tipoOferta  = null;
		try {
			connection.setAutoCommit(false);
			tipoOferta = tipoOfertaDAO.findAll(connection);
			commit = true;
		}catch(SQLException se) {

			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return tipoOferta;
	}

}
