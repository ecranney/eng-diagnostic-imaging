
import java.sql.*;

public class DBConnection {

	private static final String DB_CONNECTION = "jdbc:postgresql://localhost/dies";
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
	
	public static void selectRecordsFromDbUserTable() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String selectTableSQL = "SELECT username from users";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(selectTableSQL);

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);

			while (rs.next()) {

				String username = rs.getString("username");
				System.out.println("username : " + username);
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}

}