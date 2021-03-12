package com.abastos.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CategoriaDAOTest.class, ComunidadAutonomaDAOTest.class, ContenidoDAOTest.class,
		ContenidoProductoDAOTest.class, DireccionDAOTest.class, DireccionDtoDAOTest.class, EmpresaDAOTest.class,
		LineaListaDAOImplTest.class, LineaPedidoDAOTest.class, ListaDAOTest.class, LocalidadDAOTest.class,
		OfertaDAOTest.class, PaisDAOTest.class, ParticularDAOTest.class, PedidoDAOTest.class, ProductoDAOTest.class,
		ProductoIdiomaDAOTest.class, ProvinciaDAOTest.class, PuntuacionProductoDAOTest.class,
		PuntuacionTiendaDAOTest.class, TiendaDAOTest.class, TipoOfertaDAOTest.class })
public class AllTests {

}
