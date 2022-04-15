package com.shaw.sanos.service.handler;

/**
 * @author shaw
 * @date 2022/3/23
 */
public interface Handler {

    void handle(HandleChainContext handleChainContext);

    Handler next();

    void setNext(Handler handler);
}
