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
// @Configuration
// @ImportResource({ "classpath*:one-auth-bean.xml" })
// @PropertySources({ @PropertySource("classpath*:oneaasdk.properties"), })
@EnableConfigurationProperties({/* OneAASDKProperties.class, */
ServiceProperties.class })
// ServiceProperties.class, SpringProperties.class, PolicyProperties.class })
// @EnableAutoConfiguration(exclude = {
// org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class
// })
public class DataStorageApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication springApplication = new SpringApplication(
				DataStorageApplication.class);
		springApplication.addListeners(new ApplicationStartUp());
		springApplication.addListeners(new ApplicationStarted());
		springApplication.run(args);

		// SpringApplication.run(DataStorageApplication.class, args);
	}

	// @Bean
	// public static PropertySourcesPlaceholderConfigurer
	// placeholderConfigurer() {
	// PropertySourcesPlaceholderConfigurer c = new
	// PropertySourcesPlaceholderConfigurer();
	// c.setLocation(new ClassPathResource("oneaasdk.properties"));
	// return c;
	// }

	@Bean
	public BeanFactoryPostProcessor beanFactoryPostProcessor() {
		return new CostumBeanDefinitionRegistryPostProcessor();
		// return new DefaultFiltersBeanFactoryPostProcessor();
	}

	@Bean
	public CommandLineRunner init(final DataSource dataSource) {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				System.out.println("::::::::::::::flyway begin");
				// Flyway flyway = new Flyway();
				// flyway.setDataSource(dataSource);
				// flyway.setInitOnMigrate(true);
				// flyway.migrate();
				// System.out.println("::::::::::::::flyway end");
			}
		};
	}
}
