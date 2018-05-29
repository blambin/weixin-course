package com.jiezh.dao.weixin;

import com.github.pagehelper.Page;
import com.jiezh.entity.Base;

public interface BaseMapper {

    int updateEntity(Base base);

    Page<Base> queryBaseList(Base base);

    int insert(Base base);

    Base queryBaseById(Integer id);

    int deleteByPrimaryKey(Integer id);

    Base queryBaseByType(String name);
}