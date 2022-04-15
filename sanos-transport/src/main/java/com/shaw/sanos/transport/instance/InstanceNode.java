package com.shaw.sanos.transport.instance;

import com.shaw.sanos.transport.Node;

import java.util.*;

/**
 * @author shaw
 * @date 2022/3/22
 */
public class InstanceNode extends Node {

    private static final long serialVersionUID = -7899143984501379041L;

    /** ip */
    private String ip;

    /** 端口 */
    private Integer port;

    public InstanceNode(){}

    public InstanceNode(String application, String ip, Integer port, Long lastActiveTime) {
        this.application = application;
        this.ip = ip;
        this.port = port;
        this.lastActiveTime = lastActiveTime;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getPort() {
        return port;
    }

    @Override
    public int hashCode() {
        return Objects.hash(application, ip, port);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof InstanceNode)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return obj.hashCode() == this.hashCode();
    }

    @Override
    public String toString() {
        return String.format("instance, application:%s, host:%s, lastActiveTime:%s", application, ip + ":"+ port,
                lastActiveTime);
    }
}
