package com.shaw.sanos.client.provider;

import com.shaw.sanos.client.constant.SanosConstant;
import com.shaw.sanos.client.dto.ProviderDTO;
import com.shaw.sanos.client.event.HeartBeatEvent;
import com.shaw.sanos.common.utils.HttpClientUtil;
import com.shaw.sanos.common.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author shaw
 * @date 2022/4/7
 */
public class ProviderHeartBeatProcessor extends AbstractProviderProcessor implements ApplicationListener<HeartBeatEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ProviderHeartBeatProcessor.class);

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void onApplicationEvent(HeartBeatEvent event) {
        logger.info("接收到监听事件, {}", event.getProviderDTO().toString());
        executorService.scheduleAtFixedRate(() -> doHeartBeat(event.getProviderDTO()), 0,
                SanosConstant.INTERVAL_TIME, TimeUnit.SECONDS);
    }

    private void doHeartBeat(ProviderDTO providerDTO) {
        providerDTO.setLastActiveTime(System.currentTimeMillis());
        String heartBeatUrl = providerProcessor.registerCenterUrl + SanosConstant.API_PREFIX + "/heart/beat";
        if (logger.isDebugEnabled()) {
            logger.debug("上报心跳, url: {}, info: {}", heartBeatUrl, providerDTO.toString());
        }
        HttpClientUtil.doPostJson(heartBeatUrl, null, null, JsonUtil.toJSONString(providerDTO));
    }

}
