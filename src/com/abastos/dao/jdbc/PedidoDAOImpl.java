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

import com.abastos.dao.LineaPedidoDAO;
import com.abastos.dao.PedidoDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.LineaPedido;
import com.abastos.model.Pedido;
import com.abastos.service.DataException;

public class PedidoDAOImpl implements PedidoDAO {
	private static Logger logger = LogManager.getLogger(PedidoDAOImpl.class);
	private LineaPedidoDAO lineaPedidoDAO = null;

	public PedidoDAOImpl() {
		lineaPedidoDAO = new LineaPedidoDAOImpl();
	}
	@Override
	public Pedido findById(Connection connection ,Long idPedido)throws DataException {
		Pedido results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT ID_PARTICULAR, ID_PEDIDO, ");
			sql.append(" APLICAR_DESCUENTO, ");
			sql.append(" PRECIO_TOTAL, ");
			sql.append(" FECHA_PEDIDO, ID_ESTADO ");
			sql.append(" FROM PEDIDO ");
			sql.append("  WHERE ID_PEDIDO = ? ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			
			int i = 1;
			preparedStatement.setLong(i++, idPedido);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				results = loadNext(connection, resultSet);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando el pedido ").append(idPedido).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		} 
		return results;
	}

	@Override
	public List<Pedido> findByIdParticular(Connection connection ,Long idParticular) throws DataException {
		Pedido results = null;
		List<Pedido> listPed = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT ID_PARTICULAR, ID_PEDIDO, ");
			sql.append(" APLICAR_DESCUENTO, ");
			sql.append(" PRECIO_TOTAL, ");
			sql.append(" FECHA_PEDIDO, ID_ESTADO ");
			sql.append(" FROM PEDIDO ");
			sql.append("  WHERE ID_PARTICULAR = ? ORDER BY FECHA_PEDIDO ASC ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			
			int i = 1;
			preparedStatement.setLong(i++, idParticular);

			resultSet = preparedStatement.executeQuery();
			listPed = new ArrayList<Pedido>();
			while(resultSet.next()) {
				results = loadNext(connection, resultSet);
				listPed.add(results);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando los pedido del particular ")
					.append(idParticular).toString(), se);
		} finally {
			ConnectionManager.close(resultSet, preparedStatement);
		} 
		return listPed;
	}
	private Pedido loadNext(Connection connection ,ResultSet resultSet)throws DataException, SQLException{
		int i = 1;

		Pedido pedido = new Pedido();
		pedido.setIdParticular(resultSet.getLong(i++));
		pedido.setId(resultSet.getLong(i++));
		pedido.setAplicarDescuento(resultSet.getBoolean(i++));
		pedido.setPrecioTotal(resultSet.getDouble(i++));
		pedido.setFechaPedido( resultSet.getDate(i++));
		pedido.setIdEstado(resultSet.getString(i++).charAt(0));
		pedido.setLineaPedido(lineaPedidoDAO.findByIdPedido(connection, pedido.getId()));
		return pedido;

	}

	@Override
	public Pedido create(Connection connection ,Pedido pedido)throws DataException {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Pedido pedid = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder(); 
			logger.trace("Create statement...");

			sql.append( " INSERT INTO PEDIDO( FECHA_PEDIDO, ID_PARTICULAR, ");
			sql.append(" APLICAR_DESCUENTO, PRECIO_TOTAL, ID_ESTADO) ");
			sql.append(" VALUE(?, ?, ?, ?, ?)"); 

			preparedStatement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			logger.trace(sql.toString());

			int i = 1;
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.setLong(i++, pedido.getIdParticular());
			preparedStatement.setBoolean(i++, pedido.getAplicarDescuento());
			preparedStatement.setDouble(i++, pedido.getPrecioTotal());
			preparedStatement.setString(i++, String.valueOf(pedido.getIdEstado()));
			preparedStatement.executeUpdate();

			resultSet = preparedStatement.getGeneratedKeys();
			pedid = new Pedido();
			i = 1;
			if(resultSet.next()) {
				pedid.setId(resultSet.getLong(i++));
			}
			for(LineaPedido linea: pedido.getLineaPedido()) {
				linea.setIdPedido(pedid.getId());
				lineaPedidoDAO.create(connection, linea);
			}
		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Creando pedido ").append(pedido.getId()).toString(),se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return pedid;
	}
	@Override
	public Pedido update(Connection connection ,Pedido pedido) throws DataException{
		PreparedStatement preparedStatement = null;
		Pedido pedid = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          

			logger.trace("Create statement...");
			
			sql.append( " UPDATE PEDIDO SET FECHA_PEDIDO = ? , PRECIO_TOTAL = ? , ");
			sql.append(" APLICAR_DESCUENTO = ? where id_pedido = ?  ");
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.setDouble(i++, pedido.getPrecioTotal());
			preparedStatement.setBoolean(i++, pedido.getAplicarDescuento());
			preparedStatement.setLong(i++, pedido.getId());
			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new DataException(new StringBuilder().append("No ha sido posible actualizar el pedido ")
						.append(pedido.getId()).toString());
			} 
			
			pedid = findById(connection, pedido.getId());
			for(LineaPedido lin : pedido.getLineaPedido()) {
				lineaPedidoDAO.delete(connection, lin.getIdPedido(), lin.getIdProducto());
				lineaPedidoDAO.create(connection, lin);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Actualizando pedido ").append(pedido.getId()).toString(),se);
		} finally {
			ConnectionManager.closePreparedStatement( preparedStatement);
		}
		return pedid;

	}
	@Override
	public boolean updateEstado(Connection connection ,char estado, Long idPedido) throws DataException {
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();   
			logger.trace("Create statement...");

			sql.append( " UPDATE PEDIDO SET ID_ESTADO = ? WHERE ID_PEDIDO = ? "); 

			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setString(i++, String.valueOf(estado));
			preparedStatement.setLong(i++, idPedido);

			int updateRows = preparedStatement.executeUpdate();

			if (updateRows == 0) {
				throw new DataException(new StringBuilder().append("no se ha podido actualizar el estado del pedido ")
						.append(idPedido).toString());
			} 

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Actualizando estado del pedido ").append(idPedido).toString(),se);
		}  finally {
			ConnectionManager.closePreparedStatement( preparedStatement);
		}
		return true;
	}


}






