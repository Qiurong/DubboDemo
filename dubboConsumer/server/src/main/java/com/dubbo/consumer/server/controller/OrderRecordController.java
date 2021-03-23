package com.dubbo.consumer.server.controller;

import com.dubbo.consumer.server.request.RecordPushRequest;
import com.dubbo.consumer.server.service.OrderRecordService;
import com.google.common.collect.Maps;
import java.util.Map;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Qr
 * @create: 2021-02-22 20:56
 **/
@RestController
public class OrderRecordController {
    private static final Logger log = LoggerFactory.getLogger(OrderRecordController.class);

    private static final String prefix = "order";

    @Autowired
    private OrderRecordService orderRecordService;

    /**
     * 面向客户:客户下单
     */
    @RequestMapping(value = prefix+"/record/push", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON)
    public Map<String,Object> pushRecord(@RequestBody RecordPushRequest pushRequest){
        Map<String,Object> resMap = Maps.newHashMap();
        resMap.put("code",0);
        resMap.put("msg","成功");
        try{
            log.info("接收到请求数据：{}",pushRequest);


            //orderRecordService.pushOrder(pushRequest);
            orderRecordService.pushOrder_Generalized(pushRequest);
        }catch (Exception e){
            log.error("面向用户:用户下单-发生异常", e.fillInStackTrace());
            resMap.put("code",-1);
            resMap.put("msg","失败");
        }
        return resMap;
    }

}
