package com.icare.appointment.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static final Log logger = LogFactory.getLog(LoggingAspect.class);

	@AfterThrowing(pointcut = "execution(* com.icare.appointment.*.*.*(..))", throwing = "exception")
	public void throwsException(Exception exception) {
		logger.error(exception.getMessage(), exception);
	}
}
