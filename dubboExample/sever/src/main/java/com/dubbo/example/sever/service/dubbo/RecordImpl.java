package com.dubbo.example.sever.service.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.example.api.enums.StatusCode;
import com.dubbo.example.api.request.PushOrderDto;
import com.dubbo.example.api.response.BaseResponse;
import com.dubbo.example.api.service.RecordService;
import com.dubbo.example.model.entity.ItemInfo;
import com.dubbo.example.model.entity.OrderRecord;
import com.dubbo.example.model.mapper.ItemInfoMapper;
import com.dubbo.example.model.mapper.OrderRecordMapper;
import com.google.common.base.Strings;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: Qr
 * @create: 2021-02-22 19:01
 **/
@Service(protocol = {"dubbo","rest"},validation = "true",version = "1.0",timeout = 30000)
@Path("record")
public class RecordImpl implements RecordService {

    private static final Logger log = LoggerFactory.getLogger(RecordImpl.class);

    @Autowired
    private ItemInfoMapper itemInfoMapper;

    @Autowired
    private OrderRecordMapper orderRecordMapper;

    /**
     * 下单服务实际实现
     * @param dto
     */
    @Override
    @Path("push")
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public BaseResponse pushOrder(PushOrderDto dto) {
        if (dto.getItemId()==null || dto.getItemId()<=0 || Strings.isNullOrEmpty(dto.getCustomerName())
            || dto.getTotal()==null){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try{
            //校验商品信息是否存在
            ItemInfo item = itemInfoMapper.selectByPrimaryKey(dto.getItemId());
            if (item == null){
                return new BaseResponse(StatusCode.ItemNotExist);
            }
            OrderRecord record = new OrderRecord();
            BeanUtils.copyProperties(dto,record);
            record.setOrderTime(new Date());
            orderRecordMapper.insertSelective(record);
            response.setData(record.getId());
            log.info(record.toString());
        }catch (Exception e){
            e.printStackTrace();
            response = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
}
