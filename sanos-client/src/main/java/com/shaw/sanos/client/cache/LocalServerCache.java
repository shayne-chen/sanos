package com.shaw.sanos.client.cache;

/**
 * @author shaw
 * @date 2022/4/19
 */
public class LocalServerCache {

    private String application;

    private String ip;

    private String port;

    private static volatile LocalServerCache instance;

    public String getIp() {
        return ip;
    }

    public String getApplication() {
        return application;
    }

    public String getPort() {
        return port;
    }

    public static LocalServerCache setLocalServerInfo(String application, String ip, String port) {
        if (instance == null) {
            synchronized (LocalServerCache.class) {
                if (instance == null) {
                    instance = new LocalServerCache();
                    instance.application = application;
                    instance.ip = ip;
                    instance.port = port;
                }
            }
        }
        return instance;
    }

    public static LocalServerCache getLocalServerInfo() {
        return instance;
    }
}
