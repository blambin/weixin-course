package com.jiezh.controller.weixin;

import com.github.pagehelper.PageInfo;
import com.jiezh.entity.*;
import com.jiezh.pub.Env;
import com.jiezh.pub.util.StringUtil;
import com.jiezh.pub.web.WebAction;
import com.jiezh.service.weixin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 微信
 *
 * @author yclimb
 * @date 2018/5/1
 */
@Controller
@Scope("prototype")
@RequestMapping("/weixin/h5")
public class WeixinController extends WebAction {

    @Autowired
    private CourseService courseService;

    @Autowired
    private WeixinOrdersService weixinOrdersService;

    @Autowired
    private WeixinUserService weixinUserService;

    @Autowired
    private UserPromoterService userPromoterService;

    @Autowired
    private UserPromoterLogService userPromoterLogService;

    @Autowired
    private BaseService baseService;

    /**
     * 首页
     */
    @RequestMapping("/vipPay")
    public String vipPay() {

        // 获取微信签名
        getWeixinMap();

        Base base = baseService.queryBaseByType(Env.WEIXIN_VIP_ID);
        if (base != null) {
            Course course = courseService.queryCourseById(Integer.valueOf(base.getValue()));
            model.addAttribute("info", course);
        }

        // 最新
        Course typeLocation0 = new Course();
        typeLocation0.setTypeLocation(Env.WEIXIN_COURSE_TYPE_LOCATION_0);
        List<Course> list0 = courseService.queryCoursePage(1, Env.PAGE_SIZE, typeLocation0).getList();
        model.addAttribute("list0", list0);

        return "weixin/h5/h5_vip_pay";
    }

    /**
     * 首页
     */
    @RequestMapping("/main")
    public String main() {

        // 获取微信签名
        getWeixinMap();

        // 推广id，用于判断是否是某个用户的拉新用户
        String promoter_id = request.getParameter("promoter_id");
        if (!StringUtil.isBlank(promoter_id)) {
            request.getSession().setAttribute("promoter_id", promoter_id);
        }

        // 当前微信授权登陆用户
        WeixinUser user = (WeixinUser) request.getSession().getAttribute("weixinUser");
        if (user == null) {
            return "weixin/h5/h5_main";
        }

        // 如果不是会员，跳转到会员支付页面
        if (!Env.WEIXIN_USER_IS_VIP_1.equals(user.getIsVip())) {
            // 双重检查，购买会员可能有延迟，所以需要重新检查数据库
            user = weixinUserService.queryWeixinUserById(user.getId());
            if (Env.WEIXIN_USER_IS_VIP_1.equals(user.getIsVip())) {
                // 如果是会员，则放入session
                request.getSession().setAttribute("weixinUser", user);
            } else {
                return "redirect:/weixin/h5/vipPay.do";
            }
        }

        // 焦点图
        Course typeLocation1 = new Course();
        typeLocation1.setTypeLocation(Env.WEIXIN_COURSE_TYPE_LOCATION_1);
        List<Course> list1 = courseService.queryCoursePage(1, 4, typeLocation1).getList();
        model.addAttribute("list1", list1);

        // 推荐
        Course typeLocation2 = new Course();
        typeLocation2.setTypeLocation(Env.WEIXIN_COURSE_TYPE_LOCATION_2);
        List<Course> list2 = courseService.queryCoursePage(1, 4, typeLocation2).getList();
        model.addAttribute("list2", list2);

        // 最新
        Course typeLocation0 = new Course();
        typeLocation0.setTypeLocation(Env.WEIXIN_COURSE_TYPE_LOCATION_0);
        List<Course> list0 = courseService.queryCoursePage(1, Env.PAGE_SIZE, typeLocation0).getList();
        model.addAttribute("list0", list0);

        return "weixin/h5/h5_main";
    }

    /**
     * 课程列表(json)
     */
    @RequestMapping("queryCourseListJson")
    @ResponseBody
    public PageInfo<Course> queryCourseListJson() {

        Course course = (Course) this.getBean(Course.class);

        int currenPage = 1;
        if (request.getParameter("currenPage") != null && !"".equals(request.getParameter("currenPage"))) {
            currenPage = Integer.parseInt(request.getParameter("currenPage"));
        }

        return courseService.queryCoursePage(currenPage, 5, course);
    }

    /**
     * 进入课程列表
     */
    @RequestMapping("/queryCourseList")
    public String queryCourseList() {

        // 获取微信签名
        getWeixinMap();

        // 搜索框
        model.addAttribute("name", request.getParameter("name"));

        return "weixin/h5/h5_course_list";
    }

