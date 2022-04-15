package com.shaw.sanos.manager.instance;

import com.shaw.sanos.manager.AbstractManager;
import com.shaw.sanos.manager.constant.ManagerConstant;
import com.shaw.sanos.transport.instance.InstanceNode;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author shaw
 * @date 2022/3/22
 * provider注册上来的实例信息
 */
@Component
public class InstanceNodeManager extends AbstractManager<InstanceNode> implements InitializingBean {

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @Override
    public boolean updateNode(InstanceNode instance) {
        Set<InstanceNode> instanceSet = instanceCache.get(instance.getApplication());
        if (CollectionUtils.isEmpty(instanceSet)) {
            instanceSet = new HashSet<>();
        } else {
            instanceSet.remove(instance);
        }
        instanceSet.add(instance);
        instanceCache.put(instance.getApplication(), instanceSet);
        allInstanceNodes.remove(instance);
        allInstanceNodes.add(instance);
        return true;
    }

    @Override
    public boolean expireNode(InstanceNode instance) {
        if (!allInstanceNodes.contains(instance)) {
            return true;
        }
        allInstanceNodes.remove(instance);
        Set<InstanceNode> instanceSet = instanceCache.get(instance.getApplication());
        instanceSet.remove(instance);
        instanceCache.put(instance.getApplication(), instanceSet);
        return true;
    }

    @Override
    public boolean renewNode(InstanceNode instance) {
        return this.updateNode(instance);
    }

    @Override
    public Set<InstanceNode> getNodeList(String instance) {
        return instanceCache.get(instance) == null ? Collections.emptySet() : instanceCache.get(instance);
    }

    @Override
    public Map<String, Set<InstanceNode>> getBatchNodeList(String[] names) {
        Map<String, Set<InstanceNode>> result = new HashMap<>();
        List<String> nameList = Arrays.asList(names);
        for (Map.Entry<String, Set<InstanceNode>> entry : instanceCache.entrySet()) {
            if (nameList.contains(entry.getKey())) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        executorService.scheduleAtFixedRate(() -> {
            long now = System.currentTimeMillis();
            Set<InstanceNode> set = getAllNodeList();
            Iterator iterator = set.iterator();
            if (iterator.hasNext()) {
                InstanceNode instance = (InstanceNode) iterator.next();
                if (now - instance.getLastActiveTime() > ManagerConstant.EXPIRE_TIME) {
                    Set<InstanceNode> instanceSet = instanceCache.get(instance.getApplication());
                    instanceSet.remove(instance);
                    instanceCache.put(instance.getApplication(), instanceSet);
                    iterator.remove();
                }
            }
        }, 0, ManagerConstant.SCAN_TIME, TimeUnit.MILLISECONDS);
    }
}
