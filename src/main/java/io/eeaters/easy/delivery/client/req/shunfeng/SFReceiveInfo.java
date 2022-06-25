package io.eeaters.easy.delivery.client.req.shunfeng;

public class SFReceiveInfo {
    /**
     * 用户名称
     */
    private String user_name;
    /**
     * 用户电话
     */
    private String user_phone;
    /**
     * 用户地址
     */
    private String user_address;
    /**
     * 用户经度
     */
    private String user_lng;
    /**
     * 用户纬度
     */
    private String user_lat;

    /**
     * 城市名称
     */
    private String city_name;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_lng() {
        return user_lng;
    }

    public void setUser_lng(String user_lng) {
        this.user_lng = user_lng;
    }

    public String getUser_lat() {
        return user_lat;
    }

    public void setUser_lat(String user_lat) {
        this.user_lat = user_lat;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
}
