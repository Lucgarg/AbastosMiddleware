package com.abastos.dao;



import java.sql.Connection;
import java.util.List;

import com.abastos.model.LineaLista;
import com.abastos.service.DataException;

public interface LineaListaDAO {
	public List<LineaLista> findByIdListaDeseos(Connection connection, Long idLinea) throws DataException;
	public LineaLista findById(Connection connection, Long idLista, Long idProducto) throws DataException;
	public void create(Connection connection, LineaLista lineaLista) throws DataException;
	public boolean update(Connection connection, LineaLista lineaLista) throws DataException;
	public boolean delete(Connection connection, Long idLista, Long idProducto) throws DataException;
	public boolean deleteByIdParticular(Connection connection, Long idParticular)throws DataException;

}
