package com.abastos.dao;

import java.sql.Connection;
import java.util.List;

import com.abastos.model.Tienda;
import com.abastos.service.DataException;
import com.abastos.service.TiendaCriteria;

public interface TiendaDAO {
		public Tienda findById(Connection connection, Long idTienda) throws DataException;
		public List<Tienda> findByIdEmpresa(Connection connection, Long idEmpresa) throws DataException;
		public Results<Tienda> findByCriteria(Connection connection, TiendaCriteria tiendaCri, int startIndex, int count) throws DataException;
		public Integer count(Connection connection, Tienda tienda) throws DataException;
		public Tienda create(Connection connection, Tienda tienda) throws DataException;
		public Tienda update(Connection connection, Tienda tienda) throws DataException;
		public boolean softDelete(Connection connection, Long idTienda) throws DataException;
		public boolean softDeleteByEmpresa(Connection connction, Long idEmpresa) throws DataException;
}


