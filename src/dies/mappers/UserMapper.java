package dies.mappers;

import db.DBConnection;
import dies.models.IDomainObject;
import dies.models.Patient;
import dies.models.Technician;
import dies.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserMapper extends DataMapper {

	private DBConnection db = new DBConnection();
	private String findUserSQL = "SELECT username, password, firstname, lastname from public.user where username=? and password=?";
	private String findTechnicianSQL = "SELECT\r\n" + " t1.id,\r\n" + " t1.username,\r\n" + " t1.password,\r\n"
			+ " t1.firstname,\r\n" + " t1.lastname\r\n" + " FROM\r\n" + " public.user t1\r\n"
			+ "INNER JOIN public.technician t2 ON t1.id = t2.id;";
	private String inserSQL = "insert username, password, firstname, lastname values (?, ?, ?, ?)";
	private String updateSQL = "update public.machines set username=?, password=?, firstname=?, lastname=?";
	private String deleteSQL = "delete from public.machines where username=? and password=? and firstname=? and lastname=?";

	public User find(String username, String password) {
		Connection con = null;
		ResultSet rs = null;
		User user = null;

		try {
			con = db.getConnection();
			PreparedStatement statement = null;
			statement = con.prepareStatement(findUserSQL);
			statement.setString(1, username);
			statement.setString(2, password);

			rs = statement.executeQuery();
			while (rs.next()) {
				user = new User(0, rs.getString("username"), rs.getString("password"), rs.getString("firstname"),
						rs.getString("lastname")) {
				};
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Log In failed: An Exception has occurred! " + e);
		}

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
		try {
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(inserSQL);
			User m = (User) user;

			statement.setInt(1, m.getId());

			statement.setString(2, m.getUsername());

			statement.setString(3, m.getPassword());

			statement.setString(4, m.getFirstName());

			statement.setString(5, m.getLastName());

			statement.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void update(IDomainObject user) {
		try {
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(inserSQL);
			User m = (User) user;

			statement.setInt(1, m.getId());

			statement.setString(2, m.getUsername());

			statement.setString(3, m.getPassword());

			statement.setString(4, m.getFirstName());

			statement.setString(5, m.getLastName());

			statement.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void delete(IDomainObject user) {
		try {
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(inserSQL);
			User m = (User) user;

			statement.setInt(1, m.getId());

			statement.setString(2, m.getUsername());

			statement.setString(3, m.getPassword());

			statement.setString(4, m.getFirstName());

			statement.setString(5, m.getLastName());

			statement.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
