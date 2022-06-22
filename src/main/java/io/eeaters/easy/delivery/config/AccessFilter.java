package io.eeaters.easy.delivery.config;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class AccessFilter implements ContainerRequestFilter {


    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
//        String path = containerRequestContext.getUriInfo().getPath();
//        MultivaluedMap<String, String> headers = containerRequestContext.getHeaders();
//        headers.forEach((k,v)->{
//            System.out.println("k = " + k + " , v = " + v);
//        });
//        System.out.println("path = " + path);
    }
}
