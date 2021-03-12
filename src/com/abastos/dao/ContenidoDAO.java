package com.abastos.dao;

import java.sql.Connection;

import com.abastos.model.Contenido;
import com.abastos.service.DataException;

public interface ContenidoDAO {
	public Contenido findById(Connection connection,  Long id) throws DataException;
	public Contenido findByIdTipo(Connection connection, Long id) throws DataException;
	public Contenido create(Connection connection,  Contenido cont) throws DataException;
	public Contenido update(Connection connection, Contenido cont)throws DataException;
	public boolean hardDelete(Connection connection, Long idContenido) throws DataException;
	
}
