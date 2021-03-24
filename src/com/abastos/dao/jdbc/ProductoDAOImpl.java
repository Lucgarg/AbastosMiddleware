package com.abastos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.CategoriaDAO;
import com.abastos.dao.ContenidoDAO;
import com.abastos.dao.ContenidoProductoDAO;
import com.abastos.dao.OfertaDAO;
import com.abastos.dao.ProductoDAO;
import com.abastos.dao.ProductoIdiomaDAO;
import com.abastos.dao.PuntuacionProductoDAO;
import com.abastos.dao.util.ConnectionManager;
import com.abastos.dao.util.DAOUtils;
import com.abastos.dao.util.DBNullUtils;
import com.abastos.model.Contenido;
import com.abastos.model.ContenidoProducto;
import com.abastos.model.Producto;
import com.abastos.model.ProductoIdioma;
import com.abastos.service.DataException;
import com.abastos.service.ProductoCriteria;


public class ProductoDAOImpl implements ProductoDAO{
	private static Logger logger = LogManager.getLogger(ProductoDAOImpl.class);
	private OfertaDAO ofertaDAO;
	private PuntuacionProductoDAO puntProdDAO;
	private CategoriaDAO categoriaDAO;
	private ContenidoDAO contenidoDAO;	
	private ContenidoProductoDAO contProDAO;
	private ProductoIdiomaDAO producIdioma;
	public ProductoDAOImpl() {

		ofertaDAO = new OfertaDAOImpl();
		puntProdDAO = new PuntuacionProductoDAOImpl();
		categoriaDAO = new CategoriaDAOImpl();
		contenidoDAO= new ContenidoDAOImpl();
		contProDAO = new ContenidoProductoDAOImpl();
		producIdioma = new ProductoIdiomaDAOImpl();
	}
	@Override
	public Producto findById(Connection connection, Long idProducto, String idioma)throws DataException {
		Producto producto = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();
			logger.trace("Create statement...");
			sql.append( "SELECT p.ID_PRODUCTO, g.NOMBRE_PRODUCTO, p.PRECIO, ");
			sql.append(" g.CARACTERISTICAS_PRODUCTO, ");
			sql.append(" p.ID_ORIGEN, p.FECHA_CREACION, p.ID_TIENDA, p.ID_CATEGORIA, p.STOCK, p.ID_OFERTA, p.precio_final  ");
			sql.append(" FROM PRODUCTO  p inner join producto_idioma g on p.id_producto = g.id_producto ");
			sql.append(" WHERE p.ID_PRODUCTO = ? AND g.id_idioma = ?  AND p.DATA_BAJA IS  NULL ") ;
			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());

			int i = 1;

