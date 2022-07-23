package io.eeaters.easy.delivery.enums;

import java.util.Random;

public enum MockRiderEnum {

    BAI_GU_JING("白骨精", "13652145236"),
    HEI_XIONG_JING("黑熊精", "13652145456"),

    HARRY("哈利","13652156324"),
    HERMIONE("赫敏", "1365214528"),
    RON("罗恩", "1365214520"),

    TOMO("汤姆", "1365214465"),
    DUMBLEDORE("邓布利多", "1365216675"),
    SEVERUS("西弗勒斯", "1365678934"),
    SIRIUS("小天狼星", "1365675767"),

    ;
    private String name;

    private String phone;

    MockRiderEnum(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public static MockRiderEnum random() {
        int length = values().length;
        Random random = new Random();
        int i = random.nextInt(length);
        return values()[i];
    }

}
