package db;

import java.sql.*;

public class DBConnection {

	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/dies";
	private static final String DB_USER = "user";
	private static final String DB_PASSWORD = "password";

	public static PreparedStatement prepare(String stm) throws SQLException {

		PreparedStatement preparedStatement = null;
		try {

			Connection dbConnection = getDBConnection();

			preparedStatement = dbConnection.prepareStatement(stm);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return preparedStatement;
	}

	public static Connection getDBConnection() {

		System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
			System.out.println(e.getMessage());
		}

		try {

			Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
		System.out.println("Connection problem");
		return null;

	}

}