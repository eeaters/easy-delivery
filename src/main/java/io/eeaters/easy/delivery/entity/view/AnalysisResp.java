package io.eeaters.easy.delivery.entity.view;

import java.util.List;

public class AnalysisResp {

        private String channelName;

        private Integer all;

        private Integer delivering;

        private Integer complete;

        public static AnalysisResp nonNulReport(String channelName) {
            AnalysisResp report = new AnalysisResp();
            report.channelName = channelName;
            report.all = 0;
            report.delivering = 0;
            report.complete = 0;
            return report;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public Integer getAll() {
            return all;
        }

        public void setAll(Integer all) {
            this.all = all;
        }

        public Integer getDelivering() {
            return delivering;
        }

        public void setDelivering(Integer delivering) {
            this.delivering = delivering;
        }

        public Integer getComplete() {
            return complete;
        }

        public void setComplete(Integer complete) {
            this.complete = complete;
        }

}
