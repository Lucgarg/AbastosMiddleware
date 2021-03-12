package com.abastos.dao;

import java.sql.Connection;
import java.util.List;

import com.abastos.model.Localidad;
import com.abastos.service.DataException;

public interface LocalidadDAO {
	public List<Localidad> findByIdProvincia(Connection connection, Long idProvincia) throws DataException;
	public Localidad findByIdLocalidad(Connection connection, Long idLocalidad) throws DataException;
	public List<Localidad> findByTiendas(Connection connection, Long idProvincia) throws DataException;
}