    /**
     * 课程详情
     */
    @RequestMapping("/queryCourseDetail")
    public String queryCourseDetail() {

        // 获取微信签名
        getWeixinMap();

        String id = request.getParameter("id");
        Course course = courseService.queryCourseById(Integer.valueOf(id));
        model.addAttribute("info", course);

        // 当前微信授权登陆用户
        WeixinUser user = (WeixinUser) request.getSession().getAttribute("weixinUser");
        if (user != null) {
            // 如果用户已经买了这个课程，可以直接查看，不需要二次购买
            WeixinOrders orders = weixinOrdersService.queryOrderByIdAndUserId(course.getId(), user.getId(), Env.WEIXIN_ORDERS_STATUS_3);
            if (orders != null) {
                return "weixin/h5/h5_course_player";
            }
        }

        return "weixin/h5/h5_course_detail";
    }

    /**
     * 课程播放页面
     */
    @RequestMapping("/queryCoursePlayer")
    public String queryCoursePlayer() {

        String view = "weixin/h5/h5_course_player";

        // 获取微信签名
        getWeixinMap();

        String id = request.getParameter("id");
        Course course = courseService.queryCourseById(Integer.valueOf(id));
        model.addAttribute("info", course);

        return view;
    }

    /**
     * 用户课程列表
     */
    @RequestMapping("/toUserCourseHtml")
    public String toUserCourseHtml() {

        // 获取微信签名
        getWeixinMap();

        return "weixin/h5/h5_user_course";
    }

    /**
     * 用户课程JSON
     */
    @RequestMapping("/queryCourseListJsonByUserId")
    @ResponseBody
    public PageInfo<Course> queryCourseListJsonByUserId() {

        int currenPage = 1;
        if (request.getParameter("currenPage") != null && !"".equals(request.getParameter("currenPage"))) {
            currenPage = Integer.parseInt(request.getParameter("currenPage"));
        }

        WeixinUser user = (WeixinUser) request.getSession().getAttribute("weixinUser");

        return courseService.queryCoursePageByUserId(currenPage, Env.PAGE_SIZE, user.getId());
    }

    /**
     * 个人中心
     */
    @RequestMapping("/toUserHtml")
    public String toUserHtml() {

        // 获取微信签名
        getWeixinMap();

        Base base = baseService.queryBaseByType(Env.WEIXIN_DEFAULT_PRICE);
        model.addAttribute("base", base);

        // 个人信息，每次都去拉取数据库的最新数据
        WeixinUser user = (WeixinUser) request.getSession().getAttribute("weixinUser");
        WeixinUser weixinUser = weixinUserService.queryWeixinUserById(user.getId());
        // 金额必须每次取最新的数据
        user.setPromoterMoney(weixinUser.getPromoterMoney());
        // 放入session
        request.getSession().setAttribute("weixinUser", user);

        return "weixin/h5/h5_user";
    }

    /**
     * 订单列表(json)
     */
    @RequestMapping("queryOrderListJson")
    @ResponseBody
    public PageInfo<WeixinOrders> queryOrderListJson() {

        WeixinOrders weixinOrders = (WeixinOrders) this.getBean(WeixinOrders.class);
        WeixinUser user = (WeixinUser) request.getSession().getAttribute("weixinUser");
        weixinOrders.setUserId(user.getId());

        int currenPage = 1;
        if (request.getParameter("currenPage") != null && !"".equals(request.getParameter("currenPage"))) {
            currenPage = Integer.parseInt(request.getParameter("currenPage"));
        }

        return weixinOrdersService.queryOrderPage(currenPage, 5, weixinOrders);
    }

    /**
     * 分销用户收支详情列表(json)
     */
    @RequestMapping("queryPromoterLogListJson")
    @ResponseBody
    public PageInfo<UserPromoterLog> queryPromoterLogListJson() {

        UserPromoterLog userPromoterLog = new UserPromoterLog();
        WeixinUser user = (WeixinUser) request.getSession().getAttribute("weixinUser");
        userPromoterLog.setPromoterId(user.getId());

        int currenPage = 1;
        if (request.getParameter("currenPage") != null && !"".equals(request.getParameter("currenPage"))) {
            currenPage = Integer.parseInt(request.getParameter("currenPage"));
        }

        return userPromoterLogService.queryPromoterLogPage(currenPage, Env.PAGE_SIZE, userPromoterLog);
    }
}
