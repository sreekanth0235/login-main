package farmhub.login.utility;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import farmhub.login.exception.FarmHubException;





@RestControllerAdvice
public class ExceptionControllerAdvice {
	@Autowired
	private Environment environment;
	private static final Log logger = org.apache.commons.logging.LogFactory.getLog(ExceptionControllerAdvice.class);

	@ExceptionHandler(FarmHubException.class)
	public ResponseEntity<ErrorInfo> farmHubExceptionHandler(FarmHubException exception) {
		logger.error(exception.getMessage(), exception);
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.BAD_REQUEST);
	}
@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception) {
		logger.error(exception.getMessage(), exception);
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
		

	}
//@ExceptionHandler({MethodArgumentNotValidException.class,ConstraintViolationException.class})
//public ResponseEntity<ErrorInfo> validatorExceptionHandler(Exception exception){
//	logger.error(exception.getMessage(), exception);
//	String errorMsg;
//	if(exception instanceof MethodArgumentNotValidException) {
//		MethodArgumentNotValidException methodArgNotValidExe=(MethodArgumentNotValidException)exception;
//	//	Object ObjectError;
//		errorMsg=methodArgNotValidExe.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
//	}
//	else {
//		ConstraintViolationException cvException= (ConstraintViolationException)exception;
//		errorMsg= cvException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
//	}
//	
//		ErrorInfo errorInfo=new ErrorInfo();
//		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
//		errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
//		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.BAD_REQUEST);
//}
//validation failures on DTOs
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ErrorInfo> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
		 ErrorInfo error = new ErrorInfo();
	     error.setErrorCode(HttpStatus.BAD_REQUEST.value());
	     error.setErrorMessage(ex.getBindingResult().getAllErrors().stream()
                                                      .map(ObjectError::getDefaultMessage)
	        		                                    .collect(Collectors.joining(", ")));
	     return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
//Validation failures on URI parameters	
@ExceptionHandler(ConstraintViolationException.class)
public ResponseEntity<ErrorInfo> handleConstraintValidationExceptions(
		ConstraintViolationException ex) {
	
		 ErrorInfo error = new ErrorInfo();
       error.setErrorCode(HttpStatus.BAD_REQUEST.value());
       error.setErrorMessage(ex.getConstraintViolations().stream()
                                                       .map(ConstraintViolation::getMessage)
      		                                      .collect(Collectors.joining(", ")));
       return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    
}
	
	

}






