package com.abastos.dao;


import java.sql.Connection;
import java.util.List;

import com.abastos.model.Oferta;
import com.abastos.service.DataException;

public interface OfertaDAO {
	public Oferta findById(Connection connection, Long idOferta)throws DataException;
	public List<Oferta> findByIdEmpresa(Connection connection, Long idEmpresa)throws DataException;
	public Oferta findByIdProducto(Connection connection, Long idProducto) throws DataException;
	public Oferta create(Connection connection, Oferta oferta) throws DataException;
	public Oferta update(Connection connection, Oferta oferta) throws DataException;
	
}
