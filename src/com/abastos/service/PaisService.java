package com.abastos.service;


import java.util.List;

import com.abastos.model.Pais;

public interface PaisService {
	public Pais findById( Integer idPais) throws DataException;
	public List<Pais> findByAll()throws DataException;
}
