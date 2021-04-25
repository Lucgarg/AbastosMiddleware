package com.abastos.service;

import com.abastos.model.Contenido;

public interface ContenidoService {
	public Contenido findById(  Long id) throws DataException;
	public Contenido findByIdTipo( Long id) throws DataException;
	public Contenido create(  Contenido cont) throws DataException;
	public Contenido update( Contenido cont)throws DataException;
	public boolean hardDelete( Long idContenido) throws DataException;
}
