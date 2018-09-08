package dies.models;

public class Address {
	private Integer unitNo;
	private Integer streetNo;
	private String streetName;
	private String city;
	private String state;
	private Integer postCode;
	
	public Address(Integer unitNo, Integer streetNo, String streetName,
			String city, String state, Integer postCode) {
		this.unitNo = unitNo;
		this.streetNo = streetNo;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.postCode = postCode;
	}
	
	public Address(Integer streetNo, String streetName, String city,
			String state, Integer postCode) {
		this.unitNo = null;
		this.streetNo = streetNo;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.postCode = postCode;
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
