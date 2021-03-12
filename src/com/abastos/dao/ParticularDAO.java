package com.abastos.dao;



import java.sql.Connection;

import com.abastos.model.Particular;
import com.abastos.service.DataException;

public interface ParticularDAO {
	public Particular findById(Connection connection, Long idParticular) throws DataException;
	public Particular findByEmail(Connection connection, String email) throws DataException;
	public Particular findByAlias(Connection connection, String alias) throws DataException;
	public Integer findPuntos(Connection connection, Long idParticular) throws DataException;
	public Particular create( Connection connection, Particular perfilParti) throws DataException;
	public Particular update(Connection connection, Particular perfilParti) throws DataException;
	public boolean updatePuntos(Connection connection, Long idParticular, Integer puntos) throws DataException;
	public boolean updateAlta(Connection connection, Long idParticular) throws DataException;
	public boolean softDelete(Connection connection, Long idParticular) throws DataException;
}
