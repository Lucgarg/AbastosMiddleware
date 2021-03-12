package com.abastos.service.impl;

import java.sql.Connection;


import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.LineaListaDAO;
import com.abastos.dao.jdbc.LineaListaDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.LineaLista;
import com.abastos.service.DataException;
import com.abastos.service.LineaListaService;

public class LineaListaServiceImpl implements LineaListaService{
	private static Logger logger = LogManager.getLogger(LineaListaServiceImpl.class);
	private LineaListaDAO lineaListaDAO;

	public LineaListaServiceImpl() {
		lineaListaDAO = new LineaListaDAOImpl();
	}

	@Override
	public List<LineaLista> findByIdListaDeseos(Long idLinea) throws DataException {
		logger.info("Iniciando findByIdListaDeseos...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<LineaLista> lineaLista  = null;
		try {
			connection.setAutoCommit(false);
			lineaLista = lineaListaDAO.findByIdListaDeseos(connection, idLinea);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		logger.traceExit("aqui sale");
		return lineaLista;
	}

	@Override
	public LineaLista findById(Long idLista, Long idProducto) throws DataException {
		logger.info("Iniciando findById..");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		LineaLista lineaLista  = null;
		try {
			connection.setAutoCommit(false);
			lineaLista = lineaListaDAO.findById(connection, idLista, idProducto);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return lineaLista;
	}

	@Override
	public void create(LineaLista lineaLista) throws DataException {
		logger.info("Creando lineaLista...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		try {
			connection.setAutoCommit(false);
			lineaListaDAO.create(connection, lineaLista);
			commit = true;
			logger.info(new StringBuilder().append("lineaLista creada...").append(lineaLista.getId())
					.append(" ").append(lineaLista.getIdProducto()).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}


	}

	@Override
	public boolean update(LineaLista lineaLista) throws DataException {
		logger.info("Actualizando lineaLista...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean linea = false;
		try {
			connection.setAutoCommit(false);
			linea = lineaListaDAO.update(connection, lineaLista);
			commit = true;
			logger.info(new StringBuilder().append("lineaLista actualizada...").append(lineaLista.getId())
					.append(" ").append(lineaLista.getIdProducto()).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return linea;
	}

	@Override
	public boolean delete(Long idLista, Long idProducto) throws DataException {
		logger.info("Eliminando lineaLista...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean linea = false;
		try {
			connection.setAutoCommit(false);
			linea = lineaListaDAO.delete(connection, idLista, idProducto);
			commit = true;
			logger.info(new StringBuilder().append("lineaLista eliminada...").append(idLista)
					.append(" ").append(idProducto).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return linea;
	}

	@Override
	public boolean delete(Long idParticular) throws DataException {
		logger.info(new StringBuilder().append("Eliminando lineaLista del particular...").append(idParticular).toString());
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean linea = false;
		try {
			connection.setAutoCommit(false);
			linea = lineaListaDAO.deleteByIdParticular(connection, idParticular);
			commit = true;
			logger.info(new StringBuilder().append("lineaLista del particular ").append(idParticular)
					.append(" eliminada").toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return linea;

	}

}
