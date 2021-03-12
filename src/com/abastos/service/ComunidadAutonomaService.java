package com.abastos.service;

import java.sql.Connection;
import java.util.List;

import com.abastos.model.ComunidadAutonoma;

public interface ComunidadAutonomaService {
	public ComunidadAutonoma findById( Long idComunidad) throws DataException;
	public List<ComunidadAutonoma> findByIdPais(Long idPais) throws DataException;
	public List<ComunidadAutonoma> findByTienda(Integer idPais) throws DataException;
}
