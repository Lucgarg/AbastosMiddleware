package com.abastos.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LineaPedidoServiceImplTest.class,PedidoServiceImplTest.class, CategoriaServiceImplTest.class, ComunidadAutonomaServiceImplTest.class,
	ProductoServiceImplTest.class, TiendaServiceImplTest.class, DireccionDTOServiceImplTest.class,
		 EmpresaServiceImplTest.class, LineaListaServiceImplTest.class,
		ListaServiceImplTest.class, LocalidadServiceImplTest.class,
		OfertaServiceImplTest.class, PaisServiceImplTest.class, ParticularServiceImplTest.class,
		  ProvinciaServiceImplTest.class,
		PuntuacionProductoServiceTest.class, PuntuacionTiendaServiceImplTest.class,
		TipoOfertaServiceImplTest.class, DireccionServiceImplTest.class,  ContenidoServiceImplTest.class,
		ContenidoProductoServiceImplTest.class})
public class AllTests {

}
