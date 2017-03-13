CREATE TABLE `t_runtime_log` (
  `log_id` int NOT NULL AUTO_INCREMENT,-- '日志编号，主键',
  `start_time` VARCHAR(50) NOT NULL ,-- '开始时间',
  `end_time` VARCHAR(50)  ,-- '结束时间',
  `log_desc` text not null ,-- '日志内容',
  PRIMARY KEY (`log_id`)
);--='运行日志表';

CREATE TABLE `t_app_param` (
  `param_key` VARCHAR(255)  not null,-- '参数key，唯一',
  `param_desc` VARCHAR(255) not null ,-- '参数描述',
  `param_value` text not null ,-- '参数内容',
  PRIMARY KEY (`param_key`)
);--='应用参数表';




