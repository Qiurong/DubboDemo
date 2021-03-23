package com.dubbo.consumer.server.controller;

import com.dubbo.example.api.response.BaseResponse;
import com.dubbo.example.api.service.ItemService;
import com.google.common.collect.Maps;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Qr
 * @create: 2021-02-22 14:05
 **/
@RestController
public class ItemController {
    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    private static final String prefix = "item";

    @Autowired
    private ItemService dubboItemService;


    @RequestMapping(value = prefix+"/list",method = RequestMethod.GET)
    public Map<String,Object> list(){
        Map<String, Object> resMap = Maps.newHashMap();
        resMap.put("code","0");
        resMap.put("msg","成功");

        //TODO: 调用服务提供方dubboExample提供的列表查询功能
        try{
            BaseResponse response = dubboItemService.listItems();
            if (response!=null && response.getCode().equals(0)){
                resMap.put("data",response.getData());
            }
        }catch (Exception e){
            e.printStackTrace();
            resMap.put("code","-1");
            resMap.put("msg","失败");
        }
        return resMap;
    }
}
