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

public class Radiologist extends User {

    // note, identity field inherited from User

    // Australian medical practitioners registration number
    private String registrationNo;

    public Radiologist(int id, String username, String password,
                       String registrationNo, String firstName, String lastName, String group) {
        super(id, username, password, firstName, lastName, group);
        this.registrationNo = registrationNo;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistratinoNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

}
