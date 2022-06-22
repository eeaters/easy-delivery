package io.eeaters.easy.delivery.resource.front;

import io.eeaters.easy.delivery.entity.view.BaseResponse;
import io.eeaters.easy.delivery.entity.view.UserLogin;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static io.eeaters.easy.delivery.util.TokenUtils.generateTokenString;

@Path("login")
public class LoginResource {

    @Inject
    @Location("front/login")
    Template login;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Uni<String> get() {
        return login.instance().createUni();
    }


    @POST
    @Path("/doLogin")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<BaseResponse<String>> login(UserLogin userLogin) throws Exception {
        return Uni.createFrom().item(BaseResponse.success(generateTokenString(userLogin)));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("logout")
    public Uni<String> logout() {
        return null;
    }
}
