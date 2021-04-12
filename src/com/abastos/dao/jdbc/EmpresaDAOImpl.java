package com.abastos.dao.jdbc;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.ContenidoDAO;
import com.abastos.dao.DireccionDAO;
import com.abastos.dao.DireccionDtoDAO;
import com.abastos.dao.EmpresaDAO;
import com.abastos.dao.TiendaDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.dao.util.DBNullUtils;
import com.abastos.model.Direccion;
import com.abastos.model.Empresa;
import com.abastos.service.DataException;

public class EmpresaDAOImpl implements EmpresaDAO {
	private static Logger logger = LogManager.getLogger(EmpresaDAOImpl.class);
	private TiendaDAO tiendaDAO = null;
	private DireccionDtoDAO direccionDto = null;
	private DireccionDAO direccionDAO = null;
	private ContenidoDAO contenidoDAO = null;
	public EmpresaDAOImpl() {
		tiendaDAO = new TiendaDAOImpl();
		direccionDto = new DireccionDtoDAOImpl();
		direccionDAO = new DireccionDAOImpl();
		contenidoDAO = new ContenidoDAOImpl();
	}

	@Override
	public Empresa findById(Connection connection , Long idEmpresa) throws DataException {
		Empresa results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( "SELECT A.ID_EMPRESA, A.CONTRASENA, A.ALIAS, A.NOMBRE, ");
			sql.append(" A.APELLIDOS, A.CIF, A.CORREO_ELECTRONICO, A.RAZON_SOCIAL, A.DATA_ALTA ");
			sql.append(" FROM PERFIL_EMPRESA A ");
			sql.append(" WHERE ID_EMPRESA = ? ");
			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			
			int i = 1;

			preparedStatement.setLong(i++, idEmpresa);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				results = loadNext(connection, resultSet);
			}

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando la empresa ").append(idEmpresa).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return results;
	}

	@Override
	public Empresa findByEmail(Connection connection ,String email) throws DataException {
		Empresa results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( "SELECT A.ID_EMPRESA, A.CONTRASENA, A.ALIAS, A.NOMBRE, ");
			sql.append(" A.APELLIDOS, A.CIF, A.CORREO_ELECTRONICO, A.RAZON_SOCIAL, A.DATA_ALTA");
			sql.append(" FROM PERFIL_EMPRESA A ");
			sql.append(" WHERE UPPER(A.CORREO_ELECTRONICO) LIKE ? ");
			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
		
			int i = 1;

			preparedStatement.setString(i++, email);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				results = loadNext(connection, resultSet);
			}

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando la empresa por email ").append(email).toString(),  se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return results;
	}

	@Override
	public Empresa findByAlias(Connection connection ,String alias) throws DataException {
		Empresa results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( "SELECT A.ID_EMPRESA, A.CONTRASENA, A.ALIAS, A.NOMBRE, ");
			sql.append(" A.APELLIDOS, A.CIF, A.CORREO_ELECTRONICO,  A.RAZON_SOCIAL, A.DATA_ALTA"); 
			sql.append(" FROM PERFIL_EMPRESA A ");
			sql.append(" WHERE A.ALIAS = ? ");
			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());
			
			int i = 1;

			preparedStatement.setString(i++, alias);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				results = loadNext(connection, resultSet);
			}

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando la empresa por alias ").append(alias).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return results;
	}

	private Empresa loadNext(Connection connection ,ResultSet resultSet) throws DataException, SQLException {
		int i = 1;
		Empresa empresa = new Empresa();
		empresa.setId(resultSet.getLong(i++));
		empresa.setContrasena(resultSet.getString(i++));
		empresa.setAlias(resultSet.getString(i++));
		empresa.setNombre(resultSet.getString(i++));
		empresa.setApellidos(resultSet.getString(i++));
		empresa.setCif(resultSet.getString(i++));
		empresa.setCorreoElectronico(resultSet.getString(i++));
		empresa.setRazonSocial(resultSet.getString(i++));
		empresa.setDataAlta(resultSet.getDate(i++));
		empresa.setDireccion(direccionDto.findByIdEmp(connection, empresa.getId()));
		return empresa;

	}

	@Override
	public Empresa create(Connection connection ,Empresa empresa) throws DataException {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		Empresa empre = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();


			logger.trace("Create statement...");

			sql.append( " INSERT INTO PERFIL_EMPRESA(CONTRASENA, ALIAS, ");
			sql.append(" NOMBRE, APELLIDOS, CIF, CORREO_ELECTRONICO, RAZON_SOCIAL) ");
			sql.append(" VALUES( ? , ? , ? , ? , ? , ? , ? )");

			preparedStatement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			logger.trace(sql.toString());

			int i = 1;
			preparedStatement.setString(i++, empresa.getContrasena());
			preparedStatement.setString(i++, empresa.getAlias());
			preparedStatement.setString(i++, empresa.getNombre());
			preparedStatement.setString(i++, empresa.getApellidos());
			preparedStatement.setString(i++, empresa.getCif());
			preparedStatement.setString(i++, empresa.getCorreoElectronico());
			preparedStatement.setString(i++, empresa.getRazonSocial());

			preparedStatement.executeUpdate();

			empre = new Empresa();

			resultSet = preparedStatement.getGeneratedKeys();
			i = 1;
			if(resultSet.next()) {
				empre.setId(resultSet.getLong(i++));
			}

			empresa.setId(empre.getId());
			createDireccionEmpresa(connection, empresa);

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Creando la empresa ").append(empresa.getNombre()).toString(), se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return empre;

	}

	private void createDireccionEmpresa(Connection connection, Empresa empresa)throws DataException{
		PreparedStatement preparedStatement = null;
		Direccion direccion = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			direccion = direccionDAO.createDireccion(connection, empresa.getDireccion());
			logger.trace("Create statement...");
			sql.append( " INSERT INTO PERFIL_EMPRESA_DIRECCION(ID_EMPRESA, ID_DIRECCION) ");
			sql.append(" VALUES(?, ?) ");
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setLong(i++, empresa.getId());
			preparedStatement.setLong(i++, direccion.getId());

			preparedStatement.executeUpdate();


		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Creando la direccion de la empresa ").append(empresa.getId()).toString(), se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);

		}

	}

	@Override
	public Empresa update(Connection connection ,Empresa empresa) throws DataException {
		PreparedStatement preparedStatement = null;

		Empresa empres = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " UPDATE PERFIL_EMPRESA SET CONTRASENA = ? ,  ALIAS = ? ,");
			sql.append("  NOMBRE = ? , APELLIDOS = ? ,  CIF = ? , RAZON_SOCIAL = ? ,");
			sql.append(" ID_CONTENIDO = ?  WHERE ID_EMPRESA = ? ");
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setString(i++, empresa.getContrasena());
			preparedStatement.setString(i++, empresa.getAlias());
			preparedStatement.setString(i++, empresa.getNombre());
			preparedStatement.setString(i++, empresa.getApellidos());
			preparedStatement.setString(i++, empresa.getCif());
			preparedStatement.setString(i++, empresa.getRazonSocial());
			DBNullUtils.toNull(preparedStatement, i++, empresa.getIdContenido());
			preparedStatement.setLong(i++, empresa.getId());

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new DataException(new StringBuilder().append("No puedes actualizar la empresa ").append(empresa.getId()).toString());
			} 

			deleteDireccionEmpresa(connection, empresa);
			createDireccionEmpresa(connection, empresa);
		
			empres = findById(connection, empresa.getId());

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return empres;

	}
	@Override
	public boolean updateAlta(Connection connection, Long idEmpresa) throws DataException {
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " UPDATE PERFIL_EMPRESA SET  DATA_ALTA = ? ");
			sql.append(" WHERE ID_EMPRESA = ? ");

			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.setLong(i++, idEmpresa);

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new DataException(new StringBuilder().append("No se ha podido dar de alta a la empresa ").append(idEmpresa).toString());
			} 

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Actualizando el alta de la empresa ").append(idEmpresa).toString(),se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return true;

	}
	private void deleteDireccionEmpresa(Connection connection, Empresa empresa)throws DataException{
		PreparedStatement preparedStatement = null;

		try {


			String sql = "DELETE FROM PERFIL_EMPRESA_DIRECCION WHERE ID_EMPRESA = ? " ;
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setLong(i++, empresa.getId());
			preparedStatement.executeUpdate();
			direccionDAO.hardDelete(connection, empresa.getDireccion().getIdDireccion());

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Actualizando la empresa ").append(empresa.getRazonSocial()).toString(), se);
		} finally {
			ConnectionManager.closePreparedStatement(preparedStatement);

		}

	}
	@Override
	public boolean softDelete(Connection connection ,Long idEmpresa) throws DataException {
		PreparedStatement preparedStatement = null;
		Empresa empresa = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();
			empresa = findById(connection, idEmpresa);
			deleteDireccionEmpresa(connection, empresa);

			tiendaDAO.softDeleteByEmpresa(connection, idEmpresa);

			logger.trace("Create statement...");

			sql.append( " UPDATE PERFIL_EMPRESA SET DATA_BAJA = ?, id_contenido = null WHERE ID_EMPRESA = ? ");

			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i =1;
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.setLong(i++, idEmpresa);

			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {
				throw new DataException(new StringBuilder().append("No se puede dar de baja a la empresa ").append(idEmpresa).toString());
			}
			if(empresa.getIdContenido() != null) {
				contenidoDAO.hardDelete(connection, empresa.getIdContenido());
			}
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Dando de baja a la empresa ").append(idEmpresa).toString(), se);
		} finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return true;
	}





}
