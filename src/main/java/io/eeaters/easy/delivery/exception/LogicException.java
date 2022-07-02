package io.eeaters.easy.delivery.exception;

public class LogicException extends AbstractDeliveryException{

    private String code;

    private String message;


    public LogicException(ResultCodeEnum codeEnum) {
        this(codeEnum.getCode(), codeEnum.getMessage());
    }

    public LogicException(String message) {
        this(ResultCodeEnum.LOGIC_EXCEPTION.getCode(), message);
    }

    public LogicException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
