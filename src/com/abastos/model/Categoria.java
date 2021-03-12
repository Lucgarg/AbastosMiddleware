package com.abastos.model;

import java.util.ArrayList;
import java.util.List;

public class Categoria extends ValueObject {
	private Integer id;
	private String nombre;
	private Integer idCategoriaPadre;
	private List<Categoria> categorias;

	public Categoria() {
		categorias = new ArrayList<Categoria>();
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

	public void setNombre(String nombreCat) {
		this.nombre = nombreCat;
	}

	public Integer getIdCategoriaPadre() {
		return idCategoriaPadre;
	}

	public void setIdCategoriaPadre(Integer idCategoriaPadre) {
		this.idCategoriaPadre = idCategoriaPadre;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
public void add(Categoria categoria) {
	this.categorias.add(categoria);
}



	
}
