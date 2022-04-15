package com.shaw.sanos.manager;

import com.shaw.sanos.transport.Node;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shaw
 * @date 2022/3/22
 */
public abstract class AbstractManager<T extends Node> implements IManager<T> {

    protected ConcurrentHashMap<String, Set<T>> instanceCache = new ConcurrentHashMap<>();

    protected Set<T> allInstanceNodes = new HashSet<>(256);

    @Override
    public boolean addNode(T t) {
        if (allInstanceNodes.contains(t)) {
            return true;
        }
        Set<T> set = instanceCache.get(t.getApplication()) == null ? new HashSet<>() : instanceCache.get(t.getApplication());
        set.add(t);
        instanceCache.put(t.getApplication(), set);
        allInstanceNodes.add(t);
        return true;
    }

    @Override
    public abstract boolean updateNode(T t);

    @Override
    public abstract boolean expireNode(T t);

    @Override
    public abstract boolean renewNode(T t);

    protected Set<T> getAllNodeList() {
        return allInstanceNodes;
    }

    public abstract Set<T> getNodeList(String name);

    /** 批量获取 */
    public abstract Map<String, Set<T>> getBatchNodeList(String[] names);

}
