package io.eeaters.easy.delivery.resource.back;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.eeaters.easy.delivery.config.expand.UserThreadLocal;
import io.eeaters.easy.delivery.entity.model.ChannelAccount;
import io.eeaters.easy.delivery.entity.model.User;
import io.eeaters.easy.delivery.entity.view.AdminUserLogin;
import io.eeaters.easy.delivery.entity.view.BaseResponse;
import io.eeaters.easy.delivery.enums.ChannelEnum;
import io.eeaters.easy.delivery.exception.LogicException;
import io.eeaters.easy.delivery.util.RedisKeyUtils;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.redis.client.RedisClient;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("management")
public class AdminResource {


    @Inject
    RedisClient redisClient;

    @Inject
    ObjectMapper objectMapper;

    /**
     * //todo : 用户token和管理员token进行区分
     * @param userLogin
     * @return
     * @throws Exception
     */
    @POST
    @Path("/doLogin")
    public BaseResponse<String> login(@Valid AdminUserLogin userLogin) throws Exception {
        if ("admin".equals(userLogin.getUserName()) && "admin".equals(userLogin.getPassword())) {
            String token = UUID.randomUUID().toString();
            User admin = new User();
            admin.setId(-99999L);
            admin.setUserName("admin");
            admin.setPhone("10086");
            admin.setPassword("admin");
            String value = objectMapper.writeValueAsString(admin);
            String idKey = RedisKeyUtils.userIdKey(admin.getId());
            String tokenKey = RedisKeyUtils.tokenKey(token);
            redisClient.set(List.of(idKey, token));
            redisClient.set(List.of(tokenKey, value));
            redisClient.expire(idKey, "6000");
            redisClient.expire(tokenKey, "6000");
            return BaseResponse.success(token);
        }
        throw new LogicException("用戶或密码不正确");
    }


    @Inject
    @Location("back/home")
    Template home;

    @GET
    @Path("/home")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance home() {
        return home.instance();
    }


    @Inject
    @Location("back/channel")
    Template channel;

    @GET
    @Path("channel")
    public TemplateInstance channel() {
        List<ChannelAccount> list = ChannelAccount.findAll().list();
        return channel.data("list", list);
    }

    @Inject
    @Location("back/channelAdd")
    Template channelAdd;

    @GET
    @Path("channelAddPage")
    public TemplateInstance channelAddPage() {
       return channelAdd.data("channels", ChannelEnum.values());
    }

    @Inject
    @Location("back/channelUpdate")
    Template channelUpdate;
    @GET
    @Path("channelUpdatePage")
    public TemplateInstance channelUpdate(@QueryParam("channelId") Long channelId) {
        TemplateInstance channels = channelUpdate.data("channels", ChannelEnum.values());
        ChannelAccount account = ChannelAccount.findById(channelId);
        return channels.data("account", account);
    }

    @POST
    @Path("channelAdd")
    @Transactional
    public BaseResponse<Long> channelAdd(ChannelAccount channelAccount) {
         String channel1 = channelAccount.getChannel();
        channelAccount.setName(ChannelEnum.channelName(channel1));
        if (channelAccount.getId() != null) {
            ChannelAccount account = ChannelAccount.findById(channelAccount.getId());
            account.setAppKey(channelAccount.getAppKey());
            account.setAppSecret(channelAccount.getAppSecret());
            account.setAppToken(channelAccount.getAppToken());
            account.setUpdateUser(UserThreadLocal.getUser().getUserName());
            return BaseResponse.success(account.getId());
        }else{
            ChannelAccount channel2 = ChannelAccount.find("channel", channelAccount.getChannel()).firstResult();
            if (channel2 != null) {
                throw new LogicException("该渠道已存在账号");
            }
            channelAccount.persistAndFlush();
            return BaseResponse.success(channelAccount.getId());
        }


    }

}
