package com.abastos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.ContenidoDAO;
import com.abastos.dao.ContenidoProductoDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.ContenidoProducto;
import com.abastos.service.DataException;

public class ContenidoProductoDAOImpl implements ContenidoProductoDAO{
	private static Logger logger = LogManager.getLogger(ContenidoProductoDAOImpl.class);
	ContenidoDAO contenidoDAO = null;
	public ContenidoProductoDAOImpl() {
		contenidoDAO = new ContenidoDAOImpl();
	}
	@Override
	public List<ContenidoProducto> findByImagenes(Connection connection, Long idProducto) throws DataException {
		List<ContenidoProducto> results = null;
		ContenidoProducto pro = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT ID_PRODUCTO, ID_CONTENIDO, ID_TIPO_CONTENIDO");
			sql.append(" FROM PRODUCTO_TIENE_CONTENIDO WHERE ID_PRODUCTO =  ? ");

			preparedStatement = connection.prepareStatement(sql.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());

			int i = 1;

			preparedStatement.setLong(i++, idProducto);

			resultSet = preparedStatement.executeQuery();

			results = new ArrayList<ContenidoProducto>();

			

			
			while (resultSet.next()) {

				pro = loadNext(connection, resultSet);				
				results.add(pro);
				
			}

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Encontrando el contenido del producto ").append(idProducto).toString(), se);
		} finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}

		return results;
	}
	private ContenidoProducto loadNext(Connection connection, ResultSet resultSet) throws DataException, SQLException {
		ContenidoProducto contPro = new ContenidoProducto();
		int i = 1;
		contPro.setIdProducto(resultSet.getLong(i++));
		contPro.setContenido(contenidoDAO.findById(connection, resultSet.getLong(i++)));
		contPro.setIdTipoFoto(resultSet.getString(i++).charAt(0));
		return contPro;

	}

}

