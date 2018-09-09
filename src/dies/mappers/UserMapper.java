package dies.mappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import db.DBConnection;
import dies.models.*;

public class UserMapper extends DataMapper {

	private DBConnection db = new DBConnection();
	private String findUserSQL = "SELECT username, password, firstname, lastname from public.user where username=? and password=?";

	public User find(String username, String password) throws SQLException {
		Connection con = db.getConnection();
		PreparedStatement statement = con.prepareStatement(findUserSQL);
		statement.setString(1, username);
		statement.setString(2, password);
		User user = null;

		ResultSet rs = statement.executeQuery();
		
		while (rs.next()) {
			System.out.println(rs.getString("username") + " " + rs.getString("password") + " " + rs.getString("firstname")
			+ " " + rs.getString("lastname"));
			user = new User(0, rs.getString("username"), rs.getString("password"), rs.getString("firstname"),
					rs.getString("lastname")) {
			};
		}
		return user;
	}

	public ArrayList<Technician> findAllTechnicians() {
		return null;
		// SQL
	}

	public void insert(IDomainObject user) {
		// SQL
	}

	public void update(IDomainObject user) {
		// SQL
	}

	public void delete(IDomainObject user) {
		// SQL
	}
}
