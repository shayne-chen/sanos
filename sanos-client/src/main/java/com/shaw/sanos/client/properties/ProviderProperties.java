package com.shaw.sanos.client.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author shaw
 * @date 2022/4/11
 */
@ConfigurationProperties(ProviderProperties.PREFIX)
public class ProviderProperties {

    public static final String PREFIX = "sanos.provider";

    private boolean enable;
}
