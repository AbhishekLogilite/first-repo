package com.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

public class MyConnWithProperties {

	public static void main(String a[]) throws IOException, SQLException {

		final Logger logger = Logger.getLogger(MyConnWithProperties.class);
		
		System.setProperty("Jdbc.drivers", "org.postgresql.Driver");
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("/home/abhishek/eclipse-workspace/Log4jTest/classes/fileProp.properties");
		prop.load(fis);
		String jdbcUrl = prop.getProperty("url");
		String users = prop.getProperty("user");
		String pass = prop.getProperty("password");
		String ports = prop.getProperty("port");
		String dbName = prop.getProperty("name");
		Connection con = DriverManager.getConnection(jdbcUrl, users, pass);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM students");
		int count = 1;
		while (rs.next()) {
			count++;
		}
//		System.out.println("No of records inserted : " + count);
//		System.out.println("Database name : " + dbName);
//		System.out.println("Database port : " + ports);
		logger.info("No of records inserted : " + count +", Database name : " + dbName+", Database port : "+ports);

	}

}