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

		long startTime = System.currentTimeMillis();
		long endTime = 0;
		int total_No_Of_Records = 500000;
		int count = 1;
		int age = 0;
		Randomm random;
		String name;
		for (int i = 1; i < total_No_Of_Records; i++) {
			name = getrandomName();
//			name = "luffy";

			try {
				age = getRandomNumber(18, 60);
			} catch (Exception e) {
				e.printStackTrace();
			}
//			age= 19;
//			insertUsers(i,name,age);
			random = new Randomm(i, name, age);
			insertUsers(random);
//			System.out.println(random.toString());
//			logger.info(random.toString());
			logger.info(i + " " + name + " " + age);

			// 100000- 6:12:22 pm   -  10:16:50 = 4hr

			count++;
		}

		// System.out.printf("The average time taken was %.1f ns%n", (double) time /
		// total_No_Of_Records); long minutes
		
		   

//		logger.info("The average time taken was "+d);
//		long dd=(long) 60_000_000_000;

		
		endTime = System.currentTimeMillis();
		long minutes = (-1) * ((startTime - endTime) / 1000) / 60;
		long seconds = (-1) * ((startTime - endTime) / 1000) % 60;

		logger.info("Total Time taken : Minutes -> " + minutes + " and, Second -> " + seconds);


		// System.out.println("Total Number of Records are Added " + count);

		logger.info("Total Number of Records are Added " + count);
	}

	private static String getrandomName() {
		String[] name = { "Fred", "Jane", "Roxy", "Hana", "Rock", "Jack", "Luffy", "Ace", "Gol D Rogger" };
		Random random = new Random();

		return name[random.nextInt(name.length)];
	}

	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public static void insertUsers(Randomm ran) {
//	public static void insertUsers(int i, String s, int age) {
		Connection c = null;
		// Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/student", "postgres", "123456");

			c.setAutoCommit(false);
//			System.out.println("Opened database successfully");

//			int i = ran.getId();
//			String names = ran.getName();
//			int ages = ran.getAge();

//			 stmt = c.createStatement();

			PreparedStatement st = c.prepareStatement("INSERT INTO students (id, name ,age ) VALUES (?, ?, ?)");

//			st.setInt(1,i);
//			st.setString(2,s);
//			st.setInt(3,age);

			st.setInt(1, ran.getId());
			st.setString(2, ran.getName());
			st.setObject(3, ran.getAge());
			st.executeBatch();
//			st.executeUpdate();
			st.close();

//	         stmt.executeUpdate(sql);
//	         
//	         stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			logger.error(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
//		System.out.println("Records created successfully");
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
