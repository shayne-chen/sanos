package com.shaw.sanos.client.provider;

import com.shaw.sanos.client.dto.ProviderDTO;
import com.shaw.sanos.client.event.HeartBeatEvent;
import com.shaw.sanos.client.spring.AbstractEnvironmentProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author shaw
 * @date 2022/4/8
 */
public class ProviderProcessor extends AbstractEnvironmentProcessor {

    private static final Logger logger = LoggerFactory.getLogger(ProviderProcessor.class);

    private String localServerIp;

    private String localServerPort;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (logger.isDebugEnabled()) {
            logger.debug("应用准备完成, 触发监听事件");
            logger.debug("获取注册中心的地址: {}, 启动provider心跳检测", registerCenterUrl);
        }
        getLocalServerUrl();
        applicationContext.publishEvent(new HeartBeatEvent(new Object(), buildInstanceNode()));
    }

    private void getLocalServerUrl() {
        String ip = "";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ip = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        localServerIp = ip;
        localServerPort = environment.getProperty("server.port") == null ? "8080" : environment.getProperty("server.port");
    }

    private ProviderDTO buildInstanceNode() {
        String application = environment.getProperty("spring.application.name");
        return new ProviderDTO(application, localServerIp, localServerPort, System.currentTimeMillis());
    }
}
