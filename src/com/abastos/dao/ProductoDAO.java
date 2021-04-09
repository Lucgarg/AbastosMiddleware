package com.abastos.dao;

import java.sql.Connection;
import java.util.List;

import com.abastos.model.Producto;
import com.abastos.service.DataException;
import com.abastos.service.ProductoCriteria;

public interface ProductoDAO {
	
	public Results<Producto> findBy(Connection connection, ProductoCriteria producto, String idioma, int startIndex, int count) throws DataException;
	public Producto findById(Connection connection, Long idProducto, String idioma) throws DataException;
	public Producto create(Connection connection, Producto producto) throws DataException;
	public Producto update(Connection connection, Producto producto, String idioma) throws DataException;
	public Integer count(Connection connection, Producto producto) throws DataException;
	public void updateStock(Connection connection, Long idProducto, Integer stock)throws DataException;
	public boolean softDelete(Connection connection, Long idProducto) throws DataException;
	public boolean softDeleteByTienda(Connection connection, Long idTienda) throws DataException;

	
	
	
								
	
}
