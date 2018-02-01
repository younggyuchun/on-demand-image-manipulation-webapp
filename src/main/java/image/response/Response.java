package image.response;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

@Service
public class Response {
	public void writeOutputStream(String fileName, HttpServletResponse response) throws IOException {
		OutputStream toClient = response.getOutputStream();
		toClient.write(FileUtils.readFileToByteArray(new File(fileName)));
		toClient.close();			
		
		File targetFile = new File(fileName);
		targetFile.delete();
	}
}
