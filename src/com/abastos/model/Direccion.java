package com.abastos.model;



public class Direccion extends ValueObject{
	private Long id;
	private String calle;
	private Integer numero;
	private String piso;
	private String codigoPostal;
	private Long idLocalidad;
	private Integer tipoDireccion;

	public Direccion() {
	}

	public Long getId() {
		return id;
	}

	public String getCalle() {
		return calle;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getPiso() {
		return piso;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public Long getIdLocalidad() {
		return idLocalidad;
	}

	public Integer getTipoDireccion() {
		return tipoDireccion;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public void setIdLocalidad(Long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public void setTipoDireccion(Integer tipoDireccion) {
		this.tipoDireccion = tipoDireccion;
	}

	

}
