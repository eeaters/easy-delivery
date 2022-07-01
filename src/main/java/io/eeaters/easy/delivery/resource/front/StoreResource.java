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
    public Uni<TemplateInstance> store(@QueryParam("name") String name) {
        Uni<List<Store>> storeListUni = Store.findAll().list();
        List<Store> storeList = storeListUni.await().indefinitely();

        Uni<List<DeliveryStrategy>> strategyListUni = DeliveryStrategy.findAll().list();
        List<DeliveryStrategy> strategyList = strategyListUni.await().indefinitely();

        System.out.println("name = " + name);
        return Uni.createFrom()
                .item(() -> {
                    TemplateInstance instance = store.data("storeList", storeList)
                            .data("strategyList", strategyList)
                            .data("name", name);
                    return instance;
                });

    }

}
