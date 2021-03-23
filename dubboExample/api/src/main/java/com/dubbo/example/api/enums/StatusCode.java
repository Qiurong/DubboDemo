package com.dubbo.example.api.enums;

/**
 * @description: 状态码的枚举集合
 * @author: Qr
 * @create: 2021-02-19 16:05
 **/
public enum StatusCode {
    /**
     * StatusCode总共四种值：Success,Fail和InvalidParams
     */
    Success(0, "success"),
    Fail(-1,"failure"),
    InvalidParams(200,"invalid"),
    ItemNotExist(201,"ItemNotExist");


    private Integer code;
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
