package com.jiezh.pub.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * easy ui分页Ajax请求返回结果
 * @author 杨彭伟
 * @version V1.0 2016年03月04日 14:06
 * @className com.jiezh.pub.web
 */
public class PageResult implements Serializable {
    private static final long serialVersionUID = 2693599598164620482L;

    private Integer code;
    private Integer total;
    private List rows;

    /**
     * easy ui分页Ajax请求返回结果，总构造器
     * @param code 状态码
     * @param total 总记录数
     * @param rows 分页数据
     */
    public PageResult(Integer code, Integer total, List rows) {
        this.code = code;
        this.total = total;
        this.rows = rows;
    }

    /**
     * easy ui分页Ajax请求返回结果，无数据构造器
     */
    public PageResult() {
        this.code = 1;
        this.total = 0;
        this.rows = new ArrayList();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
