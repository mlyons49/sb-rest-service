package demo.exception;

public class ClaimsBusinessException extends Exception {

	private static final long serialVersionUID = 3990175098087713733L;

	private String code;
	
	public ClaimsBusinessException(String message) {
		super(message);
	}
	
	public ClaimsBusinessException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	public ClaimsBusinessException(String code, String message, Throwable th) {
		super(message, th);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
