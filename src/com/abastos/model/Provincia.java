package com.abastos.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Provincia {
	private Long id;
	private String nombre;
	private Long idComunidadAutonoma;

	public Provincia() {
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

	public Long getIdComunidadAutonoma() {
		return idComunidadAutonoma;
	}

	public void setIdComunidadAutonoma(Long idComunidadAutonoma) {
		this.idComunidadAutonoma = idComunidadAutonoma;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("Provincia", nombre)
				.append("ComunidadAutonoma", idComunidadAutonoma.toString()).toString();
	}
}
