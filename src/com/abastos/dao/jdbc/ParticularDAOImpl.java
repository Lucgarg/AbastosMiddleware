package com.abastos.dao.jdbc;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.ContenidoDAO;
import com.abastos.dao.DireccionDAO;
import com.abastos.dao.DireccionDtoDAO;
import com.abastos.dao.ListaDAO;
import com.abastos.dao.ParticularDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.dao.util.DBNullUtils;
import com.abastos.model.Direccion;
import com.abastos.model.DireccionDto;
import com.abastos.model.Particular;
import com.abastos.service.DataException;

public class ParticularDAOImpl implements ParticularDAO {
	private static Logger logger = LogManager.getLogger(ParticularDAOImpl.class);
	private DireccionDtoDAO direccionDto = null;
	private ListaDAO listaDeseosDAO = null;
	private DireccionDAO direccionDAO = null;
	private ContenidoDAO contenidoDAO = null;
	public ParticularDAOImpl() {
		direccionDto = new DireccionDtoDAOImpl();
		listaDeseosDAO = new ListaDAOImpl();
		direccionDAO = new DireccionDAOImpl();
		contenidoDAO = new ContenidoDAOImpl();
	}

	@Override
	public Particular findById(Connection connection ,Long idParticular) throws DataException {
		Particular results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();
			logger.trace("Create statement...");

			sql.append( " SELECT ID_PARTICULAR, CONTRASENA, CORREO_ELECTRONICO, ");
			sql.append(" ALIAS, NUMERO_TELEFONO, NUMERO_MOVIL, ");
			sql.append(" NOMBRE, APELLIDOS, NUMERO_PUNTOS, DATA_ALTA ");
			sql.append(" FROM PARTICULAR ");
			sql.append(" WHERE ID_PARTICULAR = ? ");
			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			int i = 1;

			preparedStatement.setLong(i++, idParticular);

			resultSet = preparedStatement.executeQuery();
		
			if (resultSet.next()) {
				results = loadNext(connection,resultSet);
			}

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("no se encuentra el ").append(idParticular).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return results;
	}

	@Override
	public Particular findByEmail(Connection connection ,String email) throws DataException {
		Particular results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();
			logger.trace("Create statement...");
			sql.append( " SELECT ID_PARTICULAR, CONTRASENA, CORREO_ELECTRONICO, ");
			sql.append(" ALIAS, NUMERO_TELEFONO, NUMERO_MOVIL, ");
			sql.append(" NOMBRE, APELLIDOS, NUMERO_PUNTOS, DATA_ALTA ");
			sql.append(" FROM PARTICULAR " );
			sql.append(" WHERE CORREO_ELECTRONICO = ? ");
			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setString(i++,  email);
			resultSet = preparedStatement.executeQuery();


			if (resultSet.next()) {
				results = loadNext(connection, resultSet);
			}

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("No se puede encontrar el email ").append(email).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return results;
	}

