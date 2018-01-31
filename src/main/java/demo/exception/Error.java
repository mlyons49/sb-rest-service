package demo.exception;

public class Error {

	private String code;
	private String type;
	private String message;
	private String source;
	private String actualErrorMessage;
	
	public Error() {
		
	}
	
	public Error(Throwable th) {
		initError(th);
	}

	public Error(ClaimsBusinessException be) {
		this.code = be.getCode();
		initError(be);
	}
	
	private void initError(Throwable th) {
		this.type = th.getClass().getName();
		this.message = th.getMessage();
		Throwable inner = th.getCause();
		if (inner != null)
		{
			this.source = inner.getClass().getName();
			this.actualErrorMessage = inner.getMessage();
		}
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getActualErrorMessage() {
		return actualErrorMessage;
	}
	public void setActualErrorMessage(String actualErrorMessage) {
		this.actualErrorMessage = actualErrorMessage;
	}
	
}
