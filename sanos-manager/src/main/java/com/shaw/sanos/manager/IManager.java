package com.shaw.sanos.manager;

import com.shaw.sanos.transport.Node;

/**
 * @author shaw
 * @date 2022/3/22
 */
public interface IManager<T extends Node> {

    boolean addNode(T t);

    boolean updateNode(T t);

    boolean expireNode(T t);

    boolean renewNode(T t);

}
