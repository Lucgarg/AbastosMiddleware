package com.abastos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.DireccionDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Direccion;
import com.abastos.model.DireccionDto;
import com.abastos.service.DataException;


public class DireccionDAOImpl implements DireccionDAO{
	private static Logger logger = LogManager.getLogger(DireccionDAOImpl.class);

	@Override
	public Direccion findById(Connection connection, Long idDireccion) throws DataException {
		Direccion direc = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();
			logger.trace("Create statement...");

			sql.append( " SELECT ID_DIRECCION, CALLE, NUMERO, CODIGO_POSTAL, ID_TIPO_DIRECCION, ");
			sql.append(" ID_LOCALIDAD FROM DIRECCION WHERE ID_DIRECCION = ? ");

			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());

			int i = 1;

			preparedStatement.setLong(i++, idDireccion);

			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				direc = loadNext(resultSet);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Encontrando la direccion ").append(idDireccion).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return direc;
	}

	private Direccion loadNext(ResultSet resultset) throws DataException, SQLException {
		int i = 1;
		Direccion direc = new Direccion();
		direc.setId(resultset.getLong(i++));
		direc.setCalle(resultset.getString(i++));
		direc.setNumero(resultset.getInt(i++));
		direc.setCodigoPostal(resultset.getString(i++));
		direc.setTipoDireccion(resultset.getInt(i++));
		direc.setIdLocalidad(resultset.getLong(i++));
		return direc;
	}
	@Override
	public Direccion createDireccion(Connection connection, Direccion direccion) throws DataException {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Direccion direc = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");
			sql.append( " INSERT INTO DIRECCION(CALLE, NUMERO, CODIGO_POSTAL, ID_TIPO_DIRECCION, ID_LOCALIDAD, piso) ");
			sql.append(" VALUES( ? , ? , ? , ? , ? , ?)");

			preparedStatement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			logger.trace(sql.toString());

			int i = 1;
			preparedStatement.setString(i++, direccion.getCalle());
			preparedStatement.setInt(i++, direccion.getNumero());
			preparedStatement.setString(i++, direccion.getCodigoPostal());
			preparedStatement.setLong(i++, direccion.getTipoDireccion());
			preparedStatement.setLong(i++, direccion.getIdLocalidad());
			preparedStatement.setString(i++, direccion.getPiso());

			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			direc = new Direccion();
			i = 1;
			if(resultSet.next()) {
				direc.setId(resultSet.getLong(i++));
			}

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Creando la direccion ").append(direccion.getId()).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return direc;
	}
	@Override
	public Direccion createDireccion(Connection connection, DireccionDto direccion) throws DataException {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Direccion direc = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();


			logger.trace("Create statement...");

			sql.append( " INSERT INTO DIRECCION(CALLE, NUMERO, CODIGO_POSTAL, ID_TIPO_DIRECCION, ID_LOCALIDAD, piso) ");
			sql.append(" VALUES( ? , ? , ? , ? , ? , ?)");

			preparedStatement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			logger.trace(sql.toString());

			int i = 1;
			preparedStatement.setString(i++, direccion.getCalle());
			preparedStatement.setInt(i++, direccion.getNumero());
			preparedStatement.setString(i++, direccion.getCodigoPostal());
			preparedStatement.setLong(i++, direccion.getIdTipoDireccion());
			preparedStatement.setLong(i++, direccion.getIdLocalidad());
			preparedStatement.setString(i++, direccion.getPiso());

			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			direc = new Direccion();
			i = 1;
			if(resultSet.next()) {
				direc.setId(resultSet.getLong(i++));
			}

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Creando la direccion ").append(direccion.getIdDireccion()).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return direc;

	}
	@Override
	public Direccion update(Connection connection, Direccion direccion) throws DataException {
		PreparedStatement preparedStatement = null;

		Direccion direc = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          

			logger.trace("Create statement...");

			sql.append(  " UPDATE DIRECCION SET CALLE = ? , NUMERO = ?, ");
			sql.append(" CODIGO_POSTAL = ?, ID_TIPO_DIRECCION = ?, ID_LOCALIDAD = ? WHERE ID_DIRECCION = ? ");
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setString(i++, direccion.getCalle());
			preparedStatement.setInt(i++, direccion.getNumero());
			preparedStatement.setString(i++, direccion.getCodigoPostal());
			preparedStatement.setLong(i++, direccion.getTipoDireccion());
			preparedStatement.setLong(i++, direccion.getIdLocalidad());
			preparedStatement.setLong(i++, direccion.getId());
			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new DataException(new StringBuilder().append("No es posible actualizar la direccion ").append(direccion.getId()).toString());
			} 

			direc =  findById(connection, direccion.getId());


		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Actualizando la direccion ").append(direccion.getId()).toString(),se);
		} finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}

		return direc;
	}
	@Override
	public boolean hardDelete(Connection connection, Long idDireccion) throws DataException {
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( "  DELETE  FROM DIRECCION  WHERE ID_DIRECCION = ? ");

			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setLong(i++, idDireccion);

			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {
				throw new DataException(new StringBuilder().append("No es posible eliminar la direccion ").append(idDireccion).toString());
			}


		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Eliminando la direccion ").append(idDireccion).toString(), se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return true;
	}


}











