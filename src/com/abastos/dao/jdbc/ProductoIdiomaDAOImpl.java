package com.abastos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.ProductoIdiomaDAO;
import com.abastos.dao.util.ConnectionManager;

import com.abastos.model.ProductoIdioma;

import com.abastos.service.DataException;

public class ProductoIdiomaDAOImpl implements ProductoIdiomaDAO {
	private static Logger logger = LogManager.getLogger(ProductoIdiomaDAOImpl.class);
	public ProductoIdiomaDAOImpl() {

	}

	@Override
	public List<ProductoIdioma> findBy(Connection connection, Long idProducto) throws DataException {
		ProductoIdioma productoIdioma = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<ProductoIdioma> prodIdio = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT  a.id_producto, a.id_idioma, a.nombre_producto, a.caracteristicas_producto ");
			sql.append(" from producto_idioma a where a.id_producto = ? ");

			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setLong(i++, idProducto);
			resultSet = preparedStatement.executeQuery();
			prodIdio = new ArrayList<ProductoIdioma>();
			while (resultSet.next()) {
				productoIdioma = loadNext(connection, resultSet);
				prodIdio.add(productoIdioma);
			}

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando las diferentes traduciones del producto ")
					.append(idProducto).toString(),se);
		}  
		finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}

		return prodIdio;
	}
	private ProductoIdioma loadNext(Connection connection ,ResultSet resultSet) throws DataException, SQLException {
		int i = 1;

		ProductoIdioma productoIdioma = new ProductoIdioma();
		productoIdioma.setIdProducto(resultSet.getLong(i++));
		productoIdioma.setIdIdioma(resultSet.getString(i++));
		productoIdioma.setNombreProducto(resultSet.getString(i++));
		productoIdioma.setCaractProduct(resultSet.getString(i++)); 


		return productoIdioma;

	}


}
