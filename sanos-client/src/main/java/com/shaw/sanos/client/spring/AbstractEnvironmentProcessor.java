package com.shaw.sanos.client.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.*;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;


/**
 * @author shaw
 * @date 2022/4/6
 */
public abstract class AbstractEnvironmentProcessor<E extends ApplicationEvent> implements EnvironmentAware, InitializingBean, ApplicationListener<E> {

    protected String registerCenterUrl;

    protected ConfigurableEnvironment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
    }

    @Override
    public final void afterPropertiesSet() throws Exception {
        registerCenterUrl = environment.getProperty("sanos.register.center");
    }

    @Override
    public abstract void onApplicationEvent(E event);

}
