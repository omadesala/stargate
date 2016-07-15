package com.chinacloud.bds.datastorage.service.aspectj;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.chinacloud.bds.datastorage.config.ServiceProperties;

@Aspect
@Component
public class APIAspectCheck {
	private static Logger logger = Logger.getLogger(APIAspectCheck.class);

	@SuppressWarnings("unused")
	@Autowired
	@Qualifier("serviceProperties")
	private ServiceProperties config;

	// @Before("execution(* com.chinacloud.bds.datastorage.control.OSSController.getObject(..)) and args(..)")
	public void tokenValidate(JoinPoint jp) {
		logger.info("aop point ...");
	}
}