	@Override
	public Particular findByAlias(Connection connection ,String alias) throws DataException {

		Particular results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT ID_PARTICULAR, CONTRASENA, CORREO_ELECTRONICO, ");
			sql.append(" ALIAS, NUMERO_TELEFONO, NUMERO_MOVIL, " );
			sql.append(" NOMBRE, APELLIDOS, NUMERO_PUNTOS, DATA_ALTA ");
			sql.append(" FROM PARTICULAR ");
			sql.append(" WHERE ALIAS = ? ");
			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			
			int i = 1;
			preparedStatement.setString(i++, alias);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				results = loadNext(connection, resultSet);
			}
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("No se puede encontrar el alias ").append(alias).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return results;
	}
	@Override
	public Integer findPuntos(Connection connection, Long idParticular) throws DataException {
		Integer results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT  NUMERO_PUNTOS ");
			sql.append(" FROM PARTICULAR ");
			sql.append(" WHERE id_particular = ?  ");
			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			int i = 1;

			preparedStatement.setLong(i++, idParticular);

			resultSet = preparedStatement.executeQuery();
			i =1 ;
			if (resultSet.next()) {
				results = resultSet.getInt(i++);
			}

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("No es posible encontrar los puntos del particular ")
					.append(idParticular).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return results;
	}
	private Particular loadNext(Connection connection ,ResultSet resultSet) throws DataException, SQLException {
		Particular part = new Particular();
		int i = 1;
		part.setId(resultSet.getLong(i++));
		part.setContrasena(resultSet.getString(i++));
		part.setEmail(resultSet.getString(i++));
		part.setAlias(resultSet.getString(i++));
		part.setNumeroTelefono(resultSet.getString(i++));
		part.setNumberoMovil(resultSet.getString(i++));
		part.setNombre(resultSet.getString(i++));
		part.setApellidos(resultSet.getString(i++));
		part.setPuntos(resultSet.getInt(i++));
		part.setDataAlta(resultSet.getDate(i++));
		part.setDireccionDto(direccionDto.findByIdPart(connection, part.getId()));

		return part;
	}

	@Override
	public Particular create(Connection connection ,Particular particular) throws DataException {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Particular parti = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " INSERT INTO PARTICULAR(CONTRASENA, ");
			sql.append(" CORREO_ELECTRONICO, ALIAS, NUMERO_TELEFONO, NUMERO_MOVIL, NOMBRE, ");
			sql.append(" APELLIDOS) ");
			sql.append(" VALUES (? , ? , ? , ? , ? , ? , ?) ");

			preparedStatement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setString(i++, particular.getContrasena());
			preparedStatement.setString(i++, particular.getEmail());
			preparedStatement.setString(i++, particular.getAlias());
			DBNullUtils.toNull(preparedStatement, i++, particular.getNumeroTelefono());
			DBNullUtils.toNull(preparedStatement, i++, particular.getNumberoMovil());
			preparedStatement.setString(i++, particular.getNombre());
			preparedStatement.setString(i++, particular.getApellidos());
			

			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			parti = new Particular();
			i = 1;
			if(resultSet.next()) {
				parti.setId(resultSet.getLong(i++));
			}
			particular.setId(parti.getId());
			createDireccionParticular(connection, particular);

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("No se puede crear particular ")
					.append(particular.getId()).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return parti;
	}
	private void createDireccionParticular(Connection connection, Particular particular)throws DataException{
		PreparedStatement preparedStatement = null;
		Direccion direccion = null;
		StringBuilder sql=null;
		try {
			
			for(DireccionDto direct : particular.getDireccionDto()) {
				sql=new StringBuilder();
				direccion = direccionDAO.createDireccion(connection, direct);
				logger.trace("Create statement...");
				sql.append( " INSERT INTO PARTICULAR_DIRECCION(ID_PARTICULAR, ID_DIRECCION) ");
				sql.append(" VALUES(?, ?) ");
				preparedStatement = connection.prepareStatement(sql.toString());
				logger.trace(sql.toString());
				int i = 1;
				preparedStatement.setLong(i++, particular.getId());
				preparedStatement.setLong(i++, direccion.getId());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("No se pudo crear la dirección del particular ")
					.append(particular.getId()).toString(),se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public Particular update(Connection connection ,Particular particular) throws DataException {
		PreparedStatement preparedStatement = null;
		Particular part = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");
			sql.append( " UPDATE PARTICULAR SET CONTRASENA = ? , ");
			sql.append(" CORREO_ELECTRONICO = ? , ALIAS = ? , NUMERO_TELEFONO = ?, ");
			sql.append(" NUMERO_MOVIL = ? , NOMBRE = ? , APELLIDOS = ? , NUMERO_PUNTOS = ?, ");
			sql.append(" ID_CONTENIDO = ?   WHERE ID_PARTICULAR = ? ");
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setString(i++, particular.getContrasena());
			DBNullUtils.toNull(preparedStatement, i++, particular.getEmail());
			DBNullUtils.toNull(preparedStatement, i++, particular.getAlias());
			preparedStatement.setString(i++, particular.getNumeroTelefono());
			preparedStatement.setString(i++, particular.getNumberoMovil());
			preparedStatement.setString(i++, particular.getNombre());
			preparedStatement.setString(i++, particular.getApellidos());
			preparedStatement.setInt(i++, particular.getPuntos());
			DBNullUtils.toNull(preparedStatement, i++, particular.getIdContenido());
			preparedStatement.setLong(i++, particular.getId());

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new DataException(new StringBuilder().append("No se ha podido actualizar el usuario particular ")
						.append(particular.getId()).toString());
			} 

			deleteDireccionParticular(connection, particular);
			createDireccionParticular(connection, particular);

			
			part = findById(connection, particular.getId());

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Actualizando al particular ").append(particular.getId()).toString(),se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return part;
	}
	@Override
	public boolean updatePuntos(Connection connection, Long idParticular, Integer puntos) throws DataException {
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " UPDATE PARTICULAR SET  NUMERO_PUNTOS = ? ");
			sql.append(" WHERE ID_PARTICULAR = ? ");

			preparedStatement = connection.prepareStatement(sql.toString());
			
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setInt(i++, puntos);
			preparedStatement.setLong(i++, idParticular);
		
			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new DataException(new StringBuilder().append("No se ha podido actualizar el numero de puntos del usuario particular ")
						.append(idParticular).toString());
			} 

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Actualizando puntos del particular ")
					.append(idParticular).toString(), se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}

		return true;
	}
	@Override
	public boolean updateAlta(Connection connection, Long idParticular) throws DataException {
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " UPDATE PARTICULAR SET  DATA_ALTA = ? ");
			sql.append(" WHERE ID_PARTICULAR = ? ");

			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.setLong(i++, idParticular);

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new DataException(new StringBuilder().append("No se ha podido dar de alta al usuario particular ")
						.append(idParticular).toString());
			} 

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("No es posible actualizar el estado de alta del particular ")
					.append(idParticular).toString(), se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return true;

	}

	@Override
	public boolean softDelete(Connection connection ,Long idParticular) throws DataException{
		PreparedStatement preparedStatement = null;
		Particular particular = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();
			listaDeseosDAO.delete(connection, idParticular);
			particular = findById(connection, idParticular);
			deleteDireccionParticular(connection, particular);

			logger.trace("Create statement...");

			sql.append( " UPDATE PARTICULAR SET DATA_BAJA = ?, id_contenido = null WHERE ID_PARTICULAR = ?  ");

			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.setLong(i++, idParticular);

			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {
				throw new SQLException(new StringBuilder().append("No se ha podido encontrar el usuario particular ")
						.append(idParticular).toString());
			}
			if(particular.getIdContenido() != null) {
				contenidoDAO.hardDelete(connection, particular.getIdContenido());
			}
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Dando de baja al particular ").append(idParticular).toString(), se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return true;

	}
	private void deleteDireccionParticular(Connection connection, Particular particular)throws DataException{
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			
			for(DireccionDto dir : particular.getDireccionDto()) {
				sql=new StringBuilder();
				logger.trace("Create statement...");
				sql.append( "DELETE FROM PARTICULAR_DIRECCION WHERE ID_PARTICULAR = ? ") ;
				preparedStatement = connection.prepareStatement(sql.toString());
				logger.trace(sql.toString());
				int i = 1;
				preparedStatement.setLong(i++, particular.getId());
				preparedStatement.executeUpdate();			
				direccionDAO.hardDelete(connection, dir.getIdDireccion());

			}
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Eliminando la direccion del particular ")
					.append(particular.getId()).toString(),se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);

		}

	}







}
