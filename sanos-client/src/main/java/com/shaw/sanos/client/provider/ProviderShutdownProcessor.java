package com.shaw.sanos.client.provider;

import com.shaw.sanos.client.cache.LocalServerCache;
import com.shaw.sanos.client.constant.SanosConstant;
import com.shaw.sanos.client.dto.ProviderDTO;
import com.shaw.sanos.client.spring.AbstractEnvironmentProcessor;
import com.shaw.sanos.common.utils.HttpClientUtil;
import com.shaw.sanos.common.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextClosedEvent;

/**
 * @author shaw
 * @date 2022/4/19
 */
public class ProviderShutdownProcessor extends AbstractEnvironmentProcessor<ContextClosedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ProviderShutdownProcessor.class);

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        doShutdown(buildInstanceNode());
    }

    private ProviderDTO buildInstanceNode() {
        LocalServerCache localServerCache = LocalServerCache.getLocalServerInfo();
        return new ProviderDTO(localServerCache.getApplication(), localServerCache.getIp(), localServerCache.getPort(),
                System.currentTimeMillis());
    }

    private void doShutdown(ProviderDTO providerDTO) {
        String shutdownUrl = registerCenterUrl + SanosConstant.API_PREFIX + "/provider/shutdown";
        logger.info("服务关闭, url: {}, info: {}", shutdownUrl, providerDTO.toString());
        HttpClientUtil.doPostJson(shutdownUrl, null, null, JsonUtil.toJSONString(providerDTO));
    }
}
