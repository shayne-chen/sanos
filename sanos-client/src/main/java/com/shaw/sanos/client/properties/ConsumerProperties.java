package com.shaw.sanos.client.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author shaw
 * @date 2022/4/8
 */
@ConfigurationProperties(ConsumerProperties.PREFIX)
public class ConsumerProperties {

    public static final String PREFIX = "sanos.consumer";

    private boolean enable;

    private String apps;

}
