package com.abastos.model;

public class Contenido extends ValueObject{
	private Long id;
	private String nombre;
	private String template;
	private Integer tipoContenido;

	public Contenido() {
		
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTemplate() {
		return template;
	}

	public Integer getTipoContenido() {
		return tipoContenido;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public void setTipoContenido(Integer tipoContenido) {
		this.tipoContenido = tipoContenido;
	}
	

}
