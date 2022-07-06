package io.eeaters.easy.delivery.entity.view;

import io.eeaters.easy.delivery.entity.model.DeliveryDetail;
import io.eeaters.easy.delivery.enums.ChannelEnum;
import io.eeaters.easy.delivery.enums.StatusEnum;

import javax.persistence.Column;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * 先把功能实现
 */
public class DeliveryDetailResVO {
    @Column(name = "delivery_id")
    private Long deliveryId;

    private String channelName;

    private String statusEvent;

    @Column(name = "rider_name")
    private String riderName;

    @Column(name = "rider_phone")
    private String riderPhone;

    @Column(name = "create_time")
    private String createTime;

    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static DeliveryDetailResVO convert(DeliveryDetail deliveryDetail) {
        DeliveryDetailResVO resVO = new DeliveryDetailResVO();
        resVO.setDeliveryId(deliveryDetail.getDeliveryId());
        resVO.setRiderName(Optional.ofNullable(deliveryDetail.getRiderName()).orElse("暂未分配骑手"));
        resVO.setRiderPhone(Optional.ofNullable(deliveryDetail.getRiderPhone()).orElse("暂未分配骑手"));
        resVO.setCreateTime(FORMATTER.format(deliveryDetail.getCreateTime()));
        resVO.setStatusEvent(StatusEnum.eventName(deliveryDetail.getStatus()));
        resVO.setChannelName(ChannelEnum.channelName(deliveryDetail.getChannel()));
        return resVO;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getStatusEvent() {
        return statusEvent;
    }

    public void setStatusEvent(String statusEvent) {
        this.statusEvent = statusEvent;
    }

    public String getRiderName() {
        return riderName;
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
    }

    public String getRiderPhone() {
        return riderPhone;
    }

    public void setRiderPhone(String riderPhone) {
        this.riderPhone = riderPhone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
