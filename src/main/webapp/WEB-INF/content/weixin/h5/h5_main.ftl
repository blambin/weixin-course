<!DOCTYPE html>
<#include "/weixin/include/taglibs.ftl"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="format-detection" content="telephone=no">
    <title>首页 - 微课堂</title>
    <script src="${contextPath!}/res/weixin/js/weiketang/jquery.min.js?v=2.3.3"></script>
    <link rel="stylesheet" href="${contextPath!}/res/weixin/js/weiketang/weui.min.css?v=2.3.3" />
    <link rel="stylesheet" href="${contextPath!}/res/weixin/js/weiketang/font-awesome.min.css?v=2.3.3"/>
    <link rel="stylesheet" href="${contextPath!}/res/weixin/js/weiketang/index.css?v=2.3.3"/>
</head>
<body>
<script type="text/javascript">

    // 用户授权登陆
    getBaseInfo('${(weixinUser.id)}' ,'main.do');

</script>
<div class="tabbar tabbar_wrap page_wrap">
    <div class="weui_tab">
        <style>
            .weui_tab_bd{height: auto; padding-bottom: 0;}
        </style>

        <div class="weui_tab_bd" id="weui_tab_div">
            <!-- 搜索框 -->
            <div class="index-header-search">
                <div class="u-search">
                    <i class="fa fa-search"></i>
                    <input type="text" id="searchInput" class="search_input z-abled" value="" autocorrect="off" placeholder="输入课程名称进行查找">
                </div>
            </div>
            <!-- /搜索框 -->

            <!-- 广告轮播图 -->
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <!--图片一-->
                    <#list list1 as info>
                    <div class="swiper-slide">
                        <a href="${contextPath!}/weixin/h5/queryCourseDetail.do?1=1&id=${info.id!}">
                            <img class="swiper-lazy" data-src="${access_root_path}${info.logoUrl!}">
                        </a>
                    </div>
                    </#list>
                    <!--图片一end-->
                </div>
                <div class="swiper-pagination"></div>
            </div>
            <!-- /广告轮播图 -->

            <!-- 公告 -->
            <!-- /公告 -->

            <!-- 最新课程 -->
            <!-- /最新课程 -->

            <!-- 课程板块遍历 -->
            <div class="course_wrap mt10">
                <h2 class="course_hd"><span class="bor-l"></span>推荐</h2>

                <!-- 初始化十条记录 -->
                <!--style="border-radius: 15px;overflow: hidden;"-->
                <ul class="course_main course_other">
                <#list list2 as info>
                    <li class="course_item">
                        <a href="${contextPath!}/weixin/h5/queryCourseDetail.do?1=1&id=${info.id!}">
                            <div class="course_pic">
                                <img class="lazy" src="${access_root_path}${info.logoUrl!}" alt="" />
                                <p class="course_living">${info.name!}</p>
                            </div>
                            <p>
                                <span class="fl "><#if info.isFree == '1'>免费<#else>收费</#if></span>
                                <span class="fr">价格：<i class="red-color">${(info.price)!''}</i>元</span>
                            </p>
                            <p>
                                <span class="fl">课程类型：</span>                        <span class="fr">
                                    <i class="blue-color">
                                    <#switch info.type>
                                        <#case '1'>视频<#break>
                                        <#case '2'>音频<#break>
                                        <#case '3'>文字<#break>
                                    </#switch>
                                    </i>
                                </span>
                            </p>
                        </a>
                    </li>
                </#list>
                </ul>

            </div>
            <div class="course_wrap mt10">
                <h2 class="course_hd"><span class="bor-l"></span>最新课程</h2>
                <ul class="course_main course_other">
                    <#list list0 as info>
                        <li class="course_item">
                            <a href="${contextPath!}/weixin/h5/queryCourseDetail.do?1=1&id=${info.id!}">
                                <div class="course_pic">
                                    <img class="lazy" src="${access_root_path}${info.logoUrl!}" alt="" />
                                    <p class="course_living">${info.name!}</p>
                                </div>
                                <p>
                                    <span class="fl "><#if info.isFree == '1'>免费<#else>收费</#if></span>
                                    <span class="fr">价格：<i class="red-color">${(info.price)!''}</i>元</span>
                                </p>
                                <p>
                                    <span class="fl">课程类型：</span>                        <span class="fr">
                                        <i class="blue-color">
                                        <#switch info.type>
                                            <#case '1'>视频<#break>
                                            <#case '2'>音频<#break>
                                            <#case '3'>文字<#break>
                                        </#switch>
                                        </i>
                                    </span>
                                </p>
                            </a>
                        </li>
                    </#list>
                </ul>
            </div>
            <!-- /课程板块遍历 -->

        </div>

        <!-- 绑定手机号码 -->
        <!-- /绑定手机号码 -->

        <footer>
            <a href="#"></a>
        </footer>

        <div id="spinners" style="display:none;"><div class="spinner"><div class="bounce1"></div><div class="bounce2"></div><div class="bounce3"></div></div></div>

        <script type="text/javascript">

        var search = function() {
            var keywords = $.trim($("#searchInput").val());
            if (keywords == '') {
                searchUrl = BASE_PATH + "/weixin/h5/queryCourseList.do?1=1";
            } else {
                searchUrl = BASE_PATH + '/weixin/h5/queryCourseList.do?1=1&name=' + encodeURIComponent(keywords);
            }
            document.location.href = searchUrl;
            return false;
        };
        $("#searchInput").keydown(function(event) {
            if (event.keyCode == 13) {
                search();
            }
        });
        $("#search_btn").on("click", function(){
            search();
        });
    </script>

        <!-- 底部导航 -->
        <div class="weui_tabbar ">

            <a href="javascript:window.location.reload();" class="weui_tabbar_item weui_bar_item_on">
                <div class="weui_tabbar_icon">
                    <i class="fa fa-home menu_on" style="font-size: 24px;"></i>
                </div>
                <p class="weui_tabbar_label">首 页</p>
            </a>

            <a href="${contextPath!}/weixin/h5/queryCourseList.do" class="weui_tabbar_item ">
                <div class="weui_tabbar_icon">
                    <i class="fa fa-video-camera"></i>
                </div>
                <p class="weui_tabbar_label">全部课程</p>
            </a>


            <a href="${contextPath!}/weixin/h5/toUserCourseHtml.do" class="weui_tabbar_item ">
                <div class="weui_tabbar_icon">
                    <i class="fa fa-book"></i>
                </div>
                <p class="weui_tabbar_label">我的课程</p>
            </a>

            <a href="${contextPath!}/weixin/h5/toUserHtml.do" class="weui_tabbar_item ">
                <div class="weui_tabbar_icon">
                    <i class="fa fa-user"></i>
                </div>
                <p class="weui_tabbar_label">个人中心</p>
            </a>
        </div>
        <!-- /底部导航 -->
    </div>
