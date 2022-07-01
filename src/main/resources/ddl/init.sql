CREATE TABLE `channel_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channel` varchar(25) DEFAULT NULL,
  `app_key` varchar(50) DEFAULT NULL,
  `app_secret` varchar(128) DEFAULT NULL,
  `app_token` varchar(50) DEFAULT NULL COMMENT '登录状态token',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='三方账号表';

CREATE TABLE `delivery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '所属用户id',
  `store_id` bigint(20) DEFAULT NULL COMMENT '门店id',
  `order_id` varchar(20) DEFAULT NULL COMMENT '订单id',
  `order_price` int(11) DEFAULT NULL COMMENT '金额: 单位为分',
  `delivery_fee_price` int(11) DEFAULT NULL COMMENT '订单支付的配送费',
  `tip_fee_price` int(11) DEFAULT NULL COMMENT '小费',
  `dest_longitude` varchar(20) DEFAULT NULL COMMENT '目的地经度',
  `dest_latitude` varchar(20) DEFAULT NULL COMMENT '目的地-纬度',
  `dest_address` varchar(50) DEFAULT NULL COMMENT '目的地详细地址',
  `dest_user` varchar(20) DEFAULT NULL,
  `dest_phone` varchar(25) DEFAULT NULL,
  `is_pre` tinyint(4) DEFAULT NULL COMMENT '0: 正常单, 1:预约单',
  `except_time` datetime DEFAULT NULL COMMENT '期望送达时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '配送状态: 0-> 已接单, 1-> 已推送给三方',
  `create_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `delivery_strategy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '所属用户',
  `name` varchar(20) DEFAULT NULL COMMENT '策略名称',
  `desc` varchar(255) DEFAULT NULL COMMENT '策略的描述',
  `type` tinyint(4) DEFAULT NULL COMMENT '策略类型,0: 默认排序, 1: 价格优先 ,2: 距离优先',
  `timeout_period` int(11) DEFAULT NULL COMMENT '超时时间,单位: 分钟',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user` varchar(20) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `store` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '所属用户id',
  `store_name` varchar(20) DEFAULT NULL,
  `store_code` varchar(25) DEFAULT NULL,
  `longitude` varchar(20) DEFAULT NULL,
  `latitude` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `create_user` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `strategy_channel_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `strategy_id` bigint(20) NOT NULL COMMENT '策略id',
  `channel_id` bigint(20) NOT NULL COMMENT '渠道id',
  `order` tinyint(4) unsigned DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `create_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `test`.`delivery` (`id`, `user_id`, `store_id`, `order_id`, `order_price`, `delivery_fee_price`, `tip_fee_price`, `dest_longitude`, `dest_latitude`, `dest_address`, `dest_user`, `dest_phone`, `is_pre`, `except_time`, `status`, `create_user`, `create_time`, `update_user`, `update_time`) VALUES ('1', '1', '1', '1123', '123', '123', '123', '121.1225', '43.25411', '测试地址', '随机创建用户', '96110', '0', '2022-06-29 22:50:04', '1', 'admin', '2022-06-29 22:50:14', 'admin', '2022-06-29 23:15:29');


INSERT INTO `test`.`delivery_strategy` (`id`, `user_id`, `name`, `desc`, `type`, `timeout_period`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES ('1', '1', '顺丰-美团', '3433', '3', '32', '2022-06-26 08:33:13', NULL, '2022-07-01 13:25:46', 'admin_user');


INSERT INTO `test`.`store` (`id`, `user_id`, `store_name`, `store_code`, `longitude`, `latitude`, `phone`, `create_user`, `create_time`, `update_user`, `update_time`) VALUES ('10001', '1', 'test001', '23432432', '121.36521', '56.585412', '0373-96110', 'admin', '2022-06-28 23:42:01', 'admin', '2022-06-30 23:17:02');



