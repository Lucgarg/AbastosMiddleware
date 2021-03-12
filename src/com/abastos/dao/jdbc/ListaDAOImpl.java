package com.abastos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.LineaListaDAO;
import com.abastos.dao.ListaDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.LineaLista;
import com.abastos.model.Lista;
import com.abastos.service.DataException;

public class ListaDAOImpl implements ListaDAO{
	private static Logger logger = LogManager.getLogger(ListaDAOImpl.class);
	private LineaListaDAO lineaLista = null;

	public ListaDAOImpl() {
		lineaLista = new LineaListaDAOImpl();
	}
	@Override
	public Lista findById(Connection connection ,Long idLista) throws DataException{

		Lista results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT  ID_LISTA, NOMBRE, ");
			sql.append(" FECHA_CREACION FROM LISTA_DESEOS ");
			sql.append(" WHERE ID_LISTA = ?  ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
		
			int i = 1;

			preparedStatement.setLong(i++, idLista);


			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				results = loadNext(connection ,resultSet);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando la lista ").append(idLista).toString() , se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		} 
		return results;
	}

	@Override
	public List<Lista> findByIdParticular(Connection connection ,Long idParticular) throws DataException{
		Lista results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<Lista> listaDeseos = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT  ID_LISTA, NOMBRE, ");
			sql.append(" FECHA_CREACION FROM LISTA_DESEOS ");
			sql.append(" WHERE ID_PARTICULAR = ?  ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			
			int i = 1;

			preparedStatement.setLong(i++, idParticular);

			listaDeseos = new ArrayList<Lista>();
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				results = loadNext(connection, resultSet);
				listaDeseos.add(results);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando listas del particular ").append(idParticular).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		} 
		return listaDeseos;
	}

	private Lista loadNext(Connection connection ,ResultSet resultSet)throws DataException, SQLException{
		int i = 1;
		Lista listaDeseos = new Lista();
		listaDeseos.setId(resultSet.getLong(i++));
		listaDeseos.setNombre(resultSet.getString(i++));
		listaDeseos.setFechaCreacion(resultSet.getDate(i++));
		listaDeseos.setLineaLista(lineaLista.findByIdListaDeseos(connection, listaDeseos.getId()));
		return listaDeseos;

	}
	@Override
	public Lista create(Connection connection ,Lista lista)throws DataException {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		Lista list = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          

			logger.trace("Create statement...");

			sql.append( " INSERT INTO LISTA_DESEOS ");
			sql.append("(NOMBRE, ID_PARTICULAR, FECHA_CREACION) ");
			sql.append("VALUE(? , ? , ? ) "); 

			preparedStatement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			logger.trace(sql.toString());

			int i = 1;
			preparedStatement.setString(i++, lista.getNombre());
			preparedStatement.setLong(i++, lista.getIdParticular());
			preparedStatement.setTimestamp(i++, new Timestamp(new Date().getTime()));

			preparedStatement.executeUpdate();

			resultSet = preparedStatement.getGeneratedKeys();
			i = 1;
			list = new Lista();
			if(resultSet.next()) {
				list.setId(resultSet.getLong(i++));
			}
			for(LineaLista lis: lista.getLineaLista()) {
				lis.setId(list.getId());
				lineaLista.create(connection, lis);
			}
		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Creando la lista ").append(lista.getId()).toString(),se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return list;

	}
	@Override
	public Lista update(Connection connection ,Lista lista) throws DataException {
		PreparedStatement preparedStatement = null;
		Lista list = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          

			logger.trace("Create statement...");

			sql.append( " update Lista_deseos set nombre = ? ");
			sql.append(" where id_lista = ? ");
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;

			preparedStatement.setString(i++, lista.getNombre());
			preparedStatement.setLong(i++, lista.getId());

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new DataException(new StringBuilder().append("No se puede actualizar la lista ").append(lista.getId()).toString());
			} 
			list = findById(connection, lista.getId());
			for(LineaLista lis : lista.getLineaLista()) {
				lineaLista.delete(connection, lis.getId(), lis.getIdProducto());
				lineaLista.create(connection, lis);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Actualizando la lista ").append(lista.getId()).toString(),se);
		}  finally {
			ConnectionManager.closePreparedStatement( preparedStatement);
		}

		return list;
	}
	@Override
	public boolean delete(Connection connection ,Long idLista) throws DataException{
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();    
			lineaLista.delete(connection, idLista, null);
			logger.trace("Create statement...");

			sql.append( " DELETE FROM LISTA_DESEOS WHERE ID_LISTA = ?  "); 

			preparedStatement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			logger.trace(sql.toString());

			preparedStatement.setLong(1, idLista);

			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {
				throw new DataException(new StringBuilder().append("No se puede eliminar la lista ").append(idLista).toString());
			} 


		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Eliminando la lista ").append(idLista).toString(), se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return true;

	}

	@Override
	public boolean deleteByIdParticular(Connection connection ,Long idParticular) throws DataException {
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();   
			lineaLista.deleteByIdParticular(connection, idParticular);

			logger.trace("Create statement...");

			sql.append( " DELETE FROM LISTA_DESEOS WHERE ID_PARTICULAR = ? "); 

			preparedStatement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			logger.trace(sql.toString());

			preparedStatement.setLong(1, idParticular);


			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {
				throw new SQLException(new StringBuilder().append("No se puede eliminar las listas del usuario particular ")
						.append(idParticular).toString());
			} 

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Eliminando listas del particular ").append(idParticular).toString(), se);
		} finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return true;
	}


}
