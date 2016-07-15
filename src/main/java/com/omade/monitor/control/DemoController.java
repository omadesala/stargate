package com.omade.monitor.control;

import java.sql.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omade.monitor.config.ServiceProperties;
import com.omade.monitor.domain.DeviceDao;
import com.omade.monitor.domain.DeviceItem;
import com.omade.monitor.utils.MD5Util;
import com.omade.monitor.utils.Produces;

@RestController
@RequestMapping("/demo")
public class DemoController {

	private static Logger logger = Logger.getLogger(DemoController.class);

	@SuppressWarnings("unused")
	@Autowired
	private EhCacheCacheManager ehcache;

	@Autowired
	private DeviceDao jobDao;

	@SuppressWarnings("unused")
	@Autowired
	@Qualifier("serviceProperties")
	private ServiceProperties config;

	@RequestMapping(value = "/auth", method = RequestMethod.GET, produces = Produces.JSON_STRING)
	public void getSwitfAuthToken(@RequestHeader Map<String, String> header,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("uri :  /auth ");
	}

	@RequestMapping(value = "/swift/v1", method = RequestMethod.GET, produces = Produces.JSON_STRING)
	public void listContainer(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("uri: " + "/swift/v1");
	}

	@RequestMapping(value = "/jpa", method = RequestMethod.GET, produces = Produces.JSON_STRING)
	public void createRecord(HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("uri: " + request.getRequestURI());
		DeviceItem recored = new DeviceItem();

		String idStr = UUID.randomUUID().toString();
		recored.setIdstr(idStr);
		recored.setDescription("descripe what used for such device");
		recored.setMd5(MD5Util.MD5(idStr));
		recored.setCreatedate(new Date(System.currentTimeMillis()));

		jobDao.save(recored);
		logger.info("add data to storage:  ");

	}

}
