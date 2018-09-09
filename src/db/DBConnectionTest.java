package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import dies.mappers.UserMapper;
import dies.models.User;
import dies.services.LoginService;

public class DBConnectionTest {

//	@Resource(name = "jdbc/postgresql")
//	private static DataSource ds;

	public static void main(String[] args) {
//		try {
			//selectRecordsFromDbUserTable();
			//find();
//			UserMapper um = new UserMapper();
//			User user = um.find("admi2", "admin");
			
			LoginService lg = new LoginService();
			boolean x = lg.login("admin", "admin");
			
			System.out.println(x);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	public static void selectRecordsFromDbUserTable() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String selectTableSQL = "SELECT username from public.user";

		try {
			dbConnection = DBConnection.getDBConnection();
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

	public static void find() throws SQLException {
//		Connection con = ds.getConnection();
//		Statement statement = con.createStatement();
//
//		String selectTableSQL = "SELECT username from public.user";
//
//		ResultSet rs = statement.executeQuery(selectTableSQL);
//		while (rs.next()) {
//
//			String un = rs.getString("username");
//			System.out.println("username : " + un);
//		}
	}
}
