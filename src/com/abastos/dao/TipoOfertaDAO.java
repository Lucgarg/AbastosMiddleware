package com.abastos.dao;

import java.sql.Connection;
import java.util.List;

import com.abastos.model.TipoOferta;
import com.abastos.service.DataException;

public interface TipoOfertaDAO {
	public List<TipoOferta> findAll(Connection connection )throws DataException;
}
