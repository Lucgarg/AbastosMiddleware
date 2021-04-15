package com.abastos.configuration;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ConfigurationManager {
	
	private static Logger logger = LogManager.getLogger(ConfigurationManager.class.getName());
	
    private static final String SERVICE_CONFIGURATION_FILE =
        "ServiceConfiguration.properties";
    
    private static Map parameters;

    static {
        try {
            Class configurationParametersManagerClass = ConfigurationManager.class;
            ClassLoader classLoader = configurationParametersManagerClass.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(SERVICE_CONFIGURATION_FILE);
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            
            parameters = Collections.synchronizedMap(properties);
            
        } catch (Throwable t) {
        	logger.fatal("Unable to load system configuration: "+t.getMessage(), t);
        	System.exit(0); // ...
        }

    }

    private static ConfigurationManager instance = null;
    
    /**
     * Singleton Thread-Safe.
     */
    public static ConfigurationManager getInstance() {
    	if (instance == null) {
    		synchronized(ConfigurationManager.class) {
    			if (instance == null) { // Necesario para proteger una segunda instanciación
    				instance = new ConfigurationManager();
    			}
    		}
    	}
    	return instance;    	
    }
    
    private ConfigurationManager() {    	
    };

    /**
     * Obtiene el valor de un parámetro de configuración.
     * @param name Nombre del parámetro.
     * @return Valor del parámetro o null si no se ha encontrado.
     */
    public String getParameter(String name) {
        String value = (String) parameters.get(name);       
        return value;
    }
   
}