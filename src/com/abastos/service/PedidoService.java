package com.abastos.service;

import java.sql.Connection;
import java.util.List;

import com.abastos.model.Pedido;
import com.abastos.service.exceptions.MailException;

public interface PedidoService {
	

	public Pedido findById( Long idPedido) throws DataException;
	public List<Pedido> findByIdParticular(Long idParticular) throws DataException;
	public Double calcPrecio (Pedido pedido)throws DataException;
	public Integer calcPuntos(Double precio)throws DataException;
	public Pedido create(Pedido pedido) throws DataException;
	public boolean updateEstado(char estado, Long idPedido)throws DataException;

	
}
