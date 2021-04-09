package com.abastos.service;


import java.util.List;

import com.abastos.dao.Results;
import com.abastos.model.Tienda;
import com.abastos.service.exceptions.LimitCreationException;
import com.abastos.service.exceptions.MailException;

public interface TiendaService {
	public Tienda findById(Long idTienda) throws DataException;
	public List<Tienda> findByIdEmpresa(Long idEmpresa) throws DataException;
	public Results<Tienda> findByCriteria(TiendaCriteria tiendaCri,int startIndex, int count) throws DataException;
	public Tienda create(Tienda tienda) throws DataException, LimitCreationException, MailException;
	public Tienda update(Tienda tienda) throws DataException;
	public boolean delete(Long idTienda) throws DataException;
	public boolean deleteByEmpresa(Long idEmpresa) throws DataException;
	
	
}
