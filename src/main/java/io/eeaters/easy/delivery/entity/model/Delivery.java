package io.eeaters.easy.delivery.entity.model;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "delivery")
public class Delivery extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "order_price")
    private Integer orderPrice;

    @Column(name = "delivery_fee_price")
    private Integer deliveryFeePrice;

    @Column(name = "tip_fee_price")
    private Integer tipFeePrice;

    @Column(name = "dest_longitude")
    private String destLongitude;

    @Column(name = "dest_latitude")
    private String destLatitude;

    @Column(name = "dest_address")
    private String destAddress;

    @Column(name = "dest_user")
    private String destUser;

    @Column(name = "dest_phone")
    private String destPhone;

    @Column(name = "is_pre")
    private Byte isPre;

    @Column(name = "except_time")
    private LocalDateTime exceptTime;

    private Integer status;

    @Column(name = "create_time", insertable = false, updatable = false)
    private LocalDateTime createTime;

    @Column(name = "create_user",updatable = false)
    private String createUser;

    @Column(name = "update_time", insertable = false, updatable = false)
    private LocalDateTime updateTime;

    @Column(name = "update_user")
    private String updateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getDeliveryFeePrice() {
        return deliveryFeePrice;
    }

    public void setDeliveryFeePrice(Integer deliveryFeePrice) {
        this.deliveryFeePrice = deliveryFeePrice;
    }

    public Integer getTipFeePrice() {
        return tipFeePrice;
    }

    public void setTipFeePrice(Integer tipFeePrice) {
        this.tipFeePrice = tipFeePrice;
    }

    public String getDestLongitude() {
        return destLongitude;
    }

    public void setDestLongitude(String destLongitude) {
        this.destLongitude = destLongitude;
    }

    public String getDestLatitude() {
        return destLatitude;
    }

    public void setDestLatitude(String destLatitude) {
        this.destLatitude = destLatitude;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public String getDestUser() {
        return destUser;
    }

    public void setDestUser(String destUser) {
        this.destUser = destUser;
    }

    public Byte getIsPre() {
        return isPre;
    }

    public void setIsPre(Byte isPre) {
        this.isPre = isPre;
    }

    public LocalDateTime getExceptTime() {
        return exceptTime;
    }

    public void setExceptTime(LocalDateTime exceptTime) {
        this.exceptTime = exceptTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getDestPhone() {
        return destPhone;
    }

    public void setDestPhone(String destPhone) {
        this.destPhone = destPhone;
    }
}
