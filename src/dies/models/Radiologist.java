/**
 * A Radiologist user for the DIES system. This user-type has access to the
 * report-writing interface.
 * <p>
 * Note: this class is a stub. It has been included for a more complete view of
 * the User-type hierarchy, but will not be used until Feature B is implemented.
 *
 * @author ecranney
 * @since September 2018
 */
package dies.models;

import java.sql.ResultSet;

import dies.mappers.ResultSetMap;

public class Radiologist extends User {

    // note, identity field inherited from User
	
	private static ResultSetMap rsm = new ResultSetMap();
	
    // Australian medical practitioners registration number
    private String registrationNo;

    public Radiologist(int id, String username, String password,
    		String registrationNo, String firstName, String lastName,
    		String group, String hash) {
        super(id, username, password, firstName, lastName, group, hash);
        this.registrationNo = registrationNo;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistratinoNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }
    
    
    // NOTE: check what this does
    public static User load(ResultSet rs) {
    	User user = rsm.getUser(rs);
    	
		return new Radiologist(user.getId(), user.getUsername(), user.getPassword(), "", user.getFirstName(),
				user.getLastName(), user.getGroup(), user.getHash());
    }

}
