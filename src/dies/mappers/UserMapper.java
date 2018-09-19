package dies.mappers;

import db.DBConnection;
import dies.models.IDomainObject;
import dies.models.Technician;
import dies.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserMapper extends DataMapper {
	
	private ResultSetDetails rsm = new ResultSetDetails();
	private DBConnection db = new DBConnection();
	private String findUserSQL = "select username, password, firstname as first_name, lastname as last_name from public.user where username=? and password=?";
	private String findTechnicianSQL = ""
			+ "select t1.id as technician_id, "
			+ "t1.username as technician_username, "
			+ "t1.password as technician_password, "
			+ "t1.firstname as technician_first_name, "
			+ "t1.lastname as technician_last_name \r\n" 
			+ "from public.user t1 \r\n" 
			+ "inner join public.technician t2 on t1.id = t2.id;";
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
				user = rsm.getUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public ArrayList<Technician> findAllTechnicians() {
		Connection con;
		ArrayList<Technician> technicians = new ArrayList<Technician>();
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(findTechnicianSQL);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				technicians.add(rsm.getTechnician(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		};
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
			e1.printStackTrace();
		}
	}

	public void update(IDomainObject user) {
		try {
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(updateSQL);
			User m = (User) user;

			statement.setInt(1, m.getId());
			statement.setString(2, m.getUsername());
			statement.setString(3, m.getPassword());
			statement.setString(4, m.getFirstName());
			statement.setString(5, m.getLastName());
			statement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void delete(IDomainObject user) {
		try {
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(deleteSQL);
			User m = (User) user;

			statement.setInt(1, m.getId());
			statement.setString(2, m.getUsername());
			statement.setString(3, m.getPassword());
			statement.setString(4, m.getFirstName());
			statement.setString(5, m.getLastName());
			statement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
