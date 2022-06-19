package io.eeaters.easy.delivery.entity.model;

import io.smallrye.mutiny.Uni;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Store {

    private Integer storeId;

    private String storeName;

    private String longitude;

    private String latitude;


    public static List<Store> mockList() {
        List<Store> list = new ArrayList<>();
        list.add(getInstance());
        list.add(getInstance());
        list.add(getInstance());
        return list;
    }

    static Random random = new Random();
    public static Store getInstance() {
        Store store = new Store();
        store.setStoreId(random.nextInt(100));
        store.setStoreName("测试门店" + store.getStoreId());
        store.setLatitude(String.valueOf(random.nextDouble()));
        store.setLongitude(String.valueOf(random.nextDouble()));
        return store;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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
}
