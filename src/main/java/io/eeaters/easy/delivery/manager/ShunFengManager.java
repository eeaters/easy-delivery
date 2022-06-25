package io.eeaters.easy.delivery.manager;


import io.eeaters.easy.delivery.client.ShunFengClient;
import io.eeaters.easy.delivery.client.util.SFUtils;
import io.eeaters.easy.delivery.client.req.shunfeng.SFDelivery;
import io.eeaters.easy.delivery.client.req.shunfeng.SFOrderDetail;
import io.eeaters.easy.delivery.client.req.shunfeng.SFShopInfo;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ShunFengManager {

    @RestClient
    ShunFengClient shunFengClient;

    /**
     * @return
     * @throws Exception
     */
    public String createDelivery() throws Exception {
        SFDelivery delivery = new SFDelivery();
        SFShopInfo shopInfo = new SFShopInfo();
        SFOrderDetail orderDetail = new SFOrderDetail();
        SFOrderDetail.SFProductDetail productDetail = new SFOrderDetail.SFProductDetail();
        productDetail.setProduct_id(123);
        productDetail.setItem_detail("desc");
        productDetail.setProduct_name("coffee");
        productDetail.setProduct_price(1000);
        productDetail.setProduct_num(1);
        productDetail.setProduct_unit("g");
        productDetail.setProduct_remark("商品标记");
        orderDetail.setProduct_detail(List.of(productDetail));

        orderDetail.setDelivery_money(1000);
        orderDetail.setProduct_type(1);
        orderDetail.setShop_money(1000);
        orderDetail.setProduct_num(1);
        orderDetail.setTotal_price(1000);
        orderDetail.setUser_money(100);
        orderDetail.setVolume_litre(1);
        orderDetail.setWeight_gram(1);
        orderDetail.setProduct_type_num(1);
        orderDetail.setProduct_type(1);
        orderDetail.setDelivery_money(1);
        delivery.setOrder_detail(orderDetail);

        shopInfo.setShop_name("test");
        shopInfo.setShop_address("天涯海角的测试门店");
        shopInfo.setShop_lat("123.234");
        shopInfo.setShop_lng("60.234");
        shopInfo.setShop_phone("96110");
        delivery.setShop(shopInfo);

        delivery.setDev_id(1);
        delivery.setExpect_time((int) (System.currentTimeMillis() / 1000) + 60);
        delivery.setIs_appoint(1);
        delivery.setLbs_type(1);
        delivery.setOrder_sequence("1");
        delivery.setLbs_type(1);

        String sign = SFUtils.getSign(delivery);
       return  shunFengClient.createOrder(delivery, sign);
    }

}
