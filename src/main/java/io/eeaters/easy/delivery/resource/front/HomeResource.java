package io.eeaters.easy.delivery.resource.front;

import io.eeaters.easy.delivery.entity.model.Store;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("home")
public class HomeResource {

    @Inject
    @Location("front/home")
    Template home;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance home() {
        return home.instance();
    }

}
