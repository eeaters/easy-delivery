package io.eeaters.easy.delivery.exception;

public enum ResultCodeEnum {

    PARAM_EXCEPTION("400", "校验失败异常"),
    LOGIC_EXCEPTION("401", "逻辑异常"),

    SYSTEM_ERROR("500", "系统异常"),


    ;

    private String code;

    private String message;

    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
