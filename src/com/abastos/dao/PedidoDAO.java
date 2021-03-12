package com.abastos.dao;


import java.sql.Connection;
import java.util.List;

import com.abastos.model.Pedido;
import com.abastos.service.DataException;

public interface PedidoDAO {
	public Pedido findById(Connection connection, Long idPedido) throws DataException;
	public List<Pedido> findByIdParticular(Connection connection, Long idParticular) throws DataException;
	public Pedido create(Connection connection, Pedido pedido) throws DataException;
	public Pedido update(Connection connection, Pedido pedido) throws DataException;
	public boolean updateEstado(Connection connection, char estado, Long idPedido)throws DataException;

	
}
