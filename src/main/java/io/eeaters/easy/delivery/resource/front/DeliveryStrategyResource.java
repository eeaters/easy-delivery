package io.eeaters.easy.delivery.resource.front;

import io.eeaters.easy.delivery.entity.model.ChannelAccount;
import io.eeaters.easy.delivery.entity.model.DeliveryStrategy;
import io.eeaters.easy.delivery.entity.model.StrategyChannelMapping;
import io.eeaters.easy.delivery.entity.view.BaseResponse;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
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
    public TemplateInstance strategy(@QueryParam("name") String name) {
        List<DeliveryStrategy> list = DeliveryStrategy.listAll();
        return this.strategy.data("list", list).data("name", name);
    }

    @Inject
    @Location("front/add/strategyAdd")
    Template strategyAdd;

    @GET
    @Path("addPage")
    public TemplateInstance strategyAdd(@QueryParam("strategyId") Long strategyId) {
        List<ChannelAccount> channelList = ChannelAccount.listAll();
        TemplateInstance instance = strategyAdd.instance().data("channelList", channelList);
        if (strategyId != null) {
            DeliveryStrategy strategy = DeliveryStrategy.findById(strategyId);
            List<StrategyChannelMapping> list = StrategyChannelMapping.find("strategyId", strategyId).list();
            instance.data("strategy", strategy)
                    .data("channelMapping", list);
        }
        return instance;
    }



    /**
     * 策略列表
     *
     * @return
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse<List<DeliveryStrategy>> list() {
        return BaseResponse.success(DeliveryStrategy.listAll());
    }


    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse<DeliveryStrategy> get(@PathParam("id") Long id) {
        return BaseResponse.success(DeliveryStrategy.findById(id));
    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse<Long> create(DeliveryStrategy deliveryStrategy) {
        deliveryStrategy.persistAndFlush();
        return BaseResponse.success(deliveryStrategy.getId());
    }

    @POST
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse<String> update(DeliveryStrategy deliveryStrategy) {
        DeliveryStrategy strategy = DeliveryStrategy.findById(deliveryStrategy.getId());
        if (strategy == null) {
            return null;
        }
        strategy.setDesc(deliveryStrategy.getDesc());
        strategy.setType(deliveryStrategy.getType());
        strategy.setTimeoutPeriod(deliveryStrategy.getTimeoutPeriod());
        strategy.setName(deliveryStrategy.getName());
        strategy.setUpdateUser(deliveryStrategy.getUpdateUser());
        strategy.persist();
        return BaseResponse.success();
    }

    @POST
    @Path("mapping/create")
    public BaseResponse<Long> createChannelMapping(StrategyChannelMapping channelMapping) {
        channelMapping.persistAndFlush();
        return BaseResponse.success(channelMapping.getId());
    }

}
