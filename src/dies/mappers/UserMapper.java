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
	
//	@Resource(name="jdbc/postgresql")
	private DataSource ds;
	
	public User find(String username, String password)  throws SQLException{
		PGSimpleDataSource ds = new PGSimpleDataSource() ;  // Empty instance.
		ds.setServerName( "localhost" );  // The value `localhost` means the Postgres cluster running locally on the same machine.
		ds.setDatabaseName( "dies" );   // A connection to Postgres must be made to a specific database rather than to the server as a whole. You likely have an initial database created named `public`.
		ds.setUser( "user" );         // Or use the super-user 'postgres' for user name if you installed Postgres with defaults and have not yet created user(s) for your application.
		ds.setPassword( "password" ); 
		
		
		//Connection con = DBConnection.getDBConnection();
		
//		try {
//			Class.forName("org.postgresql.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dies", "user", "password");
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
