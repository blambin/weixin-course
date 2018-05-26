package com.jiezh.service.weixin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiezh.dao.weixin.WeixinOrdersMapper;
import com.jiezh.entity.WeixinOrders;
import com.jiezh.pub.Env;
import com.jiezh.pub.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("com.jiezh.service.weixin.weixinOrdersService")
public class WeixinOrdersService {

    @Autowired
    WeixinOrdersMapper weixinOrdersMapper;

    public int modifyWeixinOrders(WeixinOrders weixinOrders) {
        if (weixinOrders == null) {
            return 0;
        }
        if (weixinOrders.getId() != null) {
            return weixinOrdersMapper.updateByPrimaryKeySelective(weixinOrders);
        } else {
            weixinOrders.setCreateDate(new Date());
            weixinOrders.setCreateUser(1);
            if (StringUtil.isBlank(weixinOrders.getStatus())) {
                weixinOrders.setStatus(Env.WEIXIN_ORDERS_STATUS_1);
            }
            return weixinOrdersMapper.insert(weixinOrders);
        }
    }


    public int modifyWeixinOrdersByNo(WeixinOrders weixinOrders) {
        if (weixinOrders == null) {
            return 0;
        }
        if (weixinOrders.getOutTradeNo() != null) {
            return weixinOrdersMapper.updateWeixinOrdersByNo(weixinOrders);
        }

        return 0;
    }

    public WeixinOrders queryOrderByIdAndUserId(int courseId, Integer userId) {
        Map<String, String> map = new HashMap<>(2);
        map.put("courseId", String.valueOf(courseId));
        map.put("userId", String.valueOf(userId));
        return weixinOrdersMapper.queryOrderByIdAndUserId(map);
    }

    public PageInfo<WeixinOrders> queryOrderPage(int currenPage, int pageSize, WeixinOrders weixinOrders) {
        PageHelper.startPage(currenPage, pageSize);
        Page<WeixinOrders> page = weixinOrdersMapper.queryOrderList(weixinOrders);

        if (page == null) {
            page = new Page<>();
        }
        return new PageInfo<>(page);
    }

    public List<WeixinOrders> queryOrderListByUserId(int userId) {
        return weixinOrdersMapper.queryOrderListByUserId(userId);
    }

    public List<WeixinOrders> queryFeeOrderListByUserId(int userId) {
        return weixinOrdersMapper.queryFeeOrderListByUserId(userId);
    }
}
