package image.engine.graphicsmagick;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.gm4java.engine.GMConnection;
import org.gm4java.engine.GMException;
import org.gm4java.engine.GMServiceException;
import org.gm4java.engine.support.GMConnectionPoolConfig;
import org.gm4java.engine.support.PooledGMService;

import image.domain.Image;
import image.engine.ImageProcessing;
import image.exception.ResizeException;
import image.response.Response;


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
	
	
	Response response = new Response();
	
	/**
	 * @param process
	 * @param tempDir
	 */
	public GraphicsMagick(int process){
		this.gmProcess = process;
		GMConnectionPoolConfig config = new GMConnectionPoolConfig();	
		config.setMaxActive(gmProcess);
		
		if(gmService == null){
			gmService = new PooledGMService(config);
		}
	}
	

	

	/* (non-Javadoc)
	 * @see image.engine.ImageProcessing#resize(java.io.File, image.domain.Image, javax.servlet.http.HttpServletResponse)
	 */
	public void resize(Image image, HttpServletResponse httpServletResponse){
		GMConnection connection = null;
		try{
			connection = gmService.getConnection();
			resize(connection, image);
			response.writeOutputStream(image.getTempImageName(), httpServletResponse);
		}catch(Exception e) {
			throw new ResizeException("Resize Exception: " + e.getMessage());
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
	private void resize(GMConnection connection, Image image) throws IOException, GMException, GMServiceException{
		connection.execute(
				"convert",
				image.getFile().getAbsolutePath(),
				"-size", String.valueOf(image.getWidth())+"x"+String.valueOf(image.getHeight())+">",
				image.getFile().getAbsolutePath(),
				"-resize", String.valueOf(image.getWidth())+"x"+String.valueOf(image.getHeight())+">",
				"+profile", "*",
				"-interlace", "Line",
				"-colorspace", "RGB",
				"-quality", "85",
				image.getTempImageName());	
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
	private void crop(GMConnection connection, Image image) throws IOException, GMException, GMServiceException{
		connection.execute(
				"convert",
				"-size", String.valueOf(image.getWidth())+"x"+String.valueOf(image.getHeight())+"^",
				image.getFile().getAbsolutePath(),
			    "-resize", String.valueOf(image.getWidth())+"x"+String.valueOf(image.getHeight())+"^",
			    "+profile", "*",
			    "-interlace", "Line",
			    "-colorspace", "RGB",
			    "-quality", "85",
			    "-gravity", image.getGravity(),
			    "-crop", String.valueOf(image.getWidth())+"x"+String.valueOf(image.getHeight())+"+0+0",
			    image.getTempImageName());		
	}



	/* (non-Javadoc)
	 * @see image.engine.ImageProcessing#crop(java.io.File, image.domain.Image, javax.servlet.http.HttpServletResponse)
	 */
	public void crop(Image image, HttpServletResponse httpServletResponse){
		GMConnection connection = null;
		try{
			connection = gmService.getConnection();
			crop(gmService.getConnection(), image);
			response.writeOutputStream(image.getTempImageName(), httpServletResponse);
		}catch(Exception e) {
			throw new ResizeException("Resize Exception: " + e.getMessage());
		}finally{
			try {
				connection.close();
			} catch (GMServiceException e) {
				throw new ResizeException(e.getMessage());
			}
		}
	}
}
