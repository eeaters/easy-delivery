package io.eeaters.easy.delivery.client.req.shunfeng;

public class SFShopInfo {
    /**
     * 门店名称
     */
    private String shop_name;
    /**
     * 门店电话
     */
    private String shop_phone;
    /**
     * 门店地址
     */
    private String shop_address;
    /**
     * 门店经度  --非必须
     */
    private String shop_lng;
    /**
     * 门店纬度 --非必须
     */
    private String shop_lat;

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_phone() {
        return shop_phone;
    }

    public void setShop_phone(String shop_phone) {
        this.shop_phone = shop_phone;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getShop_lng() {
        return shop_lng;
    }

    public void setShop_lng(String shop_lng) {
        this.shop_lng = shop_lng;
    }

    public String getShop_lat() {
        return shop_lat;
    }

    public void setShop_lat(String shop_lat) {
        this.shop_lat = shop_lat;
    }
}
