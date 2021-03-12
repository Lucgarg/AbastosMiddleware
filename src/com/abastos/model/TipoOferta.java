package com.abastos.model;

public class TipoOferta extends ValueObject{
	private Integer id;
	private String nombre;
	
	public TipoOferta() {}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
