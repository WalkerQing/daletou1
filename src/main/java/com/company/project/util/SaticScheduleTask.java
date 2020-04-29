package com.company.project.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.model.BigLotResult;
import com.company.project.model.Roll;
import com.company.project.service.BigLotResultService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private BigLotResultService bigLotResultService;

    private final Logger logger = LoggerFactory.getLogger(SaticScheduleTask.class);

    @Scheduled(cron = "0 0 10 * * ?")
    public void setDayCount(){
        redisUtil.set("rollCount",1);
        logger.info("{} -- 已重置RollCount",new Date());
    }

    @Scheduled(cron = "1 30 20 ? * 1,3,6")
    public void setLotteryResult(){
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://m.lottery.gov.cn/api/mlottery_kj_detail.jspx?_ltype=4&_term=0&_num=1");
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            JSONArray result = (JSONArray) JSON.parse(EntityUtils.toString(entity));
            JSONObject jsonObject = (JSONObject)result.get(0);
            JSONArray object = (JSONArray)jsonObject.get("mdata");
            JSONObject detail = (JSONObject)object.get(0);
            JSONObject lottery = (JSONObject)detail.get("lottery") ;
            JSONObject openTime = (JSONObject)lottery.get("openTime") ;
            String number = lottery.get("number").toString();
            String redBall = number.split("-")[0].replaceAll(" ",",");
            String blueBall = number.split("-")[1].replaceAll(" ",",");
            SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date openDate = new Date(Long.parseLong(openTime.get("time").toString()));
            Date startDate = sdf.parse(lottery.get("sTime").toString());
            BigLotResult bigLotResult = new BigLotResult(lottery.get("term").toString(),
                    redBall,blueBall,new Date(),openDate,startDate);
            bigLotResultService.save(bigLotResult);
        }catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
//
//    public List<Roll> getLotResult(){
//
//    }
}
