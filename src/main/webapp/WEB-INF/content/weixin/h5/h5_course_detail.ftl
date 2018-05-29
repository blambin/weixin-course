<!DOCTYPE html>
<#include "/weixin/include/taglibs.ftl"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="format-detection" content="telephone=no">
    <title>课程 - 微课堂</title>
    <script src="${contextPath!}/res/weixin/js/weiketang/jquery.min.js?v=2.3.3"></script>
    <script src="${contextPath!}/res/weixin/js/weiketang/common.js?v=2.3.3"></script>
    <link rel="stylesheet" href="${contextPath!}/res/weixin/js/weiketang/weui.min.css?v=2.3.3" />
    <link rel="stylesheet" href="${contextPath!}/res/weixin/js/weiketang/font-awesome.min.css?v=2.3.3"/>
    <link rel="stylesheet" href="${contextPath!}/res/weixin/js/weiketang/index.css?v=2.3.3"/>
</head>
<body>
<script type="text/javascript">

    // 用户授权登陆
    getBaseInfo('${(weixinUser.id)}' ,'queryCourseDetail.do?id=${info.id!}');

</script>
<div class="tabbar tabbar_wrap page_wrap">
    <div class="weui_tab">
        <link href="${contextPath!}/res/weixin/js/weiketang/qunService.css?v=2.3.3" rel="stylesheet" />
        <link href="${contextPath!}/res/weixin/js/weiketang/lesson.css?v=2.3.3" rel="stylesheet" />

        <style>
            .fylesson_audio{width:90%; margin:0 auto 0 auto; padding:30px 0;}
            .fylesson_audio p{padding:10px 0}
        </style>


        <div class="content-inner">
            <div class="video-wrap clearfix">
                <div id="videoarea" style="position:relative;">
                    <img src="${access_root_path}${info.logoUrl!}" alt="" width="100%">
                </div>
            </div>
            <ul class="course-tab">
                <li class="curr">详情</li>
            </ul>
            <div class="course-container">
                <div class="js-tab"  style="transform-origin:0px 0px 0px;opacity:1;transform:scale(1,1); ">
                    <ul class="course-intro">
                        <li>
                            <h2 class="chapter-title">课程信息</h2>
                            <p class="course-intro-title">课程名称：${info.name!}</p>
                            <p class="course-intro-title">课程价格：${info.price!}</p>
                            <p class="course-intro-title">是否免费：<#if info.isFree == '1'>免费<#else>收费</#if></p>
                            <p class="course-intro-title">
                                课程类型：<#switch info.type>
                                    <#case '1'>视频<#break>
                                    <#case '2'>音频<#break>
                                    <#case '3'>文字<#break>
                                </#switch>
                            </p>
                        </li>
                        <li>
                            <h2 class="chapter-title">课程介绍</h2>
                            <div class="lesson-content">
                                <p>${info.introduce!}</p>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>

            <footer>
                <a href="#"></a>
            </footer>
        </div>

        <script type="text/javascript">
            $(function() {
                $(".weui_tabbar a").click(function() {
                    $(this).addClass("weui_bar_item_on").siblings().removeClass("weui_bar_item_on");
                });
                $(".btn_home").click(function(e) {
                    e.preventDefault();
                    e.stopPropagation();
                    $(this).parent().toggleClass("active");
                    $(".btn_qq_box").toggle();
                });
                $(".btn_mark").click(function(e) {
                    $(this).toggleClass("active");
                    $(".btn_qq_box").toggle();
                });
            })
        </script>
        <!-- /快捷导航 -->

        <!--课程规格start-->
        <div class="flick-menu-mask" onclick="closeSpec();" style="display: none;"></div>
        <div class="spec-menu-content spec-menu-show" style="display: none;">
            <div class="spec-menu-top bdr-b">
                <div class="spec-first-pic">
                    <img id="spec_image" src="${access_root_path}${info.logoUrl!}" onerror="imgErr(this)">
                </div>
                <a class="rt-close-btn-wrap spec-menu-close" onclick="closeSpec();">
                    <p class="flick-menu-close"></p>
                </a>
                <div class="spec-price" id="specJdPri" style="display: block">
                    <span class="yang-pic spec-yang-pic"></span>
                    <span id="spec_price"> ￥${info.price!}元 </span>
                </div>
            </div>
            <div class="spec-menu-middle">
                <div class="prod-spec" id="prodSpecArea">
                    <!-- 已选 -->
                    <div class="spec-desc">
                        <span class="part-note-msg">已选</span>
                        <div id="specDetailInfo_spec" class="base-txt">${info.name!}</div>
                    </div>
                    <div class="spec-desc">
                        <span class="part-note-msg">类型</span>
                        <div id="specDetailInfo_spec" class="base-txt">
                            <#switch info.type>
                                    <#case '1'>视频<#break>
                                    <#case '2'>音频<#break>
                                    <#case '3'>文字<#break>
                            </#switch>
                        </div>
                    </div>
                </div>
            </div>
            <div class="flick-menu-btn spec-menu-btn">
                <a class="directorder" style="background-color: #f23030;transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);" id="buy_now">立即购买</a>
            </div>
        </div>
        <!--课程规格end-->

        <ul class="d-buynow">
            <li style="width: 100%;" class="btn-buy" id="buy-now"><a href="javascript:;" class="buy buy_now blue"><p class="num" style="padding-top:22px;"><em class="money"></em>开始学习</p></a></li>
        </ul>


        <script type="text/javascript">

            //课程规格
            $("#buy-now").click(function() {
                $(".flick-menu-mask").show();
                $(".spec-menu-show").show();
            });
            function closeSpec(){
                $(".flick-menu-mask").hide();
                $(".spec-menu-show").hide();
            }

            function updateColorSizeSpec(spec_id, spec_price, spec_day){
                $(".a-item").removeClass("selected");
                $(".spec_"+spec_id).addClass("selected");
                $("#spec_id").val(spec_id);
                document.getElementById("spec_price").innerHTML = "￥"+spec_price;
                document.getElementById("specDetailInfo_spec").innerHTML = spec_day==-1 ? '长期有效' : "有效期"+spec_day+"天";
            }

            //立即购买
            $("#buy_now").click(function(){

                // 参数设置
                var option = {"c.id":${info.id!}};

                // 加载车辆数据
                $.ajax({
                    url: BASE_PATH + "/weixin/order/preOrder.do",
                    data: option,
                    async: false,
                    type: "POST",
                    success: function(data){
                        if (data.result === 'success') {

                            if (data.object != null && data.object !== '') {
                                wx.chooseWXPay({
                                    appId: data.object.appId,
                                    timestamp: data.object.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
                                    nonceStr: data.object.nonceStr, // 支付签名随机串，不长于 32 位
                                    package: data.object.package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=\*\*\*）
                                    signType: data.object.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
                                    paySign: data.object.paySign, // 支付签名
                                    success: function (res) {
                                        // 支付成功后的回调函数
                                        alert('支付成功！');
                                        setTimeout("location.href = '${contextPath!}/weixin/h5/queryCoursePlayer.do?id=${info.id!}'", 1000);
                                    },
                                    cancel: function (res) {
                                        // 支付取消
                                    },
                                    fail: function (res) {
                                        // 支付失败
                                        alert("支付失败！");
                                    }
                                });
                            } else {
                                alert('购买成功！');
                                setTimeout("location.href = '${contextPath!}/weixin/h5/queryCoursePlayer.do?id=${info.id!}'", 1000);
                            }

                        } else {
                            alert('订单生成失败！');
                        }
                    },
                    error: function (data) {
                        alert('订单生成失败！' + data);
                    }
                });

            });

        </script>

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