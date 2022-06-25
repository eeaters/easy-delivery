package io.eeaters.easy.delivery.client.req.shunfeng;

import java.util.List;

public class SFOrderDetail {
    /**
     * 用户订单总金额
     */
    private Integer total_price;
    /**
     * 物品类型
     */
    private Integer product_type;
    /**
     * 实收用户金额 --非必须
     */
    private Integer user_money;
    /**
     * 实付商户金额 --非必须
     */
    private Integer shop_money;
    /**
     * 物品重量（单位：克）
     */
    private Integer weight_gram;
    /**
     * 物品体积（单位：升） --非必须
     */
    private Integer volume_litre;
    /**
     * 商户收取的配送费（单位：分） --非必须
     */
    private Integer delivery_money;
    /**
     * 物品个数
     */
    private Integer product_num;
    /**
     * 物品种类个数
     */
    private Integer product_type_num;
    /**
     * 物品详情；数组结构，详见product_detail结构
     */
    private List<SFProductDetail> product_detail;



    public static class SFProductDetail{
        /**
         * 物品名称
         */
        private String product_name;
        /**
         * 物品id  --非必须
         */
        private Integer product_id;
        /**
         * 物品数量
         */
        private Integer product_num;
        /**
         * 物品价格  --非必须
         */
        private Integer product_price;
        /**
         * 物品单位  --非必须
         */
        private String product_unit;
        /**
         * 物品备注  --非必须
         */
        private String product_remark;
        /**
         * 详情  --非必须
         */
        private String item_detail;

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public Integer getProduct_id() {
            return product_id;
        }

        public void setProduct_id(Integer product_id) {
            this.product_id = product_id;
        }

        public Integer getProduct_num() {
            return product_num;
        }

        public void setProduct_num(Integer product_num) {
            this.product_num = product_num;
        }

        public Integer getProduct_price() {
            return product_price;
        }

        public void setProduct_price(Integer product_price) {
            this.product_price = product_price;
        }

        public String getProduct_unit() {
            return product_unit;
        }

        public void setProduct_unit(String product_unit) {
            this.product_unit = product_unit;
        }

        public String getProduct_remark() {
            return product_remark;
        }

        public void setProduct_remark(String product_remark) {
            this.product_remark = product_remark;
        }

        public String getItem_detail() {
            return item_detail;
        }

        public void setItem_detail(String item_detail) {
            this.item_detail = item_detail;
        }
    }


    public Integer getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Integer total_price) {
        this.total_price = total_price;
    }

    public Integer getProduct_type() {
        return product_type;
    }

    public void setProduct_type(Integer product_type) {
        this.product_type = product_type;
    }

    public Integer getUser_money() {
        return user_money;
    }

    public void setUser_money(Integer user_money) {
        this.user_money = user_money;
    }

    public Integer getShop_money() {
        return shop_money;
    }

    public void setShop_money(Integer shop_money) {
        this.shop_money = shop_money;
    }

    public Integer getWeight_gram() {
        return weight_gram;
    }

    public void setWeight_gram(Integer weight_gram) {
        this.weight_gram = weight_gram;
    }

    public Integer getVolume_litre() {
        return volume_litre;
    }

    public void setVolume_litre(Integer volume_litre) {
        this.volume_litre = volume_litre;
    }

    public Integer getDelivery_money() {
        return delivery_money;
    }

    public void setDelivery_money(Integer delivery_money) {
        this.delivery_money = delivery_money;
    }

    public Integer getProduct_num() {
        return product_num;
    }

    public void setProduct_num(Integer product_num) {
        this.product_num = product_num;
    }

    public Integer getProduct_type_num() {
        return product_type_num;
    }

    public void setProduct_type_num(Integer product_type_num) {
        this.product_type_num = product_type_num;
    }

    public List<SFProductDetail> getProduct_detail() {
        return product_detail;
    }

    public void setProduct_detail(List<SFProductDetail> product_detail) {
        this.product_detail = product_detail;
    }
}
