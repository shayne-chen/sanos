package com.shaw.sanos.common.exceptions;

import com.shaw.sanos.common.enums.ExceptionCodeEnum;

/**
 * @author shaw
 * @date 2022/4/14
 */
public class SanosException extends RuntimeException {

    private static final long serialVersionUID = 3815997692093136709L;


    public SanosException(String message) {
        super(message);
    }

    public SanosException(String message, Throwable cause) {
        super(message, cause);
    }
}
