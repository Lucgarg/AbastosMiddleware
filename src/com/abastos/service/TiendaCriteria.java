package com.abastos.service;

public class TiendaCriteria {
		private Long idTienda;
		private String nombre;
		private Boolean envioDomicilio;
		private Integer categoria;
		private Long idLocalidad;
		
		
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
		
		
}
