package com.abastos.service.utils;

import java.io.StringWriter;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.abastos.service.ContenidoService;
import com.abastos.service.DataException;
import com.abastos.service.impl.ContenidoServiceImpl;

public class VelocityUtils {
	private static Logger logger = LogManager.getLogger( VelocityUtils.class);
	private static ContenidoService contenidoService = new ContenidoServiceImpl();
	public VelocityUtils() {

	}
	public static String generateTemplate(Long templat, Map<String, Object> cont) {
		StringWriter  w = null;
		String temp = "";
		try {
			temp = contenidoService.findByIdTipo(templat).getTemplate();

			w = new StringWriter();

			Velocity.init();
			VelocityContext vc = new VelocityContext();
			for(Map.Entry<String, Object> a : cont.entrySet()) {
				vc.put(a.getKey(), a.getValue());
			}

			Velocity.evaluate( vc, w, "mystring", temp );
		} catch (DataException e) {
			logger.error(e);
		}
		return w.toString();

	}


}
