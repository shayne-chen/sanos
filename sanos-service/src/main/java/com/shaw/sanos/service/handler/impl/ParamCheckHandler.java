package com.shaw.sanos.service.handler.impl;

import com.shaw.sanos.service.handler.AbstractHandler;
import com.shaw.sanos.service.handler.HandleChainContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shaw
 * @date 2022/3/23
 */
public class ParamCheckHandler extends AbstractHandler {

    @Override
    public void doHandle(HandleChainContext handleChainContext) {
        HttpServletRequest request = handleChainContext.getHttpServletRequest();


    }
}
