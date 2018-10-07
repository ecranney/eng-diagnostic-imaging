package dies.mappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DBConnection;
import dies.models.IDomainObject;
import dies.models.Report;

public class ReportMapper extends DataMapper{

    private DBConnection db = new DBConnection();
	private String updateSQL = ""       +
            "update public.report\r\n"  + 
            "set" + 
            "    author_id    = ?,\r\n" + 
            "    content      = ?,\r\n" + 
            "    state        = ?" +
            "where id = ?";
	private String updateStateSQL = ""       +
            "update public.report\r\n"  + 
            "set" + 
            "    reviewer_id  = ?,\r\n" +
            "    state        = ?" +
            "where id = ?";
	
	@Override
	public void update(IDomainObject report) {
		try {
			System.out.println(" CALLING THE DB UPDATE");
			Connection con = db.getConnection();
			Report r = (Report) report;
			
			if (r.getState().equals(Report.State.REVIEW_FAILED) || r.getState().equals(Report.State.REVIEW_PASSED)) {
				PreparedStatement statement = con.prepareStatement(updateStateSQL);
				statement.setInt(1, r.getReviewer().getId());
				statement.setString(2, r.getState().name());
				statement.setInt(3, r.getId());
				statement.executeUpdate();
			} else {
				PreparedStatement statement = con.prepareStatement(updateSQL);
				statement.setInt(1, r.getAuthor().getId());
				statement.setString(2, r.getContent());			
				statement.setString(3, r.getState().name());
				statement.setInt(4, r.getId());
				statement.executeUpdate();
			}
			
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