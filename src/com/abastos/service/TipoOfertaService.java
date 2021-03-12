package com.abastos.service;

import java.util.List;

import com.abastos.model.TipoOferta;

public interface TipoOfertaService {
	public List<TipoOferta>findAll()throws DataException;
	
}
