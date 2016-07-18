package com.omade.monitor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.omade.monitor.config.ServiceProperties;
import com.omade.monitor.config.SpringProperties;
import com.omade.monitor.domain.DeviceDao;

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

		// generate the token cache

		DeviceDao dao = (DeviceDao) event.getApplicationContext().getBean(
				"deviceDao");

		List<String> findDevicesMD5 = dao.findDevicesMD5();

		for (String md5 : findDevicesMD5) {
			LOG.info("md5: " + md5);
		}

	}
}