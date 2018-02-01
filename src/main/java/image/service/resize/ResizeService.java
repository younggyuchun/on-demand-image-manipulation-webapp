package image.service.resize;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import image.domain.Image;
import image.engine.ImageProcessing;
import image.engine.graphicsmagick.GraphicsMagick;
import image.reader.ImageIOReader;

/**
 * @author <a href="mailto:younggyuchun@gmail.com">Younggyu Chun</a>
 *
 */
@Service
public class ResizeService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ImageIOReader reader;
	
	/*@Resource(name="GraphicsMagick") 
	ImageProcessing imageProcessing;*/
	
	@Value("${image.dir}")
	private String imgDir;
	
	@Value("${image.gm.process}")
	private String process;
	
	@Value("${image.dir}")
	private String imageDir;
	
	@Value("${image.dir.tempDir}")
	private String tempDir;
	
	
	private static ImageProcessing imageProcessing;	

	
	public void imageProcess(Image image, 
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		imageProcessing = new GraphicsMagick(Integer.valueOf(process));
		
		File file = new File(imgDir.concat(File.separator).concat(image.getPath()));
		
		if(file.exists()) {
			image.setFile(file);
			image.setTempDir(tempDir);
			image.setTargetName(image.getFile().getName());
			image.setTempImageName();
			image = reader.readImageMeta(image);
			
			if("resize".equals(image.getOperation())) {
				imageProcessing.resize(image, response);
			}else if("crop".equals(image.getOperation())) {
				imageProcessing.crop(image, response);
			}
			
		}else {
			logger.info("file does not exist");
			response.setStatus(404);
		}
	}
}
