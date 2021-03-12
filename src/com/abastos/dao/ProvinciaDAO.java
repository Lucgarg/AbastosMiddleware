package com.abastos.dao;

import java.sql.Connection;
import java.util.List;

import com.abastos.model.Provincia;
import com.abastos.service.DataException;

public interface ProvinciaDAO {
		public Provincia findById(Connection connection, Long idProvincia) throws DataException;
		public List<Provincia> findByIdComunidad(Connection connection, Long idComunidad) throws DataException;
		public List<Provincia> findBy(Connection connection, Long idComunidad) throws DataException;
	
}
