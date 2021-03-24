package com.abastos.service.impl;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.ParticularDAO;
import com.abastos.dao.PedidoDAO;
import com.abastos.dao.ProductoDAO;
import com.abastos.dao.jdbc.ParticularDAOImpl;
import com.abastos.dao.jdbc.PedidoDAOImpl;
import com.abastos.dao.jdbc.ProductoDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.LineaPedido;
import com.abastos.model.Pedido;
import com.abastos.model.Producto;
import com.abastos.service.DataException;
import com.abastos.service.LineaPedidoService;
import com.abastos.service.MailService;
import com.abastos.service.ParticularService;
import com.abastos.service.PedidoService;
import com.abastos.service.exceptions.MailException;
import com.abastos.service.utils.ServiceUtils;

public class PedidoServiceImpl implements PedidoService{
	private static Logger logger = LogManager.getLogger(PedidoServiceImpl.class);
	private ParticularService particular;
	private PedidoDAO pedidoDAO;
	private LineaPedidoService lineaPedidoService;
	private ParticularDAO particularDAO;
	private MailService mailService;
	private Map <String, Object> map;
	public PedidoServiceImpl() {
		lineaPedidoService = new LineaPedidoServiceImpl();
		particular = new ParticularServiceImpl();
		particularDAO = new ParticularDAOImpl();
		pedidoDAO = new PedidoDAOImpl();
		mailService = new MailServiceImpl();
		map = new HashMap<String, Object>();
	}

	public Double calcPrecio(Pedido pedido)throws DataException {

		double precio = 0d;
		double descuento = 0.0d;

		for(LineaPedido linPed: pedido.getLineaPedido()) {
			
			if(linPed.getIdTipoOferta() != null) {
			if(linPed.getIdTipoOferta() == 3) {
				for(LineaPedido linPedid: pedido.getLineaPedido()) {
				
					if(linPedid.getIdProducto() == linPed.getIdProdOferta()) {
						
						descuento = linPed.getDescuentoFijo() == null? linPedid.getPrecio() * (linPed.getDescuentoPcn()/100)
								: linPed.getDescuentoFijo();
					
						
						
					}
				}
			}
			}
			precio+=linPed.getPrecioFinal() - descuento;
			
			descuento = 0d;
		}
		if(pedido.getAplicarDescuento() == true) {


			precio -= particular.findPuntos(pedido.getIdParticular())/10;

		}
		
		return ServiceUtils.round(precio,2);
	}


	public Integer calcPuntos(Double precio)throws DataException{
		int puntos = (int)ServiceUtils.round(precio/10, 0);

		return puntos;
	}
	@Override
	public Pedido findById(Long idPedido) throws DataException {
		logger.info("Iniciando findById...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Pedido Pedido  = null;
		try {
			connection.setAutoCommit(false);
			Pedido = pedidoDAO.findById(connection, idPedido);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return Pedido;
	}
	@Override
	public List<Pedido> findByIdParticular(Long idParticular) throws DataException {
		logger.info("Iniciando findByIdParticular...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<Pedido> Pedido  = null;
		try {
			connection.setAutoCommit(false);
			Pedido = pedidoDAO.findByIdParticular(connection, idParticular);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return Pedido;
	}
	@Override
	public Pedido create(Pedido pedido) throws DataException {
		logger.info("Creando pedido...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Pedido pedid  = null;
		Double precioTotal;
		int puntos;
		int stock;
		try {

			connection.setAutoCommit(false);

			for(int i = 0; i < pedido.getLineaPedido().size(); i++) {

				pedido.getLineaPedido().get(i).setPrecioFinal(
						lineaPedidoService.calcPrecio(pedido.getLineaPedido().get(i)));

			}
			pedido.setPrecioTotal(calcPrecio(pedido));
			precioTotal = pedido.getPrecioTotal();
			pedid = pedidoDAO.create(connection, pedido);
			pedid.setPrecioTotal(precioTotal);
			puntos = calcPuntos(pedido.getPrecioTotal());
			particularDAO.updatePuntos(connection, pedido.getIdParticular(), puntos);
			pedidoDAO.updateEstado(connection, 'C', pedid.getId());
			commit = true;

			logger.info(new StringBuilder().append("Pedido creado ").append(pedid.getId()).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {

			ConnectionManager.closeConnection(connection, commit);
		}
		return pedid;
	}

	@Override
	public boolean updateEstado(char estado, Long idPedido) throws DataException {
		logger.info("Actualizando el estado del pedido...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean pedido  = false;
		try {
			connection.setAutoCommit(false);
			pedido = pedidoDAO.updateEstado(connection, estado, idPedido);
			commit = true;
			logger.info(new StringBuilder().append("Estado del pedido actualizado ").append(idPedido).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return pedido;
	}


}
