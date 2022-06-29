package io.eeaters.easy.delivery.resource.front;

import io.eeaters.easy.delivery.entity.model.Store;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
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
    public Uni<TemplateInstance> store(@QueryParam("name") String name) {
        Uni<List<Store>> list = Store.findAll()
                .list();
        return list.onItem()
                .transform(stores -> {
                    TemplateInstance data = store.data("stores", stores)
                            .data("name", name);
                    return data;
                });
    }

}
