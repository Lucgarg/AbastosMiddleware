package com.abastos.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.DireccionDtoDAO;
import com.abastos.dao.jdbc.DireccionDtoDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.DireccionDto;
import com.abastos.service.DataException;
import com.abastos.service.DireccionDTOService;

public class DireccionDTOServiceImpl implements DireccionDTOService{
	private static Logger logger = LogManager.getLogger(DireccionDTOServiceImpl.class);
	private DireccionDtoDAO direccionDtoDAO = null;
	public DireccionDTOServiceImpl() {
		direccionDtoDAO = new DireccionDtoDAOImpl();

	}

	@Override
	public DireccionDto findByIdEmp(Long idEmpresa) throws DataException {
		logger.info("Iniciando findByIdEmp...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		DireccionDto direccionDto  = null;
		try {
			connection.setAutoCommit(false);
			direccionDto = direccionDtoDAO.findByIdEmp(connection, idEmpresa);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return direccionDto;
	}

	@Override
	public List<DireccionDto> findByIdPart(Long idParticular) throws DataException {
		logger.info("Iniciando findByIdPart...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<DireccionDto> direccionDto  = null;
		try {
			connection.setAutoCommit(false);
			direccionDto = direccionDtoDAO.findByIdPart(connection, idParticular);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return direccionDto;
	}

	@Override
	public DireccionDto findByIdTienda(Long idTienda) throws DataException {
		logger.info("Iniciando findByIdTienda...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		DireccionDto direccionDto  = null;
		try {
			connection.setAutoCommit(false);
			direccionDto = direccionDtoDAO.findByIdTienda(connection, idTienda);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return direccionDto;
	}

}
