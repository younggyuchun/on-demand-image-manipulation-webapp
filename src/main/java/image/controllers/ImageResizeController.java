package image.controllers;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import image.domain.Image;
import image.service.resize.ResizeService;


/**
 * @author <a href="mailto:younggyuchun@gmail.com">Younggyu Chun</a>
 *
 */
@Controller
public class ImageResizeController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ResizeService resizeService;
	
	@RequestMapping(value = "/images/v1/{operation}", method = RequestMethod.GET)
	public void resize(Image image, 
			HttpServletRequest request, 
			HttpServletResponse response){
		
		logger.info("width:{} height:{} qulity:{}, operation:RESIZE", image.getWidth(), image.getHeight(), image.getQuality());
		resizeService.imageProcess(image, request, response);
		
	}		
}
