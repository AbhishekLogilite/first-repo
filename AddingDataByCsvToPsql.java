package com.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AddingDataByCsvToPsql {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String csvFilePath = "/home/abhishek/Downloads/one.csv";

		int batchSize = 10000;

		Connection connection = null;

		try {
//        	Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/student", "postgres", "123456");
			connection.setAutoCommit(false);

			String sql = "INSERT INTO personal"+" (id, name, work, city, country) VALUES"+ " (?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);

			BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
			String lineText = null;

			int count = 0;
			int no_of_records = 1;

			lineReader.readLine(); // skip header line
			
			long start = System.nanoTime();
			
			while ((lineText = lineReader.readLine()) != null) {
				String[] data = lineText.split(",");
				
				int id = Integer.parseInt(data[0]);
//				String id = data[0];
				String name = data[1];
				String work = data[2];
				String city = data[3];
				String country = data[4];

				statement.setInt(1, id);
				
//				statement.setString(1, id);
				
				statement.setString(2, name);

				
				statement.setString(3, work);

				
				statement.setString(4, city);

				statement.setString(5, country);

				statement.executeLargeUpdate();

				if (count % batchSize == 0) {
					statement.executeBatch();
				}
				count++;
				no_of_records++;
			}
			long time = System.nanoTime() - start;
			
			System.out.printf("The average time taken was %.1f ns%n", (double) time /no_of_records);
			System.out.print("Total no of records : "+no_of_records);

			lineReader.close();

			// execute the remaining queries
			statement.executeBatch();

			connection.commit();
			connection.close();

		} catch (IOException ex) {
			System.err.println(ex);
		} catch (SQLException ex) {
			ex.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
