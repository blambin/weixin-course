<!DOCTYPE html>
<#include "/weixin/include/taglibs.ftl"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>个人中心</title>
    <link rel="stylesheet" href="${contextPath!}/res/weixin/js/weiketang/weui.min.css?v=2.3.3">
    <link rel="stylesheet" href="${contextPath!}/res/weixin/js/weiketang/font-awesome.min.css?v=2.3.3">
    <link rel="stylesheet" href="${contextPath!}/res/weixin/js/weiketang/index.css?v=2.3.3">
    <link rel="stylesheet" href="${contextPath!}/res/weixin/css/h5_user.css">
    <link href="${contextPath!}/res/weixin/js/weiketang/search.css?v=2.3.3" rel="stylesheet" />
    <script src="${contextPath!}/res/weixin/js/date.js?v=2.3.3"></script>
</head>
<body style="">
<script type="text/javascript">

    // 用户授权登陆
    getBaseInfo('${(weixinUser.id)}' ,'toUserHtml.do');

</script>

    <div class="head">
        <div class="avatar">
            <img src="${(weixinUser.headimgurl)!}" alt="">
        </div>
        <p class="nickname">${(weixinUser.nickname)!}</p>
        <p class="money">奖励金额：<span>${(weixinUser.promoterMoney)!0}元</span></p>
        <p class="money_rule">
            每当您拉取的新用户购买了课程，您就可以获得奖励金，奖励金满10元可以提现，每次提取10元。
        </p>
        <a class="get_money" onclick="getMoney(${(weixinUser.promoterMoney)!});">提现</a>

    </div>
    <div class="weui_panel weui_panel_access">
        <div class="weui_panel_hd">收支详情</div>
        <div class="weui_panel_bd" id="js-order-list">

        </div>
        <div id="loading_div" class="loading_div">
            <a href="javascript:void(0);" id="btn_Page"><i class="fa fa-arrow-circle-down"></i> 加载更多</a>
        </div>
        <footer>
            <a href="#"></a>
        </footer>
    </div>

    <!-- 遮罩层 -->
    <div id="sort_background" class="dropdown__background"></div>
    <!-- /遮罩层 -->
    <div id="loading" style="position:fixed;top:0;left:0;width:100%;height:100%;background:rgba(0,0,0,0.6);z-index:999999999;"><div class="spinner"><div class="bounce1"></div><div class="bounce2"></div><div class="bounce3"></div></div></div>


    <!-- 底部导航 -->
    <div class="weui_tabbar ">
        <a href="${contextPath!}/weixin/h5/main.do?1=1" class="weui_tabbar_item ">
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
        <a href="${contextPath!}/weixin/h5/toUserHtml.do" class="weui_tabbar_item  weui_bar_item_on">
            <div class="weui_tabbar_icon">
                <i class="fa fa-user"></i>
            </div>
            <p class="weui_tabbar_label">个人中心</p>
        </a>
    </div>
    <!-- /底部导航 -->

</body>

    <script src="${contextPath!}/res/weixin/js/weiketang/jquery.min.js?v=2.3.3"></script>

    <script type="text/javascript">
        var ajaxUrl   = BASE_PATH + "/weixin/h5/queryPromoterLogListJson.do";
        var lessonUrl = BASE_PATH + "/weixin/h5/queryCoursePlayer.do?1=1";
        $(function () {
            var i = 1; //设置当前页数，全局变量
            function getData(page) {
                $.get(ajaxUrl, {"currenPage":page}, function (data) {
                    if (data.pages >= i && data.size > 0) {
                        //var jsonObj = JSON.parse(data);
                        insertDiv(data);
                    } else {
                        document.getElementById("loading_div").innerHTML='<div >没有了，已经到底了</div>';
                    }
                    loading.style.display = 'none';
                    i++;
                });
            }

            //初始化加载第一页数据
            getData(1);

            //生成数据html,append到div中
            function insertDiv(resultObj) {
                var result = resultObj.list;
                var mainDiv =$("#js-order-list");
                var chtml = '';
                $.each(result, function (j, info) {
                    chtml += '<a href="#" class="weui_media_box weui_media_appmsg">';
                    chtml += '    <div class="weui_media_bd">';
                    chtml += '        <h4 class="weui_media_title" style="font-size: 13px">';
                    chtml += '        红包收入<span class="green" style="float: right">+' + info.money + '元</span>';
                    chtml += '        </h4>';
                    chtml += '        <p class="weui_media_desc">' + new Date(info.createTime).Format("yyyy-MM-dd HH:mm:ss") + '</p>';
                    chtml += '    </div>';
                    chtml += '</a>';
                });
                mainDiv.append(chtml);
                if(result.length===0){
                    document.getElementById("loading_div").innerHTML='<div >没有了，已经到底了</div>';
                }
            }

            //==============核心代码=============
            var winH = $(window).height(); //页面可视区域高度

            var scrollHandler = function () {
                var pageH = $(document.body).height();
                var scrollT = $(window).scrollTop(); //滚动条top
                var aa = (pageH - winH - scrollT) / winH;
                if (aa < 0.02) {
                    if (i % 1 === 0) {
                        $(window).unbind('scroll');
                        $("#btn_Page").show();
                    } else {
                        $("#btn_Page").hide();
                    }
                }
            };
            //定义鼠标滚动事件
            $(window).scroll(scrollHandler);
            //继续加载按钮事件
            $("#btn_Page").click(function () {
                loading.style.display = 'block';
                getData(i);
                $(window).scroll(scrollHandler);
            });

        });

        /**
         * 提现
         * @param money
         */
        function getMoney(money) {
            if (money >= 10) {
                // 提现十元钱
                alert('抱歉，暂时不支持提现！');
            } else {
                alert('金额小于10元，不能提现哦！');
            }
        }

    </script>

</html>