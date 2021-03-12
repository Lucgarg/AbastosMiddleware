package com.abastos.dao;

import java.sql.Connection;
import java.util.List;

import com.abastos.model.ComunidadAutonoma;
import com.abastos.service.DataException;

public interface ComunidadAutonomaDAO {
	public ComunidadAutonoma findById(Connection connection, Long idComunidad) throws DataException;
	public List<ComunidadAutonoma> findByIdPais(Connection connection, Long idPais) throws DataException;
	public List<ComunidadAutonoma> findByTienda(Connection connection, Integer idPais) throws DataException;
	

}
