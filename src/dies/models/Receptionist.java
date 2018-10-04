/**
 * A Receptionist user for the DIES system. This user-type has access to and
 * the appointments-management interface, and is able to manage appointments.
 *
 * @author ecranney
 * @since September 2018
 */
package dies.models;

import java.sql.ResultSet;

import dies.mappers.ResultSetMap;

public class Receptionist extends User {

	// note, identity field inherited from User
	private static ResultSetMap rsm = new ResultSetMap();

	public Receptionist(int id, String username, String password, String firstName, String lastName, String group) {
		super(id, username, password, firstName, lastName, group);
	}

	public static User load(ResultSet rs) {
		User user = rsm.getUser(rs);
		return new Receptionist(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName(),
				user.getLastName(), user.getGroup());
	}
}
