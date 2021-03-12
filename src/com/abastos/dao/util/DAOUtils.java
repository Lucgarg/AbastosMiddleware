package com.abastos.dao.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.abastos.service.DataException;

public class DAOUtils {
	public static String listToString(List<Long> a) {
	StringBuilder b = new StringBuilder();
		
		for(Long c : a) {
			b.append(c + ", ");
		}
		return b.delete(b.length()-2, b.length()).toString();
	}
	public static String listTo(List<Integer> list) {
		
		StringBuilder construc = new StringBuilder();
		construc.append("(");
		
		for(Integer c : list) {
			construc.append(c + ",");
		}
		
	
		construc.delete(construc.length()-1, construc.length()).toString();
		construc.append(")");
		return construc.toString();
	}
	
	public static void fillPrepa(PreparedStatement pre, List<Long>a) throws SQLException {
		int i = 1;
		for(Long b : a) {
			pre.setLong(i++, b);
		}
	}

	
}
