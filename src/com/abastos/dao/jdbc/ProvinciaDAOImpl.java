package com.abastos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.ProvinciaDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Provincia;
import com.abastos.service.DataException;

public class ProvinciaDAOImpl implements ProvinciaDAO {

	private static Logger logger = LogManager.getLogger(ProvinciaDAOImpl.class);
	@Override
	public Provincia findById(Connection connection ,Long idProvincia) throws DataException{
		Provincia results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT ID_PROVINCIA, NOMBRE, ID_COMUNIDAD_AUTONOMA ");
			sql.append(" FROM PROVINCIA ");
			sql.append(" WHERE ID_PROVINCIA = ? ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			
			int i = 1;

			preparedStatement.setLong(i++, idProvincia);

			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				results = loadNext(resultSet);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando la provincia ").append(idProvincia).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);

		}
		return results;
	}

	@Override
	public List<Provincia> findByIdComunidad(Connection connection ,Long idComunidad)throws DataException {

		List<Provincia> results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append(  " SELECT ID_PROVINCIA, NOMBRE, ID_COMUNIDAD_AUTONOMA ");
			sql.append(" FROM PROVINCIA ");
			sql.append(" WHERE ID_COMUNIDAD_AUTONOMA = ? ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			results = new ArrayList<Provincia>();
			int i = 1;

			preparedStatement.setLong(i++, idComunidad);

			resultSet = preparedStatement.executeQuery();
			Provincia a = new Provincia();
			while(resultSet.next()) {

				a = loadNext(resultSet);
				results.add(a);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando las provincia de la comunidad ")
					.append(idComunidad).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return results;
	}
	@Override
	public List<Provincia> findBy(Connection connection ,Long idComunidad) throws DataException {

		List<Provincia> results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append(  " SELECT A.ID_PROVINCIA, A.NOMBRE, A.ID_COMUNIDAD_AUTONOMA, COUNT(*) FROM PROVINCIA A ");
			sql.append(" INNER JOIN LOCALIDAD E ON A.ID_PROVINCIA = E.ID_PROVINCIA ");
			sql.append(" INNER JOIN DIRECCION G ON G.ID_LOCALIDAD = E.ID_LOCALIDAD ");
			sql.append(" INNER JOIN PERFIL_TIENDA_DIRECCION T ON T.ID_DIRECCION = G.ID_DIRECCION ");
			sql.append(" where a.id_comunidad_autonoma = ? ");
			sql.append(" GROUP BY A.ID_PROVINCIA ");
			sql.append(" HAVING COUNT(*) > 0 ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			results = new ArrayList<Provincia>();
			int i = 1;
			preparedStatement.setLong(i++, idComunidad);


			resultSet = preparedStatement.executeQuery();
			Provincia a = new Provincia();
			while(resultSet.next()) {

				a = loadNext(resultSet);
				results.add(a);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando provincias que tienen tiendas dentro de la comunidad ")
					.append(idComunidad).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return results;
	}
	private Provincia loadNext(ResultSet resultSet) throws SQLException, DataException{
		Provincia prov = new Provincia();
		int i = 1;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();
			prov.setId(resultSet.getLong(i++));
			prov.setNombre(resultSet.getString(i++));
			prov.setIdComunidadAutonoma(resultSet.getLong(i++));
			return prov;
		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
	}





}





