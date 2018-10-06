/**
 * An Image uploaded to the DIES system by a technician. Radiologists write
 * reports on images that have been uploaded.
 *
 * @author ecranney
 * @date October 2018
 */

package dies.models;

public class Image implements IDomainObject {
	
	// identity field, used for database lookup
	private final int id;
	
	// url of the actual image
	private String url;
	
	public Image(int id, String url) {
		this.id = id;
		this.url = url;
	}
	
	public int getId() {
		return id;
	}
	
	public String getUrl() {
		return url;
	}
}
