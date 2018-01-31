package demo.exception.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * Extend or overwrite the Exception class names in exceptionReturnCodeMap to define the 
 * HTTP Return codes 400, 403, etc.
 */
@Component
public class ResponseCodeMapper {

	private static final Integer DEFAULT_EX_RETURN_CODE = new Integer(500);
	
	private Map<String, Integer> exceptionReturnCodeMap;
	
	public ResponseCodeMapper() {
		exceptionReturnCodeMap = new HashMap<String, Integer>();
		exceptionReturnCodeMap.put("demo.exception.ClaimsValidationException", 400);
	}
	
	public int getResponseCode(Throwable th) {
		String exClassName = th.getClass().getName();
		
		Integer returnCode = exceptionReturnCodeMap.get(exClassName);
		if (returnCode == null) {
			returnCode = DEFAULT_EX_RETURN_CODE;
		}
		return returnCode.intValue();
	}

	public Map<String, Integer> getExceptionReturnCodeMap() {
		return exceptionReturnCodeMap;
	}

	public void setExceptionReturnCodeMap(Map<String, Integer> exceptionReturnCodeMap) {
		this.exceptionReturnCodeMap = exceptionReturnCodeMap;
	}

	
	
}
