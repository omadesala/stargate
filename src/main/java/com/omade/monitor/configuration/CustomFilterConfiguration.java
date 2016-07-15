package com.omade.monitor.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.omade.monitor.service.CustomFilter;

// @ImportResource("classpath*:one-auth-bean.xml")
@ComponentScan(basePackages = { "com.chinacloud.bds.datastorage" })
@Configuration
public class CustomFilterConfiguration implements ServletContextInitializer {

	public FilterRegistrationBean uriFilter(ServletContext servletContext)
			throws ServletException {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new CustomFilter());
		filterRegistrationBean.setOrder(Integer.MAX_VALUE - 1);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}

	public void onStartup(ServletContext servletContext)
			throws ServletException {
		uriFilter(servletContext);
	}

}
