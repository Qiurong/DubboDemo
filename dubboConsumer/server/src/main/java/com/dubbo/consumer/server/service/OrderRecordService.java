package com.dubbo.consumer.server.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.dubbo.consumer.server.request.RecordPushRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: Qr
 * @create: 2021-02-22 23:09
 **/
@Service
public class OrderRecordService {

    private static final Logger log = LoggerFactory.getLogger(OrderRecordService.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HttpService httpService;

    private static final  String url = "http://127.0.0.1:9020/v1/record/push";

    private OkHttpClient httpClient = new OkHttpClient();


    /**
     * 处理controller层传来的用户下单数据
     * 有异常的话抛到controller层处理
     * @param pushRequest
     */
    public void pushOrder(RecordPushRequest pushRequest) throws Exception {
        try{
            //构造builder
            Request.Builder builder=new Request.Builder()
                    .url(url)
                    .header("Content-Type","application/json");

            //构造请求体
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json")
                ,objectMapper.writeValueAsString(pushRequest));

            //构造请求
            Request request = builder.post(requestBody).build();

            //发起请求
            Response response = httpClient.newCall(request).execute();
            log.info(response.body().toString());
        }catch(Exception e){
            throw e;
        }
    }

    public void pushOrder_Generalized(RecordPushRequest pushRequest) throws Exception{
        try{
            Map<String,String> headerMap = new HashMap<>();
            headerMap.put("Content-Type","application/json");
            String res = httpService.post(url,headerMap,"application/json"
                                            ,objectMapper.writeValueAsString(pushRequest));
            log.info("响应结果:{}",res);

        }catch (Exception e){
            throw e;
        }
    }
}
