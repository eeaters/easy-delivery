package io.eeaters.easy.delivery.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.eeaters.easy.delivery.config.expand.UserThreadLocal;
import io.eeaters.easy.delivery.entity.model.User;
import io.eeaters.easy.delivery.entity.view.BaseResponse;
import io.eeaters.easy.delivery.exception.ResultCodeEnum;
import io.eeaters.easy.delivery.util.RedisKeyUtils;
import io.netty.util.internal.StringUtil;
import io.quarkus.redis.client.RedisClient;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

import static javax.ws.rs.core.Response.Status.METHOD_NOT_ALLOWED;

@Provider
public class AccessFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private List<String> excludeList = List.of("^/login.*", "^/home.*","^/management/login/.*");

    @Inject
    RedisClient redisClient;

    @Inject
    ObjectMapper objectMapper;
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String path = containerRequestContext.getUriInfo().getPath();
        for (String s : excludeList) {
            if (path.matches(s)) {
                return;
            }
        }
        String token = containerRequestContext.getHeaderString("token");

        if (StringUtil.isNullOrEmpty(token)) {
            logInFailureResponse(containerRequestContext);
            return;
        }
        String userInfo = redisClient.get(RedisKeyUtils.tokenKey(token)).toString();
        if (StringUtil.isNullOrEmpty(userInfo)) {
            logInFailureResponse(containerRequestContext);
            return;
        }
        User user = objectMapper.readValue(userInfo, User.class);
        UserThreadLocal.setUserInfo(token, user);
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        UserThreadLocal.remove();
    }

    private void logInFailureResponse(ContainerRequestContext containerRequestContext) throws JsonProcessingException {
        BaseResponse<String> response = BaseResponse.failure(ResultCodeEnum.LOGIN_ERROR);
        containerRequestContext.abortWith(Response.status(METHOD_NOT_ALLOWED)
                .entity(objectMapper.writeValueAsString(response))
                .build());
    }
}
