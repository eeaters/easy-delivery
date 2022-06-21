package io.eeaters.easy.delivery.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.Set;

@Path("/secured")
@RequestScoped
public class TokenSecuredResource {

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("permit-all")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@Context SecurityContext ctx) {
        Principal userPrincipal = ctx.getUserPrincipal();
        String name = userPrincipal == null ? "anonymous" : userPrincipal.getName();
        Set<String> claimNames = jwt.getClaimNames();
        String res = String.format("hello + %s , isSecure: %s ,authScheme: %s ,hasJWT: %s ", name, ctx.isSecure(), ctx.getAuthenticationScheme(), claimNames);
        return res;
    }


    @Inject
    JsonWebToken jsonWebToken;

    @GET
    @Path("roles-allowed")
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.TEXT_PLAIN)
    public String helloRolesAllowed(@Context SecurityContext ctx) {
        String name = jsonWebToken.getName();
        System.out.println("subject = " + name);
        System.out.println("jsonWebToken.getGroups() = " + jsonWebToken.getGroups());
        return name;
    }

    @GET
    @Path("roles-allowed-admin")
    @RolesAllowed("Admin")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloRolesAllowedAdmin(@Context SecurityContext ctx) {
        Principal userPrincipal = ctx.getUserPrincipal();
        String name = userPrincipal == null ? "anonymous" : userPrincipal.getName();
        Set<String> claimNames = jwt.getClaimNames();
        String res = String.format("hello + %s , isSecure: %s ,authScheme: %s ,hasJWT: %s ", name, ctx.isSecure(), ctx.getAuthenticationScheme(), claimNames);
        return res;
    }

}
