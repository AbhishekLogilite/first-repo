package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class PostgreSQLJDBC {
	
	
	
	public static void main(String args[]) {
		
		final Logger logger = Logger.getLogger(PostgreSQLJDBC.class);
		
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/student", "postgres", "123456");

			c.setAutoCommit(false);
			//logger.info("Opened database successfully");
			//System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM students;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				
				 logger.info("ID = " + id + " , NAME = " + name + " , AGE = " + age);
				
//				System.out.println("ID = " + id);
//				System.out.println("NAME = " + name);
//				System.out.println("AGE = " + age);
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();

		} catch (Exception e) {
			e.printStackTrace();
			 logger.error(e.getClass().getName() + ": " + e.getMessage());
			//System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		//logger.info("Opened database successfully");
		//System.out.println("Opened database successfully");
	}
}