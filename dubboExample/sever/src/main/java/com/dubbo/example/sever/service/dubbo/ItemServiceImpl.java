package com.dubbo.example.sever.service.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.example.api.enums.StatusCode;
import com.dubbo.example.api.response.BaseResponse;
import com.dubbo.example.api.service.ItemService;
import com.dubbo.example.model.entity.ItemInfo;
import com.dubbo.example.model.mapper.ItemInfoMapper;
import java.util.List;
import javax.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: Qr
 * @create: 2021-02-21 21:33
 **/

@Service(protocol = {"dubbo","rest"},validation = "true",version = "1.0",timeout = 3000)
@Path("dubboExample")
public class ItemServiceImpl implements ItemService {

    private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemInfoMapper itemInfoMapper;


    /**
     * 列表查询服务接口的实际实现逻辑
     * @return
     */
    @Override
    @Path("item/list")
    public BaseResponse listItems() {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            List<ItemInfo> infos = itemInfoMapper.selectAll();
            log.info("查询到的商品列表信息: ()",infos);
            response.setData(infos);
        }catch (Exception e){
            log.error("列表查询服务接口实现类发生异常", e.fillInStackTrace());
            response = new BaseResponse(StatusCode.Fail);
        }

        return response;
    }
}
