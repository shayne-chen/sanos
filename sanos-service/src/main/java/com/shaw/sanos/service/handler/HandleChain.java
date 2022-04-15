package com.shaw.sanos.service.handler;

/**
 * @author shaw
 * @date 2022/3/23
 */
public interface HandleChain {

    HandleChain addLast(Handler handler);

    Handler getHead();

    Handler getTail();
}
