package io.eeaters.easy.delivery.resource.front;

import io.eeaters.easy.delivery.entity.model.Delivery;
import io.eeaters.easy.delivery.entity.model.DeliveryDetail;
import io.eeaters.easy.delivery.entity.model.User;
import io.eeaters.easy.delivery.entity.view.DeliveryDetailResVO;
import io.netty.util.internal.StringUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
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
import java.util.stream.Collectors;

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
    public TemplateInstance delivery(@QueryParam("storeId") String storeId,
                                          @QueryParam("orderId") String orderId,
                                          @QueryParam("id") Long id) {
        List<Delivery> deliveryList ;
        if (id != null) {
            deliveryList = List.of(Delivery.findById(id));
        } else if (!StringUtil.isNullOrEmpty(orderId)) {
            deliveryList=  Delivery.find("#Delivery.getOrderId", orderId).list();
        }else{
            deliveryList = Delivery.findAll().list();
        }
        return this.delivery.data("list", deliveryList)
                .data("orderId", orderId)
                .data("id", id)
                .data("storeId", storeId);
    }


    @Inject
    @Location("front/detail/delivery-detail")
    Template deliveryDetailTemplate;

    @GET
    @Path("get")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get(@QueryParam("id") Long id) {
        Delivery delivery = Delivery.findById(id);
        List<DeliveryDetail> list = DeliveryDetail.find("deliveryId", id).list();
        List<DeliveryDetailResVO> collect = list
                .stream().map(DeliveryDetailResVO::convert).collect(Collectors.toList());
        return deliveryDetailTemplate.data("delivery", delivery)
                .data("detail", collect);
    }
}
