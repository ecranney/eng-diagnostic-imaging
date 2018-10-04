/**
 * A Patient registered in the DIES system. Patients are registered the first
 * time that they make a booking in the system. Note: Patients are not a user-
 * type for the system (there is no way for Patients to interface with DIES).
 * <p>
 * Note: Currently, patients can be uniquely identified by their Medicare
 * Number; this will need to be updated to accommodate people without a
 * Medicare Number (e.g. international students).
 *
 * @author ecranney
 * @since September 2018
 */
package dies.models;

public class Patient implements IDomainObject {

    // identity field, used for database lookup
    private final int id;

    private String firstName;
    private String lastName;
    private Address address;
    private String phone;
    private String medicareNo;
    private String email;

    public Patient(int id, String firstName, String lastName, Address address,
                   String phone, String medicareNo, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.medicareNo = medicareNo;
        this.email = email;
    }

    public int getId() {
        return id;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMedicareNo() {
        return medicareNo;
    }

    public void setMedicareNo(String medicareNo) {
        this.medicareNo = medicareNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getValue() {
        return (String.format("%s %s <%s>", this.firstName, this.lastName, this.medicareNo));
    }
}
