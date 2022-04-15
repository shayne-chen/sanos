package com.shaw.sanos.client.consumer;

import com.shaw.sanos.client.properties.ConsumerProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author shaw
 * @date 2022/4/8
 */
@Configuration
@EnableConfigurationProperties(ConsumerProperties.class)
@ConditionalOnProperty(prefix = ConsumerProperties.PREFIX, name = "enable", havingValue = "true")
public class ConsumerProcessorConfigure implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        registerBean(beanDefinitionRegistry, "consumerProcessor", ConsumerProcessor.class);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    private void registerBean(BeanDefinitionRegistry registry, String name, Class<?> beanClass) {
        RootBeanDefinition rbd = new RootBeanDefinition(beanClass);
        registry.registerBeanDefinition(name, rbd);
    }

}
