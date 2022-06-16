package io.eeaters.easy.delivery.resource;

import io.eeaters.easy.delivery.client.BaiduClient;
import io.eeaters.easy.delivery.entity.model.User;
import io.quarkus.qute.Template;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("hello")
public class GreetingResource {


    @RestClient
    BaiduClient baiduClient;

    @Inject
    Template hello;

    /**
     * 测试 qute
     * @param name
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> get(@QueryParam("name") String name) {
        return hello.data("name", name).createUni();
    }


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