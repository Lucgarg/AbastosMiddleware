package com.abastos.model;

import java.util.Date;

public class Oferta extends ValueObject{
	private Long id;
	private Long idEmpresa;
	private String nombreOferta;
	private Double descuentoPcn;
	private Double descuentoFijo;
	private Integer numerador;
	private Integer denominador;
	private Integer idTipoOferta;
	private String nombreTipoOfer;
	private Date fechaDesde;
	private Date fechaHasta;
	private Long idProdOferta;


	public Oferta() {
	}

	public Long getId() {
		return id;
	}

	public String getNombreOferta() {
		return nombreOferta;
	}

	public Double getDescuentoPcn() {
		return descuentoPcn;
	}

	public Double getDescuentoFijo() {
		return descuentoFijo;
	}

	public Integer getNumerador() {
		return numerador;
	}

	public Integer getDenominador() {
		return denominador;
	}

	public Integer getIdTipoOferta() {
		return idTipoOferta;
	}

	public String getNombreTipoOfer() {
		return nombreTipoOfer;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public Long getIdProdOferta() {
		return idProdOferta;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombreOferta(String nombreOferta) {
		this.nombreOferta = nombreOferta;
	}

	public void setDescuentoPcn(Double descuentoPcn) {
		this.descuentoPcn = descuentoPcn;
	}

	public void setDescuentoFijo(Double descuentoFijo) {
		this.descuentoFijo = descuentoFijo;
	}

	public void setNumerador(Integer numerador) {
		this.numerador = numerador;
	}

	public void setDenominador(Integer denominador) {
		this.denominador = denominador;
	}

	public void setIdTipoOferta(Integer idTipoOferta) {
		this.idTipoOferta = idTipoOferta;
	}

	public void setNombreTipoOfer(String nombreTipoOfer) {
		this.nombreTipoOfer = nombreTipoOfer;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public void setIdProdOferta(Long idProdOferta) {
		this.idProdOferta = idProdOferta;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}



	
	
}
