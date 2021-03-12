package com.abastos.service;

import java.sql.Connection;
import java.util.List;

import com.abastos.model.PuntuacionMediaTienda;
import com.abastos.model.PuntuacionTienda;

public interface PuntuacionTiendaService {
	public List<PuntuacionTienda> findByIdTienda(Long idTienda, Long idParticular)throws DataException;
	public PuntuacionMediaTienda findMedia(Long idTienda)throws DataException;
	public void create( PuntuacionTienda puntuacion)throws DataException;
	public boolean update( PuntuacionTienda puntuacion)throws DataException;
	public boolean delete( Long idParticular, Long idTienda) throws DataException;
}
