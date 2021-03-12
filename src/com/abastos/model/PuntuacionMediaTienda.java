package com.abastos.model;

public class PuntuacionMediaTienda extends ValueObject{
	private Long idTienda;
	private Double valoracionAtncClienteMedia;
	private Double valoracionPrecioMedia;
	private Double valoracionServDomicilioMedia;
	private Double valoracionMedia;

	public PuntuacionMediaTienda() {
	}

	public Long getIdTienda() {
		return idTienda;
	}

	public void setIdTienda(Long idTienda) {
		this.idTienda = idTienda;
	}

	public Double getValoracionAtncClienteMedia() {
		return valoracionAtncClienteMedia;
	}

	public void setValoracionAtncClienteMedia(Double valoracionAtncClienteMedia) {
		this.valoracionAtncClienteMedia = valoracionAtncClienteMedia;
	}

	public Double getValoracionPrecioMedia() {
		return valoracionPrecioMedia;
	}

	public void setValoracionPrecioMedia(Double valoracionPrecioMedia) {
		this.valoracionPrecioMedia = valoracionPrecioMedia;
	}

	public Double getValoracionServDomicilioMedia() {
		return valoracionServDomicilioMedia;
	}

	public void setValoracionServDomicilioMedia(Double valoracionServDomicilioMedia) {
		this.valoracionServDomicilioMedia = valoracionServDomicilioMedia;
	}

	public Double getValoracionMedia() {
		return valoracionMedia;
	}

	public void setValoracionMedia(Double valoracionMedia) {
		this.valoracionMedia = valoracionMedia;
	}

}
