package com.jiezh.service.weixin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiezh.dao.weixin.BaseMapper;
import com.jiezh.entity.Base;
import com.jiezh.pub.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("com.jiezh.service.weixin.baseService")
public class BaseService {

    @Autowired
    BaseMapper baseMapper;

    public PageInfo<Base> queryBasePage(int pageNum, Base user) {
        PageHelper.startPage(pageNum, Env.PAGE_SIZE);
        Page<Base> page = baseMapper.queryBaseList(user);

        if (page == null) {
            page = new Page<>();
        }
        return new PageInfo<>(page);

    }

    public int modifyBase(Base base) {
        if (base == null) {
            return 0;
        }
        if (base.getId() != null) {
            return baseMapper.updateEntity(base);
        } else {
            return baseMapper.insert(base);
        }
    }

    public Base queryBaseById(Integer id) {
        return baseMapper.queryBaseById(id);
    }

    public int delBaseById(Integer id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    public Base queryBaseByType(String type) {
        return baseMapper.queryBaseByType(type);
    }
}
