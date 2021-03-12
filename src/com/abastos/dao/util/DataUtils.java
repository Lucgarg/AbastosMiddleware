package com.abastos.dao.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
	public static Date formatDate(String a) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy H:m:s");
		Date data = df.parse(a);
		return data;
	}
}	
