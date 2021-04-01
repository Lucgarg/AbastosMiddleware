package com.abastos.dao;

import java.sql.Connection;
import java.util.List;

import com.abastos.model.PuntuacionMediaTienda;
import com.abastos.model.PuntuacionTienda;
import com.abastos.service.DataException;


public interface PuntuacionTiendaDAO {
	public List<PuntuacionTienda> findByIdTienda(Connection connection, Long idTienda)throws DataException;
	public List<PuntuacionTienda> findByIdParticular(Connection connection, Long idParticular)throws DataException;
	public PuntuacionTienda findPuntuacion(Connection connection ,Long idParticular, Long idTienda) throws DataException;
	public PuntuacionMediaTienda findMedia(Connection connection, Long idTienda)throws DataException;
	public void create(Connection connection, PuntuacionTienda puntuacion)throws DataException;
	public boolean update(Connection connection, PuntuacionTienda puntuacion)throws DataException;
	public boolean delete(Connection connection, Long idParticular, Long idTienda) throws DataException;
	
}
