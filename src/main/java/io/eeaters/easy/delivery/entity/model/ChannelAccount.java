package io.eeaters.easy.delivery.entity.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 三方渠道账号
 */
@Entity(name = "channel_account")
public class ChannelAccount extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String channel;

    @Column(name = "app_key")
    private String appKey;

    @Column(name = "app_secret")
    private String appSecret;

    @Column(name = "app_token")
    private String appToken;

    @Column(name = "create_time", insertable = false)
    private LocalDateTime createTime;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_time", insertable = false)
    private LocalDateTime updateTime;

    @Column(name = "update_user")
    private String updateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ChannelAccount{" +
                "id='" + id + '\'' +
                "channel='" + channel + '\'' +
                ", appKey='" + appKey + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", appToken='" + appToken + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
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
}
