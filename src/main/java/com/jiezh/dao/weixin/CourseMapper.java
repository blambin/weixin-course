package com.jiezh.dao.weixin;

import com.github.pagehelper.Page;
import com.jiezh.entity.Course;

public interface CourseMapper {

    /**
     * 查询课程列表
     * @param course
     * @return
     */
    Page<Course> queryCourseList(Course course);

    int updateEntity(Course course);

    int insert(Course course);

    Course queryCourseById(int id);

    Page<Course> queryCoursePageByUserId(int userId);

    int deleteByPrimaryKey(int id);
}