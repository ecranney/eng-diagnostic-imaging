package authentication;

import java.sql.*;

public class DBConnection {

	private static final String DB_CONNECTION = "jdbc:derby://localhost:1527/dies;create=true";
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

	private static Connection getDBConnection() {

		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());

			Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
		System.out.println("Connection problem");
		return null;

	}
	

	public static void main(String[] args) {
		try {
			PreparedStatement stm = DBConnection.prepare("SELECT * FROM APP.user");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}