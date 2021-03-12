package com.abastos.service;

import java.sql.Connection;
import java.util.List;

import com.abastos.model.Provincia;

public interface ProvinciaService {
	public Provincia findById(Long idProvincia) throws DataException;
	public List<Provincia> findByIdComunidad( Long idComunidad) throws DataException;
	public List<Provincia> findBy(Long idComunidad) throws DataException;
}
