package dies.mappers;

import dies.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import db.DBConnection;

public class MachineMapper extends DataMapper {

	private DBConnection db = new DBConnection();
	private String findMachinesSQL = "select id, serial_code, type from public.machines";
	private String findMachineSQL = "select id, serial_code, type from public.machines where id=?";
	private String inserMachineSQL = "insert into id, serial_code, type public.machines values (?, ?, ?)";
	private String updateMachineSQL = "update public.machines set id=?, serial_code=?, type=?";
	private String deleteMachineSQL = "delete from public.machines where id=? and serial_code=? and type=?";

	public List<Machine> findAll() {
		Connection con;
		ArrayList<Machine> macnineList = new ArrayList<Machine>();
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(findMachinesSQL);
			Machine machine = null;
			macnineList = new ArrayList<Machine>();
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				try {
					machine = new Machine(rs.getInt("id"), rs.getInt("serial_code"),
							Machine.Type.valueOf(rs.getString("type")));
					macnineList.add(machine);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return macnineList;
	}

	public Machine find(int id) throws SQLException {
		Connection con = db.getConnection();
		PreparedStatement statement = con.prepareStatement(findMachineSQL);
		statement.setInt(1, id);
		Machine machine = null;
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			try {
				machine = new Machine(rs.getInt("id"), rs.getInt("serial_code"),
						Machine.Type.valueOf(rs.getString("type")));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return machine;
	}

	public void insert(IDomainObject machine) {
		try {
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(inserMachineSQL);
			Machine m = (Machine) machine;

			statement.setInt(1, m.getId());

			statement.setLong(2, m.getSerialNo());

			statement.setString(3, m.getType().name());

			statement.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void update(IDomainObject machine) {
		try {
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(updateMachineSQL);
			Machine m = (Machine) machine;

			statement.setInt(1, m.getId());

			statement.setLong(2, m.getSerialNo());

			statement.setString(3, m.getType().name());

			statement.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void delete(IDomainObject machine) {
		try {
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(deleteMachineSQL);
			Machine m = (Machine) machine;

			statement.setInt(1, m.getId());

			statement.setLong(2, m.getSerialNo());

			statement.setString(3, m.getType().name());

			statement.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
