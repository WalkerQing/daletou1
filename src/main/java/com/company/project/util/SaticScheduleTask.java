package com.company.project.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    @Resource
    private RedisUtil redisUtil;

    @Scheduled(cron = "0 0 0 * * ? ")
    public void setDayCount(){
        if(redisUtil.hasKey("rollCount")){
            redisUtil.set("rollCount",1);
        }
    }
}
