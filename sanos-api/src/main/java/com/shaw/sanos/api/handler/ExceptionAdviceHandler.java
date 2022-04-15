package com.shaw.sanos.api.handler;

import com.shaw.sanos.common.enums.ExceptionCodeEnum;
import com.shaw.sanos.common.exceptions.SanosErrorException;
import com.shaw.sanos.common.exceptions.SanosException;
import com.shaw.sanos.transport.entity.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author shaw
 * @date 2022/4/15
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdviceHandler {

    @ExceptionHandler(SanosErrorException.class)
    public ResponseError sanosErrorException(SanosErrorException e) {
        log.error("error info", e);
        ResponseError responseError = new ResponseError(e.getErrorCode(), e.getErrorMessage());
        responseError.setCode(ExceptionCodeEnum.DEFAULT_ERROR.getCode());
        responseError.setSuccess(false);
        responseError.setTime(System.currentTimeMillis());
        return responseError;
    }

    @ExceptionHandler(SanosException.class)
    public ResponseError sanosException(SanosException e) {
        log.error("error info", e);
        return returnFormatErrorInfo(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseError exception(Exception e) {
        log.info("error info", e);
        return returnFormatErrorInfo(e.getMessage());
    }

    private ResponseError returnFormatErrorInfo(String message) {
        ResponseError responseError = new ResponseError();
        responseError.setCode(ExceptionCodeEnum.DEFAULT_ERROR.getCode());
        responseError.setSuccess(false);
        responseError.setTime(System.currentTimeMillis());
        responseError.setErrorCode("");
        responseError.setErrorMessage(message);
        return responseError;
    }
}
