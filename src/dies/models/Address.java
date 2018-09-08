package dies.models;

public class Address extends DomainObject {
	
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
