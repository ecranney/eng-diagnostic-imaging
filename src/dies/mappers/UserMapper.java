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
	private String findTechnicianSQL = "SELECT\r\n" + " t1.id,\r\n" + " t1.username,\r\n" + " t1.password,\r\n"
			+ " t1.firstname,\r\n" + " t1.lastname\r\n" + " FROM\r\n" + " public.user t1\r\n"
			+ "INNER JOIN public.technician t2 ON t1.id = t2.id;";

	public User find(String username, String password) {
		Connection con;
		User user = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(findUserSQL);
			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString("username") + " " + rs.getString("password") + " "
						+ rs.getString("firstname") + " " + rs.getString("lastname"));
				user = new User(0, rs.getString("username"), rs.getString("password"), rs.getString("firstname"),
						rs.getString("lastname")) {
				};
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		;
		return user;
	}

	public ArrayList<Technician> findAllTechnicians() {
		Connection con;
		ArrayList<Technician> technicians = new ArrayList<Technician>();
		Technician user = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(findTechnicianSQL);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				user = new Technician(0, rs.getString("username"), rs.getString("password"), rs.getString("firstname"),
						rs.getString("lastname")) {
				};
				technicians.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		;
		return technicians;
	}

	public void insert(IDomainObject user) {
		// STUB
		// SQL goes here
	}

	public void update(IDomainObject user) {
		// STUB
		// SQL goes here
	}

	public void delete(IDomainObject user) {
		// STUB
		// SQL goes here
	}
}
