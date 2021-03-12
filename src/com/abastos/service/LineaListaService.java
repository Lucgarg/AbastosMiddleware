package com.abastos.service;


import java.util.List;

import com.abastos.model.LineaLista;

public interface LineaListaService {
	public List<LineaLista> findByIdListaDeseos( Long idLinea) throws DataException;
	public LineaLista findById( Long idLista, Long idProducto) throws DataException;
	public void create(LineaLista lineaLista) throws DataException;
	public boolean update(LineaLista lineaLista) throws DataException;
	public boolean delete(Long idLista, Long idProducto) throws DataException;
	public boolean delete(Long idParticular) throws DataException;

}
