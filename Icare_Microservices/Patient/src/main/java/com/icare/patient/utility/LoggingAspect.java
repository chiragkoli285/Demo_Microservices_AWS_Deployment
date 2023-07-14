package com.icare.patient.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static final Log logger = LogFactory.getLog(LoggingAspect.class);
	
	@AfterThrowing(pointcut = "execution(* com.icare.patient.*.*.*(..))", throwing = "exception")
	public void throwException(Exception exception) {
		logger.error(exception.getMessage(), exception);
	}
}
