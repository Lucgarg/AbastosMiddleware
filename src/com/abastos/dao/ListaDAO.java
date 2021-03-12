package com.abastos.dao;



import java.sql.Connection;
import java.util.List;

import com.abastos.model.Lista;
import com.abastos.service.DataException;

public interface ListaDAO {
	public Lista findById(Connection connection, Long idLista) throws DataException;
	public List<Lista> findByIdParticular(Connection connection, Long idParticular) throws DataException;
	public Lista create(Connection connection, Lista lista) throws DataException;
	public Lista update(Connection connection, Lista lista) throws DataException;
	public boolean delete(Connection connection, Long idLista) throws DataException;
	public boolean deleteByIdParticular(Connection connection, Long idParticular)throws DataException;
	
}
