package image.reader;

import java.awt.color.ColorSpace;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.stream.ImageInputStream;

import org.springframework.stereotype.Service;

import com.twelvemonkeys.imageio.metadata.jpeg.JPEGQuality;

import image.domain.Image;
import image.exception.ImageReaderException;

/** A image reader class for retrieving width, height, quality of images.
 * 
 * @author <a href="mailto:younggyuchun@gmail.com">Younggyu Chun</a>
 *
 */
@Service
public class ImageIOReader {
	
	public Image readImageMeta(File file, Image image){
		ImageInputStream iis = null;
		ImageReader reader = null;
		
        try{
			iis = ImageIO.createImageInputStream(file);
			Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);    		
			//imageInfo.put("colorSpace", "RGB");
			reader = getImageReader(readers);
			reader.setInput(iis);
			
			image.setOriginalWidth(reader.getWidth(reader.getMinIndex()));
			image.setOriginalHeight(reader.getHeight(reader.getMinIndex()));
			image.setOriginalFormat(reader.getFormatName());
			image.setOriginalColorSpace(getColorSpace(reader));			
			
			if("jpeg".equals(image.getOriginalColorSpace())) {
				int quality = (int)((JPEGQuality.getJPEGQuality(ImageIO.createImageInputStream(file)) * 100) - 4);
				image.setOriginalQuality(quality);
			}
        }catch(IOException e){
        	throw new ImageReaderException("No Image Reader Exception");
        }finally{
        	if(iis != null){
        		try {
					iis.close();
				} catch (IOException e) {
					throw new ImageReaderException("No Image Reader Exception");
				}
        	}
        	
        	if(reader != null){
        		reader.dispose();
        	}
        }
		return image;
	}
	
	private String getColorSpace(ImageReader reader) throws IOException {		
		Iterator<ImageTypeSpecifier> imageTypeSpecs = reader.getImageTypes(reader.getMinIndex());
		String originalColorSpace = "";
		 
		while(imageTypeSpecs.hasNext()){
			ImageTypeSpecifier imageTypeSpec = imageTypeSpecs.next();
			ColorSpace colorSpace = imageTypeSpec.getColorModel().getColorSpace();
			if(ColorSpace.TYPE_CMYK == colorSpace.getType() || ColorSpace.TYPE_CMY == colorSpace.getType()){
				originalColorSpace = "CMYK";
			}
		}
		return originalColorSpace;
	}
	
	private ImageReader getImageReader(Iterator<ImageReader> readers){
		if(!readers.hasNext()){
			return null;
		}
        return readers.next();
	}
}
