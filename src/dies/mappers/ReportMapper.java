package dies.mappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import db.DBConnection;
import dies.models.IDomainObject;
import dies.models.Report;

public class ReportMapper extends DataMapper{

    private DBConnection db = new DBConnection();
	private String updateSQL = ""       +
            "update public.report\r\n"  + 
            "set id           = ?,\r\n" + 
            "    author_id    = ?,\r\n" + 
            "    content      = ?,\r\n" + 
            "    date_created = ?,\r\n" + 
            "    date_updated = ?,\r\n" + 
            "    state        = ?";
	
	@Override
	public void update(IDomainObject report) {
		try {
			System.out.println(" CALLING THE DB UPDATE");
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(updateSQL);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			
			Report r = (Report) report;
			statement.setInt(1, r.getId());
			statement.setInt(2, r.getAuthor().getId());
			statement.setString(3, r.getContent());			
			statement.setString(4, r.getDateCreated().format(formatter));
			statement.setString(5, r.getDateUpdated().format(formatter));
			statement.setString(6, r.getState().name());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(IDomainObject obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(IDomainObject obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IDomainObject find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}