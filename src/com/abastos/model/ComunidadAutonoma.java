package com.abastos.model;



public class ComunidadAutonoma extends ValueObject {
	private Long id;
	private String nombre;
	private Integer idPais;

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
	public Integer getPais() {
		return idPais;
	}
	public void setPais(Integer pais) {
		this.idPais = pais;
	}

}	
