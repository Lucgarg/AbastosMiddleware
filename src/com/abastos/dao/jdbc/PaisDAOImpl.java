package com.abastos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.PaisDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Pais;
import com.abastos.service.DataException;

public class PaisDAOImpl implements PaisDAO {
	private static Logger logger = LogManager.getLogger(PaisDAOImpl.class);
	@Override
	public Pais findById(Connection connection ,Integer idPais) throws DataException{
		Pais results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Creating Statement...");

			sql.append( " SELECT ID_PAIS, NOMBRE FROM PAIS ");
			sql.append(" WHERE ID_PAIS = ? ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			
			int i = 1;
			preparedStatement.setLong(i++, idPais);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				results = loadNext(resultSet);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando el pais ").append(idPais).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return results;
	}
	@Override
	public List<Pais> findByAll(Connection connection) throws DataException {
		Pais pais = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<Pais> listPais = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();
			logger.trace("Create statement...");

			sql.append( " SELECT ID_PAIS, NOMBRE FROM PAIS ");

			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
		
			resultSet = preparedStatement.executeQuery();
			listPais = new ArrayList<Pais>();
			while (resultSet.next()) {
				pais = loadNext(resultSet);
				listPais.add(pais);
			}

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException("Buscando todos los paises ",se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}

		return listPais;
	}

	private Pais loadNext(ResultSet resultSet) throws SQLException{
		Pais a = new Pais();
		int i = 1;
		a.setId(resultSet.getInt(i++));
		a.setNombre(resultSet.getString(i++));
		return a;
	}



}





