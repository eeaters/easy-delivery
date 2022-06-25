package io.eeaters.easy.delivery.client;

import io.eeaters.easy.delivery.client.req.shunfeng.SFDelivery;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@RegisterRestClient(configKey = "sf")
public interface ShunFengClient {

    @POST
    @Path("open/api/external/createorder")
    String createOrder(SFDelivery delivery, @QueryParam("sign") String sign);


}
