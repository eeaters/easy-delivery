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
AUTO_INCREMENT=10002
;
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
AUTO_INCREMENT=10006
;
CREATE TABLE `delivery`
(
    `id`                 BIGINT(20) NOT NULL AUTO_INCREMENT,
    `user_id`            BIGINT(20) NULL DEFAULT NULL COMMENT '所属用户id',
    `store_id`           BIGINT(20) NULL DEFAULT NULL COMMENT '门店id',
    `order_id`           VARCHAR(20) NULL DEFAULT NULL COMMENT '订单id' COLLATE 'utf8mb4_general_ci',
    `order_price`        INT(11) NULL DEFAULT NULL COMMENT '金额: 单位为分',
    `delivery_fee_price` INT(11) NULL DEFAULT NULL COMMENT '订单支付的配送费',
    `tip_fee_price`      INT(11) NULL DEFAULT NULL COMMENT '小费',
    `dest_longitude`     VARCHAR(20) NULL DEFAULT NULL COMMENT '目的地经度' COLLATE 'utf8mb4_general_ci',
    `dest_latitude`      VARCHAR(20) NULL DEFAULT NULL COMMENT '目的地-纬度' COLLATE 'utf8mb4_general_ci',
    `dest_address`       VARCHAR(50) NULL DEFAULT NULL COMMENT '目的地详细地址' COLLATE 'utf8mb4_general_ci',
    `dest_user`          VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `dest_phone`         VARCHAR(25) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `is_pre`             TINYINT(4) NULL DEFAULT NULL COMMENT '0: 正常单, 1:预约单',
    `except_time`        DATETIME NULL DEFAULT NULL COMMENT '期望送达时间',
    `status`             TINYINT(4) NULL DEFAULT NULL COMMENT '配送状态: 0-> 已接单, 1-> 已推送给三方',
    `create_user`        VARCHAR(20) NULL DEFAULT NULL COMMENT '创建人' COLLATE 'utf8mb4_general_ci',
    `create_time`        DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    `update_user`        VARCHAR(20) NULL DEFAULT NULL COMMENT '更新人' COLLATE 'utf8mb4_general_ci',
    `update_time`        DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=2
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
AUTO_INCREMENT=4
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
AUTO_INCREMENT=4
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
AUTO_INCREMENT=10003
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
AUTO_INCREMENT=10003
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
AUTO_INCREMENT=10003
;


INSERT INTO `channel_account` (`id`, `channel`, `app_key`, `app_secret`, `app_token`, `create_time`, `create_user`,
                               `update_time`, `update_user`, `name`)
VALUES (10001, 'meituan', '234234', '32423432', '234324324', '2022-07-07 13:29:39', 'admin', '2022-07-07 13:29:41',
        'admin', '美团');
INSERT INTO `channel_account` (`id`, `channel`, `app_key`, `app_secret`, `app_token`, `create_time`, `create_user`,
                               `update_time`, `update_user`, `name`)
VALUES (10002, 'fengniao', '234234', '34223432', '324234324', '2022-07-07 13:30:05', 'admin', '2022-07-07 13:30:07',
        'admin', '蜂鸟');
INSERT INTO `channel_account` (`id`, `channel`, `app_key`, `app_secret`, `app_token`, `create_time`, `create_user`,
                               `update_time`, `update_user`, `name`)
VALUES (10003, 'shunfeng', '324324', '3423432', '34324324', '2022-07-07 13:30:24', 'admin', '2022-07-07 13:23:21',
        'admin', '顺丰');
INSERT INTO `channel_account` (`id`, `channel`, `app_key`, `app_secret`, `app_token`, `create_time`, `create_user`,
                               `update_time`, `update_user`, `name`)
VALUES (10004, 'dada', '345435', '34534', '45345', '2022-07-07 13:23:15', 'admin', '2022-07-07 13:23:31', 'admin',
        '达达');
INSERT INTO `channel_account` (`id`, `channel`, `app_key`, `app_secret`, `app_token`, `create_time`, `create_user`,
                               `update_time`, `update_user`, `name`)
VALUES (10005, 'shansong', '234234', '345345', '435345345', '2022-07-07 13:31:07', 'admin', '2022-07-07 13:31:09',
        'admin', '闪送');


INSERT INTO `delivery` (`id`, `user_id`, `store_id`, `order_id`, `order_price`, `delivery_fee_price`, `tip_fee_price`,
                        `dest_longitude`, `dest_latitude`, `dest_address`, `dest_user`, `dest_phone`, `is_pre`,
                        `except_time`, `status`, `create_user`, `create_time`, `update_user`, `update_time`)
VALUES (1, 1, 1, '1123', 123, 123, 123, '121.1225', '43.25411', '测试地址', '随机创建用户', '96110', 0, '2022-06-29 22:50:04', 1,
        'admin', '2022-06-29 22:50:14', 'admin', '2022-06-29 23:15:29');

INSERT INTO `delivery_detail` (`id`, `channel`, `create_time`, `delivery_id`, `rider_name`, `rider_phone`, `status`)
VALUES (1, 'meituan', '2022-07-07 13:31:46.000000', 1, NULL, NULL, -1);
INSERT INTO `delivery_detail` (`id`, `channel`, `create_time`, `delivery_id`, `rider_name`, `rider_phone`, `status`)
VALUES (2, 'shansong', '2022-07-07 13:32:10.000000', 1, NULL, NULL, 1);
INSERT INTO `delivery_detail` (`id`, `channel`, `create_time`, `delivery_id`, `rider_name`, `rider_phone`, `status`)
VALUES (3, 'shansong', '2022-07-07 13:32:23.000000', 1, 'Jay', '13541258965', 2);


INSERT INTO `delivery_strategy` (`id`, `user_id`, `name`, `desc`, `type`, `timeout_period`, `create_time`,
                                 `create_user`, `update_time`, `update_user`)
VALUES (1, 10001, '测试修改', ' 3433', 2, 30, '2022-06-26 08:33:13', NULL, '2022-07-07 18:09:33', 'admin_user');
INSERT INTO `delivery_strategy` (`id`, `user_id`, `name`, `desc`, `type`, `timeout_period`, `create_time`,
                                 `create_user`, `update_time`, `update_user`)
VALUES (3, 10001, '234', '  234765', 2, 20, '2022-07-07 18:07:31', NULL, '2022-07-07 18:15:28', NULL);

INSERT INTO `store` (`id`, `user_id`, `store_name`, `store_code`, `longitude`, `latitude`, `phone`, `create_user`,
                     `create_time`, `update_user`, `update_time`, `address`)
VALUES (10001, 1, 'test001', '23432432', '121.36521', '56.585412', '0373-96110', 'admin', '2022-06-28 23:42:01',
        'admin', '2022-06-30 23:17:02', NULL);
INSERT INTO `store` (`id`, `user_id`, `store_name`, `store_code`, `longitude`, `latitude`, `phone`, `create_user`,
                     `create_time`, `update_user`, `update_time`, `address`)
VALUES (10002, 10001, 'test002', 'test002', '123.541', '68.5458', '13524569852', NULL, '2022-07-07 13:21:11', NULL,
        '2022-07-07 13:21:11', '中国-上海');

INSERT INTO `store_strategy_mapping` (`id`, `create_time`, `store_id`, `strategy_id`)
VALUES (1, NULL, 10002, 1);

INSERT INTO `strategy_channel_mapping` (`id`, `strategy_id`, `channel_id`, `order`, `create_time`, `create_user`,
                                        `channel_name`)
VALUES (11, 1, 10001, 0, '2022-07-07 18:09:33', NULL, NULL);
INSERT INTO `strategy_channel_mapping` (`id`, `strategy_id`, `channel_id`, `order`, `create_time`, `create_user`,
                                        `channel_name`)
VALUES (12, 1, 10005, 1, '2022-07-07 18:09:33', NULL, NULL);
INSERT INTO `strategy_channel_mapping` (`id`, `strategy_id`, `channel_id`, `order`, `create_time`, `create_user`,
                                        `channel_name`)
VALUES (13, 1, 10002, 2, '2022-07-07 18:09:33', NULL, NULL);
INSERT INTO `strategy_channel_mapping` (`id`, `strategy_id`, `channel_id`, `order`, `create_time`, `create_user`,
                                        `channel_name`)
VALUES (14, 3, 10003, 0, '2022-07-07 18:15:28', NULL, NULL);
INSERT INTO `strategy_channel_mapping` (`id`, `strategy_id`, `channel_id`, `order`, `create_time`, `create_user`,
                                        `channel_name`)
VALUES (15, 3, 10004, 1, '2022-07-07 18:15:28', NULL, NULL);

INSERT INTO `user` (`id`, `phone`, `user_name`, `password`, `create_user`, `create_time`)
VALUES (10001, '13666666666', '测试用户1', '123456', 'admin', NULL);

