package io.eeaters.easy.delivery.entity.view;


import java.util.List;

public class DeliveryStrategyReq {
    private Long id;
    private String name;
    private String desc;
    private Integer type;
    private Integer timeoutPeriod;

    private List<Long> channels;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTimeoutPeriod() {
        return timeoutPeriod;
    }

    public void setTimeoutPeriod(Integer timeoutPeriod) {
        this.timeoutPeriod = timeoutPeriod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getChannels() {
        return channels;
    }

    public void setChannels(List<Long> channels) {
        this.channels = channels;
    }

    @Override
    public String toString() {
        return "DeliveryStrategyReq{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", type=" + type +
                ", timeoutPeriod=" + timeoutPeriod +
                ", channels=" + channels +
                '}';
    }
}
