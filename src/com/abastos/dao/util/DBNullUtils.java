package com.abastos.dao.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public  class DBNullUtils {
	public  static void toNull(PreparedStatement p, int pos, String value) throws SQLException{
		if(value == null) {
			p.setNull(pos, Types.VARCHAR);
		}
		else {
			p.setString(pos, value);
		}
	}
	public static void toNull(PreparedStatement p, int pos, Integer value) throws SQLException{
		if(value== 0) {
			p.setNull(pos, Types.INTEGER);
		}
		else {
			p.setInt(pos, value);
		}
	}
	public static void toNull(PreparedStatement p, int pos, Long value) throws SQLException{
		if( value== 0) {
			p.setNull(pos, Types.BIGINT);
		}
		else {
			p.setLong(pos, value);
		}
	}
	public static void toNull(PreparedStatement p, int pos, Character value) throws SQLException{
		if(value == null) {
			p.setNull(pos, Types.CHAR);
		}
		else {
			p.setString(pos, String.valueOf(value));
		}
	}
	public static void toNull(PreparedStatement p, int pos, Double value) throws SQLException{
		if( value== 0.0) {
			p.setNull(pos, Types.DOUBLE);
		}
		else {
			p.setDouble(pos, value);
		}
	}

}
