package com.jiezh.pub;

/**
 * 系统变量
 * 
 * @author liangds
 *
 */
public class Env {

    public static final String WEB_ROOT = "/course";

    public static final String ROOT = "course";

    public static String getWebRoot() {
        return WEB_ROOT;
    }

    /**
     * 分页查询列表显示条数
     */
    public static final int PAGE_SIZE = 10;

    public static final String SUCCESS = "SUCCESS";

    public static final String UPLOAD_CODE_IMG = "img";
    public static final String UPLOAD_CODE_FILE = "file";

    /**
     * 图片类型位置:0:默认、1：焦点图、2:推荐
     */
    public static final String WEIXIN_COURSE_TYPE_LOCATION_0 = "0";
    public static final String WEIXIN_COURSE_TYPE_LOCATION_1 = "1";
    public static final String WEIXIN_COURSE_TYPE_LOCATION_2 = "2";

    /**
     * 订单状态：1：预支付；2：支付中；3：已付款；4：完结
     */
    public static final String WEIXIN_ORDERS_STATUS_1 = "1";
    public static final String WEIXIN_ORDERS_STATUS_2 = "2";
    public static final String WEIXIN_ORDERS_STATUS_3 = "3";
    public static final String WEIXIN_ORDERS_STATUS_4 = "4";

    /**
     * 是否免费:1：免费；2：收费
     */
    public static final String WEIXIN_COURSE_IS_FREE_1 = "1";
    public static final String WEIXIN_COURSE_IS_FREE_2 = "2";

    /**
     * 课程类型：1:视频；2:音频；3:文字；4:会员
     */
    public static final String WEIXIN_COURSE_TYPE_1 = "1";
    public static final String WEIXIN_COURSE_TYPE_2 = "2";
    public static final String WEIXIN_COURSE_TYPE_3 = "3";
    public static final String WEIXIN_COURSE_TYPE_4 = "4";

    /**
     * 是否会员：1是，2:否
     */
    public static final String WEIXIN_USER_IS_VIP_1 = "1";
    public static final String WEIXIN_USER_IS_VIP_2 = "2";

    /**
     * 默认拉新奖励金额 - 佣金比例
     */
    public static final String WEIXIN_COMMISSION_PROPORTION = "commission_proportion";

    /**
     * 默认提现限制金额
     */
    public static final String WEIXIN_DEFAULT_PRICE = "default_price";

    /**
     * 会员课程编码
     */
    public static final String WEIXIN_VIP_ID = "vip_id";

    public static void main(String[] args) {

        Double s1 = Double.valueOf("0.01") * 100;


        // String s2 = s1.substring(0, s1.indexOf(".")) + s1.substring(s1.indexOf(".")+1);


        System.out.println(s1.intValue());

        // System.out.println(new Date().getTime());
    }


}
