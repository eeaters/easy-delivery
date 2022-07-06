package io.eeaters.easy.delivery.enums;

public enum ChannelEnum {

    DADA("dada", "达达"),
    SHUNFENG("shunfeng", "顺丰"),
    MEITUAN("meituan", "美团"),
    FENGNIAO("fengniao", "蜂鸟"),

    ;
    private String shortName;

    private String channelName;

    ChannelEnum(String shortName, String channelName) {
        this.shortName = shortName;
        this.channelName = channelName;
    }

    public static String channelName(String shortName) {
        if (shortName == null) {
            return null;
        }
        for (ChannelEnum value : values()) {
            if (shortName.equals(value.getShortName())) {
                return value.channelName;
            }
        }
        return shortName;
    }
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
