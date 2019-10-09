package idv.heimlich.ExecuteSQL.common.exception;

public class ApBusinessException  extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApBusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ApBusinessException(final String message) {
		super(message);
	}
	
}
