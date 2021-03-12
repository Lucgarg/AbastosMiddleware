package com.abastos.service;

import com.abastos.model.Empresa;
import com.abastos.service.exceptions.ServiceException;

public interface EmpresaService {
	public Empresa login(String email, String alias, String password) throws DataException, ServiceException;
	public Empresa registrar(Empresa empresa) throws DataException,  ServiceException;
	public Empresa findById(Long idEmpresa)throws DataException;
	public Empresa update( Empresa empresa) throws DataException;
	public boolean updateAlta( Long idParticular) throws DataException;
	public boolean delete(Long idEmpresa) throws DataException;

}
