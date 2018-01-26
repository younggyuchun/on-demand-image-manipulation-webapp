package image;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"image"})
public class ImageResizeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageResizeApplication.class, args);
	}
}
