package com.abastos.service;

import java.util.List;

import com.abastos.model.Categoria;

public interface CategoriaService {
	public List<Categoria> findRoot(String idioma) throws DataException;
	public Categoria findById(Integer idCategoria, String idioma) throws DataException;
	/**
	 * 
	 * @param idCatPadre
	 * @return devuelve la lista de las categorías que tienen como categoria padre el parametro
	 * @throws DataException
	 */
	public List<Categoria> findByIdPadre(Integer idCatPadre, String idioma) throws DataException;
	/**
	 * 
	 * @param idTienda
	 * @return la lista de categorías que tienen productos de una determinada tienda
	 * @throws DataException
	 */
	public List<Categoria> findByTienda(Long idTienda, String idioma) throws DataException;
	/**
	 * 
	 * @param id
	 * @return todas las categoria hojas de un determinado id
	 * @throws DataException
	 */
	

}
