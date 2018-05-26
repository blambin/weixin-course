package com.jiezh.service.weixin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiezh.dao.weixin.CourseMapper;
import com.jiezh.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("com.jiezh.service.weixin.courseService")
public class CourseService {

    @Autowired
    CourseMapper courseMapper;

    public PageInfo<Course> queryCoursePage(int pageNum, int pageSize, Course course) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Course> page = courseMapper.queryCourseList(course);

        if (page == null) {
            page = new Page<>();
        }
        return new PageInfo<>(page);

    }

    public PageInfo<Course> queryCoursePageByUserId(int pageNum, int pageSize, int userId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Course> page = courseMapper.queryCoursePageByUserId(userId);

        if (page == null) {
            page = new Page<>();
        }
        return new PageInfo<>(page);

    }

    public int modifyCourse(Course course) {
        if (course == null) {
            return 0;
        }
        if (course.getId() != null) {
            course.setUpdateTime(new Date());
            course.setUpdateUser(1);
            return courseMapper.updateEntity(course);
        } else {
            course.setCreateTime(new Date());
            course.setCreateUser(1);
            return courseMapper.insert(course);
        }
    }

    public Course queryCourseById(Integer id) {
        return courseMapper.queryCourseById(id);
    }

    public Integer delCourseById(Integer id) {
        return courseMapper.deleteByPrimaryKey(id);
    }
}
