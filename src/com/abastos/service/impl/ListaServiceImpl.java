package com.abastos.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.ListaDAO;
import com.abastos.dao.jdbc.ListaDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Lista;
import com.abastos.model.Producto;
import com.abastos.service.DataException;
import com.abastos.service.ListaService;

public class ListaServiceImpl implements ListaService {
	private static Logger logger = LogManager.getLogger(ListaServiceImpl.class);
	private ListaDAO listaDAO;
	public ListaServiceImpl() {
		listaDAO = new ListaDAOImpl();

	}

	@Override
	public Lista findById(Long idLista) throws DataException {
		logger.info("Iniciando findById...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Lista lista  = null;
		try {
			connection.setAutoCommit(false);
			lista = listaDAO.findById(connection, idLista);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return lista;
	}

	@Override
	public List<Lista> findByIdParticular(Long idParticular) throws DataException {
		logger.info("Iniciando findByIdParticular...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<Lista> lista  = null;
		try {
			connection.setAutoCommit(false);
			lista = listaDAO.findByIdParticular(connection, idParticular);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return lista;
	}


	@Override
	public Lista create(Lista lista) throws DataException {
		logger.info("Creando lista...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Lista list  = null;
		try {
			connection.setAutoCommit(false);
			list = listaDAO.create(connection, lista);
			commit = true;
			logger.info(new StringBuilder().append("lista creada ")
					.append(list.getId()).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return list;
	}

	@Override
	public Lista update(Lista lista) throws DataException {
		logger.info("Actualizando lista...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Lista list  = null;
		try {
			connection.setAutoCommit(false);
			list = listaDAO.update(connection, lista);
			commit = true;
			logger.info(new StringBuilder().append("lista actualizada ").append(lista.getId()).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return list;
	}


	@Override
	public boolean delete(Long idLista) throws DataException {
		logger.info("eliminando lista...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean list;
		try {
			connection.setAutoCommit(false);
			list =  listaDAO.delete(connection, idLista);
			commit = true;
			logger.info(new StringBuilder().append("lista eliminada ").append(idLista).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return list;
	}

	@Override
	public boolean deleteByIdParticular(Long idParticular) throws DataException {
		logger.info(new StringBuilder().append("eliminando listas del particular").append(idParticular).toString());
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean list;
		try {
			connection.setAutoCommit(false);
			list =	listaDAO.deleteByIdParticular(connection, idParticular);
			commit = true;
			logger.info(new StringBuilder().append("Lista eliminadas del particular").append(idParticular).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return list;
	}

}
