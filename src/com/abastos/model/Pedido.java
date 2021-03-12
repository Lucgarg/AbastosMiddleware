package com.abastos.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido extends ValueObject{
	private Long id;
	private Date fechaPedido;
	private Double precioTotal;
	private Boolean aplicarDescuento;
	private List<LineaPedido> lineaPedido = null; 
	private Long idParticular;
	private Character idEstado;


	public Pedido() {
		lineaPedido = new ArrayList<LineaPedido>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}



	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Boolean getAplicarDescuento() {
		return aplicarDescuento;
	}

	public void setAplicarDescuento(Boolean aplicarDescuento) {
		this.aplicarDescuento = aplicarDescuento;
	}

	public List<LineaPedido> getLineaPedido() {
		return lineaPedido;
	}

	public void setLineaPedido(List<LineaPedido> lineaPedido) {
		this.lineaPedido = lineaPedido;
	}

	public Long getIdParticular() {
		return idParticular;
	}

	public void setIdParticular(Long idParticular) {
		this.idParticular = idParticular;
	}

	public Character getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Character idEstado) {
		this.idEstado = idEstado;
	}

	public void add(LineaPedido lineaPedido) {
		this.lineaPedido.add(lineaPedido);
	}






}
