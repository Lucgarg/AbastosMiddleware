package com.abastos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.CategoriaDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Categoria;
import com.abastos.service.DataException;

public class CategoriaDAOImpl implements CategoriaDAO {
	private static Logger logger = LogManager.getLogger(CategoriaDAOImpl.class);
	List<Long> listaCategorias = null;

	public CategoriaDAOImpl() {
		listaCategorias = new ArrayList<Long>();
	}

	@Override
	public List<Categoria> findRoot(Connection connection, String idioma) throws DataException {

		return findByIdPadre(connection ,null, idioma);
	}

	@Override
	public List<Categoria> findByIdPadre(Connection connection, Integer id, String idioma)throws DataException {
		List<Categoria> results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT g.ID_CATEGORIA, A.NOMBRE_CATEGORIA, g.ID_CATEGORIAPADRE ");
			sql.append(" FROM CATEGORIA G INNER JOIN CATEGORIA_IDIOMA A ON G.ID_CATEGORIA = A.ID_CATEGORIA");
			sql.append(" WHERE g.ID_CATEGORIAPADRE ");

			if (id == null) {
				sql.append(" IS NULL ");

			} else {
				sql.append( " = ? ");

			}
			sql.append( " AND a.id_idioma = ? ORDER BY A.NOMBRE_CATEGORIA ASC ");
			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			int i = 1;
			if (id != null) {
				preparedStatement.setLong(i++, id);
			}
			preparedStatement.setString(i++, idioma);
			Categoria categoria = null;
			results = new ArrayList<Categoria>();
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				categoria = loadNext(connection, resultSet, idioma);
				results.add(categoria);
			}

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando la categoria ").append(id).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return results;
	}

	@Override
	public Categoria findById(Connection connection, Integer id, String idioma) throws DataException {
		Categoria results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT G.ID_CATEGORIA, A.NOMBRE_CATEGORIA, G.ID_CATEGORIAPADRE");
			sql.append(" FROM CATEGORIA G INNER JOIN CATEGORIA_IDIOMA A ON");
			sql.append(" G.ID_CATEGORIA = A.ID_CATEGORIA ");
			sql.append(" WHERE G.ID_CATEGORIA = ?  AND A.ID_IDIOMA = ? ORDER BY A.NOMBRE_CATEGORIA ASC");
			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());

			int i = 1;
			preparedStatement.setLong(i++, id);
			preparedStatement.setString(i++, idioma);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				results = loadNext(connection, resultSet, idioma);
			}

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando la categoria ").append(id).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return results;
	}

	@Override
	public List<Integer> findByCategoriaHoja(Connection connection, Integer id, String idioma) throws DataException {

		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<Integer> categorias = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT g.ID_CATEGORIA  FROM CATEGORIA g");
			sql.append(" inner join categoria_idioma a on g.id_categoria = a.id_categoria");
			sql.append(" WHERE g.ID_CATEGORIAPADRE  ");

			if (id == null) {
				sql.append( " IS NULL ");

			} else {
				sql.append( " = ? ");

			}
			sql.append( " AND a.id_idioma = ? ORDER BY A.NOMBRE_CATEGORIA ASC");
			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			int i =1;
			if (id != null) {
				preparedStatement.setLong(i++, id);
			}
			preparedStatement.setString(i++, idioma);
			categorias = new ArrayList<Integer>();
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				categorias.add(id);
			} else {
				resultSet.beforeFirst();
			}

			while (resultSet.next()) {

				categorias.addAll(findByCategoriaHoja(connection, resultSet.getInt(1), idioma));

			}	
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando la categoria ").append(id).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return categorias;
	}
	@Override
	public List<Categoria> findByTienda(Connection connection, Long idTienda, String idioma) throws DataException {
		return null;
	}
	private Categoria loadNext(Connection connection, ResultSet resultSet, String idioma) throws SQLException, DataException {
		int i = 1;
		Categoria categoria = new Categoria();
		categoria.setId(resultSet.getInt(i++));
		categoria.setNombre(resultSet.getString(i++));
		categoria.setIdCategoriaPadre(resultSet.getInt(i++));
		categoria.setCategorias(findByIdPadre(connection, categoria.getId(), idioma));

		return categoria;
	}





}
