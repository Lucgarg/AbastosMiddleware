package com.abastos.service.utils;

import com.abastos.model.Oferta;

import com.abastos.model.Producto;


public class DescuentoUtils {

	public DescuentoUtils() {
		
	}
	public static Double descuento(Producto product)  {
	
		if(product.getOferta().getDescuentoFijo() != 0) {
			
			return product.getPrecio() - product.getOferta().getDescuentoFijo();
		}
		else {
			
			return product.getPrecio()  - product.getPrecio() * product.getOferta().getDescuentoPcn()/100;
		}
		
	}

}
