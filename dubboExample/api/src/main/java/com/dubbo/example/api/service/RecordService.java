package com.dubbo.example.api.service;

import com.dubbo.example.api.request.PushOrderDto;
import com.dubbo.example.api.response.BaseResponse;

/**
 * @description:
 * @author: Qr
 * @create: 2021-02-22 17:55
 **/
public interface RecordService {
    public BaseResponse pushOrder(PushOrderDto dto);

}
