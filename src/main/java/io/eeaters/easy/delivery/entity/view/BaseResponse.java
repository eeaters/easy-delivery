package io.eeaters.easy.delivery.entity.view;

public class BaseResponse <T>{

    private String code;

    private String message;

    private T result;


    public static BaseResponse success() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode("200");
        return baseResponse;
    }
    public static <R> BaseResponse<R> success(R r) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode("200");
        baseResponse.setResult(r);
        return baseResponse;
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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
