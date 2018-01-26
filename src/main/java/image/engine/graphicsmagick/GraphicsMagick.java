package image.engine.graphicsmagick;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.gm4java.engine.GMConnection;
import org.gm4java.engine.GMException;
import org.gm4java.engine.GMServiceException;
import org.gm4java.engine.support.GMConnectionPoolConfig;
import org.gm4java.engine.support.PooledGMService;

import image.domain.Image;
import image.engine.ImageProcessing;
import image.exception.ResizeException;


/**This implementation uses gm4java. It encapsulates the complexity of GraphicsMagick.
 * For further information refer to link below.
 * https://github.com/sharneng/gm4java
 * 
 * @author Younggyu Chun
 *
 */
public class GraphicsMagick implements ImageProcessing{
	
	private PooledGMService gmService;
	private int gmProcess;
	private String imgTempDir;
	
	/**
	 * @param process
	 * @param tempDir
	 */
	public GraphicsMagick(int process, String tempDir){
		this.gmProcess = process;
		this.imgTempDir = tempDir;
		
		GMConnectionPoolConfig config = new GMConnectionPoolConfig();	
		config.setMaxActive(gmProcess);
		
		if(gmService == null){
			gmService = new PooledGMService(config);
		}
	}
	

	/* (non-Javadoc)
	 * @see image.engine.ImageProcessing#resize(java.io.File, image.domain.Image, javax.servlet.http.HttpServletResponse)
	 */
	public void resize(File file, Image image, HttpServletResponse response){
		GMConnection connection = null;
		try{
			connection = gmService.getConnection();
			
			StringBuilder targetFileName = new StringBuilder();
			targetFileName.append(imgTempDir)
			.append(File.separator)			
			.append(file.getName())
			.append("_")
			.append(System.currentTimeMillis())
			.append("_")
			.append(image.getRandomNum());
			
			//String targetFileName = imgTempDir + File.pathSeparator + imgDir + file.getName() + "_" + System.currentTimeMillis() + "_" + image.getRandomNum();
			resize(connection, file, image, targetFileName.toString());
			OutputStream toClient = response.getOutputStream();
			toClient.write(FileUtils.readFileToByteArray(new File(targetFileName.toString())));
			toClient.close();
			
			File targetFile = new File(targetFileName.toString());
			targetFile.delete();
		}catch(Exception e) {
			throw new ResizeException(e.getMessage());
		}finally{
			try {
				connection.close();
			} catch (GMServiceException e) {
				throw new ResizeException(e.getMessage());
			}
		}
	}
	
	/**
	 * @param connection
	 * @param file
	 * @param image
	 * @param targetFileName
	 * @throws IOException
	 * @throws GMException
	 * @throws GMServiceException
	 */
	private void resize(GMConnection connection, File file, Image image, String targetFileName) throws IOException, GMException, GMServiceException{
		connection.execute(
				"convert",
				file.getAbsolutePath(),
				"-size", String.valueOf(image.getWidth())+"x"+String.valueOf(image.getHeight())+">",
				file.getAbsolutePath(),
				"-resize", String.valueOf(image.getWidth())+"x"+String.valueOf(image.getHeight())+">",
				"+profile", "*",
				"-interlace", "Line",
				"-colorspace", "RGB",
				"-quality", "85",
				targetFileName);	
	}



	/* (non-Javadoc)
	 * @see image.engine.ImageProcessing#crop(java.io.File, image.domain.Image, javax.servlet.http.HttpServletResponse)
	 */
	public void crop(File file, Image image, HttpServletResponse response) {
		
	}	

}
