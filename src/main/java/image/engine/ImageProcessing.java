package image.engine;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import image.domain.Image;

@Service
public interface ImageProcessing {
	void resize(File file, Image image, HttpServletResponse response);
	void crop(File file, Image image, HttpServletResponse response);
}