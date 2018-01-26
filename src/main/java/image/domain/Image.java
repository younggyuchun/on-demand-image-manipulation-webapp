package image.domain;

import java.util.Random;

/**
 * @author <a href="mailto:younggyuchun@gmail.com">Younggyu Chun</a>
 *
 */
public class Image {
	
	private int width;
	private int height;
	private int quality;
	private String path;
	private int originalWidth;
	private int originalHeight;
	private int originalQuality;
	private String originalColorSpace;
	private String originalFormat;
	
	public String getOriginalColorSpace() {
		return originalColorSpace;
	}
	public void setOriginalColorSpace(String originalColorSpace) {
		this.originalColorSpace = originalColorSpace;
	}
	public String getOriginalFormat() {
		return originalFormat;
	}
	public void setOriginalFormat(String originalFormat) {
		
		this.originalFormat = originalFormat;
	}
	public int getOriginalWidth() {
		return originalWidth;
	}
	public void setOriginalWidth(int originalWidth) {
		this.originalWidth = originalWidth;
	}
	public int getOriginalHeight() {
		return originalHeight;
	}
	public void setOriginalHeight(int originalHeight) {
		this.originalHeight = originalHeight;
	}
	public int getOriginalQuality() {
		return originalQuality;
	}
	public void setOriginalQuality(int originalQuality) {
		this.originalQuality = originalQuality;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	
	public int getRandomNum(){
    	Random oRandom = new Random();
		return oRandom.nextInt(1000) + 1;
    }
}
