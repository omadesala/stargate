package com.omade.monitor.service;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

import com.omade.monitor.config.ServiceProperties;

@Component("customFilter")
public class CustomFilter implements Filter {

	private static Logger logger = Logger.getLogger(CustomFilter.class);

	@SuppressWarnings("unused")
	@Autowired
	@Qualifier("serviceProperties")
	private ServiceProperties config;

	@SuppressWarnings("unused")
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
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.info("filter init...");
	}

}