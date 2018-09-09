/**
 * A Machine registered in the DIES system. This represents each of the
 * different machines used by technicians to take images of patients (e.g.
 * X-Ray machine, MRI machine, CT scanner, etc.).
 * 
 * Note: this class is a stub. It is included because machines are assigned to
 * Appointments when these are created (Feature A). It will be completed
 * for a separate feature.I
 * 
 * @author ecranney
 * @since 2018-09-09
 * 
 */
package dies.models;

public class Machine implements IDomainObject {

	// identity field, used for database lookup
	private final int id;
	
	// the machine's serial number
	private long serialNo;
	
	// the machine type
	public enum Type {XRAY, MRI, CAT}
	private Type type;
	
	public Machine(int id, long serialNo, Type type) {
		this.id = id;
		this.serialNo = serialNo;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	
	public long getSerialNo() {
		return serialNo;
	}
	
	public Type getType() {
		return type;
	}
}
