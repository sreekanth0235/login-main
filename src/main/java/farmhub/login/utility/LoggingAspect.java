package farmhub.login.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import farmhub.login.exception.FarmHubException;



@Aspect
@Component
public class LoggingAspect {
	private static final Log logger=LogFactory.getLog(LoggingAspect.class);
	@AfterThrowing(pointcut = "execution(* com.farmhub.service.*Impl.*(..))",throwing = "exception")
	public void logServiceExcepton(FarmHubException exception) {
		logger.error(exception.getMessage(),exception);
	}

}
