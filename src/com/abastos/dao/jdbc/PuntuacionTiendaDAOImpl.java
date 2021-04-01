package com.abastos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.PuntuacionTiendaDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.PuntuacionMediaTienda;
import com.abastos.model.PuntuacionTienda;
import com.abastos.service.DataException;

public class PuntuacionTiendaDAOImpl implements PuntuacionTiendaDAO {
	private static Logger logger = LogManager.getLogger(PuntuacionTiendaDAOImpl.class);

	@Override
	public List<PuntuacionTienda> findByIdTienda(Connection connection ,Long idTienda)throws DataException {
		PuntuacionTienda results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<PuntuacionTienda> puntTienda = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT ID_PARTICULAR, ID_TIENDA, PUNTUACION_ATENCION_CLIENTE, ");
			sql.append(" PUNTUACION_PRECIO, PUNTUACION_SERVICIO_DOMICILIO, FECHA_VALORACION");
			sql.append(" FROM PARTICULAR_VALORA_TIENDA ");
			sql.append(" WHERE ID_TIENDA = ? ORDER BY FECHA_VALORACION ASC ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			results = new PuntuacionTienda();
			int i = 1;

			preparedStatement.setLong(i++, idTienda);

			puntTienda = new ArrayList<PuntuacionTienda>();
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				results = loadNextPuntTi(connection, resultSet);
				puntTienda.add(results);
			}

		}catch (SQLException se) {
			logger.error(se);
			
			throw new DataException(new StringBuilder().append("Buscando las puntuaciones de la tienda ")
					.append(idTienda).toString(),se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		} 
		return puntTienda;
	}
	@Override
	public List<PuntuacionTienda> findByIdParticular(Connection connection ,Long idParticular) throws DataException{
		PuntuacionTienda results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<PuntuacionTienda> puntTienda = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT ID_PARTICULAR, ID_TIENDA, PUNTUACION_ATENCION_CLIENTE, ");
			sql.append(" PUNTUACION_PRECIO, PUNTUACION_SERVICIO_DOMICILIO, FECHA_VALORACION ");
			sql.append(" FROM PARTICULAR_VALORA_TIENDA ");
			sql.append(" WHERE ID_PARTICULAR = ? ORDER BY FECHA_VALORACION ASC ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			results = new PuntuacionTienda();
			int i = 1;

			preparedStatement.setLong(i++, idParticular);

			puntTienda = new ArrayList<PuntuacionTienda>();
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				results = loadNextPuntTi(connection, resultSet);
				puntTienda.add(results);
			}

		}catch (SQLException se) {
			logger.error(se);
			
			throw new DataException( new StringBuilder().append("Buscando las puntuaciones del particular ")
					.append(idParticular).toString(), se);
		} finally {
			ConnectionManager.close(resultSet, preparedStatement);
		} 
		return puntTienda;
	}
	@Override
	public PuntuacionTienda findPuntuacion(Connection connection ,Long idParticular, Long idTienda) throws DataException{
		PuntuacionTienda results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT ID_PARTICULAR, ID_TIENDA, PUNTUACION_ATENCION_CLIENTE, ");
			sql.append(" PUNTUACION_PRECIO, PUNTUACION_SERVICIO_DOMICILIO, FECHA_VALORACION ");
			sql.append(" FROM PARTICULAR_VALORA_TIENDA ");
			sql.append(" WHERE ID_PARTICULAR = ? AND ID_TIENDA = ? ORDER BY FECHA_VALORACION ASC ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			results = new PuntuacionTienda();
			int i = 1;

			preparedStatement.setLong(i++, idParticular);
			preparedStatement.setLong(i++, idTienda);

			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				results = loadNextPuntTi(connection, resultSet);
				
			}

		}catch (SQLException se) {
			logger.error(se);
			
			throw new DataException( new StringBuilder().append("Buscando las puntuaciones del particular ")
					.append(idParticular).toString(), se);
		} finally {
			ConnectionManager.close(resultSet, preparedStatement);
		} 
		return results;
	}



	@Override
	public PuntuacionMediaTienda findMedia(Connection connection ,Long idTienda) throws DataException{
		PuntuacionMediaTienda results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT ID_TIENDA, AVG(PUNTUACION_ATENCION_CLIENTE),");
			sql.append(" AVG(PUNTUACION_PRECIO), ");
			sql.append(" AVG(PUNTUACION_SERVICIO_DOMICILIO) ");
			sql.append(" FROM PARTICULAR_VALORA_TIENDA ");
			sql.append(" WHERE ID_TIENDA = ?  ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			
			int i = 1;

			preparedStatement.setLong(i++, idTienda);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				results = loadNextPuntTiMe(connection, resultSet);
			}

		}catch (SQLException se) {
			logger.error(se);
		
			throw new DataException(new StringBuilder().append("Buscando las puntuaciones media de la tienda ")
					.append(idTienda).toString(), se);
		} 
		finally {
			ConnectionManager.close(resultSet, preparedStatement);
		} 
		return results;
	}

	private Double findMediaTotal(Connection connection ,Long idTienda) throws DataException{
		Double media = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT ((AVG(PUNTUACION_ATENCION_CLIENTE ");
			sql.append(" + PUNTUACION_PRECIO + PUNTUACION_SERVICIO_DOMICILIO))/3) ");
			sql.append(" FROM PARTICULAR_VALORA_TIENDA ");
			sql.append(" WHERE ID_TIENDA = ? ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setLong(i++, idTienda);

			resultSet = preparedStatement.executeQuery();
			i = 1;
			if(resultSet.next()) {

				if(resultSet.getString(i++) == null) {
					ConnectionManager.close(resultSet, preparedStatement);
					sql = new StringBuilder();
					sql.append( " SELECT ((AVG(PUNTUACION_ATENCION_CLIENTE ");
					sql.append(" + PUNTUACION_PRECIO))/2) ");
					sql.append(" FROM PARTICULAR_VALORA_TIENDA ");
					sql.append(" WHERE ID_TIENDA = ? ");
					preparedStatement = connection.prepareStatement
							(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					logger.trace(sql.toString());
					i = 1;
					preparedStatement.setLong(i++, idTienda);
					resultSet = preparedStatement.executeQuery();
					if(resultSet.next()) {
						media = resultSet.getDouble(1);
					}
				}
				else {
					media = resultSet.getDouble(1);
				}
			}

		}catch (SQLException se) {
			logger.error(se);
			
			throw new DataException(new StringBuilder()
					.append("Buscando la puntuacion media absoluta de la tienda ")
					.append(idTienda).toString(), se);
		}  
		finally {
			ConnectionManager.close(resultSet, preparedStatement);

		}
		return media;
	}


	private PuntuacionTienda loadNextPuntTi(Connection connection ,ResultSet resultSet) throws SQLException, DataException {
		int i = 1;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();
			PuntuacionTienda puntTienda  = new PuntuacionTienda();
			puntTienda.setIdPerfilParticular(resultSet.getLong(i++));
			puntTienda.setIdTienda(resultSet.getLong(i++));
			puntTienda.setValoracionAtncCliente(resultSet.getInt(i++));
			puntTienda.setValoracionPrecio(resultSet.getInt(i++));
			puntTienda.setValoracionServDomicilio(resultSet.getInt(i++));
			puntTienda.setFechaValoracion(resultSet.getTimestamp(i++));
			return puntTienda;
		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
	}
	private PuntuacionMediaTienda loadNextPuntTiMe(Connection connection ,ResultSet resultSet) throws SQLException, DataException {
		int i = 1;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();
			PuntuacionMediaTienda puntTienda  = new PuntuacionMediaTienda();
			puntTienda.setIdTienda(resultSet.getLong(i++));
			puntTienda.setValoracionAtncClienteMedia(resultSet.getDouble(i++));
			puntTienda.setValoracionPrecioMedia(resultSet.getDouble(i++));
			puntTienda.setValoracionServDomicilioMedia(resultSet.getDouble(i++));
			puntTienda.setValoracionMedia(findMediaTotal(connection, puntTienda.getIdTienda()));
			return puntTienda;
		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
	}

	@Override
	public void create(Connection connection ,PuntuacionTienda puntTienda) throws DataException{
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          

			logger.trace("Create statement...");

			sql.append( " INSERT INTO PARTICULAR_VALORA_TIENDA ");
			sql.append(" (ID_PARTICULAR, ID_TIENDA, PUNTUACION_ATENCION_CLIENTE, PUNTUACION_PRECIO");
			sql.append(" , PUNTUACION_SERVICIO_DOMICILIO, fecha_valoracion ) ");
			sql.append(" VALUE( ? , ? , ? , ?, ?, ? ) "); 

			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());

			int i = 1;             

			preparedStatement.setLong(i++, puntTienda.getIdPerfilParticular());
			preparedStatement.setLong(i++, puntTienda.getIdTienda());
			preparedStatement.setInt(i++, puntTienda.getValoracionAtncCliente());
			preparedStatement.setInt(i++, puntTienda.getValoracionPrecio());
			preparedStatement.setInt(i++, puntTienda.getValoracionServDomicilio());
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.executeUpdate();

		}catch (SQLException se) {
			logger.error(se);
			 
			throw new DataException(new StringBuilder().append("Creando la puntuacion de la tienda ")
					.append(puntTienda.getIdTienda())
					.append(" del ")
					.append(puntTienda.getIdPerfilParticular()).toString(), se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}

	}


	@Override
	public boolean update(Connection connection ,PuntuacionTienda puntTienda) throws DataException{
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          

			logger.trace("Create statement...");

			sql.append( " UPDATE PARTICULAR_VALORA_TIENDA SET PUNTUACION_ATENCION_CLIENTE = ? ,");
			sql.append(" PUNTUACION_PRECIO = ? , PUNTUACION_SERVICIO_DOMICILIO = ? ");
			sql.append(" WHERE ID_PARTICULAR = ? AND ID_TIENDA = ? ");
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setInt(i++, puntTienda.getValoracionAtncCliente());
			preparedStatement.setInt(i++, puntTienda.getValoracionPrecio());
			preparedStatement.setInt(i++, puntTienda.getValoracionServDomicilio());
			preparedStatement.setLong(i++, puntTienda.getIdPerfilParticular());
			preparedStatement.setLong(i++, puntTienda.getIdTienda());

			int updatedRows = preparedStatement.executeUpdate();
			if (updatedRows != 1) {
				 
				throw new DataException( new StringBuilder().append("no se puede actualizar la puntuacion de la tienda ")
						.append(puntTienda.getIdTienda())
						.append(" del ")
						.append(puntTienda.getIdPerfilParticular()).toString());
			} 

		}catch (SQLException se) {
			logger.error(se);
			 
			throw new DataException( new StringBuilder()
			.append("Actualizando la puntuacion de la tienda ")
			.append(puntTienda.getIdTienda())
			.append(" del ")
			.append(puntTienda.getIdPerfilParticular()).toString(), se);
		}  
		finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}

		return true;

	}

	@Override
	public boolean delete(Connection connection ,Long idParticular, Long idTienda) throws DataException{
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          

			logger.trace("Create statement...");

			sql.append( " delete from particular_valora_tienda where  "); 
			boolean first = false;
			if(idTienda != null) {
				sql.append( "  id_tienda = ? ");
				first = true;
			}
			if(idParticular != null) {
				if(first != false) {
					sql.append( " And id_particular = ? ");
				}
				else {
					sql.append( " id_particular = ? ");
				}
			}
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
		
			int i = 1;
			if(idTienda != null) {
				preparedStatement.setLong(i++, idTienda);
			}
			if(idParticular != null) {
				preparedStatement.setLong(i++, idParticular);
			}

			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {
				
				throw new DataException( new StringBuilder().append("no se puede actualizar la puntuacion de la tienda ")
						.append(idTienda)
						.append(" del ")
						.append(idParticular).toString());
			} 


		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return true;

	}


}


