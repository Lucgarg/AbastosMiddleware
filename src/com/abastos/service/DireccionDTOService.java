package com.abastos.service;


import java.util.List;

import com.abastos.model.DireccionDto;

public interface DireccionDTOService {
	public DireccionDto findByIdEmp( Long idEmpresa) throws DataException;
	public List<DireccionDto> findByIdPart( Long idParticular) throws DataException;
	public DireccionDto findByIdTienda(Long idTienda) throws DataException;
}
