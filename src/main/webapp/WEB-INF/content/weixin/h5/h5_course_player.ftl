<!DOCTYPE html>
<#include "/weixin/include/taglibs.ftl"/>
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
    <script src="https://api.html5media.info/1.2.2/html5media.min.js"></script>
</head>
<body>
<script type="text/javascript">

    // 用户授权登陆
    getBaseInfo('${(weixinUser.id)}' ,'queryCoursePlayer.do?id=${info.id!}');

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
            <#if info.type != '3'>
            <div class="video-wrap clearfix">
                <div id="videoarea" style="position:relative;">
                    <link rel="stylesheet" href="${contextPath!}/res/weixin/js/weiketang/APlayer.min.css">
                    <script src="${contextPath!}/res/weixin/js/weiketang/APlayer.min.js"></script>
                    <div id="main">
                        <div class="fylesson_audio">
                            <input type="hidden" id="courseType" value="${(info.type)!}">
                            <#if info.type == '2'>
                            <div id="player" class="aplayer" style="display: none;"></div>
                            </#if>
                            <#if info.type == '1'>
                                <video id="videoHidden" style="display: none;" src="${access_root_path!}${(info.url)!}" width="99%" height="200px" controls preload></video>
                            </#if>
                        </div>
                    </div>
                    <script>
                        var courseType = $("#courseType").val();
                        switch (courseType) {
                            case '1':
                                $("#videoHidden").show();
                                break;
                            case '2':
                                var ap1 = new APlayer({
                                    element: document.getElementById('player'),
                                    narrow: false,
                                    autoplay: false,
                                    showlrc: false,
                                    music: {
                                        title: "${(info.name)!}",
                                        author: "",
                                        url: "${access_root_path!}${(info.url)!}",
                                        pic: "${access_root_path!}${(info.logoUrl)!}"
                                    }
                                });
                                ap1.init();
                                $("#player").show();
                                break;
                        }
                    </script>
                </div>
            </div>
            </#if>
            <#--<ul class="course-tab">
                <li class="curr">详情</li>
            </ul>-->
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
                                <p>${(info.introduce)!}</p>
                            </div>
                        </li>
                        <#if info.type == '3'>
                        <li>
                            <h2 class="chapter-title">课程内容</h2>
                            <div class="lesson-content" style="word-wrap: break-word;">
                                <p>${(info.content)!}</p>
                            </div>
                        </li>
                        </#if>
                    </ul>
                </div>
            </div>

            <footer>
                <a href="#"></a>
            </footer>
        </div>

    </div>
</div>
<div style="display:none;">
</div>
</body>
</html>