/**
 * A Receptionist user for the DIES system. This user-type has access to and
 * the appointments-management interface, and is able to manage appointments.
 *
 * @author ecranney
 * @since September 2018
 * 
 */
package dies.models;

public class Receptionist extends User {

    // note, identity field inherited from User

    public Receptionist(int id, String username, String password,
                        String firstName, String lastName) {
        super(id, username, password, firstName, lastName);
    }

}
