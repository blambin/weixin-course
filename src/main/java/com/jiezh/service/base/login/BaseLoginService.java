package com.jiezh.service.base.login;

import com.jiezh.dao.base.cache.CacheDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("base.user.BaseLoginService")
public class BaseLoginService {

    @Autowired
    CacheDao cacheDao;

}
