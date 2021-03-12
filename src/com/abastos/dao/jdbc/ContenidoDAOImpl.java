package com.abastos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.ContenidoDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Contenido;
import com.abastos.service.DataException;


public class ContenidoDAOImpl implements ContenidoDAO {
	private static Logger logger = LogManager.getLogger(ContenidoDAOImpl.class);
	@Override
	public Contenido findById(Connection connection, Long id) throws DataException {
		Contenido contenido = null;
		ResultSet resultSet = null;

		PreparedStatement preparedStatement = null;
		try {

			logger.trace("Create statement...");

			String sql = " SELECT ID_CONTENIDO, NOMBRE, TEMPLATE, ID_TIPO_CONTENIDO FROM CONTENIDO WHERE ID_CONTENIDO = ?  ";
			preparedStatement = connection.prepareStatement
					(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());

			int i = 1;

			preparedStatement.setLong(i++, id);

			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				contenido = loadNext(resultSet);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando el contenido ").append(id).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return contenido;
	}
	@Override
	public Contenido findByIdTipo(Connection connection, Long id) throws DataException {

		Contenido contenido = null;
		ResultSet resultSet = null;

		PreparedStatement preparedStatement = null;

	
		try {
			

			logger.trace("Create statement...");

			String sql = " SELECT ID_CONTENIDO, NOMBRE, TEMPLATE, ID_TIPO_CONTENIDO"
					+ " FROM CONTENIDO WHERE ID_TIPO_CONTENIDO = ?  ";
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());

			int i = 1;

			preparedStatement.setLong(i++, id);

			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				contenido = loadNext(resultSet);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando el contenido ").append(id).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return contenido;
	}


	private Contenido loadNext(ResultSet resultSet) throws DataException, SQLException {
		Contenido cont = new Contenido();
		int i = 1;
		cont.setId(resultSet.getLong(i++));
		cont.setNombre(resultSet.getString(i++));
		cont.setTemplate(resultSet.getString(i++));
		cont.setTipoContenido(resultSet.getInt(i++));
		return cont;

	}

	@Override
	public Contenido create(Connection connection, Contenido cont) throws DataException {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Contenido contenido = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          
			logger.trace("Create statement...");

			sql.append( " INSERT INTO CONTENIDO(NOMBRE, ID_TIPO_CONTENIDO)");
			sql.append(" VALUES( ? , ?) "); 
			preparedStatement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			logger.trace(sql.toString());

			int i = 1;
			preparedStatement.setString(i++, cont.getNombre());
			preparedStatement.setInt(i++, cont.getTipoContenido());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			contenido = new Contenido();
			i = 1;
			if(resultSet.next()) {
				contenido.setId(resultSet.getLong(i++));
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Creando contenido ").append(cont.getNombre()).toString(),  se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return contenido;
	}



	@Override
	public Contenido update(Connection connection, Contenido cont) throws DataException {
		PreparedStatement preparedStatement = null;
		Contenido contenido = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          
			logger.trace("Create statement...");


			sql.append( " UPDATE CONTENIDO SET NOMBRE = ?, ID_TIPO_CONTENIDO = ? WHERE ");
			sql.append(" ID_CONTENIDO = ?  ");
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setString(i++, cont.getNombre());
			preparedStatement.setLong(i++, cont.getId());
			preparedStatement.setInt(i++, cont.getTipoContenido());

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new DataException(new StringBuilder().append("No se puede actualizar el contenido ").append(cont.getId()).toString());
			} 

			contenido = findById(connection, cont.getId());

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Actualizando el contenido ").append(cont.getId()).toString(),  se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}

		return contenido;
	}

	@Override
	public boolean hardDelete(Connection connection, Long idContenido) throws DataException {
		PreparedStatement preparedStatement = null;

		try {
			
			logger.trace("Create statement...");

			String sql =  " DELETE FROM CONTENIDO WHERE ID_CONTENIDO = ? "; 

			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setLong(i++, idContenido);

			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {
				throw new DataException(new StringBuilder().append("No es posible eliminar el contenido ").append(idContenido).toString());
			} 

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Eliminando el contenido ").append(idContenido).toString(), se);
		} finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return true;
	}




}


