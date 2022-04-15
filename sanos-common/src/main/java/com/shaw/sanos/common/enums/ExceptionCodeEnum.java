package com.shaw.sanos.common.enums;

/**
 * @author shaw
 * @date 2022/4/14
 */
public enum ExceptionCodeEnum {
    DEFAULT_SUCCESS(0, "success", "成功"),
    DEFAULT_ERROR(-1, "failed", "失败"),
    PARAMS_ILLEGAL(1,"params illegal", "非法参数"),
    INTERNAL_ERROR(2,"internal error", "服务内部出错"),
    PERMISSION_DENY(3,"permission deny", "权限校验失败"),
    ;

    private Integer code;
    private String errorCode;
    private String errorMessage;


    ExceptionCodeEnum(Integer code, String errorCode, String errorMessage) {
        this.code = code;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public void setCode(Integer code) { this.code = code; }

    public Integer getCode() { return code; }

    public String getErrorCode() { return errorCode; }

    public String getErrorMessage() { return errorMessage; }

    public static ExceptionCodeEnum getByErrorCode(String errorCode) {
        ExceptionCodeEnum[] enums = ExceptionCodeEnum.values();
        for (ExceptionCodeEnum codeEnum: enums) {
            if (codeEnum.getErrorCode().equals(errorCode)) {
                return codeEnum;
            }
        }
        return null;
    }
}
