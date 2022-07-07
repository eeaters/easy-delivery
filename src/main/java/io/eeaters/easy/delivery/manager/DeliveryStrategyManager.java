package io.eeaters.easy.delivery.manager;

import io.eeaters.easy.delivery.config.expand.UserThreadLocal;
import io.eeaters.easy.delivery.entity.model.DeliveryStrategy;
import io.eeaters.easy.delivery.entity.model.StrategyChannelMapping;
import io.eeaters.easy.delivery.entity.view.DeliveryStrategyReq;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeliveryStrategyManager {


    public Long createOrUpdate(DeliveryStrategyReq strategyReq) {
        DeliveryStrategy deliveryStrategy = null;
        if (strategyReq.getId() == null) {
            deliveryStrategy = new DeliveryStrategy();
            deliveryStrategy.setDesc(strategyReq.getDesc());
            deliveryStrategy.setName(strategyReq.getName());
            deliveryStrategy.setUserId(UserThreadLocal.getUser().getId());
            deliveryStrategy.setType(strategyReq.getType());
            deliveryStrategy.setTimeoutPeriod(strategyReq.getTimeoutPeriod());
            deliveryStrategy.persistAndFlush();
        }else{
            deliveryStrategy = DeliveryStrategy.findById(strategyReq.getId());
            deliveryStrategy.setDesc(strategyReq.getDesc());
            deliveryStrategy.setName(strategyReq.getName());
            deliveryStrategy.setUserId(UserThreadLocal.getUser().getId());
            deliveryStrategy.setType(strategyReq.getType());
            deliveryStrategy.setTimeoutPeriod(strategyReq.getTimeoutPeriod());

            StrategyChannelMapping.delete("strategyId", strategyReq.getId());
        }
        Long strategyId = deliveryStrategy.getId();
        for (int i = 0; i < strategyReq.getChannels().size(); i++) {
            StrategyChannelMapping channelMapping = new StrategyChannelMapping();
            channelMapping.setChannelId(strategyReq.getChannels().get(i));
            channelMapping.setStrategyId(strategyId);
            channelMapping.setOrder(i);
            channelMapping.persist();
        }
        return strategyId;
    }

}
