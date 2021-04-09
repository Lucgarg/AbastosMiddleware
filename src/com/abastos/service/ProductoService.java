package com.abastos.service;


import com.abastos.dao.Results;
import com.abastos.model.Producto;
import com.abastos.service.exceptions.LimitCreationException;

public interface ProductoService {
	public Results<Producto> findBy(ProductoCriteria producto, String idioma, int startIndex, int count) throws DataException;
	public Producto findById(Long idProducto, String idioma) throws DataException;
	public Producto create(Producto producto) throws DataException, LimitCreationException;
	public Producto update( Producto producto, String idioma) throws DataException;
	public void updateStock(Integer stock, Long idProducto) throws DataException;
	public boolean delete(Long idProducto) throws DataException;
	public boolean deleteByIdTienda(Long idTienda) throws DataException;
	
}
