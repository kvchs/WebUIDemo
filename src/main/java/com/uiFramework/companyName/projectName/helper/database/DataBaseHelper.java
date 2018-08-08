package com.uiFramework.companyName.projectName.helper.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;

public class DataBaseHelper {
	
	private static Logger log = LoggerHelper.getLogger(DataBaseHelper.class);
	private static String url = "jdbc:mysql://localhost:3306/demo?serverTimezone=GMT&useSSL=false";
	private static String userName = "root";
	private static String driverName = "com.mysql.cj.jdbc.Driver";
	private static String password = "123456";
	private static Connection connection;
	private static DataBaseHelper instance = null;
	
	public DataBaseHelper() {
		connection = getSingleInstanceConnection();
	}
	
	
	public static DataBaseHelper getInstance() {
		if (instance == null) {
			instance = new DataBaseHelper();
		}
		return instance;
	}
	
	private Connection getSingleInstanceConnection()
	{
		try {
			Class.forName(driverName);
			try {
				connection = DriverManager.getConnection(url, userName, password);
				if (connection!=null) {
					log.info("Connect database successfully ...");
				}
			} catch (SQLException e) {
				// TODO: handle exception
				log.error("Failed to create datebase connect: ... " + e);
			}
		} catch (ClassNotFoundException e) {
			log.info("Driver not Found : " + e);
		}
		
		return connection;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public static ResultSet getResultSet(String dbQuery) {
		instance = DataBaseHelper.getInstance();
		connection = instance.getConnection();
		log.info("Executing query: " + dbQuery);
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(dbQuery);
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
