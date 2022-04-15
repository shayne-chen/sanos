package com.shaw.sanos.service.handler;

/**
 * @author shaw
 * @date 2022/3/23
 */
public class DefaultHandleChain implements HandleChain {

    private Handler head;
    private Handler tail;

    @Override
    public synchronized HandleChain addLast(Handler handler) {
        if (head == null) {
            head = handler;
            tail = head;
        } else {
            Handler prev = tail;
            prev.setNext(handler);
            tail = handler;
        }
        return this;
    }

    @Override
    public Handler getHead() {
        return head;
    }

    @Override
    public Handler getTail() {
        return tail;
    }
}
