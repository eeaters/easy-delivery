package io.eeaters.easy.delivery.resource.front;

import io.eeaters.easy.delivery.config.expand.UserThreadLocal;
import io.eeaters.easy.delivery.entity.model.Store;
import io.eeaters.easy.delivery.entity.view.BaseResponse;
import io.eeaters.easy.delivery.util.StringUtils;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("store")
public class StoreResource {

    @Inject
    @Location("front/store")
    Template store;

    @GET
    public TemplateInstance store(@QueryParam("name") String name) {
        List<Store> storeList = null;
        if (StringUtils.hasText(name)) {
            storeList = Store.find("storeName", name).list();
        }else {
            storeList = Store.findAll().list();
        }
        return store.data("storeList", storeList)
                .data("name", name);

    }

    @Inject
    @Location("front/add/storeAdd")
    Template storeAdd;

    @GET
    @Path("addPage")
    public TemplateInstance storeAddPage(@QueryParam("storeId") Long storeId) {
        TemplateInstance instance = storeAdd.instance();
        if (storeId != null) {
            Store byId = Store.findById(storeId);
            instance.data("store", byId);
        }
        return instance;
    }

    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public BaseResponse<Long> storeAdd(@Valid Store store) {
        if(store.getId() == null) {
            store.setUserId(UserThreadLocal.getUser().getId());
            store.persistAndFlush();
        }else{
            Store storeMeta = Store.findById(store.getId());
            storeMeta.setAddress(store.getAddress());
            storeMeta.setStoreName(store.getStoreName());
            storeMeta.setPhone(store.getPhone());
            storeMeta.setLongitude(store.getLongitude());
            storeMeta.setLatitude(store.getLatitude());
            storeMeta.setStoreCode(store.getStoreCode());
            storeMeta.setUserId(UserThreadLocal.getUser().getId());
        }
        return BaseResponse.success(store.getId());
    }

}
