package com.abastos.model;

public class ProductoIdioma {
	private Long idProducto;
	private String idIdioma;
	private String nombreProducto;
	private String caractProduct;
	public ProductoIdioma() {
	
	}
	public Long getIdProducto() {
		return idProducto;
	}
	public String getIdIdioma() {
		return idIdioma;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public String getCaractProduct() {
		return caractProduct;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public void setIdIdioma(String idIdioma) {
		this.idIdioma = idIdioma;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public void setCaractProduct(String caractProduct) {
		this.caractProduct = caractProduct;
	}

}
