package com.chinacloud.bds.datastorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.chinacloud.bds.datastorage.config.ServiceProperties;
import com.chinacloud.bds.datastorage.config.SpringProperties;

public class ApplicationStarted implements
		ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOG = LoggerFactory
			.getLogger(ApplicationStartUp.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		LOG.info("spring has started !!!");

		@SuppressWarnings("unused")
		SpringProperties springProperties = (SpringProperties) event
				.getApplicationContext().getBean("springProperties");

		@SuppressWarnings("unused")
		ServiceProperties serviceProperties = (ServiceProperties) event
				.getApplicationContext().getBean("serviceProperties");

		// do some initial when spring started.
	}
}