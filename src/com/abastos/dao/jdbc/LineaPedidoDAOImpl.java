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

import com.abastos.dao.LineaPedidoDAO;

import com.abastos.dao.util.ConnectionManager;
import com.abastos.dao.util.DBNullUtils;
import com.abastos.model.LineaPedido;

import com.abastos.service.DataException;

public class LineaPedidoDAOImpl implements LineaPedidoDAO{
	private static Logger logger = LogManager.getLogger(LineaPedidoDAOImpl.class);
	public LineaPedidoDAOImpl() {

	}
	@Override
	public List<LineaPedido> findByIdPedido(Connection connection ,Long idPedido) throws DataException{

		LineaPedido results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		List<LineaPedido> listaPedido = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT ID_PEDIDO, ID_PRODUCTO, ");
			sql.append(" NUMERO_UNIDADES, ADD_DATE, PRECIO, ");
			sql.append(" ID_OFERTA, DESCUENTO_PCN, DESCUENTO_FIJO, NUMERADOR, ");
			sql.append(" DENOMINADOR, PRECIO_FINAL, NOMBRE_PRODUCTO, ");
			sql.append(" ID_TIPO_OFERTA, ID_PRODUCTO_OFERTA, ID_TIENDA  ");
			sql.append(" FROM LINEA_PEDIDO  ");
			sql.append(" WHERE ID_PEDIDO = ? ORDER BY ADD_DATE ASC ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());

			int i = 1;

			preparedStatement.setLong(i++, idPedido);

			listaPedido = new ArrayList<LineaPedido>();
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				results = loadNext(resultSet);
				listaPedido.add(results);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando pedido ").append(idPedido).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		} 
		return listaPedido;
	}

	@Override
	public LineaPedido findById(Connection connection ,Long idPed, Long idProd) throws DataException{

		LineaPedido results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");


			sql.append( " SELECT ID_PEDIDO, ID_PRODUCTO, ");
			sql.append(" NUMERO_UNIDADES, ADD_DATE, PRECIO, ");
			sql.append(" ID_OFERTA, DESCUENTO_PCN, DESCUENTO_FIJO, NUMERADOR, ");
			sql.append(" DENOMINADOR, PRECIO_FINAL, NOMBRE_PRODUCTO , ");
			sql.append(" ID_TIPO_OFERTA, ID_PRODUCTO_OFERTA,  ID_TIENDA ");
			sql.append(" FROM LINEA_PEDIDO  ");

			boolean isFirst = false;
			if(idPed !=null) {
				sql.append( " where id_pedido = ? ");
				isFirst = true;
			}
			if(idProd != null) {

				sql.append( isFirst == false? " WHERE ID_producto = ? ": " AND ID_producto = ? ");
			}
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());

			int i = 1;
			if(idPed != null) {
				preparedStatement.setLong(i++, idPed);
			}
			if(idProd != null) {
				preparedStatement.setLong(i++, idProd);
			}
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				results = loadNext(resultSet);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Pedido ").append(idPed).append("no encontrado o ").append(idProd)
					.append(" no encontrado").toString(),se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		} 
		return results;
	}
	private LineaPedido loadNext(ResultSet resultSet)throws SQLException{
		int i = 1;
		LineaPedido lineaPedido = new LineaPedido();
		lineaPedido.setIdPedido(resultSet.getLong(i++));
		lineaPedido.setIdProducto(resultSet.getLong(i++));
		lineaPedido.setNumeroUnidades(resultSet.getInt(i++));
		lineaPedido.setAddDate(resultSet.getDate(i++));
		lineaPedido.setPrecio(resultSet.getDouble(i++));
		lineaPedido.setIdOferta(resultSet.getLong(i++));
		lineaPedido.setDescuentoPcn(resultSet.getDouble(i++));
		lineaPedido.setDescuentoFijo(resultSet.getDouble(i++));
		lineaPedido.setNumerador(resultSet.getInt(i++));
		lineaPedido.setDenominador(resultSet.getInt(i++));
		lineaPedido.setPrecioFinal(resultSet.getDouble(i++));
		lineaPedido.setNombreProducto(resultSet.getString(i++));
		lineaPedido.setIdTipoOferta(resultSet.getInt(i++));
		lineaPedido.setIdProdOferta(resultSet.getLong(i++));
		lineaPedido.setIdTienda(resultSet.getLong(i++));
		return lineaPedido;



	}
	@Override
	public void create(Connection connection ,LineaPedido lineaPedido)throws DataException {
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();      

			logger.trace("Create statement...");

			sql.append( " INSERT INTO LINEA_PEDIDO(ID_PRODUCTO, NUMERO_UNIDADES, ");
			sql.append(" ID_PEDIDO, ADD_DATE, PRECIO, PRECIO_FINAL, nombre_producto , ID_TIENDA ");
			if(lineaPedido.getIdOferta() != null) {
				sql.append( " , ID_OFERTA, ");
				sql.append(" DESCUENTO_PCN, DESCUENTO_FIJO, NUMERADOR, DENOMINADOR, ID_TIPO_OFERTA, ID_PRODUCTO_OFERTA ) "); 
				sql.append(" VALUE(?, ? , ? , ?, ? , ?, ? , ? , ? , ?, ?, ?, ?, ?, ?) "); 
			}
			else {
				sql.append(  " ) VALUE(?, ? , ? , ?, ?, ?, ?, ? ) "); 
			}


			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());

			int i = 1;
			preparedStatement.setLong(i++, lineaPedido.getIdProducto());
			preparedStatement.setInt(i++, lineaPedido.getNumeroUnidades());
			preparedStatement.setLong(i++, lineaPedido.getIdPedido());
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.setDouble(i++, lineaPedido.getPrecio());
			preparedStatement.setDouble(i++, lineaPedido.getPrecioFinal());
			preparedStatement.setString(i++, lineaPedido.getNombreProducto());
			preparedStatement.setLong(i++, lineaPedido.getIdTienda());
			if(lineaPedido.getIdOferta() !=null) {
				preparedStatement.setLong(i++, lineaPedido.getIdOferta());
				DBNullUtils.toNull(preparedStatement, i++, lineaPedido.getDescuentoPcn());
				DBNullUtils.toNull(preparedStatement, i++, lineaPedido.getDescuentoFijo());
				DBNullUtils.toNull(preparedStatement, i++,	lineaPedido.getNumerador());
				DBNullUtils.toNull(preparedStatement, i++, lineaPedido.getDenominador());
				DBNullUtils.toNull(preparedStatement, i++, lineaPedido.getIdTipoOferta());
				DBNullUtils.toNull(preparedStatement, i++, lineaPedido.getIdProdOferta());
			}
			logger.info(preparedStatement.toString());
			preparedStatement.executeUpdate();

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Creando lineaPedido ").append(lineaPedido.getIdPedido())
					.append(" ").append(lineaPedido.getIdProducto()).toString(), se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}

	}
	@Override
	public boolean delete(Connection connection ,Long idPedido, Long idProducto) throws DataException {
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          

			logger.trace("Create statement...");

			sql.append( " DELETE FROM LINEA_PEDIDO WHERE ID_PEDIDO = ? AND ID_PRODUCTO = ? "); 
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setLong(i++, idPedido);
			preparedStatement.setLong(i++, idProducto);

			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {
				throw new DataException(new StringBuilder().append("no se puede eliminar la linea pedido ").append(idPedido)
						.append(" ").append(idProducto).toString());
			} 
		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Eliminando la linea pedido ").append(idPedido).append(" ")
					.append(idProducto).toString(),se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return true;

	}





}




