package com.dubbo.example.api.request;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: Qr
 * @create: 2021-02-22 17:59
 **/
@Data
@ToString
public class PushOrderDto implements Serializable {
    //商品ID
    private Integer itemId;

    //商品下单数量
    private Integer total;

    //客户姓名
    private String customerName;

}
