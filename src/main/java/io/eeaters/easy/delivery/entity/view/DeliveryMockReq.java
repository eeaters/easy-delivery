package io.eeaters.easy.delivery.entity.view;

import io.eeaters.easy.delivery.enums.MockReceiveUserEnum;

import java.util.Random;

public class DeliveryMockReq {

    private String orderId;

    private Integer amount;

    private String destUser;

    private String destAddress;

    private String destPhone;

    private String longitude;

    private String latitude;

    public static DeliveryMockReq mock() {
        Random random = new Random();
        double longitude = random.nextDouble() + random.nextInt(50) + 73.5d;
        double latitude = random.nextDouble() + random.nextInt(50) + 4;

        DeliveryMockReq req = new DeliveryMockReq();
        req.setAmount(520);
        req.setLatitude(String.valueOf(latitude).substring(0, 9));
        req.setLongitude(String.valueOf(longitude).substring(0, 9));
        req.setOrderId("M-" + (random.nextInt(100000000) + 100000000));

        MockReceiveUserEnum random1 = MockReceiveUserEnum.random();
        req.setDestUser(random1.getName());
        req.setDestPhone(random1.getPhone());
        req.setDestAddress(random1.getAddress());
        return req;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDestUser() {
        return destUser;
    }

    public void setDestUser(String destUser) {
        this.destUser = destUser;
    }

    public String getDestPhone() {
        return destPhone;
    }

    public void setDestPhone(String destPhone) {
        this.destPhone = destPhone;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }
}
