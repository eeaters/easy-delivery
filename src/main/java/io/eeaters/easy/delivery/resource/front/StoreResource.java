package io.eeaters.easy.delivery.resource.front;

import io.eeaters.easy.delivery.entity.model.DeliveryStrategy;
import io.eeaters.easy.delivery.entity.model.Store;
import io.eeaters.easy.delivery.entity.view.BaseResponse;
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
        List<Store> storeList = Store.findAll().list();
        return store.data("storeList", storeList)
                .data("name", name);

    }

    @Inject
    @Location("front/add/storeAdd")
    Template storeAdd;

    @GET
    @Path("addPage")
    public TemplateInstance storeAddPage() {
        return storeAdd.instance();
    }

    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public BaseResponse<Long> storeAdd(@Valid Store store) {
        store.persistAndFlush();
        return BaseResponse.success(store.getId());
    }

}
