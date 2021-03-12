package com.abastos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.ComunidadAutonomaDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.ComunidadAutonoma;
import com.abastos.service.DataException;

public class ComunidadAutonomaDAOImpl implements ComunidadAutonomaDAO {
	private static Logger logger = LogManager.getLogger(ComunidadAutonomaDAOImpl.class);
	@Override
	public ComunidadAutonoma findById(Connection connection, Long idComunidad)throws DataException {

		ComunidadAutonoma results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT ID_COMUNIDAD_AUTONOMA, NOMBRE, ID_PAIS ");
			sql.append(" FROM COMUNIDAD_AUTONOMA ");
			sql.append(" WHERE ID_COMUNIDAD_AUTONOMA = ? ");

			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			
			int i = 1;

			preparedStatement.setLong(i++, idComunidad);

			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				results = loadNext(resultSet);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando comunidad ").append(idComunidad).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return results;
	}

	@Override
	public List<ComunidadAutonoma> findByIdPais(Connection connection, Long idPais) throws DataException{

		List<ComunidadAutonoma> results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT ID_COMUNIDAD_AUTONOMA, NOMBRE, ID_PAIS ");
			sql.append(" FROM COMUNIDAD_AUTONOMA ");
			sql.append(" WHERE ID_PAIS = ? ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			results = new ArrayList<ComunidadAutonoma>();
			int i = 1;

			preparedStatement.setLong(i++, idPais);

			resultSet = preparedStatement.executeQuery();
			ComunidadAutonoma a = new ComunidadAutonoma();
			while(resultSet.next()) {

				a = loadNext(resultSet);
				results.add(a);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando comunidad por pais ").append(idPais).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return results;
	}
	@Override
	public List<ComunidadAutonoma> findByTienda(Connection connection, Integer idPais) throws DataException {
		List<ComunidadAutonoma> results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append(  " SELECT A.ID_COMUNIDAD_AUTONOMA, A.NOMBRE, A.ID_PAIS, COUNT(*) FROM COMUNIDAD_AUTONOMA A");
			sql.append(" INNER JOIN PROVINCIA F ON A.ID_COMUNIDAD_AUTONOMA = F.ID_COMUNIDAD_AUTONOMA ");
			sql.append(" INNER JOIN LOCALIDAD E ON F.ID_PROVINCIA = E.ID_PROVINCIA ");					
					sql.append(" INNER JOIN DIRECCION G ON G.ID_LOCALIDAD = E.ID_LOCALIDAD ");
					sql.append(" INNER JOIN PERFIL_TIENDA_DIRECCION T ON T.ID_DIRECCION = G.ID_DIRECCION ");
					sql.append(" WHERE A.ID_PAIS = ? ");
					sql.append(" GROUP BY A.ID_COMUNIDAD_AUTONOMA ");
					sql.append(" HAVING COUNT(*) > 0 ");
					preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					logger.trace(sql.toString());
					results = new ArrayList<ComunidadAutonoma>();
					int i = 1;
					preparedStatement.setLong(i++, idPais);
					resultSet = preparedStatement.executeQuery();
					ComunidadAutonoma a = new ComunidadAutonoma();
					while(resultSet.next()) {

						a = loadNext(resultSet);
						results.add(a);
					}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando comunidad que tengan tienda por pais ").append(idPais).toString(), se);
		} finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return results;
	}
	private ComunidadAutonoma loadNext(ResultSet resultSet) throws SQLException{
		ComunidadAutonoma a = new ComunidadAutonoma();
		int i = 1;
		a.setId(resultSet.getLong(i++));
		a.setNombre(resultSet.getString(i++));
		a.setPais(resultSet.getInt(i++));
		return a;
	}





}


