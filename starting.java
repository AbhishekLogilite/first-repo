package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
import java.util.Random;
//import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class starting {
	final static Logger logger = Logger.getLogger(starting.class);

	public static void main(String[] args) throws SQLException {

		int total_No_Of_Records = 100000; // How much data we need to store

		int count = 1; // creating variable to count the insertion

		int age = 0;
		Randomm random; // creating object of Randomm class
		String name;

		long startTime = System.currentTimeMillis(); // starting the time in millisecond and store them
		long endTime = 0;

		for (int i = 1; i < total_No_Of_Records; i++) {
			
			name = getrandomName();// by this we get the random number
			
			try {
				age = getRandomNumber(18, 60); // by this get the random number between 18,60
			} catch (Exception e) {
				e.printStackTrace();
			}

			random = new Randomm(i, name, age);
			insertUsers(random); // inserting data to insertuser method

			logger.info(i + " " + name + " " + age);

			count++;
		}

		// taking end time in milliseconds
		endTime = System.currentTimeMillis();

		// converting millisecond to minutes
		long minutes = (-1) * ((startTime - endTime) / 1000) / 60;

		// converting millisecond to seconds
		long seconds = (-1) * ((startTime - endTime) / 1000) % 60;

		// store the values in log files
		logger.info("Total Time taken : Minutes -> " + minutes + " and, Second -> " + seconds);
		logger.info("Total Number of Records are Added " + count);
	}

	// method for generating random names
	private static String getrandomName() {
		String[] name = { "Fred", "Jane", "Roxy", "Hana", "Rock", "Jack", "Luffy", "Ace", "Gol D Rogger" };
		Random random = new Random();

		return name[random.nextInt(name.length)];
	}

	// method for generating random age
	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	// method for inserting data in database
	public static void insertUsers(Randomm ran) {
		Connection c = null;
		try {
			
			Class.forName("org.postgresql.Driver");


			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/student", "postgres", "123456");

			c.setAutoCommit(false);

			PreparedStatement st = c.prepareStatement("INSERT INTO students (id, name ,age ) VALUES (?, ?, ?)");
			
			st.setInt(1, ran.getId());
			st.setString(2, ran.getName());
			st.setObject(3, ran.getAge());
			st.executeBatch();
			st.close();

			c.commit();
			c.close();
		} catch (Exception e) {
			logger.error(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
}

class Randomm {
	int id;
	String name;
	int age;

	public Randomm(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", age=" + age;
	}

}
