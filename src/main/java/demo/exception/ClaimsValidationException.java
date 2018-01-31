package demo.exception;

public class ClaimsValidationException extends ClaimsBusinessException {

	private static final long serialVersionUID = -3043101092967595611L;
	
	public ClaimsValidationException(String message) {
		super(message);
	}
	
	public ClaimsValidationException(String code, String message) {
		super(code, message);
	}
	
	public ClaimsValidationException(String code, String message, Throwable th) {
		super(code, message, th);
	}



}
