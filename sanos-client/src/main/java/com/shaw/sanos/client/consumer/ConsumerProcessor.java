package com.shaw.sanos.client.consumer;

import com.shaw.sanos.client.constant.SanosConstant;
import com.shaw.sanos.client.dto.ResponseDTO;
import com.shaw.sanos.client.spring.AbstractEnvironmentProcessor;
import com.shaw.sanos.common.utils.HttpClientUtil;
import com.shaw.sanos.common.utils.JsonUtil;
import com.shaw.sanos.transport.instance.InstanceNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author shaw
 * @date 2022/4/8
 * 消费者逻辑，获取关注的apps的providers地址
 */

public class ConsumerProcessor extends AbstractEnvironmentProcessor<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerProcessor.class);

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    protected ConcurrentHashMap<String, Set<InstanceNode>> providerMap = new ConcurrentHashMap<>();

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // 获取关注的apps providers地址
        String appNames = environment.getProperty("sanos.consumer.apps");
        if (appNames != null && appNames.length() != 0) {
            executorService.scheduleAtFixedRate(()-> getProviders(appNames), 0, SanosConstant.INTERVAL_TIME,
                    TimeUnit.SECONDS);
        }
    }

    private void getProviders(String appNames) {
        Map<String, String> map = new HashMap<>(4);
        map.put("applications", appNames);
        String resp = HttpClientUtil.doGet(registerCenterUrl + SanosConstant.API_PREFIX +"/provider/get/batch", map, null);
        logger.info("consumer get providers: {}", resp);
        ResponseDTO responseDTO = JsonUtil.parseObject(resp, ResponseDTO.class);
        providerMap = (ConcurrentHashMap<String, Set<InstanceNode>>) responseDTO.getResult();
        logger.debug("provider info: {}", JsonUtil.toJSONString(providerMap));
    }

}
