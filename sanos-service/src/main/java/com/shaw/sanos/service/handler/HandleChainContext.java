package com.shaw.sanos.service.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shaw
 * @date 2022/3/23
 */
public class HandleChainContext {

    private HandleChain handleChain;

    private volatile Handler handler;

    private HttpServletRequest httpServletRequest;

    private HttpServletResponse httpServletResponse;

    public HandleChainContext(HandleChain handleChain, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        this.handleChain = handleChain;
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
    }

    public void doHandle() {
        if (handleChain == null || handleChain.getHead() == null) {
            return;
        }
        Handler handler = handleChain.getHead();
        handler.handle(this);
    }

    public void setHandleChain(HandleChain handleChain) { this.handleChain = handleChain; }

    public HandleChain getHandleChain() { return handleChain; }

    public void setHandler(Handler handler) { this.handler = handler; }

    public Handler getHandler() { return handler; }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) { this.httpServletRequest = httpServletRequest; }

    public HttpServletRequest getHttpServletRequest() { return httpServletRequest; }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) { this.httpServletResponse = httpServletResponse; }

    public HttpServletResponse getHttpServletResponse() { return httpServletResponse; }
}
