package io.eeaters.easy.delivery.resource.front;

import io.eeaters.easy.delivery.entity.model.ChannelAccount;
import io.eeaters.easy.delivery.entity.view.BaseResponse;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/channel/account")
public class ChannelAccountResource {

    @POST
    @Path("/create")
    @Blocking
    public Uni<BaseResponse<Long>> create(ChannelAccount channelAccount) {
        //这里需要调用flush后,才能正确返回id, 单纯的persist可以正确保存但是无法返回自增的id
        Uni<ChannelAccount> persist = channelAccount.persistAndFlush();
        return persist.onItem()
                .transform(account -> account.getId())
                .onItem()
                .transform(BaseResponse::success);
    }

    @GET
    @Path("/get/{id}")
    @Blocking
    public Uni<BaseResponse<ChannelAccount>> get(@PathParam("id") Long id) {
        Uni<ChannelAccount> channelAccount = ChannelAccount.findById(id);
        return channelAccount
                .onItem()
                .transform(BaseResponse::success);
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    public Uni<BaseResponse<List<ChannelAccount>>> listAll() {
        Uni<List<ChannelAccount>> listAll = ChannelAccount.listAll();
        return listAll.onItem()
                .transform(BaseResponse::success);
    }

}
