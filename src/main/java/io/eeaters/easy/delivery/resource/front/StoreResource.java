package io.eeaters.easy.delivery.resource.front;

import io.eeaters.easy.delivery.config.expand.UserThreadLocal;
import io.eeaters.easy.delivery.entity.model.DeliveryStrategy;
import io.eeaters.easy.delivery.entity.model.Store;
import io.eeaters.easy.delivery.entity.model.StoreStrategyMapping;
import io.eeaters.easy.delivery.entity.view.BaseResponse;
import io.eeaters.easy.delivery.entity.view.StoreResVO;
import io.eeaters.easy.delivery.util.StringUtils;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("store")
public class StoreResource {

    @Inject
    @Location("front/store")
    Template store;

    @GET
    public TemplateInstance store(@QueryParam("name") String name) {
        List<Store> storeList = null;
        if (StringUtils.hasText(name)) {
            storeList = Store.find("storeName", name).list();
        }else {
            storeList = Store.findAll().list();
        }
        List<StoreResVO> storeResVOList = null;
        if (!storeList.isEmpty()) {
            List<DeliveryStrategy> strategyList = DeliveryStrategy.findAll().list();
            Map<Long, String> idToNameMap = strategyList.stream().collect(Collectors.toMap(DeliveryStrategy::getId, DeliveryStrategy::getName));
            storeResVOList = storeList.stream()
                    .map(store -> {
                        StoreResVO resVO = new StoreResVO();
                        resVO.setStore(store);
                        StoreStrategyMapping mapping = StoreStrategyMapping.find("storeId", store.getId()).firstResult();
                        resVO.setStrategyName(Optional.ofNullable(mapping)
                                .map(StoreStrategyMapping::getStrategyId)
                                .map(id -> idToNameMap.get(id))
                                .orElse("暂无绑定策略")
                        );
                        return resVO;
                    }).collect(Collectors.toList());
        }

        return store.data("storeList", storeResVOList)
                .data("name", name);

    }

    @Inject
    @Location("front/add/storeAdd")
    Template storeAdd;

    @GET
    @Path("addPage")
    public TemplateInstance storeAddPage(@QueryParam("storeId") Long storeId) {
        TemplateInstance instance = storeAdd.instance();
        List<DeliveryStrategy> list = DeliveryStrategy.findAll().list();
        if (storeId != null) {
            Store byId = Store.findById(storeId);
            StoreStrategyMapping storeStrategyMapping = StoreStrategyMapping.find("storeId", storeId).firstResult();
            instance.data("store", byId)
                    .data("strategyId", Optional.ofNullable(storeStrategyMapping).map(StoreStrategyMapping::getStrategyId).orElse(null));
        }
        return instance.data("list", list);
    }

    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public BaseResponse<Long> storeAdd(@Valid Store store, @QueryParam("strategyId") Long strategyId) {
        if (store.getId() == null) {
            store.setUserId(UserThreadLocal.getUser().getId());
            store.persistAndFlush();

            StoreStrategyMapping storeStrategyMapping = new StoreStrategyMapping();
            storeStrategyMapping.setStoreId(store.getId());
            storeStrategyMapping.setStrategyId(strategyId);
            storeStrategyMapping.persist();
        } else {
            Store storeMeta = Store.findById(store.getId());
            storeMeta.setAddress(store.getAddress());
            storeMeta.setStoreName(store.getStoreName());
            storeMeta.setPhone(store.getPhone());
            storeMeta.setLongitude(store.getLongitude());
            storeMeta.setLatitude(store.getLatitude());
            storeMeta.setStoreCode(store.getStoreCode());
            storeMeta.setUserId(UserThreadLocal.getUser().getId());

            StoreStrategyMapping mapping = StoreStrategyMapping.find("storeId", store.getId()).firstResult();
            if (mapping != null) {
                mapping.setStrategyId(strategyId);
            }else{
                StoreStrategyMapping storeStrategyMapping = new StoreStrategyMapping();
                storeStrategyMapping.setStoreId(store.getId());
                storeStrategyMapping.setStrategyId(strategyId);
                storeStrategyMapping.persist();
            }

        }
        return BaseResponse.success(store.getId());
    }

}
