package com.abastos.service.impl;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.LineaPedidoDAO;
import com.abastos.dao.jdbc.LineaPedidoDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.LineaPedido;
import com.abastos.service.DataException;
import com.abastos.service.LineaPedidoService;
import com.abastos.service.ProductoService;
import com.abastos.service.utils.ServiceUtils;

public class LineaPedidoServiceImpl implements LineaPedidoService {
	private static Logger logger = LogManager.getLogger(LineaPedidoServiceImpl.class);
	private LineaPedidoDAO lineaPedidoDAO;
	private ProductoService productoService;

	public LineaPedidoServiceImpl() {
		lineaPedidoDAO = new LineaPedidoDAOImpl();
		productoService = new ProductoServiceImpl();
	}


	@Override
	public List<LineaPedido> findByIdPedido(Long idPedido) throws DataException {
		logger.info("Iniciando findByIdPedido");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<LineaPedido> lineaPedido  = null;
		try {
			connection.setAutoCommit(false);
			lineaPedido = lineaPedidoDAO.findByIdPedido(connection, idPedido);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return lineaPedido;
	}
	@Override
	public LineaPedido findById(Long idPed, Long idProd) throws DataException {
		logger.info("Iniciando findById");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		LineaPedido lineaPedido  = null;
		try {
			connection.setAutoCommit(false);
			lineaPedido = lineaPedidoDAO.findById(connection, idPed, idProd);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return lineaPedido;
	}
	@Override
	public void create(LineaPedido lineaPedido) throws DataException {
		logger.info("Creando lineaPedido..");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		try {
			connection.setAutoCommit(false);
			lineaPedido.setPrecioFinal(calcPrecio(lineaPedido));
			lineaPedidoDAO.create(connection, lineaPedido);
			commit = true;
			logger.info(new StringBuilder().append("lineaPedido creada").append(lineaPedido.getIdPedido())
					.append(" ").append(lineaPedido.getIdProducto()).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}

	}
	public Double calcPrecio(LineaPedido lineaPedido) throws DataException{
		logger.info("Iniciando calcPrecio lineaPedido...");
		Double descuento = null;
	
		if(lineaPedido.getIdOferta() != null) {
			if(lineaPedido.getIdTipoOferta() == 2) {

				if(lineaPedido.getNumeroUnidades() >= lineaPedido.getDenominador()) {
					int producDescuento = (lineaPedido.getNumeroUnidades()/lineaPedido.getDenominador())
							*(lineaPedido.getDenominador() - lineaPedido.getNumerador());
					
					double desct =  lineaPedido.getDescuentoFijo() == 0.0? 
							lineaPedido.getDescuentoPcn()/100: lineaPedido.getDescuentoFijo();
							descuento = lineaPedido.getDescuentoFijo() == 0.0?  lineaPedido.getPrecio() * desct:
								desct;
							descuento = ServiceUtils.round(descuento,2);
						
							return ServiceUtils.round((lineaPedido.getPrecio()*lineaPedido.getNumeroUnidades()) - (descuento * producDescuento),2);
				}
			}}
		
		
		return ServiceUtils.round(lineaPedido.getPrecio() * lineaPedido.getNumeroUnidades(),2);
	}


}
