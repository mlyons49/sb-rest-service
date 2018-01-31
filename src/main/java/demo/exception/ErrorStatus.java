package demo.exception;

public class ErrorStatus {

	private String status;
	private Error error;
	
	public ErrorStatus() {
		this.status = "FAILURE";
		this.error = new Error();
	}
	
	public ErrorStatus(Throwable th) {
		this.status = "FAILURE";
		this.error = new Error(th);
	}

	public ErrorStatus(ClaimsBusinessException busEx) {
		this.status = "FAILURE";
		this.error = new Error(busEx);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}
	
}
