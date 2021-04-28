package com.abastos.service.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ServiceUtils {

	public ServiceUtils() {
		
	}

	/**
	 * @param a 
	 * @param num numero de cifras decimales
	 * @return valor aproximado con el número de cifras decimales indicadas
	 */
	public static double round(Double a, Integer num) {
		 
		    BigDecimal bd = new BigDecimal(Double.toString(a));
		    bd = bd.setScale(num, RoundingMode.HALF_UP);
		    return bd.doubleValue();
		}
	}

