package com.abastos.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.cache.impl.CacheManagerImpl;
import com.abastos.dao.PuntuacionTiendaDAO;
import com.abastos.dao.jdbc.PuntuacionTiendaDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.PuntuacionMediaTienda;
import com.abastos.model.PuntuacionTienda;
import com.abastos.service.DataException;
import com.abastos.service.PuntuacionTiendaService;
import com.abastos.service.utils.CacheNames;
import com.abastos.service.utils.ServiceUtils;

public class PuntuacionTiendaServiceImpl implements PuntuacionTiendaService{
	private static Logger logger = LogManager.getLogger(PuntuacionTiendaServiceImpl.class);
	private PuntuacionTiendaDAO puntuacionTiendaDAO;
	public PuntuacionTiendaServiceImpl() {
		puntuacionTiendaDAO = new PuntuacionTiendaDAOImpl();
	}

	@Override
	public List<PuntuacionTienda> findByIdTienda(Long idTienda) throws DataException {
		logger.info("Iniciando findByIdTienda...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<PuntuacionTienda> puntuacionTienda  = null;
		try {
			connection.setAutoCommit(false);

			puntuacionTienda =  puntuacionTiendaDAO.findByIdTienda(connection, idTienda);
			commit = true;

		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return puntuacionTienda;
	}
	@Override
	public List<PuntuacionTienda> findByIdParticular(Long idParticular) throws DataException {
		logger.info("Iniciando findByIdTienda...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<PuntuacionTienda> puntuacionTienda  = null;
		try {
			connection.setAutoCommit(false);

			puntuacionTienda =  puntuacionTiendaDAO.findByIdParticular(connection, idParticular);
			commit = true;

		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return puntuacionTienda;
	}
	@Override
	public PuntuacionTienda findPuntuacion(Long idParticular, Long idTienda) throws DataException {
		logger.info("Iniciando findByIdTienda...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		PuntuacionTienda puntuacionTienda  = null;
		try {
			connection.setAutoCommit(false);

			puntuacionTienda =  puntuacionTiendaDAO.findPuntuacion(connection, idParticular, idTienda);
			commit = true;

		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return puntuacionTienda;
	}




	@Override
	public PuntuacionMediaTienda findMedia(Long idTienda) throws DataException {
		logger.info("Iniciando findMedia...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		PuntuacionMediaTienda puntuacion  = null;
		try {
			connection.setAutoCommit(false);
			puntuacion = puntuacionTiendaDAO.findMedia(connection, idTienda);
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
	public void create(PuntuacionTienda puntuacion) throws DataException {
		logger.info("Creando puntuacion de la Tienda...");
		CacheManagerImpl.getInstance().remove(CacheNames.TIENDA);
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		try {
			connection.setAutoCommit(false);
			puntuacionTiendaDAO.create(connection, puntuacion);
			commit = true;
			logger.info(new StringBuilder().append("Puntuacion de la tienda ")
					.append(puntuacion.getIdTienda())
					.append(" por el particular ")
					.append(puntuacion.getIdTienda()).append(" creada").toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}



	}

	@Override
	public boolean update(PuntuacionTienda puntuacion) throws DataException {
		logger.info("Actualizando puntuacion de la Tienda...");
		CacheManagerImpl.getInstance().remove(CacheNames.TIENDA);
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean punt = false;
		try {
			connection.setAutoCommit(false);
			punt = puntuacionTiendaDAO.update(connection, puntuacion);
			commit = true;
			logger.info(new StringBuilder().append("Puntuacion de la tienda ")
					.append(puntuacion.getIdTienda())
					.append(" por el particular ")
					.append(puntuacion.getIdTienda()).append(" actualizada").toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return punt;
	}

	@Override
	public boolean delete(Long idParticular, Long idTienda) throws DataException {
		logger.info("Eliminando puntuacion de la Tienda...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean puntuacion = false;
		try {
			connection.setAutoCommit(false);
			puntuacion = puntuacionTiendaDAO.delete(connection, idParticular, idTienda);
			commit = true;
			logger.info(new StringBuilder().append("Puntuacion de la tienda ")
					.append(idTienda)
					.append(" por el particular ")
					.append(idParticular).append(" actualizada").toString());
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
