package com.abastos.service;


import java.util.List;


import com.abastos.model.LineaPedido;


public interface LineaPedidoService {

	public List<LineaPedido> findByIdPedido( Long idPedido) throws DataException;
	public LineaPedido findById(Long idPed, Long idProd) throws DataException;
	public Double calcPrecio(LineaPedido lineaPedido) throws DataException;
	public void create(LineaPedido lineaPedido) throws DataException;

}
