package io.eeaters.easy.delivery.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.concurrent.CompletionStage;

@RegisterRestClient(configKey = "baidu-client")
public interface BaiduClient {

    @GET
    @Path("geocoder")
    CompletionStage<String> geocoder(@QueryParam("output") String output, @QueryParam("location") String location);

}
