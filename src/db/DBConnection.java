package db;

import java.sql.*;

import org.postgresql.ds.PGSimpleDataSource;

public class DBConnection {
	private static final String DB_NAME = "dies";
	private static final String DB_SERVERNAME = "user";
	private static final int DB_SERVERPORT = 5432;
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
			PGSimpleDataSource ds = new PGSimpleDataSource();
			ds.setServerName(DB_SERVERNAME);
			ds.setDatabaseName(DB_NAME);
			ds.setUser(DB_USER);
			ds.setPassword(DB_PASSWORD);
			ds.setPortNumber(DB_SERVERPORT);
			Connection dbConnection = ds.getConnection();
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Connection problem");
		return null;
	}
}