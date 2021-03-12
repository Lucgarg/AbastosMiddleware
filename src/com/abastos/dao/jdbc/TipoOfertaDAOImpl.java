package com.abastos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.TipoOfertaDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.TipoOferta;
import com.abastos.service.DataException;

public class TipoOfertaDAOImpl implements TipoOfertaDAO {
	private static Logger logger = LogManager.getLogger(TipoOfertaDAOImpl.class);
	@Override
	public List<TipoOferta> findAll(Connection connection) throws DataException {
		List<TipoOferta> results = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();
			logger.trace("Create statement...");
			sql.append( " SELECT ID_TIPO_OFERTA, NOMBRE FROM TIPO_OFERTA ");


			preparedStatement = connection.prepareStatement(sql.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());

			resultSet = preparedStatement.executeQuery();

			results = new ArrayList<TipoOferta>();

			TipoOferta tipOferta = null;

			while (resultSet.next()) {

				tipOferta = new TipoOferta();
				tipOferta = loadNext(resultSet);				
				results.add(tipOferta);

			}

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException("Buscando los tipos de oferta ",se);
		} finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}

		return results;
	}
	private TipoOferta loadNext(ResultSet resultset) throws SQLException {
		int i = 1;
		TipoOferta tipOferta = new TipoOferta();
		tipOferta.setId(resultset.getInt(i++));
		tipOferta.setNombre(resultset.getString(i++));
		return tipOferta;


	}
}


