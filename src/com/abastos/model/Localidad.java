package com.abastos.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Localidad {
	private Long id;
	private String nombre;
	private Long idProvincia;

	public Localidad() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("localidad", nombre).

				toString();
	}
}
