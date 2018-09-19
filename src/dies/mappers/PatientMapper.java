package dies.mappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import dies.models.IDomainObject;
import dies.models.Patient;

public class PatientMapper extends DataMapper {

	private ResultSetDetails rsm = new ResultSetDetails();
	private Patient patient = null;

	private DBConnection db = new DBConnection();
	private String findSQL = "" +
			"select t1.id,\r\n" + 
			"       t1.first_name,\r\n" + 
			"       t1.last_name,\r\n" + 
			"       t2.id          as patient_address_id,\r\n" + 
			"       t2.unit_no     as patient_unit_no,\r\n" + 
			"       t2.street_no   as patient_street_no,\r\n" + 
			"       t2.street_name as patient_street_name,\r\n" + 
			"       t2.city        as patient_city,\r\n" + 
			"       t2.state       as patient_state,\r\n" + 
			"       t2.post_code   as patient_post_code,\r\n" + 
			"       t1.phone,\r\n" + 
			"       t1.medicare_no,\r\n" + 
			"       t1.email\r\n" + 
			"from public.patient t1\r\n" + 
			"       inner join public.address t2 on t1.address_id = t2.id ";
	
	private String findByIDSQL = findSQL + " where t1.id=?";
	private String findByMedicareSQL = findSQL + " where lower(medicare_no) like lower(?) limit 10";
	private String insertSQL = ""
			+ "with rows as ( \r\n"
			+ "insert into public.address (unit_no, street_no, street_name, city, state, post_code) \r\n"
			+ "values (?, ?, ?, ?,?,?) returning id \r\n" + ") \r\n"
			+ "insert into public.patient (first_name,last_name,address_id, phone, medicare_no, email)\r\n"
			+ "select ?, ?, id, ?, ?, ? from rows \r\n";
	private String updateSQL = ""
			+ "update public.patient \r\n" + "set first_name=?, \r\n" + "last_name=?, \r\n"
			+ "address_id=?, phone=?, medicare_no=?, email=? \r\n" 
			+ "where id=?;\r\n" 
			+ "update public.address \r\n"
			+ "set unit_no=?, \r\n" 
			+ "street_no=?, \r\n" 
			+ "street_name=?, \r\n" 
			+ "city=?, \r\n" 
			+ "state=?,\r\n"
			+ "post_code=?\r\n" 
			+ "where id=?;\r\n";
	private String deleteSQL = ""
			+ "delete from public.patient "
			+ "where id=? and first_name=? and last_name=? and address_id=? and phone=? and medicare_no=?";

	public ArrayList<Patient> find(String medicareNo, boolean autocomplate) {
		Connection con = null;
		ResultSet rs = null;
		ArrayList<Patient> patientList = new ArrayList<Patient>();

		try {
			con = db.getConnection();
			PreparedStatement statement = null;
			statement = con.prepareStatement(findByMedicareSQL);
			if (autocomplate) {
				statement.setString(1, "%" + medicareNo + "%");
			} else {
				statement.setString(1, medicareNo);
			}
			rs = statement.executeQuery();

			while (rs.next()) {
				patientList.add(rsm.getPatient(rs, rsm.getPatientAddress(rs)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patientList;
	}

	public boolean find(String medicareNo) {		
		try {
			Connection con = db.getConnection();
			PreparedStatement statement = null;
			boolean medicareNoExist = false;
			statement = con.prepareStatement(findByMedicareSQL);
			statement.setString(1, medicareNo);
			
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				medicareNoExist = true;
			}
			return medicareNoExist;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Patient find(int id) {
		Connection con = null;
		ResultSet rs = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = null;
			statement = con.prepareStatement(findByIDSQL);
			statement.setInt(1, id);

			rs = statement.executeQuery();
			while (rs.next()) {
				patient = rsm.getPatient(rs, rsm.getPatientAddress(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patient;
	}

	public Patient findAll() {
		Connection con;
		
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(findSQL);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				patient = rsm.getPatient(rs, rsm.getPatientAddress(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		;
		return patient;
	}

	public void insert(IDomainObject patient) {
		try {
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(insertSQL);
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
			statement.setString(11, m.getEmail());
			statement.executeUpdate();

		} catch (SQLException e1) {
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
			statement.setString(6, m.getEmail());
			statement.setInt(7, m.getId());
			statement.setInt(8, m.getAddress().getUnitNo());
			statement.setInt(9, m.getAddress().getStreetNo());
			statement.setString(10, m.getAddress().getStreetName());
			statement.setString(11, m.getAddress().getCity());
			statement.setString(12, m.getAddress().getState());
			statement.setInt(13, m.getAddress().getPostCode());
			statement.setInt(14, m.getAddress().getId());
			statement.executeUpdate();
		} catch (SQLException e1) {
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
			e1.printStackTrace();
		}
	}
}
