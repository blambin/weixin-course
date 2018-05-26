package com.jiezh.dao.base.cache;

import com.jiezh.controller.base.login.GroupBean;
import com.jiezh.controller.base.login.ModelBean;

import java.util.List;
import java.util.Map;

/**
 * 利用mybatis缓存
 * 
 * @author liangds
 *
 */
public interface CacheDao {

    public List<GroupBean> getModuleGroup(long userId);

    public List<ModelBean> getModuleUrl(long groupId);

    public List<GroupBean> getModuleGroupAll();

    // 标准的码表值
    public List<Map<String, String>> getOptions(Map<String, String> map);

    // 自定义的 通过sql去查询
    public String getCustomSql(Map<String, String> map);

    public List<Map<String, Object>> getCustomOptions(Map<String, String> map);

    // 获取机构信息
    public List<Map<String, String>> getOrganInfo(Map<String, String> map);

    public Map<String, String> getOrganByOrganId(Map<String, String> map);

    public List<Map<String, String>> getOrganLevel(Map<String, String> map);

    // 获取用户角色
    public List<String> getUserRole(long userId);
    
    // 获取用户本机构和监管机构数据权限 addby btdong2017年3月20日17:25:07
    public List<String> getOrgCompanyList(long userId);
    
    // 获取用户主数据数据权限
    public List<String> getUserDataPermission(long userId);
    
    //
    public String selectOrganChildList(String organId);
}
