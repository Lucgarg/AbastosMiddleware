package com.abastos.model;

import java.util.Date;

public class PuntuacionTienda extends ValueObject{

	private Long idPerfilParticular;
	private Long idTienda;
	private Integer valoracionAtncCliente;
	private Integer valoracionPrecio;
	private Integer valoracionServDomicilio;
	private Date fechaValoracion;
	public PuntuacionTienda() {}
	public Long getIdPerfilParticular() {
		return idPerfilParticular;
	}
	public Long getIdTienda() {
		return idTienda;
	}
	public Integer getValoracionAtncCliente() {
		return valoracionAtncCliente;
	}
	public Integer getValoracionPrecio() {
		return valoracionPrecio;
	}
	public Integer getValoracionServDomicilio() {
		return valoracionServDomicilio;
	}
	public Date getFechaValoracion() {
		return fechaValoracion;
	}
	public void setIdPerfilParticular(Long idPerfilParticular) {
		this.idPerfilParticular = idPerfilParticular;
	}
	public void setIdTienda(Long idTienda) {
		this.idTienda = idTienda;
	}
	public void setValoracionAtncCliente(Integer valoracionAtncCliente) {
		this.valoracionAtncCliente = valoracionAtncCliente;
	}
	public void setValoracionPrecio(Integer valoracionPrecio) {
		this.valoracionPrecio = valoracionPrecio;
	}
	public void setValoracionServDomicilio(Integer valoracionServDomicilio) {
		this.valoracionServDomicilio = valoracionServDomicilio;
	}
	public void setFechaValoracion(Date fechaValoracion) {
		this.fechaValoracion = fechaValoracion;
	}
	

	

}
