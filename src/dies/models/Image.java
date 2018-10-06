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
	private String url;
	private String type;
	
	public Image(int id, String url, String type) {
		this.id = id;
		this.url = url;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}

	public String getURL() {
        return url;
    }

    public void setURL(String imageURL) {
        this.url = imageURL;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.url = type;
    }
}
