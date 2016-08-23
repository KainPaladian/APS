package br.com.aps.commons.exception;

public class APSException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -628898817901569998L;

	public APSException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public APSException(String message, Throwable cause) {
		super(message, cause);
	}

	public APSException(String message) {
		super(message);
	}

	public APSException(Throwable cause) {
		super(cause);
	}

}
