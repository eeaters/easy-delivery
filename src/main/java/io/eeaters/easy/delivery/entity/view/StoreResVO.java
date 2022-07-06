package io.eeaters.easy.delivery.entity.view;

import io.eeaters.easy.delivery.entity.model.Store;


public class StoreResVO{

    private Store store;

    private String strategyName;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }
}
