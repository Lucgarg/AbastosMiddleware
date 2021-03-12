package com.abastos.service;

public class ProductoCriteria {
	private Double predioDesde;
	private Double precioHasta;
	private Character idOrigen;
	private Boolean oferta;
	private Long idTienda;
	private Integer idCategoria;
	private Long idEmpresa;
	public ProductoCriteria() {
		
	}
	public Double getPredioDesde() {
		return predioDesde;
	}
	public void setPredioDesde(Double predioDesde) {
		this.predioDesde = predioDesde;
	}
	public Double getPrecioHasta() {
		return precioHasta;
	}
	public void setPrecioHasta(Double precioHasta) {
		this.precioHasta = precioHasta;
	}
	public Character getIdOrigen() {
		return idOrigen;
	}
	public void setIdOrigen(Character idOrigen) {
		this.idOrigen = idOrigen;
	}
	public Boolean getOferta() {
		return oferta;
	}
	public void setOferta(Boolean oferta) {
		this.oferta = oferta;
	}
	public Long getIdTienda() {
		return idTienda;
	}
	public void setIdTienda(Long idTienda) {
		this.idTienda = idTienda;
	}
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
}
