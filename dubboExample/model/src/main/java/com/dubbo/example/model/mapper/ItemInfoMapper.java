package com.dubbo.example.model.mapper;

import com.dubbo.example.model.entity.ItemInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author Qr
 */
public interface ItemInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemInfo record);

    int insertSelective(ItemInfo record);

    ItemInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemInfo record);

    int updateByPrimaryKey(ItemInfo record);

    List<ItemInfo> selectAll();

    List<ItemInfo> selectAllByParams(@Param("search") String search);
}














