package com.shaw.sanos.transport.entity;

/**
 * @author shaw
 * @date 2022/4/14
 */
public class Response<T> extends BaseEntity {

    public Response(String message) {
        this.message = message;
    }

    public Response() {}

    private String message;

    private T data;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setData(T t) { this.data = t; }

    public T getData() { return data; }
}
