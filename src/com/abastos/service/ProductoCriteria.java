package com.abastos.service;

public class ProductoCriteria {
	private Double predioDesde;
	private Double precioHasta;
	private Character idOrigen;
	private Boolean oferta;
	private Long idTienda;
	private Integer idCategoria;
	private String nombre;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCategoria == null) ? 0 : idCategoria.hashCode());
		result = prime * result + ((idEmpresa == null) ? 0 : idEmpresa.hashCode());
		result = prime * result + ((idOrigen == null) ? 0 : idOrigen.hashCode());
		result = prime * result + ((idTienda == null) ? 0 : idTienda.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((oferta == null) ? 0 : oferta.hashCode());
		result = prime * result + ((precioHasta == null) ? 0 : precioHasta.hashCode());
		result = prime * result + ((predioDesde == null) ? 0 : predioDesde.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoCriteria other = (ProductoCriteria) obj;
		if (idCategoria == null) {
			if (other.idCategoria != null)
				return false;
		} else if (!idCategoria.equals(other.idCategoria))
			return false;
		if (idEmpresa == null) {
			if (other.idEmpresa != null)
				return false;
		} else if (!idEmpresa.equals(other.idEmpresa))
			return false;
		if (idOrigen == null) {
			if (other.idOrigen != null)
				return false;
		} else if (!idOrigen.equals(other.idOrigen))
			return false;
		if (idTienda == null) {
			if (other.idTienda != null)
				return false;
		} else if (!idTienda.equals(other.idTienda))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (oferta == null) {
			if (other.oferta != null)
				return false;
		} else if (!oferta.equals(other.oferta))
			return false;
		if (precioHasta == null) {
			if (other.precioHasta != null)
				return false;
		} else if (!precioHasta.equals(other.precioHasta))
			return false;
		if (predioDesde == null) {
			if (other.predioDesde != null)
				return false;
		} else if (!predioDesde.equals(other.predioDesde))
			return false;
		return true;
	}
	
}
