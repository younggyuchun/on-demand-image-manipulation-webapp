package image.exception;

/**
 * @author <a href="mailto:younggyuchun@gmail.com">Younggyu Chun</a>
 *
 */
public class ResizeException extends RuntimeException{
	
	private static final long serialVersionUID = -2864697280817935729L;

	public ResizeException(String message) {
		super(message);
	}
}