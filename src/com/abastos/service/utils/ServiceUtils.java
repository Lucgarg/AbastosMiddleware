package com.abastos.service.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ServiceUtils {

	public ServiceUtils() {
		// TODO Auto-generated constructor stub
	}
	public static double round(Double a, Integer num) {
		 
		    BigDecimal bd = new BigDecimal(Double.toString(a));
		    bd = bd.setScale(num, RoundingMode.HALF_UP);
		    return bd.doubleValue();
		}
	}

