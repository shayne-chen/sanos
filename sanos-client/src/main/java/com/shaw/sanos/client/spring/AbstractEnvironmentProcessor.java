package com.shaw.sanos.client.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;


/**
 * @author shaw
 * @date 2022/4/6
 */
public abstract class AbstractEnvironmentProcessor implements EnvironmentAware, InitializingBean, ApplicationContextAware, ApplicationListener<ApplicationReadyEvent> {

    public String registerCenterUrl;

    protected ConfigurableEnvironment environment;

    protected ApplicationContext applicationContext;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public final void afterPropertiesSet() throws Exception {
        registerCenterUrl = environment.getProperty("sanos.register.center");
    }

    @Override
    public abstract void onApplicationEvent(ApplicationReadyEvent event);

}
