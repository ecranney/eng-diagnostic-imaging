/**
 * An Address object registered in the DIES system. This is used to record
 * the addresses of employees (system Users), and the contact addresses for
 * Patients.
 *
 * @author ecranney
 * @since September 2018
 */
package dies.models;

public class Address implements IDomainObject {

    // identity field, used for database lookup
    private final int id;

    private Integer unitNo;
    private Integer streetNo;
    private String streetName;
    private String city;
    private String state;
    private Integer postCode;

    public Address(int id, Integer unitNo, Integer streetNo,
                   String streetName, String city, String state, Integer postCode) {
        this.id = id;
        this.unitNo = unitNo;
        this.streetNo = streetNo;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.postCode = postCode;
    }

    public int getId() {
        return id;
    }

    public Integer getUnitNo() {
        return unitNo;
    }

    public Integer getStreetNo() {
        return streetNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Integer getPostCode() {
        return postCode;
    }
}
