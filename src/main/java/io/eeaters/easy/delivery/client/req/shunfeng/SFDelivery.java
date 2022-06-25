package io.eeaters.easy.delivery.client.req.shunfeng;

public class SFDelivery {
    /**
     * 同城开发者ID  --必须
     */
    private Integer dev_id;
    /**
     * 店铺ID --必须
     */
    private String shop_id;
    /**
     * 店铺ID类型
     * 1、顺丰店铺ID ；
     * 2、接入方店铺ID  --非必须
     */
    private Integer shop_type;
    /**
     * 商家订单号  --必须
     */
    private String shop_order_id;
    /**
     * 订单接入来源 1、美团；2、饿了么；3、百度；4、口碑； 其他请直接填写中文字符串值  --必须
     */
    private String order_source;
    /**
     * 取货序号 用于骑士快速寻找配送物 --非必须
     */
    private String order_sequence;
    /**
     * 坐标类型
     * 1、百度坐标，
     * 2、高德坐标 --非必须
     */
    private Integer lbs_type;
    /**
     * 用户支付方式
     * 1、在线支付
     * 0、货到付款 --必须
     */
    private Integer pay_type;
    /**
     * 代收金额 单位：分 --非必须
     */
    private Integer receive_user_money;
    /**
     * 用户下单时间 --必须
     */
    private Integer order_time;
    /**
     * 推单时间 --必须
     */
    private Integer push_time;
    /**
     * 是否是预约单
     * 0、非预约单；
     * 1、预约单 --必须
     */
    private Integer is_appoint;
    /**
     * 用户期望送达时间 预约单需必传,秒级时间戳  --非必须，预约单必须
     */
    private Integer expect_time;
    /**
     * 订单备注
     */
    private String remark;
    /**
     * 版本号 参照文档主版本号填写  如：文档版本号1.7,version=17  --必须
     */
    private Integer version;
    /**
     * 收货人信息 --必须
     */
    private SFReceiveInfo receive;
    /**
     * 门店信息  --非必须
     */
    private SFShopInfo shop;
    /**
     * 订单信息 --必须
     */
    private SFOrderDetail order_detail;
    /**
     * 返回字段控制标志位  1、价格，2、距离，4、重量，组合条件请相加例如全部返回为填入7  --非必须
     */
    private Integer return_flag;
    /**
     * 物流流向 1、从门店取件送至用户；   2、从用户取件送至门店  --非必须
     */
    private Integer rider_pick_method;

    public Integer getDev_id() {
        return dev_id;
    }

    public void setDev_id(Integer dev_id) {
        this.dev_id = dev_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public Integer getShop_type() {
        return shop_type;
    }

    public void setShop_type(Integer shop_type) {
        this.shop_type = shop_type;
    }

    public String getShop_order_id() {
        return shop_order_id;
    }

    public void setShop_order_id(String shop_order_id) {
        this.shop_order_id = shop_order_id;
    }

    public String getOrder_source() {
        return order_source;
    }

    public void setOrder_source(String order_source) {
        this.order_source = order_source;
    }

    public String getOrder_sequence() {
        return order_sequence;
    }

    public void setOrder_sequence(String order_sequence) {
        this.order_sequence = order_sequence;
    }

    public Integer getLbs_type() {
        return lbs_type;
    }

    public void setLbs_type(Integer lbs_type) {
        this.lbs_type = lbs_type;
    }

    public Integer getPay_type() {
        return pay_type;
    }

    public void setPay_type(Integer pay_type) {
        this.pay_type = pay_type;
    }

    public Integer getReceive_user_money() {
        return receive_user_money;
    }

    public void setReceive_user_money(Integer receive_user_money) {
        this.receive_user_money = receive_user_money;
    }

    public Integer getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Integer order_time) {
        this.order_time = order_time;
    }

    public Integer getPush_time() {
        return push_time;
    }

    public void setPush_time(Integer push_time) {
        this.push_time = push_time;
    }

    public Integer getIs_appoint() {
        return is_appoint;
    }

    public void setIs_appoint(Integer is_appoint) {
        this.is_appoint = is_appoint;
    }

    public Integer getExpect_time() {
        return expect_time;
    }

    public void setExpect_time(Integer expect_time) {
        this.expect_time = expect_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public SFReceiveInfo getReceive() {
        return receive;
    }

    public void setReceive(SFReceiveInfo receive) {
        this.receive = receive;
    }

    public SFShopInfo getShop() {
        return shop;
    }

    public void setShop(SFShopInfo shop) {
        this.shop = shop;
    }

    public SFOrderDetail getOrder_detail() {
        return order_detail;
    }

    public void setOrder_detail(SFOrderDetail order_detail) {
        this.order_detail = order_detail;
    }

    public Integer getReturn_flag() {
        return return_flag;
    }

    public void setReturn_flag(Integer return_flag) {
        this.return_flag = return_flag;
    }

    public Integer getRider_pick_method() {
        return rider_pick_method;
    }

    public void setRider_pick_method(Integer rider_pick_method) {
        this.rider_pick_method = rider_pick_method;
    }
}
