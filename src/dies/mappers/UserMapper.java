package dies.mappers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;

import dies.models.*;
import db.DBConnection;;

public class UserMapper extends DataMapper {

	private DataSource ds;

	public User find(String username, String password) throws SQLException {
		PGSimpleDataSource ds = new PGSimpleDataSource(); 
		ds.setServerName("localhost"); 
		ds.setDatabaseName("dies"); 
		ds.setUser("user"); 
		ds.setPassword("password");
		Connection con = ds.getConnection();

		String selectTableSQL = "SELECT username, password from public.user where username='admin' and password='admin'";
		System.out.println(selectTableSQL);

		PreparedStatement statement = con.prepareStatement(selectTableSQL);

		User user = null;

		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			user = new User(0, rs.getString("username"), rs.getString("password"));
			System.out.println(rs.getString("username") + " this is the username ");
		}
		return user;
	}

	public ArrayList<Technician> findAllTechnicians() {
		return null;
		// SQL
	}

	public void insert(DomainObject user) {
		// SQL
	}

	public void update(DomainObject user) {
		// SQL
	}

	public void delete(DomainObject user) {
		// SQL
	}
}
