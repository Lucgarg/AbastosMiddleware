package com.abastos.service;


import java.util.List;

import com.abastos.model.Localidad;

public interface LocalidadService {
	public List<Localidad> findByIdProvincia( Long idProvincia) throws DataException;
	public Localidad findByIdLocalidad(Long idLocalidad) throws DataException;
	public List<Localidad> findByTiendas(Long idProvincia) throws DataException;
}
