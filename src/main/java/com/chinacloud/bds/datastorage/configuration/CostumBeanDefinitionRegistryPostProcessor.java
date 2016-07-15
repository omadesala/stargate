package com.chinacloud.bds.datastorage.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;

@Configuration
public class CostumBeanDefinitionRegistryPostProcessor implements
		BeanDefinitionRegistryPostProcessor {

	private static final Logger logger = LoggerFactory
			.getLogger(CostumBeanDefinitionRegistryPostProcessor.class);

	private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();
	private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		logger.info("Invoke Metho postProcessBeanFactory");

		// BeanDefinition bd = beanFactory.getBeanDefinition("dataSourceA");
		// MutablePropertyValues mpv = bd.getPropertyValues();
		//
		// mpv.addPropertyValue("driverClassName", "com.mysql.jdbc.Driver");
		// mpv.addPropertyValue("url", "jdbc:mysql://localhost:3306/test");
		// mpv.addPropertyValue("username", "root");
		// mpv.addPropertyValue("password", "123456");
	}

	@Override
	public void postProcessBeanDefinitionRegistry(
			BeanDefinitionRegistry registry) throws BeansException {
		logger.info("Invoke Metho postProcessBeanDefinitionRegistry");

		// registerBean(registry, "roleDao", RoleDaoImpl.class);
		// registerBean(registry, "yamlPrivDao", YamlPrivDaoImpl.class);
		// registerBean(registry, "yamlPrivService", YamlPrivServiceImpl.class);
		// registerBean(registry, "userPrivService", UserPrivServiceImpl.class);
		// registerBean(registry, "oneAAService", OneAAServiceImpl.class);
	}

	@SuppressWarnings("unused")
	private void registerBean(BeanDefinitionRegistry registry, String name,
			Class<?> beanClass) {

		AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(
				beanClass);

		abd.setAutowireCandidate(true);
		abd.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);
		// abd.setAttribute("id", rebuilder);
		// abd.setAttribute("name", rebuilder);
		// abd.setInitMethodName("init");
		abd.setScope(BeanDefinition.SCOPE_SINGLETON);
		// abd.setDependencyCheck(AbstractBeanDefinition.DEPENDENCY_CHECK_NONE);

		ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();

		abd.setConstructorArgumentValues(constructorArgumentValues);

		ScopeMetadata scopeMetadata = this.scopeMetadataResolver
				.resolveScopeMetadata(abd);
		abd.setScope(scopeMetadata.getScopeName());
		// 可以自动生成name
		String beanName = (name != null ? name : this.beanNameGenerator
				.generateBeanName(abd, registry));

		AnnotationConfigUtils.processCommonDefinitionAnnotations(abd);

		BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(abd,
				beanName);

		BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder,
				registry);
	}
}