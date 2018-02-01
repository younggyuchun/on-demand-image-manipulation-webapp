package image.domain;

import java.io.File;
import java.util.Random;

/**
 * @author <a href="mailto:younggyuchun@gmail.com">Younggyu Chun</a>
 *
 */
public class Image {
	
	//requested parameters
	private int width;
	private int height;
	private int quality;
	private String path;
	private String gravity;
	private String operation;
	
	//image's original information derived from ImageIOReader.class
	private int originalWidth;
	private int originalHeight;
	private int originalQuality;
	private String originalColorSpace;
	private String originalFormat;
	
	//image name
	private String targetName;
	private String tempName;
	private String tempDir;
	
	File file;
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getTempName() {
		return tempName;
	}
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	public String getTempDir() {
		return tempDir;
	}
	public void setTempDir(String tempDir) {
		this.tempDir = tempDir;
	}
	public String getGravity() {
		return gravity;
	}
	public void setGravity(String gravity) {
		this.gravity = gravity;
	}
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
	
	private int getRandomNum(){
    	Random oRandom = new Random();
		return oRandom.nextInt(1000) + 1;
    }
	
	
	
	public void setTempImageName() {
		StringBuilder targetFileName = new StringBuilder();
		this.tempName = targetFileName.append(getTempDir())
				.append(File.separator)			
				.append(getTargetName())
				.append("_")
				.append(System.currentTimeMillis())
				.append("_")
				.append(getRandomNum()).toString();
	}
	public String getTempImageName() {
		return tempName;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
}
