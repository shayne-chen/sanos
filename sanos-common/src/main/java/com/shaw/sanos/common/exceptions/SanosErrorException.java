package com.shaw.sanos.common.exceptions;

import com.shaw.sanos.common.enums.ExceptionCodeEnum;

/**
 * @author shaw
 * @date 2022/4/15
 */
public class SanosErrorException extends SanosException {

    private static final long serialVersionUID = -7235038288808499735L;

    private String errorCode;

    private String errorMessage;

    public SanosErrorException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getErrorCode());
        this.errorCode = exceptionCodeEnum.getErrorCode();
        this.errorMessage = exceptionCodeEnum.getErrorMessage();
    }

    public String getErrorCode() { return errorCode; }

    public String getErrorMessage() { return errorMessage; }
}
