package com.abastos.service;

public class TiendaCriteria {



		private Long idTienda;
		private String nombre;
		private Boolean envioDomicilio;
		private Integer categoria;
		private Long idLocalidad;
		private Long idPais;
		private Long idComunidad;
		private Long idProvincia;
		private Long idEmpresa;
		
		public TiendaCriteria() {
			
		}


		public Long getIdTienda() {
			return idTienda;
		}


		public String getNombre() {
			return nombre;
		}


		public Boolean getEnvioDomicilio() {
			return envioDomicilio;
		}


		public Integer getCategoria() {
			return categoria;
		}


		public Long getIdLocalidad() {
			return idLocalidad;
		}


		public void setIdTienda(Long idTienda) {
			this.idTienda = idTienda;
		}


		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public void setEnvioDomicilio(Boolean envioDomicilio) {
			this.envioDomicilio = envioDomicilio;
		}


		public void setCategoria(Integer categoria) {
			this.categoria = categoria;
		}


		public void setIdLocalidad(Long idLocalidad) {
			this.idLocalidad = idLocalidad;
		}


		public Long getIdEmpresa() {
			return idEmpresa;
		}


		public void setIdEmpresa(Long idEmpresa) {
			this.idEmpresa = idEmpresa;
		}


		public Long getIdPais() {
			return idPais;
		}


		public void setIdPais(Long idPais) {
			this.idPais = idPais;
		}


		public Long getIdComunidad() {
			return idComunidad;
		}


		public void setIdComunidad(Long idComunidad) {
			this.idComunidad = idComunidad;
		}


		public Long getIdProvincia() {
			return idProvincia;
		}


		public void setIdProvincia(Long idProvincia) {
			this.idProvincia = idProvincia;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
			result = prime * result + ((envioDomicilio == null) ? 0 : envioDomicilio.hashCode());
			result = prime * result + ((idComunidad == null) ? 0 : idComunidad.hashCode());
			result = prime * result + ((idEmpresa == null) ? 0 : idEmpresa.hashCode());
			result = prime * result + ((idLocalidad == null) ? 0 : idLocalidad.hashCode());
			result = prime * result + ((idPais == null) ? 0 : idPais.hashCode());
			result = prime * result + ((idProvincia == null) ? 0 : idProvincia.hashCode());
			result = prime * result + ((idTienda == null) ? 0 : idTienda.hashCode());
			result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
			TiendaCriteria other = (TiendaCriteria) obj;
			if (categoria == null) {
				if (other.categoria != null)
					return false;
			} else if (!categoria.equals(other.categoria))
				return false;
			if (envioDomicilio == null) {
				if (other.envioDomicilio != null)
					return false;
			} else if (!envioDomicilio.equals(other.envioDomicilio))
				return false;
			if (idComunidad == null) {
				if (other.idComunidad != null)
					return false;
			} else if (!idComunidad.equals(other.idComunidad))
				return false;
			if (idEmpresa == null) {
				if (other.idEmpresa != null)
					return false;
			} else if (!idEmpresa.equals(other.idEmpresa))
				return false;
			if (idLocalidad == null) {
				if (other.idLocalidad != null)
					return false;
			} else if (!idLocalidad.equals(other.idLocalidad))
				return false;
			if (idPais == null) {
				if (other.idPais != null)
					return false;
			} else if (!idPais.equals(other.idPais))
				return false;
			if (idProvincia == null) {
				if (other.idProvincia != null)
					return false;
			} else if (!idProvincia.equals(other.idProvincia))
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
			return true;
		}


		



}
