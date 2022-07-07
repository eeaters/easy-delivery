package io.eeaters.easy.delivery.resource.front;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.eeaters.easy.delivery.config.expand.UserThreadLocal;
import io.eeaters.easy.delivery.entity.model.User;
import io.eeaters.easy.delivery.entity.view.BaseResponse;
import io.eeaters.easy.delivery.entity.view.UserLogin;
import io.eeaters.easy.delivery.exception.LogicException;
import io.eeaters.easy.delivery.util.RedisKeyUtils;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.redis.client.RedisClient;
import io.vertx.redis.client.Response;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.UUID;


@Path("login")
public class LoginResource {

    @Inject
    @Location("front/login")
    Template login;

    @Inject
    RedisClient redisClient;

    @Inject
    ObjectMapper objectMapper;
    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return login.instance();
    }


    @POST
    @Path("/doLogin")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse<String> login(@Valid UserLogin userLogin) throws Exception {
        User user = User.find("phone", userLogin.getPhone()).firstResult();
        if (user == null || !user.getPassword().equals(userLogin.getPassword())) {
            throw new LogicException("用戶或密码不正确");
        }
        String token = UUID.randomUUID().toString();
        String value = objectMapper.writeValueAsString(user);
        if (user.getPassword().equals(userLogin.getPassword())) {
            String idKey = RedisKeyUtils.userIdKey(user.getId());
            String tokenKey = RedisKeyUtils.tokenKey(token);
            redisClient.set(List.of(idKey, token));
            redisClient.set(List.of(tokenKey, value));
            redisClient.expire(idKey, "6000");
            redisClient.expire(tokenKey, "6000");
        }
        return BaseResponse.success(token);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("logout")
    public BaseResponse<String> logout() {
        Long id = UserThreadLocal.getUser().getId();
        String token = UserThreadLocal.getToken();
        String userIdKey = RedisKeyUtils.userIdKey(id);
        String tokenKey = RedisKeyUtils.tokenKey(token);
        redisClient.del(List.of(userIdKey, tokenKey));
        return BaseResponse.success();
    }

}
