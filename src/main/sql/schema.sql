-- 创建table
CREATE DATABASE SECKILL;

USE SECKILL;

CREATE TABLE seckill(
  `seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'GoodsID',
  `name` varchar(120) NOT NULL COMMENT 'GoodsName',
  `number` int NOT NULL COMMENT 'GoodsQuantity',
  `start_time` TIMESTAMP NOT NULL COMMENT 'WhenSecKillStart',
  `end_time` TIMESTAMP NOT NULL COMMENT 'WhenSecKillEnd',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'WhenThisGoodsWasCreated',
  PRIMARY KEY (secKill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)ENGINE=innoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8 COMMENT='GoodsTable';

-- 初始化数据
INSERT INTO seckill (name, number, start_time, end_time)
VALUES
  ('1000元秒杀Iphone6!', 100, '2018-01-11 00:00:00', '2018-01-12 00:00:00'),
  ('300元秒杀111!', 200, '2018-01-11 00:00:00', '2018-01-12 00:00:00'),
  ('500元秒杀222!', 300, '2018-01-11 00:00:00', '2018-01-12 00:00:00'),
  ('1000元秒杀Iphone6!', 400, '2018-01-11 00:00:00', '2018-01-12 00:00:00');

-- 秒杀成功明细表
-- 用户登录
CREATE TABLE SUCCESS_KILLED(
  `secKill_id` bigint NOT NULL COMMENT 'GoodsID',
  `phoneNumber` bigint NOT NULL COMMENT 'USERS_PHONE_NUMBER',
  `state` tinyint NOT NULL DEFAULT -1 COMMENT '-1:FAIL, 0:SUCCESS, 1:PAID, 2:SEND',
  `create_time` TIMESTAMP NOT NULL COMMENT 'create_time',
  PRIMARY KEY (secKill_id, phoneNumber),
  KEY idx_create_time(create_time)
) ENGINE innoDB DEFAULT CHARSET=utf8 COMMENT='ResultSheet';
