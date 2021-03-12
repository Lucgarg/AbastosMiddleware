package com.abastos.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lista extends ValueObject{
	private Long id;
	private String nombre;
	private Date fechaCreacion;
	private List<LineaLista> lineaLista = null;
	private Long idParticular;

	public Lista() {
		lineaLista = new ArrayList<LineaLista>();

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

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<LineaLista> getLineaLista() {
		return lineaLista;
	}

	public void setLineaLista(List<LineaLista> lineaLista) {
		this.lineaLista = lineaLista;
	}

	public Long getIdParticular() {
		return idParticular;
	}

	public void setIdParticular(Long idParticular) {
		this.idParticular = idParticular;
	}
	public void add(LineaLista lineaLista) {
		this.lineaLista.add(lineaLista);
	}
}
