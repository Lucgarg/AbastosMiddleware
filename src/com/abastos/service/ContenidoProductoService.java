package com.abastos.service;

import java.sql.Connection;
import java.util.List;

import com.abastos.model.ContenidoProducto;

public interface ContenidoProductoService {
	public List<ContenidoProducto> findByImagenes( Long idProducto)throws DataException;
}
