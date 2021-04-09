package com.abastos.dao.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	/**
	 * Obtencion del total de filas de un resultSet, sin repetir consulta.
	 * Metodo orientado a implementar paginación.
	 * No existe una solución en el API estandar de JDBC.
	 * Esta es un solución para todas las BD pero NO ES LA MEJOR EN RENDIMIENTO.
	 * Por ello en este caso es habitual usar soluciones propietarias
	 * de cada BD (rownum de Oracle, y similar en MySQL).
	 * (En Hibernate, con ScrollableResults, no lo vemos porque lo implementa con el dialecto de cada DB).
	 * 
	 * Encantado de recibir mensajes son soluciones mejores (válidas para todas las BD): 
	 * @author https://www.linkedin.com/in/joseantoniolopezperez
	 * @version 0.2  
	 */
	public static final int getTotalRows(ResultSet resultSet) throws SQLException {
		int totalRows = 0;
		if(resultSet.last()) {
			totalRows = resultSet.getRow();
		}
		resultSet.beforeFirst();
		return totalRows;
	}
}
