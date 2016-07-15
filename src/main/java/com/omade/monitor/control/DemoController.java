package com.omade.monitor.control;

import java.util.Map;

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
import com.omade.monitor.utils.Produces;

@RestController
@RequestMapping("/demo")
public class DemoController {

	private static Logger logger = Logger.getLogger(DemoController.class);

	@SuppressWarnings("unused")
	@Autowired
	private EhCacheCacheManager ehcache;

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

}
