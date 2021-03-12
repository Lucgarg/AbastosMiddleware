package com.abastos.dao;

import java.sql.Connection;

import com.abastos.model.Direccion;
import com.abastos.model.DireccionDto;
import com.abastos.service.DataException;

public interface DireccionDAO {
	public Direccion findById(Connection connection, Long idDireccion) throws DataException;
	public Direccion createDireccion(Connection connection, Direccion direccion)throws DataException;
	public Direccion createDireccion(Connection connection, DireccionDto direccion)throws DataException;
	public Direccion update(Connection connection, Direccion direccion) throws DataException;
	public boolean hardDelete(Connection connection, Long idDireccion) throws DataException;
	

	
}
