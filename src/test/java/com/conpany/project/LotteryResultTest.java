//package com.conpany.project;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.company.project.model.BigLotResult;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class LotteryResultTest {
//    public static void main(String[] args) {
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        HttpGet httpGet = new HttpGet("https://m.lottery.gov.cn/api/mlottery_kj_detail.jspx?_ltype=4&_term=0&_num=10");
//        HttpResponse response = null;
//        try {
//            response = httpClient.execute(httpGet);
//            HttpEntity entity = response.getEntity();
//
//            JSONArray result = (JSONArray) JSON.parse(EntityUtils.toString(entity));
//            JSONObject jsonObject = (JSONObject)result.get(0);
//            JSONArray object = (JSONArray)jsonObject.get("mdata");
//            JSONObject detail = (JSONObject)object.get(0);
//            JSONObject lottery = (JSONObject)detail.get("lottery") ;
//            String number = lottery.get("number").toString();
//            String redBall = number.split("-")[0].replaceAll(" ",",");
//            String blueBall = number.split("-")[1].replaceAll(" ",",");
//            SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date openDate = sdf.parse(lottery.get("sTime").toString());
//            BigLotResult bigLotResult = new BigLotResult(lottery.get("term").toString(),
//                    redBall,blueBall,new Date(),openDate,o);
//            System.out.println(result);
//        }catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//}
