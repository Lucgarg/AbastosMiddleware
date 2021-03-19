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

import com.abastos.dao.OfertaDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.dao.util.DBNullUtils;
import com.abastos.model.Oferta;
import com.abastos.service.DataException;


public class OfertaDAOImpl implements OfertaDAO {
	private static Logger logger = LogManager.getLogger(OfertaDAOImpl.class);
	public OfertaDAOImpl() {

	}
	@Override
	public Oferta findById(Connection connection ,Long idOferta) throws DataException {
		Oferta ofert = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT A.ID_OFERTA, A.DESCUENTO_PCN, A.DESCUENTO_FIJO, A.NUMERADOR, A.DENOMINADOR, ");
			sql.append(" A.ID_TIPO_OFERTA, A.ID_PRODUCTO, B.NOMBRE_PRODUCTO, A.NOMBRE_OFERTA, A.FECHA_DESDE, ");
			sql.append(" A.FECHA_HASTA, F.ID_TIPO_OFERTA, F.NOMBRE, A.ID_EMPRESA FROM OFERTA A");
			sql.append(" LEFT JOIN PRODUCTO B ON A.ID_PRODUCTO = B.ID_PRODUCTO");
			sql.append(" INNER JOIN TIPO_OFERTA F ON F.ID_TIPO_OFERTA = A.ID_TIPO_OFERTA ");
			sql.append(" WHERE A.ID_OFERTA = ? AND A.FECHA_DESDE < ? AND A.FECHA_HASTA > ? ");

			preparedStatement = connection.prepareStatement(sql.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setLong(i++, idOferta);
			preparedStatement.setTimestamp(i++,new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.setTimestamp(i++,new java.sql.Timestamp(new Date().getTime()));
			
			resultSet = preparedStatement.executeQuery();

			if(resultSet.next()) {
				ofert = loadNext(resultSet);
			
			}

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando la oferta ").append(idOferta).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}

		return ofert;
	}
	@Override
	public List<Oferta> findByIdEmpresa(Connection connection, Long idEmpresa) throws DataException {
		Oferta ofert = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		List <Oferta> listOferts = null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT A.ID_OFERTA, A.DESCUENTO_PCN, A.DESCUENTO_FIJO, A.NUMERADOR, A.DENOMINADOR, ");
			sql.append(" A.ID_TIPO_OFERTA, A.ID_PRODUCTO, B.NOMBRE_PRODUCTO, A.NOMBRE_OFERTA, A.FECHA_DESDE, ");
			sql.append(" A.FECHA_HASTA, F.ID_TIPO_OFERTA, F.NOMBRE, A.ID_EMPRESA FROM OFERTA A");
			sql.append(" LEFT JOIN PRODUCTO B ON A.ID_PRODUCTO = B.ID_PRODUCTO");
			sql.append(" INNER JOIN TIPO_OFERTA F ON F.ID_TIPO_OFERTA = A.ID_TIPO_OFERTA ");
			sql.append(" WHERE A.ID_EMPRESA = ? AND  A.FECHA_HASTA > ? ORDER BY A.FECHA_DESDE DESC");

			preparedStatement = connection.prepareStatement(sql.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setLong(i++, idEmpresa);
			preparedStatement.setTimestamp(i++,new java.sql.Timestamp(new Date().getTime()));
		
			
			resultSet = preparedStatement.executeQuery();
			listOferts = new ArrayList<Oferta>();
			while(resultSet.next()) {
				ofert = loadNext(resultSet);
				listOferts.add(ofert);
			
			}

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando la oferta de la empresa ").append(idEmpresa).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}

		return listOferts;
	}
	
	private Oferta loadNext(ResultSet resultset) throws SQLException {
		int i = 1;
		Oferta oferta = new Oferta();
		oferta.setId(resultset.getLong(i++));
		oferta.setDescuentoPcn(resultset.getDouble(i++));
		oferta.setDescuentoFijo(resultset.getDouble(i++));
		oferta.setNumerador(resultset.getInt(i++));
		oferta.setDenominador(resultset.getInt(i++));
		oferta.setIdTipoOferta(resultset.getInt(i++));
		oferta.setIdProdOferta(resultset.getLong(i++));
		oferta.setNombreProdOferta(resultset.getString(i++));
		oferta.setNombreOferta(resultset.getString(i++));
		oferta.setFechaDesde(resultset.getTimestamp(i++));
		oferta.setFechaHasta(resultset.getTimestamp(i++));
		oferta.setIdTipoOferta(resultset.getInt(i++));
		oferta.setNombreTipoOfer(resultset.getString(i++));
		oferta.setIdEmpresa(resultset.getLong(i++));
		return oferta;
	}
	@Override
	public Oferta create(Connection connection ,Oferta oferta)throws DataException {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		Oferta ofert = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          

			logger.trace("Create statement...");

			sql.append( " INSERT INTO OFERTA (DESCUENTO_PCN,");
			sql.append(" DESCUENTO_FIJO ,NUMERADOR,");
			sql.append("DENOMINADOR,ID_TIPO_OFERTA,ID_PRODUCTO,");
			sql.append("NOMBRE_OFERTA,FECHA_DESDE, FECHA_HASTA, ID_EMPRESA)");
			sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			preparedStatement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			logger.trace(sql.toString());

			int i = 1;
			DBNullUtils.toNull(preparedStatement, i++, oferta.getDescuentoPcn());
			DBNullUtils.toNull(preparedStatement, i++, oferta.getDescuentoFijo());
			DBNullUtils.toNull(preparedStatement, i++, oferta.getNumerador());
			DBNullUtils.toNull(preparedStatement, i++, oferta.getDenominador());
			preparedStatement.setInt(i++, oferta.getIdTipoOferta());
			DBNullUtils.toNull(preparedStatement, i++, oferta.getIdProdOferta());
			DBNullUtils.toNull(preparedStatement, i++, oferta.getNombreOferta());
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(oferta.getFechaDesde().getTime()));
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(oferta.getFechaHasta().getTime()));
			preparedStatement.setLong(i++, oferta.getIdEmpresa());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			ofert = new Oferta();
			i = 1;
			if(resultSet.next()) {

				ofert.setId(resultSet.getLong(i++));

			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Creando la oferta ").append(oferta.getId()).toString(),se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return ofert;

	}
	@Override
	public Oferta update(Connection connection ,Oferta oferta) throws DataException {
		PreparedStatement preparedStatement = null;

		Oferta ofert = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          

			logger.trace("Create statement...");

			sql.append( " UPDATE OFERTA set DESCUENTO_PCN = ? , ");
			sql.append(" DESCUENTO_FIJO = ? , NUMERADOR = ? ,");
			sql.append(" DENOMINADOR = ? , ID_TIPO_OFERTA = ? , ID_PRODUCTO = ? ,");
			sql.append(" NOMBRE_OFERTA = ? , FECHA_DESDE = ? , FECHA_HASTA = ? WHERE ID_OFERTA = ? ");
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			
			int i = 1;
			DBNullUtils.toNull(preparedStatement, i++, oferta.getDescuentoPcn());
			DBNullUtils.toNull(preparedStatement, i++, oferta.getDescuentoFijo());
			DBNullUtils.toNull(preparedStatement, i++, oferta.getNumerador());
			DBNullUtils.toNull(preparedStatement, i++, oferta.getDenominador());
			preparedStatement.setInt(i++, oferta.getIdTipoOferta());
			DBNullUtils.toNull(preparedStatement, i++, oferta.getIdProdOferta());
			DBNullUtils.toNull(preparedStatement, i++, oferta.getNombreOferta());
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(oferta.getFechaDesde().getTime()));
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(oferta.getFechaHasta().getTime()));
			preparedStatement.setLong(i++, oferta.getId());

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new DataException(new StringBuilder().append("No puede actualizar la oferta ").append(oferta.getId()).toString());
			} 

			ofert = findById(connection, oferta.getId());

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Actualizando la oferta ").append(oferta.getId()).toString(),se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}

		return ofert; 
	}
	

}








