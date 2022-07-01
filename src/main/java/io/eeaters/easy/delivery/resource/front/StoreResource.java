package io.eeaters.easy.delivery.resource.front;

import io.eeaters.easy.delivery.entity.model.DeliveryStrategy;
import io.eeaters.easy.delivery.entity.model.Store;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("store")
public class StoreResource {

    @Inject
    @Location("front/store")
    Template store;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    public TemplateInstance store(@QueryParam("name") String name) {
        List<Store> storeList = Store.findAll().list();
        List<DeliveryStrategy> strategyList = DeliveryStrategy.findAll().list();
        return store.data("storeList", storeList)
                .data("strategyList", strategyList)
                .data("name", name);

    }

}
