package io.eeaters.easy.delivery.resource.front;

import io.eeaters.easy.delivery.config.expand.UserThreadLocal;
import io.eeaters.easy.delivery.entity.model.*;
import io.eeaters.easy.delivery.entity.view.BaseResponse;
import io.eeaters.easy.delivery.entity.view.DeliveryDetailResVO;
import io.eeaters.easy.delivery.entity.view.DeliveryMockReq;
import io.eeaters.easy.delivery.enums.MockRiderEnum;
import io.eeaters.easy.delivery.enums.StatusEnum;
import io.eeaters.easy.delivery.util.StringUtils;
import io.quarkus.panache.common.Parameters;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
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
        sql += " order by create_time desc";
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

    @Inject
    @Location("front/add/deliveryAdd")
    Template deliveryAddTemplate;

    @GET
    @Path("addPage")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance addPage() {
        List<Store> storeList = Store.findAll().list();
        return deliveryAddTemplate.data("storeList", storeList)
                .data("mock", DeliveryMockReq.mock());
    }

    @POST
    @Path("add")
    @Transactional
    public BaseResponse<Long> add(Delivery delivery) {


        StoreStrategyMapping strategyMapping = StoreStrategyMapping.find("storeId", delivery.getStoreId()).firstResult();

        delivery.setUserId(UserThreadLocal.getUser().getId());
        completeDelivery(delivery);
        delivery.persistAndFlush();

        if (strategyMapping == null) {
            DeliveryDetail deliveryDetail = new DeliveryDetail();
            deliveryDetail.setDeliveryId(delivery.getId());
            deliveryDetail.setStatus(StatusEnum.REJECT.getCode());
            deliveryDetail.setChannel("无方案");
            deliveryDetail.setCreateTime(LocalDateTime.now());
            deliveryDetail.persistAndFlush();
            return BaseResponse.success(delivery.getId());
        }
        List<StrategyChannelMapping> mappings = StrategyChannelMapping.list("strategyId", strategyMapping.getStrategyId());

        if (mappings.isEmpty()) {
            DeliveryDetail deliveryDetail = new DeliveryDetail();
            deliveryDetail.setDeliveryId(delivery.getId());
            deliveryDetail.setStatus(StatusEnum.REJECT.getCode());
            deliveryDetail.setChannel("无方案");
            deliveryDetail.setCreateTime(LocalDateTime.now());
            deliveryDetail.persistAndFlush();
            return BaseResponse.success(delivery.getId());
        }

        String channel = null;
        int status = 1;
        for (StrategyChannelMapping mapping : mappings) {
            Random random = new Random();
            ChannelAccount channelAccount = ChannelAccount.findById(mapping.getChannelId());

            if (!random.nextBoolean()) {     //随机
                DeliveryDetail deliveryDetail = new DeliveryDetail();
                deliveryDetail.setDeliveryId(delivery.getId());
                deliveryDetail.setStatus(StatusEnum.REJECT.getCode());
                deliveryDetail.setChannel(channelAccount.getChannel());
                deliveryDetail.setCreateTime(LocalDateTime.now());
                deliveryDetail.persistAndFlush();
                status = StatusEnum.REJECT.getCode();
            }else{
                DeliveryDetail deliveryDetail = new DeliveryDetail();
                deliveryDetail.setDeliveryId(delivery.getId());
                deliveryDetail.setStatus(StatusEnum.PUSH.getCode());
                deliveryDetail.setChannel(channelAccount.getChannel());
                deliveryDetail.setCreateTime(LocalDateTime.now());
                deliveryDetail.persistAndFlush();
                DeliveryDetail deliveryDetail2 = new DeliveryDetail();
                deliveryDetail2.setDeliveryId(delivery.getId());
                deliveryDetail2.setStatus(StatusEnum.DELIVERING.getCode());
                deliveryDetail2.setCreateTime(LocalDateTime.now());
                deliveryDetail2.setChannel(channelAccount.getChannel());

                MockRiderEnum rider = MockRiderEnum.random();
                deliveryDetail2.setRiderName(rider.getName());
                deliveryDetail2.setRiderPhone(rider.getPhone());
                deliveryDetail2.persistAndFlush();
                channel = channelAccount.getChannel();
                status = StatusEnum.DELIVERING.getCode();
                break;
            }
        }
        if (channel != null) {
            delivery.setChannel(channel);
            delivery.setStatus(status);
            delivery.persist();
        }
        return BaseResponse.success(delivery.getId());

    }

    private void completeDelivery(Delivery delivery) {
        delivery.setExceptTime(LocalDateTime.now().plusHours(1));
        delivery.setIsPre((byte)0);
        delivery.setTipFeePrice(0);
        delivery.setDeliveryFeePrice(0);
        delivery.setStatus(0);
    }


}
