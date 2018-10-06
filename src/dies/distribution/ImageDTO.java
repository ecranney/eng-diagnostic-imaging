/**
 * Data Transfer Object for an Image.
 * 
 * @author ecranney
 * @date October 2018
 */

package dies.distribution;

import java.io.OutputStream;
import java.io.InputStream;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;

public class ImageDTO {
	
	// static method for encoding images
	public static void toXML(ImageDTO imageDTO, OutputStream outputStream) {
		XMLEncoder encoder = new XMLEncoder(outputStream);
		encoder.writeObject(imageDTO);
		encoder.close();
	}
	
	// static method for decoding images
	public static ImageDTO fromXML(InputStream inputStream) {
		XMLDecoder decoder = new XMLDecoder(inputStream);
		ImageDTO imageDTO = (ImageDTO) decoder.readObject();
		decoder.close();
		return imageDTO;
	}
	
	private int id;
	private String url;
	
	public ImageDTO(int id, String url) {
		this.id = id;
		this.url = url;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}