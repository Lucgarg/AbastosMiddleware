package com.abastos.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Producto extends ValueObject{
	private Long id;
	private String nombre;
	private Double precio;
	private Double precioFinal;
	private String caracteristicas;
	private Character tipoOrigen;
	private Date fechaCreacion;
	private Double valoracion;
	private Oferta oferta;
	private Long idTienda;
	private Integer Stock;
	private Integer idCategoria;
	private List<ContenidoProducto> contenidoProducto;
	private List<ProductoIdioma> productoIdioma;
	public Producto() {
		contenidoProducto = new ArrayList<ContenidoProducto>();
		productoIdioma = new ArrayList<ProductoIdioma>();
	}
	public Long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public Double getPrecio() {
		return precio;
	}
	public String getCaracteristicas() {
		return caracteristicas;
	}
	public Character getTipoOrigen() {
		return tipoOrigen;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public Double getValoracion() {
		return valoracion;
	}
	public Oferta getOferta() {
		return oferta;
	}
	public Long getIdTienda() {
		return idTienda;
	}
	public Integer getStock() {
		return Stock;
	}
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public List<ContenidoProducto> getContenidoProducto() {
		return contenidoProducto;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public void setTipoOrigen(Character tipoOrigen) {
		this.tipoOrigen = tipoOrigen;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public void setValoracion(Double valoracion) {
		this.valoracion = valoracion;
	}
	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
	public void setIdTienda(Long idTienda) {
		this.idTienda = idTienda;
	}
	public void setStock(Integer stock) {
		Stock = stock;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public void setContenidoProducto(List<ContenidoProducto> contenidoProducto) {
		this.contenidoProducto = contenidoProducto;
	}

	public Double getPrecioFinal() {
		return precioFinal;
	}
	public void setPrecioFinal(Double precioFinal) {
		this.precioFinal = precioFinal;
	}
	public List<ProductoIdioma> getProductoIdioma() {
		return productoIdioma;
	}
	public void setProductoIdioma(List<ProductoIdioma> productoIdioma) {
		this.productoIdioma = productoIdioma;
	}
	public void add(ContenidoProducto contenidoProducto) {
		this.contenidoProducto.add(contenidoProducto);
	}
	public void add(ProductoIdioma productoIdioma) {
		this.productoIdioma.add(productoIdioma);
	}
	
	

}
