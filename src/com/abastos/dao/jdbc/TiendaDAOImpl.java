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

import com.abastos.dao.ContenidoDAO;
import com.abastos.dao.DireccionDAO;
import com.abastos.dao.DireccionDtoDAO;
import com.abastos.dao.ProductoDAO;
import com.abastos.dao.PuntuacionTiendaDAO;
import com.abastos.dao.Results;
import com.abastos.dao.TiendaDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.dao.util.DAOUtils;
import com.abastos.dao.util.DBNullUtils;
import com.abastos.model.Direccion;
import com.abastos.model.Producto;
import com.abastos.model.Tienda;
import com.abastos.service.DataException;
import com.abastos.service.TiendaCriteria;

public class TiendaDAOImpl implements TiendaDAO {
	private static Logger logger = LogManager.getLogger(TiendaDAOImpl.class);
	private ProductoDAO productoDAO = null;
	private DireccionDtoDAO direccionDto = null;
	private PuntuacionTiendaDAO puntTienda = null;
	private DireccionDAO direccionDAO = null;
	private ContenidoDAO contenidoDAO = null;
	public TiendaDAOImpl() {
		direccionDto = new DireccionDtoDAOImpl();
		puntTienda = new PuntuacionTiendaDAOImpl();
		productoDAO = new ProductoDAOImpl();
		direccionDAO = new DireccionDAOImpl();
		contenidoDAO = new ContenidoDAOImpl();
	}

