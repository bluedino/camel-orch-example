package basaki;

/**
 * 
 * <tt>ValidationException</tt> is thrown when any process encounters any
 * exceptional condition while processing.
 * <p/>
 * 
 * @author Indra Basak
 * 
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}
}
