package com.abastos.dao;

import java.sql.Connection;
import java.util.List;

import com.abastos.model.ContenidoProducto;
import com.abastos.service.DataException;

public interface ContenidoProductoDAO {

	public List<ContenidoProducto> findByImagenes(Connection connection, Long idProducto)throws DataException;

}
