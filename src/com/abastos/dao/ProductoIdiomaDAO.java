package com.abastos.dao;

import java.sql.Connection;
import java.util.List;

import com.abastos.model.ProductoIdioma;
import com.abastos.service.DataException;

public interface ProductoIdiomaDAO {
	public List<ProductoIdioma> findBy(Connection connection, Long idProducto) throws DataException;

}
