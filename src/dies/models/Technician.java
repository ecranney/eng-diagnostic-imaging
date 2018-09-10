/**
 * A Technician user for the DIES system. This user-type has access to the
 * image-taking interface.
 * <p>
 * Note: This class is effectively a stub. It is included because Feature A
 * requires a Technician to be assigned to a patient's Appointment. Technicians
 * will eventually have their own interface for the DIES system (for taking
 * and uploading patients images) - the class will be completed when this is
 * is implemented.
 *
 * @author ecranney
<<<<<<< HEAD
 * @since 2018-09-09
=======
 * @since September 2018
 * 
>>>>>>> 0c3a339f7d1f7170233cafb2e7f031649b86a1d6
 */
package dies.models;

public class Technician extends User {

    // note, identity field inherited from User

    public Technician(int id, String username, String password,
                      String firstName, String lastName) {
        super(id, username, password, firstName, lastName);
    }

}
