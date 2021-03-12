package com.abastos.model;

public class ContenidoProducto extends ValueObject{
	private Contenido contenido;
	private Character idTipoFoto;
	private Long idProducto;
	public ContenidoProducto() {
		
	}
	
	public Contenido getContenido() {
		return contenido;
	}
	public Character getIdTipoFoto() {
		return idTipoFoto;
	}
	public Long getIdProducto() {
		return idProducto;
	}
	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
	}
	public void setIdTipoFoto(Character idTipoFoto) {
		this.idTipoFoto = idTipoFoto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}



}
