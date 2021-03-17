package com.abastos.service;



import java.sql.Connection;
import java.util.List;

import com.abastos.model.Oferta;

public interface OfertaService {
	//eliminar oferta cuando se termina la fecha de vigencia
	public Oferta findById(Long idOferta)throws DataException;
	public List<Oferta> findByIdEmpresa(Long idEmpresa)throws DataException;
	public Oferta create(Oferta oferta) throws DataException;
	public Oferta update(Oferta oferta) throws DataException;

}
