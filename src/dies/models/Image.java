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
	private String imageURL;
	
	public Image(int id, String imageURL) {
		this.id = id;
		this.imageURL = imageURL;
	}
	
	public int getId() {
		return id;
	}

	public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
	
}
