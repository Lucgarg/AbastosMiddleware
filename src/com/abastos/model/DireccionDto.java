package com.abastos.model;




public class DireccionDto extends ValueObject {
	private Long idDireccion;
	private String calle;
	private Integer numero;
	private String piso;
	private Integer idTipoDireccion;
	private String nombreDireccion;
	private Long idLocalidad;
	private String localidad;
	private Long idComunidadAutonoma;
	private String comunidadAutonoma;
	private Long idPais;
	private String pais;
	private Long idProvincia;
	private String provincia;
	private String codigoPostal;

	public DireccionDto() {
		
	}

	public Long getIdDireccion() {
		return idDireccion;
	}

	public String getCalle() {
		return calle;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getPiso() {
		return piso;
	}

	public Integer getIdTipoDireccion() {
		return idTipoDireccion;
	}

	public String getNombreDireccion() {
		return nombreDireccion;
	}

	public Long getIdLocalidad() {
		return idLocalidad;
	}

	public String getLocalidad() {
		return localidad;
	}

	public Long getIdComunidadAutonoma() {
		return idComunidadAutonoma;
	}

	public String getComunidadAutonoma() {
		return comunidadAutonoma;
	}

	public Long getIdPais() {
		return idPais;
	}

	public String getPais() {
		return pais;
	}

	public Long getIdProvincia() {
		return idProvincia;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setIdDireccion(Long idDireccion) {
		this.idDireccion = idDireccion;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public void setIdTipoDireccion(Integer idTipoDireccion) {
		this.idTipoDireccion = idTipoDireccion;
	}

	public void setNombreDireccion(String nombreDireccion) {
		this.nombreDireccion = nombreDireccion;
	}

	public void setIdLocalidad(Long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public void setIdComunidadAutonoma(Long idComunidadAutonoma) {
		this.idComunidadAutonoma = idComunidadAutonoma;
	}

	public void setComunidadAutonoma(String comunidadAutonoma) {
		this.comunidadAutonoma = comunidadAutonoma;
	}

	public void setIdPais(Long idPais) {
		this.idPais = idPais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	


}
