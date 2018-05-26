package com.jiezh.dao.weixin;

import com.github.pagehelper.Page;
import com.jiezh.entity.WeixinOrders;

import java.util.List;
import java.util.Map;

public interface WeixinOrdersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeixinOrders record);

    WeixinOrders selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeixinOrders record);

    int updateWeixinOrdersByNo(WeixinOrders weixinOrders);

    WeixinOrders queryOrderByIdAndUserId(Map<String, String> map);

    Page<WeixinOrders> queryOrderList(WeixinOrders weixinOrders);

    List<WeixinOrders> queryOrderListByUserId(int userId);

    List<WeixinOrders> queryFeeOrderListByUserId(int userId);
}