package demo.exception.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.exception.ErrorStatus;

@ControllerAdvice
public class ClaimsControllerAdvice {

	private static final Log LOG = LogFactory.getLog(ClaimsControllerAdvice.class);
	
	@Autowired
	private ResponseCodeMapper responseCodeMapper; 
	
	@ExceptionHandler(value = Throwable.class)
	@ResponseBody
	public ErrorStatus defaultErrorHandler(HttpServletRequest req, HttpServletResponse resp, Throwable th) throws Exception 
	{
		LOG.debug("in defaultErrorHandle");
		
		resp.setContentType(MediaType.APPLICATION_JSON.toString());
		
		int responseCode = responseCodeMapper.getResponseCode(th);
		if (responseCode != 400) {
			LOG.error(th);
		}
        
		ErrorStatus errorStatus = new ErrorStatus(th);
        return errorStatus;
	  }	
}
