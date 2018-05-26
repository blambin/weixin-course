package com.jiezh.entity;

import com.jiezh.pub.web.GeneralBean;

/**
 * 用户课程关联关系
 *
 * @author 系统自动生成
 * @version 1.0 2018-04-30
 */
public class UserSourse extends GeneralBean {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户编号
     */
    private int userId;
    /**
     * 课程编号
     */
    private int courseId;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}