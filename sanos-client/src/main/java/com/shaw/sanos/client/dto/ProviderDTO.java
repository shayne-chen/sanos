package com.shaw.sanos.client.dto;

import java.io.Serializable;

/**
 * @author shaw
 * @date 2022/4/6
 */
public class ProviderDTO implements Serializable {

    private static final long serialVersionUID = -7975219845804112122L;

    public ProviderDTO(String application, String ip, String port, Long lastActiveTime) {
        this.application = application;
        this.ip = ip;
        this.port = port;
        this.lastActiveTime = lastActiveTime;
    }

    private String ip;

    private String port;

    private String application;

    private Long lastActiveTime;

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPort() {
        return port;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getApplication() {
        return application;
    }

    public void setLastActiveTime(Long lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public Long getLastActiveTime() {
        return lastActiveTime;
    }

    @Override
    public String toString() {
        return String.format("application:%s, ip:%s, port:%s", application, ip, port);
    }
}
