package com.company.project.web;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.BigLotResult;
import com.company.project.service.BigLotResultService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/04/23.
 */
@RestController
@RequestMapping("/lottery")
public class BigLotResultController {
    @Resource
    private BigLotResultService bigLotResultService;

    @PostMapping("/add")
    public Result add(BigLotResult bigLotResult) {
        bigLotResultService.save(bigLotResult);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        bigLotResultService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(BigLotResult bigLotResult) {
        bigLotResultService.update(bigLotResult);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        BigLotResult bigLotResult = bigLotResultService.findById(id);
        return ResultGenerator.genSuccessResult(bigLotResult);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BigLotResult> list = bigLotResultService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/getLottery")
    public Result getLottery(){
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://m.lottery.gov.cn/api/mlottery_kj_detail.jspx?_ltype=4&_term=0&_num=10");
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
        return ResultGenerator.genSuccessResult();
    }
}
