package com.abastos.service;


import java.util.List;

import com.abastos.model.Lista;

public interface ListaService {
	public Lista findById( Long idLista) throws DataException;
	public List<Lista> findByIdParticular( Long idParticular) throws DataException;
	public Lista create( Lista lista) throws DataException;
	public Lista update( Lista lista) throws DataException;
	public boolean delete(Long idLista)throws DataException;
	public boolean deleteByIdParticular(Long idParticular)throws DataException;
}
