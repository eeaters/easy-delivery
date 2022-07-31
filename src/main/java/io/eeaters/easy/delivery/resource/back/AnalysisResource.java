package io.eeaters.easy.delivery.resource.back;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.eeaters.easy.delivery.entity.model.ChannelAccount;
import io.eeaters.easy.delivery.entity.model.Delivery;
import io.eeaters.easy.delivery.entity.view.AnalysisResp;
import io.quarkus.panache.common.Parameters;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static io.eeaters.easy.delivery.enums.ChannelEnum.channelName;
import static java.util.Optional.ofNullable;

@Path("management/analysis")
public class AnalysisResource {

    @Inject
    @Location("back/analyse")
    Template analyse;

    @Inject
    ObjectMapper objectMapper;


    @GET
    @Path("page")
    public TemplateInstance toAnalysis(@QueryParam("range")Integer range) throws JsonProcessingException {
        //一天
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = null;
        if (range == 1) {
            startTime = endTime.minusDays(1);
        } else if (range == 2) {
            startTime = endTime.minusWeeks(1);
        } else if (range == 3) {
            startTime = endTime.minusMonths(1);
        }
        String sql = "createTime > :startTime and createTime < :endTime";
        Parameters parameters = new Parameters();
        parameters.and("startTime", startTime);
        parameters.and("endTime", endTime);

        List<Delivery> deliveryList = Delivery.find(sql, parameters).list();
        Map<Integer, List<Delivery>> statusMap = deliveryList.stream().collect(Collectors.groupingBy(Delivery::getStatus));

        AnalysisResp all = new AnalysisResp();
        all.setAll(deliveryList.size());
        all.setComplete(0);
        int delivering = ofNullable(statusMap.get(2)).map(List::size).orElse(0);
        all.setDelivering(delivering);


        Map<String, List<Delivery>> channelMap = deliveryList.stream().filter(delivery -> delivery.getChannel() != null)
                .collect(Collectors.groupingBy(Delivery::getChannel));

        List<ChannelAccount> list = ChannelAccount.findAll().list();

        List<AnalysisResp> allChannelList = new ArrayList<>();
        for (ChannelAccount account : list) {
            List<Delivery> deliveries = channelMap.get(account.getChannel());
            if (deliveries == null) {
                AnalysisResp report = AnalysisResp.nonNulReport(channelName(account.getChannel()));
                allChannelList.add(report);
            }else{
                AnalysisResp report = new AnalysisResp();
                int deliver = (int)deliveries.stream().filter(delivery -> delivery.getStatus() == 2).count();
                report.setChannelName(channelName(account.getChannel()));
                report.setComplete(0);
                report.setAll(deliveries.size());
                report.setDelivering(deliver);
                allChannelList.add(report);
            }
        }
        return analyse.data("range", range)
                .data("all", all)
                .data("channelList", allChannelList);
    }


}
