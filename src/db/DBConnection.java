package db;

import java.sql.*;

import org.postgresql.ds.PGSimpleDataSource;

public class DBConnection {
	private static final String DB_NAME = "ddd1ffc2dcciud";
	private static final String DB_SERVERNAME = "user";
	private static final int DB_SERVERPORT = 5432;
	private static final String DB_USER = "user";
	private static final String DB_PASSWORD = "password";

	public Connection getConnection() throws SQLException {
		System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");

		PGSimpleDataSource ds = new PGSimpleDataSource(); 
		ds.setServerName("ec2-50-17-194-129.compute-1.amazonaws.com"); 
		ds.setDatabaseName("ddd1ffc2dcciud");
		ds.setUser("akasvjtcdtwoly"); 
		ds.setPassword("af2fb830b539917e1e7f2b5087c3d0028b4b0775651fb43a141a8e5816d705ed");
		ds.setPortNumber(5432);
		
		Connection dbConnection = ds.getConnection();
		return dbConnection;
	}
}