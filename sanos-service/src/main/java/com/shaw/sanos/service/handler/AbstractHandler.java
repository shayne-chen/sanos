package com.shaw.sanos.service.handler;

/**
 * @author shaw
 * @date 2022/3/23
 */
public abstract class AbstractHandler implements Handler {

    private Handler next = null;

    @Override
    public final void handle(HandleChainContext handleChainContext) {
        handleChainContext.setHandler(this);
        this.doHandle(handleChainContext);
        if (next != null) {
            next().handle(handleChainContext);
        }
    }

    public abstract void doHandle(HandleChainContext handleChainContext);

    @Override
    public Handler next() { return next; }

    @Override
    public void setNext(Handler handler) { this.next = handler; }
}
