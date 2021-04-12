package com.abastos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.DireccionDtoDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.DireccionDto;
import com.abastos.service.DataException;

public class DireccionDtoDAOImpl implements DireccionDtoDAO{
	private static Logger logger = LogManager.getLogger(DireccionDtoDAOImpl.class);
	@Override
	public DireccionDto findByIdEmp(Connection connection, Long idEmpresa) throws DataException{
		DireccionDto results = null;
		ResultSet resultSet = null;
		PreparedStatement  preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( "  SELECT F.ID_PAIS, F.NOMBRE,  I.ID_COMUNIDAD_AUTONOMA, I.NOMBRE, H.ID_PROVINCIA, H.NOMBRE, ");
			sql.append("	 E.ID_LOCALIDAD, E.NOMBRE_LOCALIDAD, G.CODIGO_POSTAL, G.ID_DIRECCION, ");
			sql.append("	 G.CALLE, G.NUMERO, G.PISO, G.ID_TIPO_DIRECCION, t.nombre FROM DIRECCION G");
			sql.append("	INNER JOIN TIPO_DIRECCION t on t.id_tipo_direccion = g.id_tipo_direccion ");
			sql.append("	 INNER JOIN LOCALIDAD E ON E.ID_LOCALIDAD = G.ID_LOCALIDAD  ");
			sql.append("	INNER JOIN PROVINCIA H ON E.ID_PROVINCIA = H.ID_PROVINCIA  ");
			sql.append("	INNER JOIN COMUNIDAD_AUTONOMA I ON I.ID_COMUNIDAD_AUTONOMA = H.ID_COMUNIDAD_AUTONOMA  ");
			sql.append(" INNER JOIN PAIS F ON F.ID_PAIS = I.ID_PAIS ");
			sql.append(" inner join perfil_empresa_direccion c on c.id_direccion = g.id_direccion ");
			sql.append(" WHERE c.ID_EMPRESA = ? ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());

			int i = 1;
			preparedStatement.setLong(i++, idEmpresa);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				results = loadNext(resultSet);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando la direccion de la  empresa ").append(idEmpresa).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		} 
		return results;

	}

	@Override
	public List<DireccionDto> findByIdPart(Connection connection, Long idParticular) throws DataException{
		List<DireccionDto> results = null;
		ResultSet resultSet = null;
		PreparedStatement  preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");
			sql.append( " SELECT F.ID_PAIS, F.NOMBRE,  I.ID_COMUNIDAD_AUTONOMA, I.NOMBRE, H.ID_PROVINCIA, H.NOMBRE, ");
			sql.append("	 E.ID_LOCALIDAD, E.NOMBRE_LOCALIDAD, G.CODIGO_POSTAL, G.ID_DIRECCION, ");
			sql.append("	 G.CALLE, G.NUMERO, G.PISO, G.ID_TIPO_DIRECCION, T.NOMBRE FROM DIRECCION G ");
			sql.append("	INNER JOIN TIPO_DIRECCION T ON T.ID_TIPO_DIRECCION = G.ID_TIPO_DIRECCION ");
			sql.append("	 INNER JOIN LOCALIDAD E ON E.ID_LOCALIDAD = G.ID_LOCALIDAD  ");
			sql.append("	INNER JOIN PROVINCIA H ON E.ID_PROVINCIA = H.ID_PROVINCIA  ");
			sql.append("	INNER JOIN COMUNIDAD_AUTONOMA I ON I.ID_COMUNIDAD_AUTONOMA = H.ID_COMUNIDAD_AUTONOMA  ");
			sql.append(" INNER JOIN PAIS F ON F.ID_PAIS = I.ID_PAIS ");
			sql.append(" INNER JOIN PARTICULAR_DIRECCION C ON C.ID_DIRECCION = G.ID_DIRECCION ");
			sql.append(" WHERE C.ID_PARTICULAR = ? ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());

			int i = 1;
			preparedStatement.setLong(i++, idParticular);
			 
			results = new ArrayList<DireccionDto>();
			resultSet = preparedStatement.executeQuery();
			DireccionDto direccion = null;
			while(resultSet.next()) {
				 direccion = loadNext(resultSet);
				results.add(direccion);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Bsucando la direccion del particular ").append(idParticular).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		} 
		return results;
	}

	@Override
	public DireccionDto findByIdTienda(Connection connection, Long idTienda) throws DataException {
		DireccionDto results = null;
		ResultSet resultSet = null;
		PreparedStatement  preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");


			sql.append( " SELECT F.ID_PAIS, F.NOMBRE,  I.ID_COMUNIDAD_AUTONOMA, I.NOMBRE, H.ID_PROVINCIA, H.NOMBRE, ");
			sql.append("	 E.ID_LOCALIDAD, E.NOMBRE_LOCALIDAD, G.CODIGO_POSTAL, G.ID_DIRECCION, ");
			sql.append("	 G.CALLE, G.NUMERO, G.PISO, G.ID_TIPO_DIRECCION, T.NOMBRE FROM DIRECCION G ");
			sql.append("	INNER JOIN TIPO_DIRECCION T ON T.ID_TIPO_DIRECCION = G.ID_TIPO_DIRECCION ");
			sql.append("	 INNER JOIN LOCALIDAD E ON E.ID_LOCALIDAD = G.ID_LOCALIDAD  ");
			sql.append("	INNER JOIN PROVINCIA H ON E.ID_PROVINCIA = H.ID_PROVINCIA  ");
			sql.append("	INNER JOIN COMUNIDAD_AUTONOMA I ON I.ID_COMUNIDAD_AUTONOMA = H.ID_COMUNIDAD_AUTONOMA  ");
			sql.append(" INNER JOIN PAIS F ON F.ID_PAIS = I.ID_PAIS ");
			sql.append(" INNER JOIN PERFIL_TIENDA_DIRECCION C ON C.ID_DIRECCION = G.ID_DIRECCION ");
			sql.append(" WHERE C.ID_TIENDA = ? ");
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());

			int i = 1;
			preparedStatement.setLong(i++, idTienda);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				results = loadNext(resultSet);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando la direccion de la tienda ").append(idTienda).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		} 
		return results;
	}
	private DireccionDto loadNext(ResultSet resultSet) throws SQLException{
		int i = 1;
		DireccionDto direccion = new DireccionDto();
		direccion.setIdPais(resultSet.getLong(i++));
		direccion.setPais(resultSet.getString(i++));
		direccion.setIdComunidadAutonoma(resultSet.getLong(i++));
		direccion.setComunidadAutonoma(resultSet.getString(i++));
		direccion.setIdProvincia(resultSet.getLong(i++));
		direccion.setProvincia(resultSet.getString(i++));
		direccion.setIdLocalidad(resultSet.getLong(i++));
		direccion.setLocalidad(resultSet.getString(i++));
		direccion.setCodigoPostal(resultSet.getString(i++));
		direccion.setIdDireccion(resultSet.getLong(i++));
		direccion.setCalle(resultSet.getString(i++));
		direccion.setNumero(resultSet.getInt(i++));
		direccion.setPiso(resultSet.getString(i++));
		direccion.setIdTipoDireccion(resultSet.getInt(i++));
		direccion.setNombreDireccion(resultSet.getString(i++));

		return direccion;
	}


}