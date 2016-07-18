/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.omade.monitor;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.mangofactory.swagger.plugin.EnableSwagger;
import com.omade.monitor.config.ServiceProperties;
import com.omade.monitor.configuration.CostumBeanDefinitionRegistryPostProcessor;

@SpringBootApplication
@EnableSwagger
@ComponentScan
@EnableConfigurationProperties({ ServiceProperties.class })
public class Application {

	private static final Logger logger = LoggerFactory
			.getLogger(Application.class);

	public static void main(String[] args) throws Exception {

		SpringApplication springApplication = new SpringApplication(
				Application.class);
		springApplication.addListeners(new ApplicationStartUp());
		springApplication.addListeners(new ApplicationStarted());
		springApplication.run(args);
	}

	@Bean
	public BeanFactoryPostProcessor beanFactoryPostProcessor() {
		return new CostumBeanDefinitionRegistryPostProcessor();
	}

	@Bean
	public CommandLineRunner init(final DataSource dataSource) {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				logger.info("CommandLineRunner running ...");
			}
		};
	}
}
