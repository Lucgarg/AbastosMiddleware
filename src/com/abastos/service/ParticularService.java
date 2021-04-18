package com.abastos.service;



import java.sql.Connection;

import com.abastos.model.Empresa;
import com.abastos.model.Particular;
import com.abastos.service.exceptions.ServiceException;

public interface ParticularService {
	public Particular findById(Long idParticular)throws DataException;
	public Particular findByEmail( String email) throws DataException;
	public Particular findByAlias( String alias) throws DataException;
	public Particular login(String email, String alias, String password) throws DataException, ServiceException;
	public Particular registrar( Particular Parti) throws DataException;
	public Integer findPuntos( Long idParticular) throws DataException;
	public Particular update(Particular Parti) throws DataException;
	public boolean updatePuntos(Long idParticular, Integer puntos) throws DataException;
	public boolean updateAlta( Long idParticular) throws DataException;
	public boolean delete(Long idParticular) throws DataException;
}
