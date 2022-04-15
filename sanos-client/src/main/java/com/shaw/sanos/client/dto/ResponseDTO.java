package com.shaw.sanos.client.dto;

/**
 * @author shaw
 * @date 2022/4/12
 */
public class ResponseDTO<T> {

    private boolean success;

    private Integer code = 0;

    private T result;

    public void setSuccess(boolean success) { this.success = success; }

    public boolean getSuccess() {return success;}

    public void setCode(Integer code) { this.code = code; }

    public Integer getCode() { return code; }

    public void setResult(T result) { this.result = result; }

    public T getResult() { return result; }

}
