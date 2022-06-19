package io.eeaters.easy.delivery.resource;

import io.eeaters.easy.delivery.client.BaiduClient;
import io.eeaters.easy.delivery.entity.model.User;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("hello")
public class GreetingResource {


    @RestClient
    BaiduClient baiduClient;


    /**
     * 测试db
     * @return
     */
    @GET
    @Path("userList")
    public Uni<List<User>> userList() {
        return User.listAll();
    }

    @GET
    @Path("geocoder")
    @Blocking
    public Uni<String> geocoder(@QueryParam("location") String location) {
        return Uni.createFrom().completionStage(baiduClient.geocoder("json", location));
    }


}