package io.eeaters.easy.delivery.resource.temp;

import io.eeaters.easy.delivery.client.BaiduClient;
import io.eeaters.easy.delivery.entity.model.User;
import io.quarkus.redis.client.RedisClient;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Arrays;
import java.util.List;

@Path("hello")
public class GreetingResource {


    @RestClient
    BaiduClient baiduClient;


    @Inject
    RedisClient redisClient;
    @GET
    @Path("redis")
    public String redis() {
        redisClient.set(Arrays.asList("quarkus.test", "hello quarkus"));
        return redisClient.get("quarkus.test").toString();
    }

    /**
     * 测试db
     * @return
     */
    @GET
    @Path("userList")
    public List<User> userList() {
        return User.listAll();
    }

    @GET
    @Path("geocoder")
    public Uni<String> geocoder(@QueryParam("location") String location) {
        return Uni.createFrom().completionStage(baiduClient.geocoder("json", location));
    }


}