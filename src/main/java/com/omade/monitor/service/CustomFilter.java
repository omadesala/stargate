package com.omade.monitor.service;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

import com.omade.monitor.config.ServiceProperties;
import com.omade.monitor.configuration.CacheConfiguration;
import com.omade.monitor.utils.EhCacheUtils;

@Component("customFilter")
public class CustomFilter implements Filter {

	private static Logger logger = Logger.getLogger(CustomFilter.class);

	@Autowired
	@Qualifier("serviceProperties")
	private ServiceProperties config;

	@Autowired
	private EhCacheCacheManager ehcache;

	@Override
	public void destroy() {
		logger.info("destroy...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		logger.info("doFilter...");

		if (config.getTokenswitch() && request instanceof HttpServletRequest) {

			String accessToken = ((HttpServletRequest) request)
					.getHeader(Constant.XAUTH_TOKEN);
			String requestURI = ((HttpServletRequest) request).getRequestURI();
			logger.info("requestToken: " + accessToken);
			logger.info("requestURI: " + requestURI);

			if (!requestURI.startsWith(config.getOpenapi())) {
				EhCacheUtils.getTokenFromNamedCache(ehcache,
						CacheConfiguration.CACHE_USERID_TOKEN, accessToken);
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.info("filter init...");
	}

}