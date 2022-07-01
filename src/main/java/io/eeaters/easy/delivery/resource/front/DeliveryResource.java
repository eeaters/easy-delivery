package io.eeaters.easy.delivery.resource.front;

import io.eeaters.easy.delivery.entity.model.Delivery;
import io.netty.util.internal.StringUtil;
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
import java.util.ArrayList;
import java.util.List;

@Path("delivery")
public class DeliveryResource {

    @Inject
    @Location("front/delivery")
    Template delivery;

    /**
     * 框架不熟,先写一下功能
     *
     * @param orderId
     * @param id
     * @return
     */
    @GET
    public Uni<TemplateInstance> delivery(@QueryParam("storeId") String storeId,
                                          @QueryParam("orderId") String orderId,
                                          @QueryParam("id") Long id) {
        Uni<List<Delivery>> uni;
        if (id != null && id > 0) {
            Uni<Delivery> baseUni = Delivery.findById(id);
            uni = baseUni.onItem()
                    .transform(item -> {
                        List<Delivery> deliveries = new ArrayList<>();
                        if (item != null) {
                            deliveries.add(item);
                        }
                        return deliveries;
                    });
        } else if (!StringUtil.isNullOrEmpty(orderId)) {
            uni = Delivery.find("#Delivery.getOrderId", orderId).list();
        } else {
            uni = Delivery.findAll().list();
        }
        return uni.onItem()
                .transform(list -> {
                    TemplateInstance strategy = this.delivery.data("list", list)
                            .data("orderId", orderId)
                            .data("id", id)
                            .data("storeId", storeId);
                    return strategy;
                });

    }


    @Inject
    @Location("front/detail/delivery-detail")
    Template deliveryDetailTemplate;

    @GET
    @Path("get")
    @Produces(MediaType.TEXT_HTML)
    public Uni<TemplateInstance> get(@QueryParam("id") Long id) {
        Uni<Delivery> baseUni = Delivery.findById(id);
        return baseUni.onItem()
                .transform(deliveryDetail -> {
                    TemplateInstance delivery = deliveryDetailTemplate.data("delivery", deliveryDetail);
                    return delivery;
                });
    }
}
