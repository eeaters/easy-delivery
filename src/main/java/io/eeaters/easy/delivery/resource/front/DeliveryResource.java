package io.eeaters.easy.delivery.resource.front;

import io.eeaters.easy.delivery.entity.model.Delivery;
import io.eeaters.easy.delivery.entity.model.DeliveryDetail;
import io.eeaters.easy.delivery.entity.view.DeliveryDetailResVO;
import io.eeaters.easy.delivery.util.StringUtils;
import io.quarkus.panache.common.Parameters;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
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
    public TemplateInstance delivery(@QueryParam("storeId") Long storeId,
                                     @QueryParam("orderId") String orderId,
                                     @QueryParam("id") Long id) {
        System.out.println("id = " + id);
        String sql = "1=1";
        Parameters parameters = new Parameters();

        if (id != null) {
            sql += " and id = :id";
            parameters.and("id", id);
        }
        if (StringUtils.hasText(orderId)) {
            sql += " and order_id = :orderId" ;
            parameters.and("orderId", orderId);
        }
        if (storeId != null) {
            sql += " and store_id = :storeId";
            parameters.and("storeId", storeId);
        }
        System.out.println("sql ============= " + sql);
        List<Delivery> list = Delivery.find(sql, parameters).list();
        return this.delivery.data("list", list)
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
