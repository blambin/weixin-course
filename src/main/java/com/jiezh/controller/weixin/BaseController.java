package com.jiezh.controller.weixin;

import com.jiezh.entity.Base;
import com.jiezh.pub.web.WebAction;
import com.jiezh.service.weixin.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 数据控制类
 *
 * @author yclimb
 * @date 2018/4/30
 */
@Controller
@Scope("prototype")
@RequestMapping("/weixin/base")
public class BaseController extends WebAction {

    @Autowired
    private BaseService baseService;

    @RequestMapping("/queryBaseList")
    public String queryBaseList() {

        Base bean = (Base) getBean(Base.class);

        int currenPage = 1;
        if (request.getParameter("currenPage") != null && !"".equals(request.getParameter("currenPage"))) {
            currenPage = Integer.parseInt(request.getParameter("currenPage"));
        }

        model.addAttribute("page", baseService.queryBasePage(currenPage, bean));
        model.addAttribute("findObj", bean);

        return "weixin/base/base_list";
    }


    /**
     * 进入新增页面
     */
    @RequestMapping("/addBaseHtml")
    public ModelAndView addBaseHtml() {
        return new ModelAndView("weixin/base/base_add");
    }

    /**
     * 保存或者修改数据
     */
    @RequestMapping("/modifyBase")
    public String modifyBase() {

        Base base = (Base) getBean(Base.class);

        int result = baseService.modifyBase(base);
        if (result > 0) {
            redirectAttributes.addAttribute("msg", "提交成功");
        } else {
            redirectAttributes.addAttribute("msg", "提交失败");
        }

        return queryBaseList();
    }

    /**
     * 修改信息
     * @param id id
     */
    @RequestMapping("/modifyBaseByIdHtml")
    public String modifyCourseById(Integer id) {

        model.addAttribute("findObj", baseService.queryBaseById(id));

        return "weixin/base/base_add";
    }

    /**
     * 删除数据配置
     */
    @ResponseBody
    @RequestMapping("/delBase")
    public Map<String, Object> delCourse(Integer id) {

        if (id == null || id <= 0) {
            return fail("没有数据配置编号", null);
        }

        int result = baseService.delBaseById(id);
        if (result <= 0) {
            return fail("删除失败", null);
        }

        return success("删除成功", null);
    }

}
