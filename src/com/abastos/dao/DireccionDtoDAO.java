package com.abastos.dao;

import java.sql.Connection;
import java.util.List;

import com.abastos.model.DireccionDto;
import com.abastos.service.DataException;

public interface DireccionDtoDAO {
	public DireccionDto findByIdEmp(Connection connection, Long idEmpresa) throws DataException;
	public List<DireccionDto> findByIdPart(Connection connection, Long idParticular) throws DataException;
	public DireccionDto findByIdTienda(Connection connection, Long idTienda) throws DataException;
	
	
	
}
