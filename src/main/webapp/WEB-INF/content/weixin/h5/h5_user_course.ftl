<!DOCTYPE html>
<#include "/weixin/include/taglibs.ftl"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="format-detection" content="telephone=no">
    <title>课程列表 - 微课堂</title>
    <script src="${contextPath!}/res/weixin/js/weiketang/jquery.min.js?v=2.3.3"></script>
    <link rel="stylesheet" href="${contextPath!}/res/weixin/js/weiketang/weui.min.css?v=2.3.3" />
    <link rel="stylesheet" href="${contextPath!}/res/weixin/js/weiketang/font-awesome.min.css?v=2.3.3"/>
    <link rel="stylesheet" href="${contextPath!}/res/weixin/js/weiketang/index.css?v=2.3.3"/>
</head>
<body>
<script type="text/javascript">

    // 用户授权登陆
    getBaseInfo('${(weixinUser.id)}' ,'toUserCourseHtml.do');

</script>
<div class="tabbar tabbar_wrap page_wrap">
    <div class="weui_tab">
        <link href="${contextPath!}/res/weixin/js/weiketang/search.css?v=2.3.3" rel="stylesheet" />

        <!-- 筛选条件 -->
        <#--<div id="nav" class="nav">
            <div class="dropdown" onclick="location.href=''">
                <div class="dropdown_description" style="color: red;">全部课程</div>
            </div>
            <div class="dropdown" onclick="location.href=''">
                <div class="dropdown_description">待付款</div>
            </div>
            <div class="dropdown" onclick="location.href=''">
                <div class="dropdown_description">已付款</div>
            </div>
        </div>-->
        <!-- /筛选条件 -->

        <!-- 课程列表 -->
        <div class="section">
            <div style="margin:0px auto 10px;">
                <ul id="js-course-list" class="course-list list-view" style="min-height:1px;">
                </ul>
                <div id="loading_div" class="loading_div">
                    <a href="javascript:void(0);" id="btn_Page"><i class="fa fa-arrow-circle-down"></i> 加载更多</a>
                </div>
            </div>
            <footer>
                <a href=""></a>
            </footer>
        </div>
        <!-- /课程列表 -->

        <!-- 遮罩层 -->
        <div id="sort_background" class="dropdown__background"></div>
        <!-- /遮罩层 -->
        <div id="loading" style="position:fixed;top:0;left:0;width:100%;height:100%;background:rgba(0,0,0,0.6);z-index:999999999;"><div class="spinner"><div class="bounce1"></div><div class="bounce2"></div><div class="bounce3"></div></div></div>

        <script type="text/javascript">
            var nav = document.getElementById("nav");
            var dropdown_wrapper = document.getElementById("dropdown_wrapper");
            var contact_ico = document.getElementById("contact_ico");
            var sort_background = document.getElementById("sort_background");

            $("#sort-list").on("click", function(){
                var sort = $("#sort").val();
                if(sort==0){
                    $("#sort").val(1);
                    dropdown_wrapper.style.display = "block";
                    contact_ico.style.display = "block";
                    nav.style.borderBottomColor = "#23b8ff";
                    sort_background.style.display = "block";
                }else{
                    $("#sort").val(0);
                    dropdown_wrapper.style.display = "none";
                    contact_ico.style.display = "none";
                    nav.style.borderBottomColor = "#e2e2e2";
                    sort_background.style.display = "none";
                }
            });
            $("#sort_background").on("click", function(){
                $("#sort").val(0);
                dropdown_wrapper.style.display = "none";
                contact_ico.style.display = "none";
                nav.style.borderBottomColor = "#e2e2e2";
                sort_background.style.display = "none";
            });

            function goSearch(sort){
                var siteUrl = BASE_PATH + "/weixin/h5/queryCourseList.do?1=1";
                if(sort=='free'){
                    siteUrl = siteUrl + "&sort=free";
                }else if(sort=='price'){
                    siteUrl = siteUrl + "&sort=price";
                }else if(sort=='hot'){
                    siteUrl = siteUrl + "&sort=hot";
                }else if(sort=='score'){
                    siteUrl = siteUrl + "&sort=score";
                }else{
                    siteUrl = siteUrl + "&sort=";
                }

                location.href = siteUrl;
            }

            var search = function() {
                var keywords = $.trim($("#searchInput").val());
                if (keywords == '') {
                    searchUrl = BASE_PATH + "/weixin/h5/queryCourseList.do?1=1";
                } else {
                    searchUrl = 'queryCourseList.do?1=1&name=' + encodeURIComponent(keywords);
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
        <script type="text/javascript">
            var ajaxUrl   = BASE_PATH + "/weixin/h5/queryCourseListJsonByUserId.do?1=1";
            var lessonUrl = BASE_PATH + "/weixin/h5/queryCoursePlayer.do?1=1";
            var lessonDetailUrl = BASE_PATH + "/weixin/h5/queryCourseDetail.do?1=1";
            var keywords = $.trim($("#searchInput").val());
            $(function () {
                var i = 1; //设置当前页数，全局变量
                function getData(page) {
                    $.get(ajaxUrl, {"currenPage":page,"name":keywords}, function (data) {
                        if (data.pages >= page && data.size > 0) {
                            //var jsonObj = JSON.parse(data);
                            insertDiv(data);
                        } else {
                            document.getElementById("loading_div").innerHTML='<div class="loading_bd">没有了，已经到底了</div>';
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
                    var mainDiv =$("#js-course-list");
                    var chtml = '';
                    $.each(result, function (j, info) {
                        chtml += '<li class="lesson_list">';
                        if(info.status === '3'){
                            chtml += '	<a href="' + lessonUrl + '&id=' + info.id + '" class="package">';
                        }else{
                            chtml += '	<a href="' + lessonDetailUrl + '&id=' + info.id + '" class="package">';
                        }
                        chtml += '		<div class="package__cover-wrap">';
                        chtml += '			<div class="package__cover" style="background-image: url(' + BASE_IMG_PATH + info.logoUrl + ');">';
                        //chtml += '				<span class="package__cover-tips package__cover-tips--status">' + info.name + '人已学习</span>';
                        chtml += '			</div>';
                        chtml += '		</div>';
                        chtml += '		<div class="package__content">';
                        chtml += '			<h3 class="package__name">' + info.name + '</h3>';
                        chtml += '			<div class="package__info">';
                        chtml += '				<span class="u-price">价格：' + info.price + '</span>';
                        chtml += '			</div>';
                        chtml += '			<div class="package__info">';
                        //chtml += '				<span>共<i class="blue-color">' + info.name + '</i>节课程</span>';
                        if(info.isFree === '1'){
                            chtml += '				<span><i class="">免费</i></span>';
                        }else{
                            if(info.status === '3'){
                                chtml += '				<span><i class="">收费</i></span><span class="green-color">（已付款）</span>';
                            }else{
                                chtml += '				<span><i class="">收费</i></span><span class="red-color">（待付款）</span>';
                            }
                        }
                        chtml += '			</div>';
                        chtml += '		</div>';
                        chtml += '	</a>';
                        chtml += '</li>';
                    });
                    mainDiv.append(chtml);
                    if(result.length==0){
                        document.getElementById("loading_div").innerHTML='<div class="loading_bd">没有了，已经到底了</div>';
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
                }
                //定义鼠标滚动事件
                $(window).scroll(scrollHandler);
                //继续加载按钮事件
                $("#btn_Page").click(function () {
                    loading.style.display = 'block';
                    getData(i);
                    $(window).scroll(scrollHandler);
                });

            });
        </script>

        <!-- 底部导航 -->
        <div class="weui_tabbar ">

            <a href="${contextPath!}/weixin/h5/main.do?1=1" class="weui_tabbar_item ">
                <div class="weui_tabbar_icon">
                    <i class="fa fa-home menu_on" style="font-size: 24px;"></i>
                </div>
                <p class="weui_tabbar_label">首 页</p>
            </a>

            <a href="${contextPath!}/weixin/h5/queryCourseList.do?1=1" class="weui_tabbar_item">
                <div class="weui_tabbar_icon">
                    <i class="fa fa-video-camera"></i>
                </div>
                <p class="weui_tabbar_label">全部课程</p>
            </a>


            <a href="${contextPath!}/weixin/h5/toUserCourseHtml.do?1=1" class="weui_tabbar_item weui_bar_item_on">
                <div class="weui_tabbar_icon">
                    <i class="fa fa-book"></i>
                </div>
                <p class="weui_tabbar_label">我的课程</p>
            </a>

            <a href="${contextPath!}/weixin/h5/toUserHtml.do?1=1" class="weui_tabbar_item ">
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