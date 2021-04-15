package com.abastos.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.configuration.ConfigurationManager;
import com.abastos.service.DataException;
import com.abastos.service.impl.LineaListaServiceImpl;

public class ConnectionManager {
	// JDBC driver name and database URL
	private static Logger logger = LogManager.getLogger(ConnectionManager.class);
	static final String JDBC_DRIVER = "jdbc.driver.classname";
	static final String DB_URL = "jdbc.url";

	// Database credentials
	static final String USER = "jdbc.user";
	static final String PASS = "jdbc.password";
	private static ConfigurationManager cfg = ConfigurationManager.getInstance();
	static {

		// Register JDBC driver	
		try {
			Class.forName(cfg.getParameter(JDBC_DRIVER));
		} catch (Exception e) {

			logger.error(e);

		}

	}

	public static final Connection getConnection()
			throws DataException {		

		try {
			return DriverManager.getConnection(cfg.getParameter(DB_URL), cfg.getParameter(USER), cfg.getParameter(PASS));
		} catch (SQLException e) {
			logger.error(e);
			throw new DataException(e);
		}
	}

	public static final void closeResultSet (ResultSet resultSet) {
		try {
			if (resultSet != null)
				resultSet.close();

		} catch (SQLException se) {
			logger.error(se);
		}
	}

	public static final void closePreparedStatement (PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null)
				preparedStatement.close();

		} catch (SQLException se) {
			logger.error(se);
		}
	}

	public static final void closeConnection (Connection connection )  {
		try {
			if (connection != null) 
				connection.close();
		}catch(SQLException se) {
			logger.error(se);
		}

	}
	public static void closeConnection(Connection connection, boolean commitOrRollback)
			throws DataException {
		if (connection != null) {
			try {

				if (commitOrRollback) {
					connection.commit();
				} else {
					connection.rollback();
					
				}
			} catch (SQLException e) { 
				throw new DataException(e);
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DataException(e);
				}
			}
		}
	}	

	public static final void close(ResultSet resultSet, PreparedStatement preparedStatement) {
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);


	}


}

