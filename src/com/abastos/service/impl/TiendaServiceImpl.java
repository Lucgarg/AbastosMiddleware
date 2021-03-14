package com.abastos.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.TiendaDAO;
import com.abastos.dao.jdbc.TiendaDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Tienda;
import com.abastos.service.ContenidoService;
import com.abastos.service.DataException;
import com.abastos.service.MailService;
import com.abastos.service.MailServiceImpl;
import com.abastos.service.TiendaCriteria;
import com.abastos.service.TiendaService;
import com.abastos.service.exceptions.LimitCreationException;
import com.abastos.service.exceptions.ServiceException;

public class TiendaServiceImpl implements TiendaService{
	private static Logger logger = LogManager.getLogger(TiendaServiceImpl.class);
	private TiendaDAO tiendaDAO;
	private Map<String, Object> velo;

	private MailService mailService;
	public TiendaServiceImpl() {
		tiendaDAO = new TiendaDAOImpl();
		velo = new HashMap<String, Object>();
		mailService = new MailServiceImpl();
	}

	@Override
	public Tienda findById(Long idTienda) throws DataException {
		logger.info("Iniciando findById...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Tienda tienda  = null;
		try {
			connection.setAutoCommit(false);
			tienda = tiendaDAO.findById(connection, idTienda);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return tienda;
	}


	@Override
	public List<Tienda> findByIdEmpresa(Long idEmpresa) throws DataException {
		logger.info("Iniciando findByIdEmpresa...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<Tienda> tienda  = null;
		try {
			connection.setAutoCommit(false);
			tienda = tiendaDAO.findByIdEmpresa(connection, idEmpresa);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return tienda;
	}

	@Override
	public List<Tienda> findByCriteria(TiendaCriteria tiendaCri) throws DataException {
		logger.info("Iniciando findByCriteria...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<Tienda> tienda  = null;
		try {
			
			connection.setAutoCommit(false);
			tienda = tiendaDAO.findByCriteria(connection, tiendaCri);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return tienda;	

	}

	@Override
	public Tienda create(Tienda tienda) throws DataException, ServiceException {
		logger.info("Creando tienda...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Tienda tiend  = null;
		try {
			connection.setAutoCommit(false);
			if(tiendaDAO.count(connection, tienda)==5) {
				throw new LimitCreationException("N�mero m�ximo de tiendas creadas alcanzado");
			}
			tiend = tiendaDAO.create(connection, tienda);
			velo.put("tienda", tienda);
			mailService.sendMailHtml(velo,6L,tienda.getEmail());
			commit = true;	
			logger.info(new StringBuilder().append("tienda creada ").append(tienda.getEmail()));
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return tiend;

	}

	@Override
	public Tienda update(Tienda tienda) throws DataException {
		logger.info("Actualizando tienda...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Tienda tiend  = null;
		try {
			connection.setAutoCommit(false);
			tiend = tiendaDAO.update(connection, tienda);
			commit = true;
			logger.info(new StringBuilder().append("tienda actualizada ").append(tienda.getId()).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return tiend;
	}



	@Override
	public boolean delete(Long idTienda) throws DataException {
		logger.info("Eliminando tienda...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean tiend  = false;
		try {
			connection.setAutoCommit(false);
			tiend =  tiendaDAO.softDelete(connection, idTienda);
			commit = true;
			logger.info(new StringBuilder().append("tienda eliminada ").append(idTienda).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return tiend;
	}

	@Override
	public boolean deleteByEmpresa(Long idEmpresa) throws DataException {
		logger.info(new StringBuilder().append("Eliminando tienda por empresa...").append(idEmpresa).toString());
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean tiend  = false;
		try {
			connection.setAutoCommit(false);
			tiend =  tiendaDAO.softDeleteByEmpresa(connection, idEmpresa);
			commit = true;
			logger.info(new StringBuilder().append("tienda eliminada por empresa ").append(idEmpresa).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return tiend;
	}
}


