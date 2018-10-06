package dies.mappers;

import db.DBConnection;
import dies.models.IDomainObject;
import dies.models.Machine;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MachineMapper extends DataMapper {

    private DBConnection db = new DBConnection();
    private String findMachinesSQL = "select id, serial_code, type from public.machines";
    private String findFreeMachinesSQL = "" +
            "select distinct t2.id, t2.serial_code, t2.type\r\n" +
            "from appointment_machine t1\r\n" +
            "       right outer join machine t2 on t1.machine_id = t2.id\r\n" +
            "where t2.id not in (select distinct t3.machine_id\r\n" +
            "                    from appointment_machine t3\r\n" +
            "                           right outer join appointment t4 on t3.appointment_id = t4.id\r\n" +
            "                    where t4.date >= ? \r\n" +
            "                      and t4.date <= cast(? as timestamp) + interval '1 hours');";

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
                    machine = new Machine(rs.getInt("id"), rs.getString("serial_code"),
                            Machine.Type.valueOf(rs.getString("type")));
                    macnineList.add(machine);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return macnineList;
    }

    public List<Machine> findAll(LocalDateTime datetime) {
        Connection con;
        ArrayList<Machine> macnineList = new ArrayList<Machine>();
        try {
            con = db.getConnection();
            PreparedStatement statement = con.prepareStatement(findFreeMachinesSQL);
            statement.setTimestamp(1, Timestamp.valueOf(datetime));
            statement.setTimestamp(2, Timestamp.valueOf(datetime));
            Machine machine = null;
            macnineList = new ArrayList<Machine>();
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                try {
                    machine = new Machine(rs.getInt("id"), rs.getString("serial_code"),
                            Machine.Type.valueOf(rs.getString("type")));
                    macnineList.add(machine);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return macnineList;
    }

	public Machine find(int id) {
		Connection con;
		Machine machine = null;

		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(findMachineSQL);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				try {
					machine = new Machine(rs.getInt("id"), rs.getString("serial_code"),
							Machine.Type.valueOf(rs.getString("type")));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return machine;
	}

    public void insert(IDomainObject machine) {
        try {
            Connection con = db.getConnection();
            PreparedStatement statement = con.prepareStatement(inserMachineSQL);
            Machine m = (Machine) machine;
            statement.setInt(1, m.getId());
            statement.setString(2, m.getSerialNo());
            statement.setString(3, m.getType().name());
            statement.executeUpdate();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void update(IDomainObject machine) {
        try {
            Connection con = db.getConnection();
            PreparedStatement statement = con.prepareStatement(updateMachineSQL);
            Machine m = (Machine) machine;
            statement.setInt(1, m.getId());
            statement.setString(2, m.getSerialNo());
            statement.setString(3, m.getType().name());
            statement.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void delete(IDomainObject machine) {
        try {
            Connection con = db.getConnection();
            PreparedStatement statement = con.prepareStatement(deleteMachineSQL);
            Machine m = (Machine) machine;
            statement.setInt(1, m.getId());
            statement.setString(2, m.getSerialNo());
            statement.setString(3, m.getType().name());
            statement.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
