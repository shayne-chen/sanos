package com.shaw.sanos.transport.entity;

/**
 * @author shaw
 * @date 2022/4/14
 */
public class BaseEntity {

    private Integer code;

    private boolean success;

    private Long time;

    public void setCode(Integer code) { this.code = code; }

    public Integer getCode() { return code; }

    public void setSuccess(boolean success) { this.success = success; }

    public boolean getSuccess() {return success;}

    public void setTime(Long time) { this.time = time; }

    public Long getTime() { return time; }
}
