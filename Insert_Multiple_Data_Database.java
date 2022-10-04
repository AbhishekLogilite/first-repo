package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

import org.apache.log4j.Logger;

public class Insert_Multiple_Data_Database {

	final static Logger logger = Logger.getLogger(Insert_Multiple_Data_Database.class);

	public static void main(String args[]) {

		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "123456");

			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO personal (id, name ,work, city, country ) VALUES (?,?,?,?,?)");
			int a = 1;

			long startTime = System.currentTimeMillis();
			long endTime = 0;
			int total_No_Of_Records = 1000000; // How much data we need to store
			for (int i = 1; i < total_No_Of_Records; i++) {

				stmt.setInt(1, i);
				stmt.setString(2, getName());// by this we get the random name
				stmt.setString(3, getWork());// by this we get the random work
				stmt.setString(4, getCity());// by this we get the random city
				stmt.setString(5, getCountry());// by this we get the random country

				// store the values in log files
				logger.info("ID : "+i + ", NAme : " + getName() + ", Work : " + getWork() +", Country : " + getCountry());

				a += stmt.executeUpdate();//counting the number of records 
			}
			endTime = System.currentTimeMillis();

			// converting millisecond to minutes
			long minutes = (-1) * ((startTime - endTime) / 1000) / 60;

			// converting millisecond to seconds
			long seconds = (-1) * ((startTime - endTime) / 1000) % 60;

			// store the values in log files
			logger.info("Total Time taken : Minutes -> " + minutes + " and, Second -> " + seconds);
			logger.info("Total Number of Records are Added " + a);

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	//by this method we generate random names data
	static String getName() {
		String[] name = { "Fred", "Jane", "Roxy", "Hana", "Rock", "Jack", "Luffy", "Ace", "Gol D Rogger" };
		Random random = new Random();

		return name[random.nextInt(name.length)];
	}

	//by this method we generate random work data
	static String getWork() {
		String[] name = { "Software", "Mechanical", "Civil", "Electrical", "Aeronotical", "Manager" };
		Random random = new Random();

		return name[random.nextInt(name.length)];
	}

	//by this method we generate random city data
	static String getCity() {
		String[] name = { "Mumbai", "Dehradun", "Delhi", "Ahmedabad" };
		Random random = new Random();

		return name[random.nextInt(name.length)];
	}

	//by this method we generate random country data
	static String getCountry() {
		String[] name = { "India", "U.S", "Russia" };
		Random random = new Random();

		return name[random.nextInt(name.length)];
	}

}
