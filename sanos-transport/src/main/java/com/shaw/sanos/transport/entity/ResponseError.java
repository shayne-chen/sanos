package com.shaw.sanos.transport.entity;

/**
 * @author shaw
 * @date 2022/4/14
 */
public class ResponseError extends BaseEntity {

    private String errorCode;

    private String errorMessage;

    public ResponseError() {}

    public ResponseError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
