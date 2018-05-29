package com.jiezh.controller.weixin;

import com.jiezh.entity.Course;
import com.jiezh.pub.Env;
import com.jiezh.pub.util.UploadUtil;
import com.jiezh.pub.web.WebAction;
import com.jiezh.service.weixin.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

/**
 * 课程控制类
 *
 * @author yclimb
 * @date 2018/4/30
 */
@Controller
@Scope("prototype")
@RequestMapping("/weixin/course")
public class CourseController extends WebAction {

    @Autowired
    private CourseService courseService;

    /**
     * 进入课程列表
     * @return
     */
    @RequestMapping("/queryCourseList")
    public String queryCourseList() {

        Course course = (Course) getBean(Course.class);

        int currenPage = 1;
        if (request.getParameter("currenPage") != null && !"".equals(request.getParameter("currenPage"))) {
            currenPage = Integer.parseInt(request.getParameter("currenPage"));
        }

        model.addAttribute("page", courseService.queryCoursePage(currenPage, Env.PAGE_SIZE, course));
        model.addAttribute("findObj", course);
        model.addAttribute("msg", request.getParameter("msg") == null ? request.getAttribute("msg") : request.getParameter("msg"));

        return "weixin/course/course_list";
    }

    /**
     * 进入新增页面
     * @return
     */
    @RequestMapping("/addCourseHtml")
    public ModelAndView addCourseHtml() {
        return new ModelAndView("weixin/course/course_add");
    }

    /**
     * 保存或者修改课程
     * @return
     */
    @RequestMapping("/modifyCourse")
    public String modifyCourse() {

        Course course = (Course) getBean(Course.class);

        try {

            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            MultipartFile logoUrlFile = multiRequest.getFile("logoUrl");
            if (logoUrlFile != null && logoUrlFile.getSize() > 0) {
                Map<String, String> logoUrl = UploadUtil.upload(logoUrlFile, Env.ROOT);
                course.setLogoUrl(logoUrl.get("filePath"));
            }

            MultipartFile urlFile = multiRequest.getFile("url");
            if (urlFile != null && urlFile.getSize() > 0) {
                Map<String, String> url = UploadUtil.upload(urlFile, Env.ROOT);
                course.setUrl(url.get("filePath"));
            }

            int result = courseService.modifyCourse(course);
            if (result > 0) {
                redirectAttributes.addAttribute("msg", "提交成功");
            } else {
                redirectAttributes.addAttribute("msg", "提交失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return queryCourseList();
    }

    /**
     * 查看课程信息
     * @param id
     * @return
     */
    @RequestMapping("/queryCourseByIdHtml")
    public String queryCourseByIdHtml(Integer id) {

        model.addAttribute("findObj", courseService.queryCourseById(id));

        return "weixin/course/course_show";
    }

    /**
     * 修改课程信息
     * @param id
     * @return
     */
    @RequestMapping("/modifyCourseByIdHtml")
    public String modifyCourseById(Integer id) {

        model.addAttribute("findObj", courseService.queryCourseById(id));

        return "weixin/course/course_add";
    }

    /**
     * 删除课程
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delCourse")
    public Map<String, Object> delCourse(Integer id) {

        if (id == null || id <= 0) {
            return fail("没有课程编号", null);
        }

        int result = courseService.delCourseById(id);
        if (result <= 0) {
            return fail("删除失败", null);
        }

        return success("删除成功", null);
    }


}
