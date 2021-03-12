package com.abastos.dao;

import java.sql.Connection;
import java.util.List;

import com.abastos.service.DataException;

public interface PuntuacionProductoDAO {
	public List<Integer> findByIdProducto(Connection connection, Long idProducto)throws DataException;
	public List<Integer> findByIdParticular(Connection connection, Long idParticular)throws DataException;
	public Double findMedia(Connection connection, Long idProducto)throws DataException;
	public void create(Connection connection, Long idParticular,  Long idProducto, Integer puntuPro)throws DataException;
	public boolean update(Connection connection, Long idParticular,  Long idProducto, Integer puntuPro)throws DataException;
	public boolean delete(Connection connection, Long idParticular, Long idProducto)throws DataException;
	
	
}
