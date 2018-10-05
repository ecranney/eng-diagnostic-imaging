package dies.mappers;

import db.DBConnection;
import dies.models.IDomainObject;
import dies.models.Radiologist;
import dies.models.Receptionist;
import dies.models.Technician;
import dies.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserMapper extends DataMapper {

    private ResultSetMap rsm = new ResultSetMap();
    private DBConnection db = new DBConnection();
    private String findUserSQL = "" +
            "select "
            + "t1.username, "
            + "t1.password, "
            + "t1.firstname as first_name, "
            + "t1.lastname as last_name, "
            + "t1.hash as password_hash, "
            + "t2.name as group\r\n" +
            "from public.user t1\r\n" +
            "       inner join public.group t2 on t1.group_id = t2.id\r\n";
    
    private String findUserByUsernameSQL = findUserSQL + " where t1.username = ?\r\n";
    private String findUserByUserIdSQL =  findUserSQL + " where id = ?\r\n";
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

    public User find(String username) {
        Connection con = null;
        ResultSet rs = null;
        User user = null;

        try {
            con = db.getConnection();
            PreparedStatement statement = null;
            statement = con.prepareStatement(findUserByUsernameSQL);
            statement.setString(1, username);

            rs = statement.executeQuery();
            while (rs.next()) {            	
                String group = rs.getString("group");
                if (group.equalsIgnoreCase("RECEPTIONIST")) {
                	user = Receptionist.load(rs);
                } else if (group.equalsIgnoreCase("RADIOLOGIST")) {
                	user = Radiologist.load(rs);
                } else if (group.equalsIgnoreCase("TECHNICIAN")) {
                	user =  Technician.load(rs);
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public User find(int userId) {
        Connection con = null;
        ResultSet rs = null;
        User user = null;

        try {
            con = db.getConnection();
            PreparedStatement statement = null;
            statement = con.prepareStatement(findUserByUserIdSQL);
            statement.setInt(1, userId);

            rs = statement.executeQuery();
            while (rs.next()) {
            	String group = rs.getString("group");
                if (group == "RECEPTIONIST") {
                	user = Receptionist.load(rs);
                } else if (group == "RADIOLOGIST") {
                	user = Radiologist.load(rs);
                } else if (group == "TECHNICIAN") {
                	user =  Technician.load(rs);
                }
            }
            con.close();
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
