import java.sql.SQLException;

public class DBConnectionTest {

	public static void main(String[] args) {
		DBConnection connection = new DBConnection();
		if (connection != null) {
			try {
				connection.selectRecordsFromDbUserTable();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Failed to make connection!");
		}
	}

}
