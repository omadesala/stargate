package com.chinacloud.bds.datastorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by daniel on 15-9-23.
 */
public class ApplicationStartUp implements
		ApplicationListener<ApplicationStartedEvent> {

	private static final Logger LOG = LoggerFactory
			.getLogger(ApplicationStartUp.class);

	@Override
	public void onApplicationEvent(
			ApplicationStartedEvent applicationStartedEvent) {
		LOG.info("onApplicationEvent do something initial ");
	}
}
