package io.eeaters.easy.delivery.enums;

import java.util.Random;

public enum MockReceiveUserEnum {

    SAN_ZANG("唐三藏", "13652142344", "东土大唐"),
    WU_KONG("孙悟空", "13652123456", "花果山"),
    BA_JIE("猪悟能", "13652165436", "高老庄"),
    SHA_SENG("沙悟净", "13652148964", "流沙河"),
    ;
    private String name;

    private String phone;

    private String address;

    MockReceiveUserEnum(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public static MockReceiveUserEnum random() {
        int length = values().length;
        Random random = new Random();
        int i = random.nextInt(length);
        return values()[i];
    }

}
