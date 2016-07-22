package com.omade.monitor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.omade.monitor.config.ServiceProperties;
import com.omade.monitor.config.SpringProperties;
import com.omade.monitor.configuration.CacheConfiguration;
import com.omade.monitor.domain.DeviceDao;
import com.omade.monitor.domain.DeviceItem;
import com.omade.monitor.utils.EhCacheUtils;

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

		DeviceDao dao = (DeviceDao) event.getApplicationContext().getBean(
				"deviceDao");

		List<DeviceItem> findAllDevices = dao.findAllDevices();

		EhCacheCacheManager ehcache = (EhCacheCacheManager) event
				.getApplicationContext().getBean("appEhCacheCacheManager");

		for (DeviceItem device : findAllDevices) {
			String deviceid = device.getDeviceid();
			String md5 = device.getMd5();
			EhCacheUtils.update(ehcache, CacheConfiguration.CACHE_USERID_TOKEN,
					md5, deviceid);
		}

	}
}