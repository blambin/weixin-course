package com.jiezh.pub.web;

import java.io.Serializable;

/**
 * 普通Ajax请求返回结果
 * @author 杨彭伟
 * @version V1.0 2016年03月04日 13:44
 * @className com.jiezh.pub.web
 */
public class JsonResult implements Serializable {
    private static final long serialVersionUID = 3189049032220974879L;
    public static final String SUCCESS = "操作成功";
    public static final String FAIL = "操作失败";

    private int code;
    private String msg;
    private Object data;

    /**
     * 普通Ajax请求返回结果，总构造器
     * @param code 状态码
     * @param msg 消息
     * @param data 数据
     */
    public JsonResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 普通Ajax请求返回结果，无返回数据构造器
     * @param code 状态码
     * @param msg 消息
     */
    public JsonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    /**
     * 普通Ajax请求返回结果，请求成功构造器
     */
    public JsonResult() {
        code = 1;
        msg = SUCCESS;
        data = null;
    }

    public JsonResult(boolean flag) {
        data = null;
        if (flag){
            code = 1;
            msg = SUCCESS;
        } else {
            code = 0;
            msg = FAIL;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
