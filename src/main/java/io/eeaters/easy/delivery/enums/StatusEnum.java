package io.eeaters.easy.delivery.enums;

public enum StatusEnum {

    REJECT(-1,"推送失败"),
    PUSH(1,"已推送"),
    DELIVERING(2,"已分配骑手")


    ;
    private Integer code;

    private String eventName;

    StatusEnum(Integer code, String eventName) {
        this.code = code;
        this.eventName = eventName;
    }

    public static String eventName(Integer code) {
        for (StatusEnum value : values()) {
            if (value.code == code) {
                return value.eventName;
            }
        }
        return code.toString();
    }


    public Integer getCode() {
        return code;
    }
}
