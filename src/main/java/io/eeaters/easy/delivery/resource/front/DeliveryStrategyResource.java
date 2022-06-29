package io.eeaters.easy.delivery.resource.front;

import io.eeaters.easy.delivery.entity.model.DeliveryStrategy;
import io.eeaters.easy.delivery.entity.model.StrategyChannelMapping;
import io.eeaters.easy.delivery.entity.view.BaseResponse;
import io.netty.util.internal.StringUtil;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("strategy")
public class DeliveryStrategyResource {

    @Inject
    @Location("front/strategy")
    Template strategy;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Uni<TemplateInstance> strategy(@QueryParam("name") String name) {
        Uni<List<DeliveryStrategy>> listUni = null;
        if (StringUtil.isNullOrEmpty(name)) {
            listUni = DeliveryStrategy.list("name", name);
        } else {
            listUni = DeliveryStrategy.listAll();
        }
        return listUni.onItem()
                .transform(list -> {
                    TemplateInstance strategy = this.strategy.data("list", list).data("name", name);
                    return strategy;
                });
    }

    /**
     * 策略列表
     *
     * @return
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<BaseResponse<List<DeliveryStrategy>>> list() {
        Uni<List<DeliveryStrategy>> listUni = DeliveryStrategy.listAll();
        return listUni.onItem().transform(BaseResponse::success);
    }


    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    public Uni<BaseResponse<DeliveryStrategy>> get(@PathParam("id") Long id) {
        Uni<DeliveryStrategy> byId = DeliveryStrategy.findById(id);
        return byId.onItem().transform(BaseResponse::success);
    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<BaseResponse<Long>> create(DeliveryStrategy deliveryStrategy) {
        Uni<DeliveryStrategy> baseUni = deliveryStrategy.persistAndFlush();
        return baseUni.onItem()
                .transform(DeliveryStrategy::getId)
                .onItem()
                .transform(BaseResponse::success);
    }

    @POST
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    public Uni<BaseResponse<String>> update(DeliveryStrategy deliveryStrategy) {
        Uni<DeliveryStrategy> byId = DeliveryStrategy.findById(deliveryStrategy.getId());
        return byId.onItem()
                .ifNull()
                .failWith(new RuntimeException("不存在的配送策略"))
                .onItem()
                .call(strategy -> {
                    strategy.setDesc(deliveryStrategy.getDesc());
                    strategy.setType(deliveryStrategy.getType());
                    strategy.setTimeoutPeriod(deliveryStrategy.getTimeoutPeriod());
                    strategy.setName(deliveryStrategy.getName());
                    strategy.setUpdateUser(deliveryStrategy.getUpdateUser());
                    return strategy.flush();
                })
                .onItem()
                .transform(strategy -> BaseResponse.success());
    }

    @POST
    @Path("mapping/create")
    public Uni<BaseResponse<Long>> createChannelMapping(StrategyChannelMapping channelMapping) {
        Uni<StrategyChannelMapping> uni = channelMapping.persistAndFlush();
        return uni.onItem()
                .transform(StrategyChannelMapping::getChannelId)
                .onItem()
                .transform(BaseResponse::success);
    }

}
