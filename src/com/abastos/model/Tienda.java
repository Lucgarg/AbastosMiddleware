package com.abastos.model;

import java.util.Date;




public class Tienda extends ValueObject{
	private Long id;
	private String nombre;
	private String numeroTelefono;
	private String numeroMovil;
	private Boolean envioDomicilio;
	private String email;
	private Double precioMedio;
	private Integer categoria;
	private Date fechaCreacion;
	private PuntuacionMediaTienda puntuacionMedia;
	private DireccionDto direccionDto;
	private Long idEmpresa;
	private Long idContenido;
	
	public Tienda() {

	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public String getNumeroMovil() {
		return numeroMovil;
	}

	public Boolean getEnvioDomicilio() {
		return envioDomicilio;
	}

	public String getEmail() {
		return email;
	}

	public Double getPrecioMedio() {
		return precioMedio;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public PuntuacionMediaTienda getPuntuacionMedia() {
		return puntuacionMedia;
	}

	public DireccionDto getDireccionDto() {
		return direccionDto;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public Long getIdContenido() {
		return idContenido;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public void setNumeroMovil(String numeroMovil) {
		this.numeroMovil = numeroMovil;
	}

	public void setEnvioDomicilio(Boolean envioDomicilio) {
		this.envioDomicilio = envioDomicilio;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPrecioMedio(Double precioMedio) {
		this.precioMedio = precioMedio;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public void setPuntuacionMedia(PuntuacionMediaTienda puntuacionMedia) {
		this.puntuacionMedia = puntuacionMedia;
	}

	public void setDireccionDto(DireccionDto direccionDto) {
		this.direccionDto = direccionDto;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public void setIdContenido(Long idContenido) {
		this.idContenido = idContenido;
	}
	

}
