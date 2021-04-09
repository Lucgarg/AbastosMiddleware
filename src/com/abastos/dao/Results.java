package com.abastos.dao;

import java.util.List;

import com.abastos.model.ValueObject;
/**
 * DTO, wrapper, o como se le quiera llamar, para los resultados
 * de una consulta, y el total de resultados.
 * 
 * Orientado a paginaci�n.
 * 
 * Si estamos en la capa de datos e integraci�n deberiamos llamarle
 * ResultsDTO o similar, pero realmente vale para calquier capa... 
 * 
 * @author https://www.linkedin.com/in/joseantoniolopezperez
 * @version 0.2
 */
public class Results<T> extends ValueObject {

	
		
		private List<T> page = null;
		private int startIndex = 0;
		private int total = 0;
		// Preguntas para los alumnos:
		// - Por qu� no est� el count para el tama�o de la pagina?
		// - Por qu� est� el start index, si realmente es un parametro que se
		//   indicara en la peticion?
		
		public Results(List<T> page, int startIndex, int total) {
			setPage(page);
			setStartIndex(startIndex);
			setTotal(total);		
		}

		public List<T> getPage() {
			return page;
		}

		public void setPage(List<T> page) {
			this.page = page;
		}

		public int getStartIndex() {
			return startIndex;
		}

		public void setStartIndex(int startIndex) {
			this.startIndex = startIndex;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}
}
