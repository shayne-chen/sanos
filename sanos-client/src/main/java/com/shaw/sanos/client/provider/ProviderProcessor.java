package com.shaw.sanos.client.provider;

import com.shaw.sanos.client.cache.LocalServerCache;
import com.shaw.sanos.client.constant.SanosConstant;
import com.shaw.sanos.client.dto.ProviderDTO;
import com.shaw.sanos.client.spring.AbstractEnvironmentProcessor;
import com.shaw.sanos.common.utils.HttpClientUtil;
import com.shaw.sanos.common.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author shaw
 * @date 2022/4/8
 */
public class ProviderProcessor extends AbstractEnvironmentProcessor<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ProviderProcessor.class);

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (logger.isDebugEnabled()) {
            logger.debug("应用准备完成, 触发监听事件");
            logger.debug("获取注册中心的地址: {}, 启动provider心跳检测", registerCenterUrl);
        }
        ProviderDTO providerDTO = buildInstanceNode();
        executorService.scheduleAtFixedRate(() -> doHeartBeat(providerDTO), 0, SanosConstant.INTERVAL_TIME, TimeUnit.SECONDS);
    }

    private String[] getLocalServerUrl() {
        String[] result = new String[2];
        String ip = "";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ip = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        result[0] = ip;
        result[1] = environment.getProperty("server.port") == null ? "8080" : environment.getProperty("server.port");
        return result;
    }

    private ProviderDTO buildInstanceNode() {
        String application = environment.getProperty("spring.application.name");
        String[] ipInfo = this.getLocalServerUrl();
        LocalServerCache localServerCache = LocalServerCache.setLocalServerInfo(application, ipInfo[0], ipInfo[1]);
        return new ProviderDTO(localServerCache.getApplication(), localServerCache.getIp(), localServerCache.getPort(),
                System.currentTimeMillis());
    }

    private void doHeartBeat(ProviderDTO providerDTO) {
        String heartBeatUrl = registerCenterUrl + SanosConstant.API_PREFIX + "/heart/beat";
        if (logger.isDebugEnabled()) {
            logger.debug("上报心跳, url: {}, info: {}", heartBeatUrl, providerDTO.toString());
        }
        HttpClientUtil.doPostJson(heartBeatUrl, null, null, JsonUtil.toJSONString(providerDTO));
    }
}
