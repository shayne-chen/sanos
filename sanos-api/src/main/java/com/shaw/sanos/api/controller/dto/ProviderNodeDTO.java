package com.shaw.sanos.api.controller.dto;

import java.io.Serializable;

/**
 * @author shaw
 * @date 2022/4/6
 */

public class ProviderNodeDTO implements Serializable {

    private static final long serialVersionUID = 8871997470880818124L;

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
