package com.shaw.sanos.client.provider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author shaw
 * @date 2022/4/7
 */
public abstract class AbstractProviderProcessor implements ApplicationContextAware {

    protected ProviderProcessor providerProcessor;

    @Override
    public final void setApplicationContext(ApplicationContext applicationContext) {
        this.providerProcessor = applicationContext.getBean(ProviderProcessor.class);
    }
}
