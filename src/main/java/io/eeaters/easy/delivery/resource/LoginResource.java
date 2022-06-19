package io.eeaters.easy.delivery.resource;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("login")
public class LoginResource {

    @Inject
    @Location("login")
    Template login;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Uni<String> get() {
        return login.instance().createUni();
    }

}
