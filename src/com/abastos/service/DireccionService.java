package com.abastos.service;

import java.sql.Connection;

import com.abastos.model.Direccion;
import com.abastos.model.DireccionDto;

public interface DireccionService {
	public Direccion findById( Long idDireccion) throws DataException;
	public Direccion createDireccion(Direccion direccion)throws DataException;
	public Direccion createDireccion( DireccionDto direccion)throws DataException;
	public Direccion update(Direccion direccion) throws DataException;
	public boolean hardDelete( Long idDireccion) throws DataException;
}
