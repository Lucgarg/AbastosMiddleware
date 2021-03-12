package com.abastos.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.service.DataException;
import com.abastos.service.impl.LineaListaServiceImpl;

public class ConnectionManager {
	// JDBC driver name and database URL
	private static Logger logger = LogManager.getLogger(ConnectionManager.class);
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/abastos_database";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "eiradovello";

	static {

		// Register JDBC driver	
		try {
			Class.forName(JDBC_DRIVER);
		} catch (Exception e) {

			logger.error(e);

		}

	}

	public static final Connection getConnection()
			throws DataException {		

		try {
			return DriverManager.getConnection(DB_URL, USER, PASS);
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

