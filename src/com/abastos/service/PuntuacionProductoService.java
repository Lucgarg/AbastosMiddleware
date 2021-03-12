package com.abastos.service;


import java.util.List;

public interface PuntuacionProductoService {
	public List<Integer> findById( Long idProducto, Long idParticular)throws DataException;
	public Double findMedia(Long idProducto)throws DataException;
	public void create(Long idParticular,  Long idProducto, Integer puntuPro)throws DataException;
	public boolean update( Long idParticular,  Long idProducto, Integer puntuPro)throws DataException;
	public boolean delete( Long idParticular, Long idProducto)throws DataException;
}