</div>
<script src="${contextPath!}/res/weixin/js/weiketang/swiper.3.1.7.min.js"></script>
<script>
    //动画效果
    var mySwiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        /*分页器焦点*/
        effect: 'coverflow',
        /*动画过渡效果*/
        paginationClickable: true,
        /*添加点击效果*/
        centeredSlides: true,
        /*活动内容居中*/
        autoplay: 5000,
        /*自动滑动时间*/
        autoplayDisableOnInteraction: false,
        /*用户操作动画，3s后可以继续执行动画*/
        preloadImages: false,
        lazyLoading: true
    });
    //   分类为两个时的样式
    var $gridNum = $('.grid_wrap .grid_item')
    if($gridNum.length == 2) {
        $gridNum.first().css('border-right', '1px solid #f1f1f5')
    }
    //课程为奇数时图片平铺
    $('.course_main').each(function() {
        var $item = $(this).find(' .course_item');
        var itemNum = $item.length % 2;
        if(itemNum == 1 && $(this).hasClass('course_live')) {
            var $firstitem = $item.first(),
                    $img = $firstitem.find("img");
            $firstitem.css('width', "100%");
            var wd = $firstitem.width() || window.innerWidth - 20;
            var boxh = Math.ceil(wd / 2);
            $img.wrap("<div class='big-box' style='height:" + boxh + "px'></div>");
        } else if(itemNum == 1 && $(this).hasClass('course_other')) {
            var $lastitem = $item.last(),
                    $img = $lastitem.find("img");
            $lastitem.css('width', "100%");
            var wd = $lastitem.width() || window.innerWidth - 20;
            var boxh = Math.ceil(wd / 2);
            $img.wrap("<div class='big-box' style='height:" + boxh + "px'></div>");
        }
    });
</script>
<div style="display:none;">
</div>
</html>