			preparedStatement.setLong(i++, idProducto);
			preparedStatement.setString(i++, String.valueOf(idioma));

			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				producto = loadNext(connection, resultSet);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Buscando el producto " ).append(idProducto).toString(),se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return producto;
	}
	@Override
	public List<Producto> findBy(Connection connection ,ProductoCriteria producto, String idioma)throws DataException {
		List<Producto> results = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();

			logger.trace("Create statement...");

			sql.append( " SELECT A.ID_PRODUCTO, G.NOMBRE_PRODUCTO, A.PRECIO, G.CARACTERISTICAS_PRODUCTO, ");
			sql.append(" A.ID_ORIGEN, A.FECHA_CREACION, A.ID_TIENDA, A.ID_CATEGORIA, A.STOCK, A.ID_OFERTA, A.precio_final FROM PRODUCTO A ");
			sql.append(" INNER JOIN PRODUCTO_IDIOMA G ON A.ID_PRODUCTO = G.ID_PRODUCTO ");
			if(producto.getIdEmpresa() !=null) {
				sql.append(" INNER JOIN TIENDA F ON F.ID_TIENDA = A.ID_TIENDA ");
			}
			if(producto.getOferta() !=null) {
				sql.append(" INNER JOIN OFERTA E ON A.ID_OFERTA = E.ID_OFERTA ");
			}
			if(producto.getIdCategoria() != null) {
				sql.append(" INNER JOIN CATEGORIA I ON A.ID_CATEGORIA = I.ID_CATEGORIA");
				if(producto.getIdEmpresa() == null) {
					sql.append(" WHERE A.ID_TIENDA = ? AND  I.ID_CATEGORIA IN ");
					sql.append(DAOUtils.listTo(categoriaDAO.findByCategoriaHoja(connection, producto.getIdCategoria(), idioma)));
				}
				else {
					sql.append(" WHERE F.ID_EMPRESA = ? AND  I.ID_CATEGORIA IN ");
					sql.append(DAOUtils.listTo(categoriaDAO.findByCategoriaHoja(connection, producto.getIdCategoria(), idioma)));
				}
			}
			else {
				if(producto.getIdEmpresa() == null) {
					sql.append(" WHERE A.ID_TIENDA = ? ");
				}
				else {
					sql.append(" WHERE F.ID_EMPRESA = ? ");
				}
			}

			if(producto.getPredioDesde() != null || producto.getPrecioHasta() != null) {
				sql.append( producto.getPredioDesde() != null? (producto.getPrecioHasta() !=null?
						" AND A.PRECIO BETWEEN ? AND ? ": " AND A.PRECIO > ? "): "AND A.PRECIO < ? ");

			}

			if (producto.getIdOrigen() != null) {
				sql.append( " AND UPPER(A.ID_ORIGEN) LIKE ? "); 
			}
			if(producto.getNombre() != null) {
				sql.append( " AND UPPER(G.Nombre_producto) like ?   ");
			}
			if(producto.getOferta() !=null) {
				if(producto.getOferta() != false) {

					sql.append( " AND A.ID_OFERTA IS NOT NULL AND E.FECHA_DESDE < ? AND E.FECHA_HASTA > ?   ");

				}
			}

			sql.append( " AND g.id_idioma = ? AND A.DATA_BAJA IS  NULL ");

			preparedStatement = connection.prepareStatement(sql.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());

			int i = 1;
			if(producto.getIdEmpresa() == null) {
				preparedStatement.setLong(i++, producto.getIdTienda());
			}
			else {
				preparedStatement.setLong(i++, producto.getIdEmpresa());
			}

			if(producto.getPredioDesde() != null) {
				preparedStatement.setDouble(i++, producto.getPredioDesde());
			}
			if(producto.getPrecioHasta() != null) {
				preparedStatement.setDouble(i++, producto.getPrecioHasta());
			}
			if(producto.getIdOrigen() != null) {
				preparedStatement.setString(i++, String.valueOf(producto.getIdOrigen()).toUpperCase());
			}
			if(producto.getNombre() != null) {
				preparedStatement.setString(i++,"%" + producto.getNombre()+ "%" );
			}
			if(producto.getOferta() != null) {
				if(producto.getOferta() !=false) {
					preparedStatement.setTimestamp(i++,new java.sql.Timestamp(new Date().getTime()));
					preparedStatement.setTimestamp(i++,new java.sql.Timestamp(new Date().getTime()));
				}
			}
			preparedStatement.setString(i++, idioma);

			resultSet = preparedStatement.executeQuery();
			logger.info(preparedStatement.toString());
			results = new ArrayList<Producto>();

			Producto pro = null;

			while (resultSet.next()) {

				pro = new Producto();
				pro = loadNext(connection, resultSet);				
				results.add(pro);
			}

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException("Buscando productos ",se);
		}  
		finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}

		return results;
	}
	@Override
	public Integer count(Connection connection, Producto producto) throws DataException {
		Integer count = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();
			logger.trace("Create statement...");
			sql.append( "SELECT COUNT(*) FROM PRODUCTO WHERE ID_TIENDA = ?  ");

			preparedStatement = connection.prepareStatement
					(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.trace(sql.toString());

			int i = 1;

			preparedStatement.setLong(i, producto.getIdTienda());

			resultSet = preparedStatement.executeQuery();
			i = 1;
			if(resultSet.next()) {
				count = resultSet.getInt(i++);
			}

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Contando los productos de la tienda " ).append(producto.getIdTienda()).toString(),se);
		}  finally {
			ConnectionManager.close(resultSet, preparedStatement);
		}
		return count;
	}
	private Producto loadNext(Connection connection ,ResultSet resultset) throws  DataException, SQLException  {
		int i = 1;

		Producto producto = new Producto();
		producto.setId(resultset.getLong(i++));
		producto.setNombre(resultset.getString(i++));
		producto.setPrecio(resultset.getDouble(i++));
		producto.setCaracteristicas(resultset.getString(i++));
		producto.setTipoOrigen(resultset.getString(i++).charAt(0));
		producto.setFechaCreacion(resultset.getTimestamp(i++));
		producto.setIdTienda(resultset.getLong(i++));
		producto.setIdCategoria(resultset.getInt(i++));
		producto.setStock(resultset.getInt(i++));
		producto.setOferta(ofertaDAO.findById(connection,resultset.getLong(i++)));
		producto.setPrecioFinal(resultset.getDouble(i++));
		producto.setValoracion(puntProdDAO.findMedia(connection, producto.getId()));
		producto.setContenidoProducto(contProDAO.findByImagenes(connection, producto.getId()));
		producto.setProductoIdioma(producIdioma.findBy(connection, producto.getId()));
		return producto;


	}
	@Override
	public Producto create(Connection connection, Producto producto) throws DataException{
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		Producto pro = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          

			logger.trace("Create statement...");

			sql.append( " INSERT INTO PRODUCTO(nombre_producto, PRECIO, FECHA_CREACION, ");
			sql.append(" ID_ORIGEN, STOCK, ID_TIENDA, ID_OFERTA, ID_CATEGORIA, PRECIO_FINAL) ");
			sql.append(" VALUES( ? , ? , ? , ? , ?,  ? , ?, ?, ?  ) "); 

			preparedStatement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			logger.trace(sql.toString());

			int i = 1;
			preparedStatement.setString(i++, producto.getNombre());
			preparedStatement.setDouble(i++, producto.getPrecio());
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.setString(i++, String.valueOf(producto.getTipoOrigen()));
			preparedStatement.setInt(i++, producto.getStock());
			preparedStatement.setLong(i++, producto.getIdTienda());
			DBNullUtils.toNull(preparedStatement, i++, producto.getOferta().getId());
			preparedStatement.setInt(i++, producto.getIdCategoria());
			preparedStatement.setDouble(i++, producto.getPrecioFinal());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			pro = new Producto();
			i = 1;
			if(resultSet.next()) {

				pro.setId(resultSet.getLong(i++));

			}
			producto.setId(pro.getId());
			createContenidoProducto(connection, producto);
			createProductoIdioma(connection, producto);
		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Creando producto ").append(producto.getId()).toString(),se);
		}   
		finally {
			ConnectionManager.close(resultSet, preparedStatement);

		}

		return pro;
	}

	private void createContenidoProducto(Connection connection, Producto producto)throws DataException{
		PreparedStatement preparedStatement = null;
		Contenido contenido = null;
		StringBuilder sql=null;
		try {


			for(ContenidoProducto lis : producto.getContenidoProducto()) {
				sql=new StringBuilder();
				contenido = contenidoDAO.create(connection, lis.getContenido());
				sql.append( "INSERT INTO PRODUCTO_TIENE_CONTENIDO(ID_PRODUCTO, ID_TIPO_CONTENIDO, ID_CONTENIDO) ");
				sql.append(" VALUES(?, ?, ?)");
				preparedStatement = connection.prepareStatement(sql.toString());
				logger.trace(sql.toString());
				int i = 1;
				preparedStatement.setLong(i++, producto.getId());
				preparedStatement.setString(i++, String.valueOf(lis.getIdTipoFoto()));
				preparedStatement.setLong(i++, contenido.getId());
				preparedStatement.executeUpdate();
			}


		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Creando el contenido del producto ")
					.append(producto.getId()).toString(), se);
		}  
		finally {
			ConnectionManager.closePreparedStatement(preparedStatement);

		}

	}
	private void createProductoIdioma(Connection connection, Producto producto) throws DataException {
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			
			
			for(ProductoIdioma pro : producto.getProductoIdioma()) {
				sql=new StringBuilder();
				logger.trace("Create statement...");
				sql.append( "INSERT INTO PRODUCTO_IDIOMA(ID_PRODUCTO, ID_IDIOMA, NOMBRE_PRODUCTO, CARACTERISTICAS_PRODUCTO) ");
				sql.append(" VALUES(?, ?, ?, ?)");
				preparedStatement = connection.prepareStatement(sql.toString());
				logger.trace(sql.toString());
				int i = 1;
				preparedStatement.setLong(i++, producto.getId());
				preparedStatement.setString(i++, String.valueOf(pro.getIdIdioma()));
				preparedStatement.setString(i++, pro.getNombreProducto());
				preparedStatement.setString(i++, pro.getCaractProduct());
				preparedStatement.executeUpdate();
			}


		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("En la introdución de diferentes idiomas del producto ")
					.append(producto.getId()).toString(),se);
		}  
		finally {
			ConnectionManager.closePreparedStatement(preparedStatement);

		}
	}
	@Override
	public Producto update(Connection connection, Producto producto, String idioma)throws DataException {
		PreparedStatement preparedStatement = null;

		Producto product = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          

			logger.trace("Create statement...");

			sql.append( "UPDATE PRODUCTO g  SET ");
			sql.append(" g.PRECIO = ? , ");
			sql.append("	 g.ID_ORIGEN = ? , ");
			sql.append("	 g.ID_TIENDA = ? , ");
			sql.append("	g.ID_CATEGORIA = ? , g.STOCK = ? , g.precio_final = ? ");

			if(producto.getOferta() != null) {
				sql.append(  " , g.id_oferta = ? ");

			}
			sql.append( " where g.id_producto = ? ");
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setDouble(i++, producto.getPrecio());
			preparedStatement.setString(i++, String.valueOf(producto.getTipoOrigen()));
			preparedStatement.setLong(i++, producto.getIdTienda());
			preparedStatement.setLong(i++, producto.getIdCategoria());
			preparedStatement.setInt(i++, producto.getStock());
			preparedStatement.setDouble(i++, producto.getPrecioFinal());	
			if(producto.getOferta() != null) {
				preparedStatement.setLong(i++, producto.getOferta().getId());
			}
			preparedStatement.setLong(i++, producto.getId());

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new DataException(new StringBuilder().append("No ha sido posible actualizar el producto ")
						.append(producto.getId()).toString());
			} 
			deleteContenidoProducto(connection, producto);
			createContenidoProducto(connection, producto);

			product = findById(connection, producto.getId(), idioma);
			for(ProductoIdioma p: producto.getProductoIdioma()) {
				updateProductoIdioma(connection, p);
			}


		}catch (SQLException se) {
			logger.error(se);
			throw new DataException("Actualizando el producto ",se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}

		return product;
	}
	private void updateProductoIdioma(Connection connection, ProductoIdioma productoIdi) throws DataException{
		PreparedStatement preparedStatement = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          

			logger.trace("Create statement...");

			sql.append( "UPDATE PRODUCTO_idioma g  SET ");
			sql.append("	g.nombre_producto = ? , ");
			sql.append(" g.caracteristicas_producto = ?  ");
			sql.append("	WHERE g.ID_idioma = ? AND g.id_producto = ? ");
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setString(i++, productoIdi.getNombreProducto());
			DBNullUtils.toNull(preparedStatement, i++, productoIdi.getCaractProduct());
			preparedStatement.setString(i++, productoIdi.getIdIdioma());
			preparedStatement.setLong(i++, productoIdi.getIdProducto());

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new DataException(new StringBuilder().append("No ha sido posible actualizar el idioma del producto ")
						.append(productoIdi.getIdProducto()).toString());
			} 


		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Al introducir los nombres del producto en los diferentes idiomas ")
					.append(productoIdi.getIdProducto()).toString(), se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
	}
	private void deleteContenidoProducto(Connection connection, Producto producto)throws DataException{
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();
			sql.append( "DELETE from producto_tiene_contenido WHERE ID_PRODUCTO = ? ");
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setLong(i++, producto.getId());

			preparedStatement.executeUpdate();

			for(ContenidoProducto cont: producto.getContenidoProducto()) {
				contenidoDAO.hardDelete(connection, cont.getContenido().getId());
			}
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Borrando el contenido del producto ")
					.append(producto.getId()).toString(), se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public boolean softDelete(Connection connection ,Long idProducto) throws DataException {
		PreparedStatement preparedStatement = null;
		Producto producto = null;

		StringBuilder sql=null;
		try {
			sql=new StringBuilder(); 
			producto = findById(connection, idProducto, "es");
			if(!producto.getContenidoProducto().isEmpty()) {
				deleteContenidoProducto(connection, producto);
			}
			logger.trace("Create statement...");

			sql.append( " UPDATE PRODUCTO SET DATA_BAJA = ? WHERE ID_PRODUCTO = ? "); 

			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.setLong(i++, idProducto);

			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {
				throw new DataException(new StringBuilder().append("No se ha encontrado el producto").append(idProducto).toString());
			} 

		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Dando de baja el producto").append(idProducto).toString(), se);
		} 
		finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return true;

	}

	@Override
	public boolean softDeleteByTienda(Connection connection ,Long idTienda) throws DataException {
		PreparedStatement preparedStatement = null;
		List<Producto> listProduct = null;
		ProductoCriteria productoCriteria = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder(); 

			productoCriteria = new ProductoCriteria();
			productoCriteria.setIdTienda(idTienda);
			listProduct = findBy(connection, productoCriteria, "es");
			for(Producto p: listProduct) {
				deleteContenidoProducto(connection, p);
			}
			logger.trace("Create statement...");

			sql.append( " UPDATE PRODUCTO SET DATA_BAJA = ?  WHERE ID_TIENDA = ? "); 

			preparedStatement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.setLong(i++, idTienda);

			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {
				throw new DataException(new StringBuilder().append("No se ha encontrado la tienda o la tienda no tiene productos ")
						.append(idTienda).toString());
			} 


		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(new StringBuilder().append("Dando de baja el producto por medio de la tienda ")
					.append(idTienda).toString(), se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}
		return true;

	}

	@Override
	public void updateStock(Connection connection, Long idProducto, Integer stock) throws DataException {
		PreparedStatement preparedStatement = null;
		StringBuilder sql=null;
		try {
			sql=new StringBuilder();          

			logger.trace("Create statement...");

			sql.append( " UPDATE PRODUCTO SET ");
			sql.append(" STOCK = ?  WHERE ID_PRODUCTO = ? ");
			preparedStatement = connection.prepareStatement(sql.toString());
			logger.trace(sql.toString());
			int i = 1;
			preparedStatement.setInt(i++, stock);
			preparedStatement.setLong(i++, idProducto);

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new DataException("No ha sido posible actualizar el stock del producto" + idProducto);
			} 


		}catch (SQLException se) {
			logger.error(se);
			throw new DataException("Actualizando el stock del producto" + idProducto,se);
		}  finally {
			ConnectionManager.closePreparedStatement(preparedStatement);
		}


	}


}
