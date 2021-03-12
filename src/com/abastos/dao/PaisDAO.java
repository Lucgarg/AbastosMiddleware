package com.abastos.dao;

import java.sql.Connection;
import java.util.List;

import com.abastos.model.Pais;
import com.abastos.service.DataException;

public interface PaisDAO 
{
	public Pais findById(Connection connection, Integer idPais) throws DataException;
	public List<Pais> findByAll(Connection connection)throws DataException;

}
