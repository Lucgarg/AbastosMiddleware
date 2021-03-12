package com.abastos.dao;




import java.sql.Connection;

import com.abastos.model.Empresa;
import com.abastos.service.DataException;


public interface EmpresaDAO {
	public Empresa findById(Connection connection, Long idEmpresa) throws DataException;
	public Empresa findByEmail(Connection connection, String email) throws DataException;
	public Empresa findByAlias(Connection connection, String alias) throws DataException;
	public Empresa create(Connection connection, Empresa empresa) throws DataException;
	public Empresa update(Connection connection, Empresa empresa) throws DataException;
	public boolean updateAlta(Connection connection, Long idEmpresa) throws DataException;
	public boolean softDelete(Connection connection, Long idEmpresa) throws DataException;
}
