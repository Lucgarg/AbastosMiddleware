package com.abastos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.PuntuacionProductoDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.service.DataException;



public class PuntuacionProductoDAOImpl implements PuntuacionProductoDAO {
	private static Logger logger = LogManager.getLogger(PuntuacionProductoDAOImpl.class);
	@Override
	public List<Integer> findByIdProducto(Connection connection ,Long idProducto) throws DataException {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		List<Integer> puntProduc = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT PUNTUACION_PRODUCTO  FROM ");
			sql.append(" PARTICULAR_VALORA_PRODUCTO ");
			sql.append(" WHERE ID_PRODUCTO = ? ORDER BY FECHA_VALORACION ASC ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());

			int i = 1;
			
			preparedStatement.setLong(i++, idProducto);
			Integer punt = null;
			puntProduc = new ArrayList<Integer>();
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				punt = resultSet.getInt(1);
				puntProduc.add(punt);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando las puntuaciones del producto ").append(idProducto).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		} 
		return puntProduc;
	}
	@Override
	public List<Integer> findByIdParticular(Connection connection ,Long idParticular) throws DataException {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<Integer> puntProduc = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");
			sql.append( " SELECT  PUNTUACION_PRODUCTO FROM ");
			sql.append(" PARTICULAR_VALORA_PRODUCTO ");
			sql.append(" WHERE ID_PARTICULAR = ? ORDER BY FECHA_VALORACION ASC ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			Integer punt = null;
			int i = 1;

			preparedStatement.setLong(i++, idParticular);

			puntProduc = new ArrayList<Integer>();
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				punt = resultSet.getInt(1);
				puntProduc.add(punt);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando las puntuaciones del particular ")
					.append(idParticular).toString(),se);
		} 
		finally {
			ConnectionManager.close(resultSet, preparedStatement);
		} 
		return puntProduc;
	}
	@Override
	public Double findMedia(Connection connection ,Long idProducto)throws DataException {
		Double media = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT AVG(PUNTUACION_PRODUCTO) ");
			sql.append(" FROM PARTICULAR_VALORA_PRODUCTO ");
			sql.append(" WHERE ID_PRODUCTO = ? ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());

			int i = 1;
			preparedStatement.setLong(i++, idProducto);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				media = resultSet.getDouble(1);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando la media de puntuacion del producto ")
					.append(idProducto).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return media;
	}

	@Override
	public void create(Connection connection ,Long idParticular, Long idProducto, Integer puntuPro)throws DataException {
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          

			logger.trace("Create statement...");

			sql.append( " INSERT INTO PARTICULAR_VALORA_PRODUCTO ");
			sql.append(" (ID_PARTICULAR, ID_PRODUCTO, PUNTUACION_PRODUCTO, FECHA_VALORACION) ");
			sql.append(" VALUE( ? , ? , ?, ?) "); 

			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());

			int i = 1;             

			preparedStatement.setLong(i++, idParticular);
			preparedStatement.setLong(i++, idProducto);
			preparedStatement.setLong(i++, puntuPro);
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.executeUpdate();

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Creando la puntuacion del producto ")
					.append(idProducto).append(" por el particular ").append(idParticular).toString(), se);
		}finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}

	}
	@Override
	public boolean update(Connection connection ,Long idParticular, Long idProducto, Integer puntuPro)throws DataException {
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          
			logger.trace("Create statement...");
			sql.append( " UPDATE PARTICULAR_VALORA_PRODUCTO SET PUNTUACION_PRODUCTO = ?  ");
			sql.append(" WHERE ID_PARTICULAR = ? AND ID_PRODUCTO = ? ");

			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());

			int i = 1;    
			preparedStatement.setInt(i++, puntuPro);
			preparedStatement.setLong(i++, idParticular);
			preparedStatement.setLong(i++, idProducto);

			int updatedRows = preparedStatement.executeUpdate();
			if (updatedRows != 1) {
			
				throw new DataException(	
						new StringBuilder().append("No se puede actualizar la puntuacion del producto ")
						.append(idProducto)
						.append(" por el particular ")
						.append(idParticular).toString());
			} 

		}catch (SQLException se) {
			logger.error(se);
		
			throw new DataException(new StringBuilder().append("Actualizando la puntuacion del producto del particular ")
					.append(idParticular)
					.append(" del producto ")
					.append(idProducto).toString(), se);
		} 
		finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return true;

	}	

	@Override
	public boolean delete(Connection connection ,Long idParticular, Long idProducto) throws DataException{
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          

			logger.trace("Create statement...");

			sql.append( " DELETE FROM PARTICULAR_VALORA_PRODUCTO WHERE   "); 

			boolean first = false;
			if(idProducto != null) {
				sql.append( "  id_producto = ? ");
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
			if(idProducto != null) {
				preparedStatement.setLong(i++, idProducto);
			}
			if(idParticular != null) {
				preparedStatement.setLong(i++, idParticular);
			}

			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {
			
				throw new DataException(new StringBuilder().append("No se puede eliminar la puntuacion del producto ")
						.append(idProducto)
						.append(" del particular ")
						.append(idParticular).toString());
			} 


		}catch (SQLException se) {
			logger.error(se);
			
			throw new DataException(new StringBuilder().append("Eliminando la puntuacion del producto ")
					.append(idProducto)
					.append(" del particular ")
					.append(idParticular).toString(),se);
		}  
		finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return true;

	}



}
