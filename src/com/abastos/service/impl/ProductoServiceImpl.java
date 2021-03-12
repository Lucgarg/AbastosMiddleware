package com.abastos.service.impl;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.ProductoDAO;
import com.abastos.dao.jdbc.ProductoDAOImpl;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.model.Producto;
import com.abastos.service.DataException;
import com.abastos.service.ProductoCriteria;
import com.abastos.service.ProductoService;
import com.abastos.service.exceptions.LimitCreationException;
import com.abastos.service.utils.DescuentoUtils;

public class ProductoServiceImpl implements ProductoService {
	private static Logger logger = LogManager.getLogger(ProductoServiceImpl.class);
	private ProductoDAO productoDAO;
	public ProductoServiceImpl() {
		productoDAO = new ProductoDAOImpl();

	}

	@Override
	public List<Producto> findBy(ProductoCriteria producto, String idioma) throws DataException {
		logger.info("Iniciando findBy...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		List<Producto> produc  = null;
		try {
			connection.setAutoCommit(false);
			produc = productoDAO.findBy(connection, producto, idioma);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return produc;
	}

	@Override
	public Producto findById(Long idProducto, String idioma) throws DataException {
		logger.info("Iniciando findById...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Producto producto  = null;
		try {
			connection.setAutoCommit(false);
			producto = productoDAO.findById(connection, idProducto, idioma);
			commit = true;
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return producto;
	}

	@Override
	public Producto create(Producto producto) throws DataException, LimitCreationException {
		logger.info("Creando producto...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Producto product  = null;
		try {
			connection.setAutoCommit(false);
			
			if(productoDAO.count(connection, producto)==109) {
				throw new LimitCreationException("Número máximo de producto creados alcanzado");
			}
			if(producto.getOferta() != null) {

				if(producto.getOferta().getIdTipoOferta() == 1) {

					producto.setPrecioFinal(DescuentoUtils.descuento(producto));
				}
			}
			else {
				producto.setPrecioFinal(producto.getPrecio());
			}
			product = productoDAO.create(connection, producto);
			commit = true;
			logger.info(new StringBuilder().append("producto creado ").append(product.getId()).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return product;
	}

	@Override
	public Producto update(Producto producto, String idioma) throws DataException {
		logger.info("Actualizando producto...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Producto product  = null;
		try {
			connection.setAutoCommit(false);
			
			if(producto.getOferta() !=null) {
				if(producto.getOferta().getIdTipoOferta() == 1) {
				producto.setPrecioFinal(DescuentoUtils.descuento(producto));
				}
				else {
					producto.setPrecioFinal(producto.getPrecio());
				}
			}
			else {
				producto.setPrecioFinal(producto.getPrecio());
			}
			product = productoDAO.update(connection, producto, idioma);
			commit = true;
			logger.info(new StringBuilder().append("Producto actualizado ").append(producto.getId()).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return product;
	}


	@Override
	public void updateStock(Integer stock, Long idProducto) throws DataException {
		logger.info("Actualizando el stock del producto...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		Producto product = null;
		try {
			connection.setAutoCommit(false);
			product = findById(idProducto, "es");
			
			productoDAO.updateStock(connection, idProducto, product.getStock() - stock);
			commit = true;
			logger.info(new StringBuilder().append("Stock del producto").append(idProducto)
					.append("Actualizado").toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}


	}

	@Override
	public boolean delete(Long idProducto) throws DataException {
		logger.info("Eliminando producto...");
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean product  = false;
		try {
			connection.setAutoCommit(false);
			product =  productoDAO.softDelete(connection, idProducto);
			commit = true;
			logger.info(new StringBuilder().append("producto eliminado...").append(idProducto).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return product;
	}

	@Override
	public boolean deleteByIdTienda(Long idTienda) throws DataException {
		logger.info(new StringBuilder().append("Eliminando producto de la tienda...").append(idTienda).toString());
		Connection connection = ConnectionManager.getConnection();
		boolean commit = false;
		boolean product  = false;
		try {
			connection.setAutoCommit(false);
			product =  productoDAO.softDeleteByTienda(connection, idTienda);
			commit = true;
			logger.info(new StringBuilder().append("producto eliminado de la tienda...").append(idTienda).toString());
		}catch(SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			ConnectionManager.closeConnection(connection, commit);
		}
		return product;
	}

}
