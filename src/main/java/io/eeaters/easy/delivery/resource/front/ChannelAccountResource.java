package io.eeaters.easy.delivery.resource.front;

import io.eeaters.easy.delivery.entity.model.ChannelAccount;
import io.eeaters.easy.delivery.entity.view.BaseResponse;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/channel/account")
public class ChannelAccountResource {

    @POST
    @Path("/create")
    public BaseResponse<Long> create(ChannelAccount channelAccount) {
        //这里需要调用flush后,才能正确返回id, 单纯的persist可以正确保存但是无法返回自增的id
        channelAccount.persistAndFlush();
        return BaseResponse.success(channelAccount.getId());
    }

    @GET
    @Path("/get/{id}")
    public BaseResponse<ChannelAccount> get(@PathParam("id") Long id) {
        return BaseResponse.success(ChannelAccount.findById(id));
    }

    @Inject
    @Location("front/detail/account-list")
    Template template;

    @GET
    @Path("list")
    public TemplateInstance listAll() {
        System.out.println("ChannelAccount.listAll() = " + ChannelAccount.listAll());
        return template.instance().data("list", ChannelAccount.listAll());
    }

}
