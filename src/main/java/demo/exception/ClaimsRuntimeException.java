package demo.exception;

public class ClaimsRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -3043101092967595612L;
	
	public ClaimsRuntimeException(String message) {
		super(message);
	}
	
	public ClaimsRuntimeException(String message, Throwable th) {
		super(message, th);
	}

}
