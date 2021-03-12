package com.abastos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.LocalidadDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Localidad;
import com.abastos.service.DataException;



public class LocalidadDAOImpl implements LocalidadDAO {
	private static Logger logger = LogManager.getLogger(LocalidadDAOImpl.class);
	@Override
	public List<Localidad> findByIdProvincia(Connection connection ,Long idProvincia)throws DataException {

		List<Localidad> results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " select id_localidad, nombre_localidad, id_provincia ");
			sql.append(" from localidad ");
			sql.append(" where id_provincia = ? ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			results = new ArrayList<Localidad>();
			int i = 1;

			preparedStatement.setLong(i++, idProvincia);

			resultSet = preparedStatement.executeQuery();
			Localidad local = new Localidad();
			while(resultSet.next()) {

				local = loadNext(resultSet);
				results.add(local);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando las localidades de la provincia ")
					.append(idProvincia).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return results;
	}

	@Override
	public Localidad findByIdLocalidad(Connection connection ,Long idLocalidad) throws DataException{
		Localidad results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");
			sql.append( " SELECT ID_LOCALIDAD, NOMBRE_LOCALIDAD, ID_PROVINCIA ");
			sql.append(" FROM LOCALIDAD ");
			sql.append(" WHERE ID_LOCALIDAD = ? ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			
			int i = 1;

			preparedStatement.setLong(i++, idLocalidad);

			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				results = loadNext(resultSet);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando la localidad ").append(idLocalidad).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return results;
	}

	@Override
	public List<Localidad> findByTiendas(Connection connection ,Long idProvincia) throws DataException {
		List<Localidad> results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append(  " SELECT A.ID_LOCALIDAD, A.NOMBRE_LOCALIDAD, A.id_PROVINCIA, COUNT(*) FROM LOCALIDAD A ");
			sql.append("	INNER JOIN DIRECCION G ON G.ID_LOCALIDAD = A.ID_LOCALIDAD ");
			sql.append("	INNER JOIN PERFIL_TIENDA_DIRECCION T ON T.ID_DIRECCION = G.ID_DIRECCION ");
			sql.append("  where a.id_provincia = ? ");
			sql.append("	 GROUP BY A.ID_LOCALIDAD ");
			sql.append("	 HAVING COUNT(*) > 0");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			results = new ArrayList<Localidad>();
			int i = 1;

			preparedStatement.setLong(i++, idProvincia);

			resultSet = preparedStatement.executeQuery();
			Localidad a = new Localidad();
			while(resultSet.next()) {
				a = loadNext(resultSet);
				results.add(a);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando localidades con tiendas en la provincia ")
					.append(idProvincia).toString(), se);
		}  finally {

			ConnectionManager.close(resultSet, preparedStatement);
		}
		return results;
	}
	private Localidad loadNext(ResultSet resultSet) throws SQLException{
		Localidad a = new Localidad();
		int i = 1;
		a.setId(resultSet.getLong(i++));
		a.setNombre(resultSet.getString(i++));
		a.setIdProvincia(resultSet.getLong(i++));
		return a;
	}







}
