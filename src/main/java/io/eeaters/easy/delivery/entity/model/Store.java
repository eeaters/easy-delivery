package io.eeaters.easy.delivery.entity.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity(name = "store")
public class Store extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "store_name")
    @NotBlank(message = "门店名称不能为空")
    private String storeName;

    @Column(name = "store_code")
    @NotBlank(message = "门店编号不能为空")
    private String storeCode;

    @NotBlank(message = "门店经度不能为空")
    private String longitude;

    @NotBlank(message = "门店纬度不能为空")
    private String latitude;

    @NotBlank(message = "门店电话不能为空")
    private String phone;

    @Column(name = "create_user",updatable = false)
    private String createUser;

    @Column(name = "create_time", insertable = false, updatable = false)
    private LocalDateTime createTime;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_time", insertable = false, updatable = false)
    private LocalDateTime updateTime;

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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", storeName='" + storeName + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
