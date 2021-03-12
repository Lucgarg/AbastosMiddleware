package com.abastos.dao;




import java.sql.Connection;
import java.util.List;

import com.abastos.model.LineaPedido;
import com.abastos.service.DataException;

public interface LineaPedidoDAO {
	public List<LineaPedido> findByIdPedido(Connection connection, Long idPedido) throws DataException;
	public LineaPedido findById(Connection connection, Long idPed, Long idProd) throws DataException;
	public void create(Connection connection, LineaPedido lineaPedido) throws DataException;
	public boolean delete(Connection connection, Long idPedido, Long idProducto) throws DataException;
}
