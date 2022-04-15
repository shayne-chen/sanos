package com.shaw.sanos.transport;

import java.io.Serializable;

/**
 * @author shaw
 * @date 2022/3/22
 */
public class Node implements Serializable {
    private static final long serialVersionUID = 7441514136063596055L;

    protected String application;

    /** 最后上报时间戳，单位：毫秒 */
    protected Long lastActiveTime;

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

}
