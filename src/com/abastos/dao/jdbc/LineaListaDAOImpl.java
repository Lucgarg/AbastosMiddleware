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

import com.abastos.dao.LineaListaDAO;
import com.abastos.dao.ProductoDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.LineaLista;
import com.abastos.model.Producto;
import com.abastos.service.DataException;
import com.abastos.service.impl.LineaListaServiceImpl;


public class LineaListaDAOImpl  implements LineaListaDAO{
	private static Logger logger = LogManager.getLogger(LineaListaServiceImpl.class);
	public LineaListaDAOImpl() {
	}
	@Override
	public List<LineaLista> findByIdListaDeseos(Connection connection ,Long idLista)throws DataException {

		LineaLista results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<LineaLista> listaPedido = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( "SELECT ADD_DATE, id_lista, ID_PRODUCTO, nombre_producto, precio ");
			sql.append("FROM LINEA_LISTA ");
			sql.append(" WHERE ID_LISTA = ? ORDER BY ADD_DATE ASC ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
		
			int i = 1;

			preparedStatement.setLong(i++, idLista);

			listaPedido = new ArrayList<LineaLista>();
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				results = loadNext(resultSet);
				listaPedido.add(results);
			}

		}catch (SQLException se) {
		
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando lineas de la lista ").append(idLista).toString(),se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		} 

		return listaPedido;
	}

	@Override
	public LineaLista findById(Connection connection ,Long idLista, Long idProducto)throws DataException {
		LineaLista results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT  ADD_DATE, id_lista, ID_PRODUCTO, nombre_producto, precio ");
			sql.append("FROM LINEA_LISTA ");

			boolean isFirst = false;
			if(idLista !=null) {
				sql.append( " where id_lista = ? ");
				isFirst = true;
			}
			if(idProducto != null) {

				sql.append( isFirst == false? " WHERE ID_PRODUCTO = ? ": " AND ID_PRODUCTO = ? ");
			}
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			
			int i = 1;
			if(idLista != null) {
				preparedStatement.setLong(i++, idLista);
			}
			if(idProducto !=null) {
				preparedStatement.setLong(i++, idProducto);
			}

			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				results = loadNext(resultSet);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("idLista ").append(idLista)
					.append(" no encontrado o idProducto ").append(idProducto).append(" no encontrado").toString(),se );
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		} 
		return results;
	}

	private LineaLista loadNext(ResultSet resultSet)throws SQLException{
		int i = 1;
		LineaLista lineaLista = new LineaLista();
		lineaLista.setAddDate(resultSet.getDate(i++));
		lineaLista.setId(resultSet.getLong(i++));
		lineaLista.setIdProducto(resultSet.getLong(i++));
		lineaLista.setNombreProducto(resultSet.getString(i++));
		lineaLista.setPrecio(resultSet.getDouble(i++));
		return lineaLista;

	}
	@Override
	public void create(Connection connection ,LineaLista lineaList)throws DataException {
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          

			logger.trace("Create statement...");

			sql.append( " insert into linea_lista (add_date, id_lista, id_producto, nombre_producto, precio) ");
			sql.append("value (? , ? , ?, ? , ? ) "); 

			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());

			int i = 1;             
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.setLong(i++, lineaList.getId());
			preparedStatement.setLong(i++, lineaList.getIdProducto());
			preparedStatement.setString(i++, lineaList.getNombreProducto());
			preparedStatement.setDouble(i++, lineaList.getPrecio());
			preparedStatement.executeUpdate();

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Creando lineaLista de la lista ")
					.append(lineaList.getId()).append("con el producto ")
					.append(lineaList.getIdProducto()).toString(),se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}

	}
	@Override
	public boolean update(Connection connection ,LineaLista lineaList)throws DataException {
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();       

			logger.trace("Create statement...");

			sql.append( " UPDATE LINEA_LISTA SET ID_PRODUCTO = ?");
			sql.append(" , NOMBRE_PRODUCTO = ? , PRECIO = ?  WHERE ID_LISTA = ? ");
			sql.append(" AND ID_PRODUCTO = ? ");

			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());

			int i = 1;    

			preparedStatement.setLong(i++, lineaList.getIdProducto());
			preparedStatement.setString(i++, lineaList.getNombreProducto());
			preparedStatement.setDouble(i++, lineaList.getPrecio());
			preparedStatement.setLong(i++, lineaList.getId());
			preparedStatement.setLong(i++, lineaList.getIdProducto());

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new DataException(new StringBuilder().append("No se puede actualizar la linea de lista ")
						.append(lineaList.getId()).append("del producto ").append(lineaList.getIdProducto()).toString());
			} 

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}

		return true;
	}

	@Override
	public boolean delete(Connection connection ,Long idLista, Long idProducto) throws DataException{
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          

			logger.trace("Create statement...");

			sql.append( " DELETE FROM LINEA_LISTA ");

			Boolean isFirst = false;
			if(idLista != null) {
				sql.append( " WHERE ID_LISTA = ? ");
				isFirst = true;
			}
			if(idProducto != null) {
				if(isFirst == true) {
					sql.append( " AND ID_PRODUCTO = ? ");
				}
				else {
					sql.append( " WHERE ID_PRODUCTO = ? ");
				}
			}
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			if(idLista != null) {
				preparedStatement.setLong(i++, idLista);
			}
			if(idProducto != null) {
				preparedStatement.setLong(i++, idProducto);
			}
			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {
				throw new DataException(new StringBuilder().append("No se encuentra la lista o el producto")
						.append(idLista).append(" ").append(idProducto).toString());
			} 

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		} finally {
			ConnectionManager.closePreparedStatement( preparedStatement);
		}
		return true;

	}
	
	@Override
	public boolean deleteByIdParticular(Connection connection ,Long idParticular) throws DataException {
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();   


			logger.trace("Create statement...");

			sql.append( " DELETE B  FROM LINEA_LISTA  B INNER JOIN ");
			sql.append(" LISTA_DESEOS A ON A.ID_LISTA = B.ID_LISTA ");
			sql.append(" WHERE A.ID_PARTICULAR =  ? "); 

			preparedStatement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			logger.trace(sql.toString());

			preparedStatement.setLong(1, idParticular);

			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {
				throw new DataException(new StringBuilder().append("No se puede eliminar por el id del particular ")
						.append(idParticular).toString());
			} 

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Eliminando lineaLista").append(idParticular).toString(),se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return true;
	}



}
