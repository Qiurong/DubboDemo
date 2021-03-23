package com.dubbo.example.sever.controller;


import com.dubbo.example.api.enums.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dubbo.example.api.response.BaseResponse;

/**
 * @description:
 * @author: Qr
 * @create: 2021-02-19 16:28
 **/
@RestController
public class BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    private static final String prefix = "base";

    /**
     * for test purpose
     * 访问URL: localhost:8093/dubboExample/base/test?param=
     *          IP:端口号/项目设置的servlet.context-path/prefix/test?参数
     * @param param
     * @return
     */
    @RequestMapping(value = prefix+"/test",method = RequestMethod.GET)
    public BaseResponse test(@RequestParam String param){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try{
            //把传来的param直接放入到基本相应模型的data中
            response.setData(param);
        }catch (Exception e){
            e.printStackTrace();
            response = new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

}
