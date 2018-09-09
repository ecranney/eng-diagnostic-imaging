package db;

import java.sql.*;

import org.postgresql.ds.PGSimpleDataSource;

public class DBConnection {
	private static final String DB_NAME = "dies";
	private static final String DB_SERVERNAME = "user";
	private static final int DB_SERVERPORT = 5432;
	private static final String DB_USER = "user";
	private static final String DB_PASSWORD = "password";

	public Connection getConnection() throws SQLException {
		System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");

		PGSimpleDataSource ds = new PGSimpleDataSource(); 
		ds.setServerName("localhost"); 
		ds.setDatabaseName("dies");
		ds.setUser("user"); 
		ds.setPassword("password");
		ds.setPortNumber(5432);
		
		Connection dbConnection = ds.getConnection();
		return dbConnection;
	}
}