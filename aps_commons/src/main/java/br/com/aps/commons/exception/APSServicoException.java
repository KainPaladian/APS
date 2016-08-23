package br.com.aps.commons.exception;

public class APSServicoException extends APSException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5939545233220489962L;

	public APSServicoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public APSServicoException(String message, Throwable cause) {
		super(message, cause);
	}

	public APSServicoException(String message) {
		super(message);
	}

	public APSServicoException(Throwable cause) {
		super(cause);
	}

}