	@Override
	public Tienda findById(Connection connection ,Long idTienda) throws DataException{
		Tienda tienda = null;
		ResultSet resultSet = null;

		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT  A.ID_TIENDA,  A.NOMBRE_TIENDA, A.FECHA_CREACION, ");
			sql.append(" A.ID_CATEGORIA, A.NUMERO_MOVIL, A.NUMERO_TELEFONO, ");
			sql.append(" A.CORREO_ELECTRONICO,  A.ENVIO_DOMICILIO, A.ID_EMPRESA, A.ID_CONTENIDO "); 
			sql.append(" FROM TIENDA A ");
			sql.append(" WHERE  A.ID_TIENDA = ? AND A.DATA_BAJA IS NULL ");

			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setLong(i++, idTienda);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				tienda = loadNext(connection, resultSet);
			}

		} catch (SQLException se) {
			logger.error(se);

			throw new DataException( new StringBuilder().append("Buscando la tienda ")
					.append(idTienda).toString(),se);
		} 

		finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}

		return tienda;

	}

	@Override
	public List<Tienda> findByIdEmpresa(Connection connection ,Long idEmpresa) throws DataException {
		Tienda tienda = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		List<Tienda> tiendas = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT  A.ID_TIENDA,  A.NOMBRE_TIENDA, A.FECHA_CREACION, ");
			sql.append(" A.ID_CATEGORIA, A.NUMERO_MOVIL, A.NUMERO_TELEFONO, ");
			sql.append(" A.CORREO_ELECTRONICO,  A.ENVIO_DOMICILIO,  A.ID_EMPRESA, A.ID_CONTENIDO ");
			sql.append(" FROM TIENDA A ");
			sql.append(" WHERE  A.ID_EMPRESA = ? AND A.DATA_BAJA IS  NULL");
			sql.append(" ORDER BY A.FECHA_CREACION DESC ");

			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setLong(i++, idEmpresa);
			resultSet = preparedStatement.executeQuery();
			tiendas = new ArrayList<Tienda>();
			while (resultSet.next()) {
				tienda = loadNext(connection, resultSet);
				tiendas.add(tienda);
			}

		} catch (SQLException se) {
			logger.error(se);

			throw new DataException( new StringBuilder().append("Buscando las tiendas de la empresa ")
					.append(idEmpresa).toString(),se);
		}  
		finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}

		return tiendas;
	}

	@Override
	public Results<Tienda> findByCriteria(Connection connection ,TiendaCriteria tiendaCriteria, int startIndex, int count) throws DataException {

		List<Tienda> results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Results<Tienda> resultsTienda = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT   A.ID_TIENDA,  A.NOMBRE_TIENDA, A.FECHA_CREACION, ");
			sql.append("	A.ID_CATEGORIA,  A.NUMERO_MOVIL, A.NUMERO_TELEFONO,  ");
			sql.append("	A.CORREO_ELECTRONICO,  A.ENVIO_DOMICILIO, A.ID_EMPRESA, A.ID_CONTENIDO ");
			sql.append("	FROM TIENDA A  ");


			boolean isFirst = false;
			if (tiendaCriteria.getIdLocalidad() != null) {

				sql.append( " inner join perfil_tienda_direccion h on a.id_tienda = h.id_tienda");
				sql.append(" inner join direccion d on h.id_direccion = d.id_direccion ");
				sql.append(" WHERE D.ID_LOCALIDAD = ? ");
				isFirst = true;

			}

			if (tiendaCriteria.getCategoria() != null) {
				if (isFirst == false) {
					sql.append( " WHERE A.ID_CATEGORIA = ? ");
					isFirst = true;
				} else {
					sql.append( " AND A.ID_CATEGORIA = ? ");
				}
			}

			if (tiendaCriteria.getEnvioDomicilio() != null) {
				if (isFirst == false) {
					sql.append( " WHERE A.ENVIO_DOMICILIO = ? ");
					isFirst = true;
				} else {
					sql.append( " AND A.ENVIO_DOMICILIO = ? ");
				}

			}
			if (tiendaCriteria.getNombre() != null) {
				if (isFirst == false) {
					sql.append( " WHERE upper(A.nombre_tienda) like ? ");
					isFirst = true;
				} else {
					sql.append( " AND upper(A.nombre_tienda) like  ?  ");
				}

			}
			if (tiendaCriteria.getIdEmpresa() != null) {
				if (isFirst == false) {
					sql.append( " WHERE A.ID_EMPRESA = ? ");
					isFirst = true;
				} else {
					sql.append( " AND A.ID_EMPRESA = ? ");
				}

			}
			sql.append( isFirst == false ? " WHERE A.DATA_BAJA IS NULL ORDER BY A.FECHA_CREACION ASC  ":
					" AND A.DATA_BAJA IS NULL ORDER BY A.FECHA_CREACION ASC  ");

			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());

		

			int i = 1;

			if (tiendaCriteria.getIdLocalidad() != null) {
				preparedStatement.setLong(i++, tiendaCriteria.getIdLocalidad());
			}
			if (tiendaCriteria.getCategoria() != null) {
				preparedStatement.setInt(i++, tiendaCriteria.getCategoria());
			}

			if (tiendaCriteria.getEnvioDomicilio() != null) {
				preparedStatement.setBoolean(i++, tiendaCriteria.getEnvioDomicilio());
			}
			if (tiendaCriteria.getNombre() != null) {
				preparedStatement.setString(i++, "%" + tiendaCriteria.getNombre().toUpperCase() + "%");
			}
			if (tiendaCriteria.getIdEmpresa() != null) {
				preparedStatement.setLong(i++, tiendaCriteria.getIdEmpresa() );
			}
			resultSet = preparedStatement.executeQuery();
			results = new ArrayList<Tienda>();

			Tienda e = null;
			int currentCount = 0;
			if ((startIndex >=1) && resultSet.absolute(startIndex)) {
				do {
					e = loadNext(connection, resultSet);
					results.add(e);
					currentCount++;
				} while ((currentCount < count) && resultSet.next()) ;
			}
			int totalRows = DAOUtils.getTotalRows(resultSet);
			resultsTienda = new Results<Tienda>(results, startIndex, totalRows);
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException("Buscando tiendas ",se);
		} finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}

		return resultsTienda;
	}
	@Override
	public Integer count(Connection connection, Tienda tienda) throws DataException{
		Integer count = null;
		ResultSet resultSet = null;

		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT  count(*) from tienda where id_empresa = ? ");

			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setLong(i++, tienda.getIdEmpresa());

			resultSet = preparedStatement.executeQuery();
			i = 1;
			if (resultSet.next()) {
				count = resultSet.getInt(i++);
			}

		} catch (SQLException se) {
			logger.error(se);

			throw new DataException( new StringBuilder().append("Contando las tiendas de la empresa ")
					.append(tienda.getIdEmpresa()).toString(),se);
		} 

		finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}

		return count;
	}
	private Tienda loadNext(Connection connection ,ResultSet resultSet) throws SQLException, DataException {
		int i = 1;
		Tienda tienda = new Tienda();
		tienda.setId(resultSet.getLong(i++));
		tienda.setNombre(resultSet.getString(i++));
		tienda.setFechaCreacion(resultSet.getTimestamp(i++));
		tienda.setCategoria(resultSet.getInt(i++));
		tienda.setNumeroMovil(resultSet.getString(i++));
		tienda.setNumeroTelefono(resultSet.getString(i++));
		tienda.setEmail(resultSet.getString(i++));
		tienda.setEnvioDomicilio(resultSet.getBoolean(i++));
		tienda.setIdEmpresa(resultSet.getLong(i++));
		tienda.setIdContenido(resultSet.getLong(i++));
		tienda.setPuntuacionMedia(puntTienda.findMedia(connection, tienda.getId())); 
		tienda.setDireccionDto(direccionDto.findByIdTienda (connection, tienda.getId()));

		return tienda;

	}

	@Override
	public Tienda create(Connection connection ,Tienda tienda) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Tienda tien = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");


			sql.append( " INSERT INTO TIENDA ");
			sql.append(" (NOMBRE_TIENDA, NUMERO_TELEFONO, NUMERO_MOVIL, ENVIO_DOMICILIO, ");
			sql.append(" CORREO_ELECTRONICO,  ID_EMPRESA, ID_CATEGORIA, FECHA_CREACION) ");
			sql.append(" VALUES(? , ? , ? , ?, ? , ?, ? , ? ) ");

			preparedStatement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			logger.trace(sql.toString());

			int i = 1;
			preparedStatement.setString(i++, tienda.getNombre());
			DBNullUtils.toNull(preparedStatement, i++, tienda.getNumeroTelefono());
			DBNullUtils.toNull(preparedStatement, i++, tienda.getNumeroMovil());
			preparedStatement.setBoolean(i++, tienda.getEnvioDomicilio());
			preparedStatement.setString(i++, tienda.getEmail());
			preparedStatement.setLong(i++, tienda.getIdEmpresa());
			preparedStatement.setInt(i++, tienda.getCategoria());
			preparedStatement.setTimestamp(i++, new Timestamp(new Date().getTime()));

			preparedStatement.executeUpdate();


			resultSet = preparedStatement.getGeneratedKeys();
			tien = new Tienda();
			i = 1;
			if (resultSet.next()) {

				tien.setId(resultSet.getLong(i++));
				tienda.setId(tien.getId());
			}


			createDireccionTienda(connection, tienda);

		} catch (SQLException se) {
			logger.error(se);

			throw new DataException( new StringBuilder().append("Creando tienda ")
					.append(tienda.getId()).toString(),se);
		}
		finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return tien;

	}
	private void createDireccionTienda(Connection connection, Tienda tienda)throws DataException{
		PreparedStatement preparedStatement = null;
		Direccion direccion = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			direccion = direccionDAO.createDireccion(connection, tienda.getDireccionDto());
			sql.append( "INSERT INTO PERFIL_TIENDA_DIRECCION(ID_TIENDA, ID_DIRECCION) ");
			sql.append(" VALUES(?, ?)");
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setLong(i++, tienda.getId());
			preparedStatement.setLong(i++, direccion.getId());

			preparedStatement.executeUpdate();


		} catch (SQLException se) {
			logger.error(se);

			throw new DataException(new StringBuilder().append("Creando direccion de tienda ")
					.append(tienda.getId()).toString(),se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);

		}

	}

	@Override
	public Tienda update(Connection connection ,Tienda tienda) throws DataException {
		PreparedStatement preparedStatement = null;
		Tienda tien = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " UPDATE TIENDA SET NOMBRE_TIENDA = ? ,  NUMERO_TELEFONO = ? , ");
			sql.append(" NUMERO_MOVIL = ? , ENVIO_DOMICILIO = ? ,");
			sql.append(" CORREO_ELECTRONICO = ? , ID_CATEGORIA = ? , ");
			sql.append(" FECHA_CREACION = ? ,  ID_CONTENIDO = ? WHERE ID_TIENDA = ? ");

			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setString(i++, tienda.getNombre());
			DBNullUtils.toNull(preparedStatement, i++, tienda.getNumeroTelefono());
			DBNullUtils.toNull(preparedStatement, i++, tienda.getNumeroMovil());
			preparedStatement.setBoolean(i++, tienda.getEnvioDomicilio());
			preparedStatement.setString(i++, tienda.getEmail());
			preparedStatement.setInt(i++, tienda.getCategoria());
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(tienda.getFechaCreacion().getTime()));
			DBNullUtils.toNull(preparedStatement, i++, tienda.getIdContenido());
			preparedStatement.setLong(i++, tienda.getId());

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {

				throw new DataException( new StringBuilder().append("No ha sido posible actualizar la tienda ")
						.append(tienda.getId()).toString());
			} 

			deleteDireccionTienda(connection, tienda);
			createDireccionTienda(connection, tienda);
			tien =  findById(connection, tienda.getId());

		} catch (SQLException se) {
			logger.error(se);

			throw new DataException(new StringBuilder().append("Actualizando la tienda ")
					.append(tienda.getId()).toString(), se);
		} finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return tien;

	}
	private void deleteDireccionTienda(Connection connection, Tienda tienda)throws DataException{
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();


			sql.append( "DELETE FROM PERFIL_TIENDA_DIRECCION WHERE ID_TIENDA = ? ") ;
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setLong(i++, tienda.getId());


			preparedStatement.executeUpdate();
			direccionDAO.hardDelete(connection, tienda.getDireccionDto().getIdDireccion());


		} catch (SQLException se) {
			logger.error(se);

			throw new DataException(new StringBuilder().append("Creando direccion tienda ")
					.append(tienda.getId()).toString(), se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);

		}

	}


	@Override
	public boolean softDelete(Connection connection ,Long idTienda) throws DataException {
		PreparedStatement preparedStatement = null;
		Tienda tienda = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();
			tienda = findById(connection, idTienda);
			deleteDireccionTienda(connection, tienda);
			productoDAO.softDeleteByTienda(connection, idTienda);


			logger.trace("Create statement...");

			sql.append( "UPDATE TIENDA SET DATA_BAJA = ?, id_contenido = null WHERE ID_TIENDA = ? ");

			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.setLong(i++, idTienda);

			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {

				throw new DataException( new StringBuilder().append("No ha sido posible dar de baja la tienda ")
						.append(idTienda).toString());
			}
			contenidoDAO.hardDelete(connection, tienda.getIdContenido());


		} catch (SQLException se) {
			logger.error(se);

			throw new DataException( new StringBuilder().append("Dando de baja la tienda ")
					.append(tienda.getId()).toString(), se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return true;

	}

	@Override
	public boolean softDeleteByEmpresa(Connection connection ,Long idEmpresa) throws DataException {
		PreparedStatement preparedStatement = null;
		List<Tienda> tienda = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();
			tienda = findByIdEmpresa(connection, idEmpresa);
			for(Tienda t: tienda) {
				deleteDireccionTienda(connection, t);
				productoDAO.softDeleteByTienda(connection, t.getId());
			}
			logger.trace("Create statement...");

			sql.append( "UPDATE TIENDA SET DATA_BAJA = ?, id_contenido = null WHERE ID_EMPRESA = ? ");

			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.setLong(i++, idEmpresa);

			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {
				throw new DataException("No ha sido posible eliminar la tiendas");
			}
			for(Tienda ti: tienda) {
				contenidoDAO.hardDelete(connection, ti.getIdContenido());
			}
		} catch (SQLException se) {
			logger.error(se);

			throw new DataException(  new StringBuilder().append("Dando de baja la tienda por la empresa ")
					.append(idEmpresa).toString(),se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return true;

	}


}






