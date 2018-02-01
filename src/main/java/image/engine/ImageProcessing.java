package image.engine;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import image.domain.Image;

@Service
public interface ImageProcessing {
	void resize(Image image, HttpServletResponse response);
	void crop(Image image, HttpServletResponse response);
}