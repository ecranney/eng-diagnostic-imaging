package dies.mappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import dies.models.Address;
import dies.models.IDomainObject;
import dies.models.Machine;
import dies.models.Patient;
import dies.models.Technician;
import dies.models.User;

public class PatientMapper extends DataMapper {

	private DBConnection db = new DBConnection();
	private String findSQL = "select t1.id, t1.first_name, t1.last_name, \r\n" + 
			"t2.id as patient_address_id, t2.unit_no as patient_unit_no, t2.street_no as patient_street_no, t2.street_name as patient_street_name, t2.city as patient_city, t2.state as patient_state, t2.post_code as patient_post_code,\r\n" + 
			" t1.phone, t1.medicare_no from public.patient t1 inner join public.address t2 on t1.address_id= t2.id";
	private String findByIDSQL = "select t1.id, t1.first_name, t1.last_name, \r\n" + 
			"t2.id as patient_address_id, t2.unit_no as patient_unit_no, t2.street_no as patient_street_no, t2.street_name as patient_street_name, t2.city as patient_city, t2.state as patient_state, t2.post_code as patient_post_code,\r\n" + 
			" t1.phone, t1.medicare_no from public.patient t1 inner join public.address t2 on t1.address_id= t2.id where t1.id=?";
	private String findByMedicareSQL = "select t1.id, t1.first_name, t1.last_name, \r\n" + 
			"t2.id as patient_address_id, \r\n" + 
			"t2.unit_no as patient_unit_no, \r\n" + 
			"t2.street_no as patient_street_no, \r\n" + 
			"t2.street_name as patient_street_name, \r\n" + 
			"t2.city as patient_city, \r\n" + 
			"t2.state as patient_state, \r\n" + 
			"t2.post_code as patient_post_code,\r\n" + 
			"t1.phone, t1.medicare_no from public.patient t1 \r\n" + 
			"left outer join public.address t2 on t1.address_id= t2.id where medicare_no=?";
	private String inserSQL = 
			"with rows as ( \r\n" + 
			"insert into public.address (unit_no, street_no, street_name, city, state, post_code) \r\n" + 
			"values (?, ?, ?, ?,?,?) returning id \r\n" + 
			") \r\n" + 
			"insert into public.patient (first_name,last_name,address_id, phone, medicare_no)\r\n" + 
			"select ?, ?, id, ?, ? from rows \r\n" + 
			"";
	
	
	private String updateSQL = "update public.patient \r\n" + 
			"set first_name=?, \r\n" + 
			"last_name=?, \r\n" + 
			"address_id=?, phone=?, medicare_no=?\r\n" + 
			"where id=?;\r\n" + 
			"update public.address \r\n" + 
			"set unit_no=?, \r\n" + 
			"street_no=?, \r\n" + 
			"street_name=?, \r\n" + 
			"city=?, \r\n" + 
			"state=?,\r\n" + 
			"post_code=?\r\n" + 
			"where id=?;\r\n" + 
			"";
	private String deleteSQL = "delete from public.patient where id=? and first_name=? and last_name=? and address_id=? and phone=? and medicare_no=?";

	public Patient find(String medicareNo) {
		Connection con = null;
		ResultSet rs = null;
		Patient user = null;
		Address patientAddress = null;

		try {
			con = db.getConnection();
			PreparedStatement statement = null;
			statement = con.prepareStatement(findByMedicareSQL);
			statement.setString(1, medicareNo);

			rs = statement.executeQuery();
			while (rs.next()) {
				
				patientAddress = new Address(rs.getInt("patient_address_id"), rs.getInt("patient_unit_no"),
						rs.getInt("patient_street_no"), rs.getString("patient_street_name"),
						rs.getString("patient_city"), rs.getString("patient_state"),
						rs.getInt("patient_post_code"));
				
				user = new Patient(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), patientAddress,
						rs.getString("phone"), rs.getString("medicare_no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Log In failed: An Exception has occurred! " + e);
		}

		return user;
	}

	public Patient find(int id) {
		Connection con = null;
		ResultSet rs = null;
		Patient user = null;
		Address patientAddress = null;

		try {
			con = db.getConnection();
			PreparedStatement statement = null;
			statement = con.prepareStatement(findByIDSQL);
			statement.setInt(1, id);

			rs = statement.executeQuery();
			while (rs.next()) {
				patientAddress = new Address(rs.getInt("patient_address_id"), rs.getInt("patient_unit_no"),
						rs.getInt("patient_street_no"), rs.getString("patient_street_name"),
						rs.getString("patient_city"), rs.getString("patient_state"),
						rs.getInt("patient_post_code"));
				
				user = new Patient(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), patientAddress,
						rs.getString("phone"), rs.getString("medicare_no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Log In failed: An Exception has occurred! " + e);
		}

		return user;
	}

	public Patient findAll() {
		Connection con;
		ArrayList<Patient> technicians = new ArrayList<Patient>();
		Patient user = null;
		Address patientAddress = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(findSQL);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				patientAddress = new Address(rs.getInt("patient_address_id"), rs.getInt("patient_unit_no"),
						rs.getInt("patient_street_no"), rs.getString("patient_street_name"),
						rs.getString("patient_city"), rs.getString("patient_state"),
						rs.getInt("patient_post_code"));
				
				user = new Patient(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), patientAddress,
						rs.getString("phone"), rs.getString("medicare_no"));			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		;
		return user;
	}

	public void insert(IDomainObject patient) {
		try {
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(inserSQL);
			Patient m = (Patient) patient;
			
			statement.setInt(1, m.getAddress().getUnitNo());
			statement.setInt(2, m.getAddress().getStreetNo());
			statement.setString(3, m.getAddress().getStreetName());
			statement.setString(4, m.getAddress().getCity());
			statement.setString(5, m.getAddress().getState());
			statement.setInt(6, m.getAddress().getPostCode());
			
			statement.setString(7, m.getFirstName());
			statement.setString(8, m.getLastName());
			statement.setString(9, m.getPhone());
			statement.setString(10, m.getMedicareNo());

			System.out.println(statement + " printing statement to check the errors");
			
			statement.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void update(IDomainObject patient) {
		try {
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(updateSQL);
			Patient m = (Patient) patient;
		
			statement.setString(1, m.getFirstName());
			statement.setString(2, m.getLastName());
			statement.setInt(3, m.getAddress().getId());
			statement.setString(4, m.getPhone());
			statement.setString(5, m.getMedicareNo());
			statement.setInt(6, m.getId());
			
			statement.setInt(7, m.getAddress().getUnitNo());
			statement.setInt(8, m.getAddress().getStreetNo());
			statement.setString(9, m.getAddress().getStreetName());
			statement.setString(10, m.getAddress().getCity());
			statement.setString(11, m.getAddress().getState());
			statement.setInt(12, m.getAddress().getPostCode());
			statement.setInt(13, m.getAddress().getId());

			statement.executeQuery();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void delete(IDomainObject patient) {
		try {
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(deleteSQL);
			Patient m = (Patient) patient;

			statement.setInt(1, m.getId());

			statement.setString(2, m.getFirstName());

			statement.setString(3, m.getLastName());

			statement.setInt(4, m.getAddress().getId());

			statement.setString(5, m.getPhone());

			statement.setString(6, m.getMedicareNo());

			statement.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
