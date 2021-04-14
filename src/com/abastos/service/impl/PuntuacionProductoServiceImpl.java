package com.abastos.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.cache.impl.CacheManagerImpl;
import com.abastos.dao.PuntuacionProductoDAO;
import com.abastos.dao.jdbc.PuntuacionProductoDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Localidad;
import com.abastos.service.DataException;
import com.abastos.service.PuntuacionProductoService;
import com.abastos.service.utils.CacheNames;
import com.abastos.service.utils.ServiceUtils;

public class PuntuacionProductoServiceImpl implements PuntuacionProductoService{
	private static Logger logger = LogManager.getLogger(PuntuacionProductoServiceImpl.class);
	private PuntuacionProductoDAO puntuacionProductoDAO;
	public PuntuacionProductoServiceImpl() {
		puntuacionProductoDAO = new PuntuacionProductoDAOImpl();
	}

	@Override
	public List<Integer> findByIdProducto(Long idProducto) throws DataException {
		logger.info("Iniciando findById...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<Integer> puntuacion  = null;
		try {
			connection.setAutoCommit(false);

			puntuacion = puntuacionProductoDAO.findByIdProducto(connection, idProducto);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return puntuacion;
	}
	@Override
	public List<Integer> findByIdParticular(Long idParticular) throws DataException {
		logger.info("Iniciando findById...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<Integer> puntuacion  = null;
		try {
			connection.setAutoCommit(false);

			puntuacion = puntuacionProductoDAO.findByIdParticular(connection, idParticular);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return puntuacion;
	}
	@Override
	public Integer findPuntuacion(Long idParticular, Long idProducto) throws DataException {
		logger.info("Iniciando findById...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Integer puntuacion  = null;
		try {
			connection.setAutoCommit(false);

			puntuacion = puntuacionProductoDAO.findPuntuacion(connection, idParticular, idProducto);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return puntuacion;
	}


	@Override
	public Double findMedia(Long idProducto) throws DataException {
		logger.info("Iniciando findByMedia...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Double puntuacion  = null;
		try {
			connection.setAutoCommit(false);
			puntuacion = ServiceUtils.round(puntuacionProductoDAO.findMedia(connection, idProducto),1);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return puntuacion;
	}

	@Override
	public void create(Long idParticular, Long idProducto, Integer puntuPro) throws DataException {
		logger.info("Creando puntuacion...");
		CacheManagerImpl.getInstance().remove(CacheNames.PRODUCTO);
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		try {
			connection.setAutoCommit(false);
			puntuacionProductoDAO.create(connection, idParticular, idProducto, puntuPro);
			commit = true;
			logger.info(new StringBuilder().append("Puntuacion del producto ")
					.append(idProducto).append(" por el particular ").append(idParticular).append(" creada").toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}


	}

	@Override
	public boolean update(Long idParticular, Long idProducto, Integer puntuPro) throws DataException {
		logger.info("Actualizando puntuacion...");
		CacheManagerImpl.getInstance().remove(CacheNames.PRODUCTO);
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean puntuacion = false;
		try {
			connection.setAutoCommit(false);
			puntuacion = puntuacionProductoDAO.update(connection, idParticular, idProducto, puntuPro);
			commit = true;
			logger.info(new StringBuilder().append("Puntuacion del producto ")
					.append(idProducto).append(" por el particular ").append(idParticular).append(" actualizada").toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return puntuacion;
	}

	@Override
	public boolean delete(Long idParticular, Long idProducto) throws DataException {
		logger.info("Eliminando puntuacion...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean puntuacion = false;
		try {
			connection.setAutoCommit(false);
			puntuacion = puntuacionProductoDAO.delete(connection, idParticular, idProducto);
			commit = true;
			logger.info(new StringBuilder().append("Puntuacion del producto ")
					.append(idProducto).append(" por el particular ").append(idParticular).append(" eliminada").toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return puntuacion;
	}

}
