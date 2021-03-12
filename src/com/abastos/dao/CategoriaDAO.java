package com.abastos.dao;

import java.sql.Connection;
import java.util.List;

import com.abastos.model.Categoria;
import com.abastos.service.DataException;

public interface CategoriaDAO {
	/**
	 * llama al metodo findByIdPadre
	 * @return la lista completa de categorías
	 * @throws DataException
	 */
	public List<Categoria> findRoot(Connection connection, String idioma) throws DataException;
	/**
	 * Buscar una categoria por id;
	 * @param connection
	 * @param idCategoria
	 * @param idioma
	 * @return Categoria
	 * @throws DataException
	 */
	public Categoria findById(Connection connection , Integer idCategoria, String idioma) throws DataException;
	/**
	 * 
	 * @param idCatPadre
	 * @return devuelve la lista de las categorías que tienen como categoria padre el parametro
	 * @throws DataException
	 */
	public List<Categoria> findByIdPadre(Connection connection ,Integer idCatPadre, String idioma) throws DataException;
	/**
	 * 
	 * @param idTienda
	 * @return la lista de categorías que tienen productos de una determinada tienda
	 * @throws DataException
	 */
	public List<Categoria> findByTienda(Connection connection, Long idTienda, String idioma) throws DataException;
	/**
	 * 
	 * @param id
	 * @return todas las categoria hojas de un determinado id
	 * @throws DataException
	 */
	public List<Integer> findByCategoriaHoja(Connection connection, Integer id, String idioma)throws DataException;

}
