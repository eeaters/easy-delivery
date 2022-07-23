CREATE TABLE `channel_account`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
    `channel`     VARCHAR(25) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `app_key`     VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `app_secret`  VARCHAR(128) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `app_token`   VARCHAR(50) NULL DEFAULT NULL COMMENT '登录状态token' COLLATE 'utf8mb4_general_ci',
    `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_user` VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `update_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_user` VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `name`        VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    PRIMARY KEY (`id`) USING BTREE
) COMMENT='三方账号表'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=10000
;
CREATE TABLE `delivery`
(
    `id`                 BIGINT(20) NOT NULL AUTO_INCREMENT,
    `user_id`            BIGINT(20) NULL DEFAULT NULL COMMENT '所属用户id',
    `store_id`           BIGINT(20) NULL DEFAULT NULL COMMENT '门店id',
    `order_id`           VARCHAR(50) NULL DEFAULT NULL COMMENT '订单id' COLLATE 'utf8mb4_general_ci',
    `order_price`        INT(11) NULL DEFAULT NULL COMMENT '金额: 单位为分',
    `delivery_fee_price` INT(11) NULL DEFAULT '10' COMMENT '订单支付的配送费',
    `tip_fee_price`      INT(11) NULL DEFAULT '0' COMMENT '小费',
    `dest_longitude`     VARCHAR(20) NULL DEFAULT NULL COMMENT '目的地经度' COLLATE 'utf8mb4_general_ci',
    `dest_latitude`      VARCHAR(20) NULL DEFAULT NULL COMMENT '目的地-纬度' COLLATE 'utf8mb4_general_ci',
    `dest_address`       VARCHAR(50) NULL DEFAULT NULL COMMENT '目的地详细地址' COLLATE 'utf8mb4_general_ci',
    `dest_user`          VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `dest_phone`         VARCHAR(25) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `is_pre`             TINYINT(4) NULL DEFAULT '0' COMMENT '0: 正常单, 1:预约单',
    `except_time`        DATETIME NULL DEFAULT NULL COMMENT '期望送达时间',
    `status`             TINYINT(4) NULL DEFAULT '1' COMMENT '配送状态: 0-> 已接单, 1-> 已推送给三方',
    `create_user`        VARCHAR(20) NULL DEFAULT 'admin' COMMENT '创建人' COLLATE 'utf8mb4_general_ci',
    `create_time`        DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    `update_user`        VARCHAR(20) NULL DEFAULT 'admin' COMMENT '更新人' COLLATE 'utf8mb4_general_ci',
    `update_time`        DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=10000
;
CREATE TABLE `delivery_detail`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
    `channel`     VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
    `create_time` DATETIME(6) NULL DEFAULT NULL,
    `delivery_id` BIGINT(20) NULL DEFAULT NULL,
    `rider_name`  VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
    `rider_phone` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
    `status`      INT(11) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=10000
;
CREATE TABLE `delivery_strategy`
(
    `id`             BIGINT(20) NOT NULL AUTO_INCREMENT,
    `user_id`        BIGINT(20) NULL DEFAULT NULL COMMENT '所属用户',
    `name`           VARCHAR(20) NULL DEFAULT NULL COMMENT '策略名称' COLLATE 'utf8mb4_general_ci',
    `desc`           VARCHAR(255) NULL DEFAULT NULL COMMENT '策略的描述' COLLATE 'utf8mb4_general_ci',
    `type`           TINYINT(4) NULL DEFAULT NULL COMMENT '策略类型,0: 默认排序, 1: 价格优先 ,2: 距离优先',
    `timeout_period` INT(11) NULL DEFAULT NULL COMMENT '超时时间,单位: 分钟',
    `create_time`    DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_user`    VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `update_time`    DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_user`    VARCHAR(20) NULL DEFAULT NULL COMMENT '更新人' COLLATE 'utf8mb4_general_ci',
    PRIMARY KEY (`id`) USING BTREE
) COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=10000
;
CREATE TABLE `store`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
    `user_id`     BIGINT(20) NULL DEFAULT NULL COMMENT '所属用户id',
    `store_name`  VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `store_code`  VARCHAR(25) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `longitude`   VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `latitude`    VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `phone`       VARCHAR(20) NULL DEFAULT NULL COMMENT '电话' COLLATE 'utf8mb4_general_ci',
    `create_user` VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_user` VARCHAR(20) NULL DEFAULT NULL COMMENT '更新人' COLLATE 'utf8mb4_general_ci',
    `update_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `address`     VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    PRIMARY KEY (`id`) USING BTREE
) COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=10000
;
CREATE TABLE `store_strategy_mapping`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
    `create_time` DATETIME(6) NULL DEFAULT NULL,
    `store_id`    BIGINT(20) NULL DEFAULT NULL,
    `strategy_id` BIGINT(20) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=3
;
CREATE TABLE `strategy_channel_mapping`
(
    `id`           BIGINT(20) NOT NULL AUTO_INCREMENT,
    `strategy_id`  BIGINT(20) NOT NULL COMMENT '策略id',
    `channel_id`   BIGINT(20) NOT NULL COMMENT '渠道id',
    `order`        TINYINT(4) UNSIGNED NULL DEFAULT NULL COMMENT '排序',
    `create_time`  DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    `create_user`  VARCHAR(20) NULL DEFAULT NULL COMMENT '创建人' COLLATE 'utf8mb4_general_ci',
    `channel_name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    PRIMARY KEY (`id`) USING BTREE
) COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=10000
;
CREATE TABLE `user`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
    `phone`       VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
    `user_name`   VARCHAR(20) NULL DEFAULT NULL COMMENT '用户名' COLLATE 'utf8_general_ci',
    `password`    VARCHAR(50) NULL DEFAULT NULL COMMENT '密码' COLLATE 'utf8_general_ci',
    `create_user` VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
    `create_time` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) COMMENT='用户表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=10000
;

INSERT INTO `user` (`id`, `phone`, `user_name`, `password`, `create_user`, `create_time`) VALUES (10001, '13666666666', '测试用户1', '123456', 'admin', NULL);
