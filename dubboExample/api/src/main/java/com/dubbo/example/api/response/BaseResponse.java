package com.dubbo.example.api.response;

import com.dubbo.example.api.enums.StatusCode;
import java.io.Serializable;

/**
 * @description: 处理请求的基本类
 *               仿照HTTP的请求响应模型
 * @author: Qr
 * @create: 2021-02-19 15:59
 **/

public class BaseResponse<T> implements Serializable {

    /**
     *  code:状态码
     *  msg:消息
     *  data:数据
     */
    private Integer code;
    private String msg;
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(StatusCode statusCode){
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
