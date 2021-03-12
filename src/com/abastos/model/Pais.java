package com.abastos.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Pais {
	private Integer id;
	private String nombre;

	public Pais() {

	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String toString() {
		return new ToStringBuilder(this).
				append("id", id).
				append("nombre", nombre).
				toString();
	}
}
