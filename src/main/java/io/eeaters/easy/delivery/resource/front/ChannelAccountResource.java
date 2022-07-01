package io.eeaters.easy.delivery.resource.front;

import io.eeaters.easy.delivery.entity.model.ChannelAccount;
import io.eeaters.easy.delivery.entity.view.BaseResponse;
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

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse<List<ChannelAccount>> listAll() {
        return BaseResponse.success(ChannelAccount.listAll());
    }

}
