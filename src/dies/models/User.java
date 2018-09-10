/**
 * A User of the DIES system. This is the abstract parent of all other
 * user-types (Radiologist, Receptionist, Technician, Admin). It contains
 * the properties and methods common to all user-types, and permits for
 * polymorphic operations over sets of users.
 *
 * @author ecranney
 * @since 2018-09-09
 */
package dies.models;

public abstract class User implements IDomainObject {

    // identity field, used for database lookup
    private final int id;

    // attributes common to all users
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public User(int id, String username, String password, String firstName,
                String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
