<!DOCTYPE HTML>
<#include "/weixin/include/taglibs.ftl"/>
<html>
<head>
    <meta charset="utf-8">
    <title>xxx小课堂</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <#include "/weixin/include/h5_header_res_css.ftl"/>
</head>
<body style="visibility: visible;">

    <!--下拉刷新容器-->
    <div class="container" style="margin-top: 10px;">

        <!-- 初始化十条记录 -->
        <!--style="border-radius: 15px;overflow: hidden;"-->
        <#--<#list page.list as info>
        <div class="card" onclick="toRegistDetail('${info.id!}');" >
            <div class="card-header bgm-cyan">
                <h2>${info.name!}
                    <small>${info.introduce!}</small>
                </h2>
                <ul class="actions actions-alt">
                    <span class="btn-success badge"><#if info.isFree == '1'>免费<#else>收费</#if></span>
                </ul>
            </div>

            <div class="card-body card-padding">
                <span>价格：${(info.price)!''}</span>
                <span style="float: right;">课程类型：
                    <#switch info.type>
                        <#case '1'>视频<#break>
                        <#case '2'>音频<#break>
                        <#case '3'>文字<#break>
                    </#switch>
                </span>
            </div>
        </div>
        </#list>

        <!-- 加载更多 &ndash;&gt;
        <div class="load-more-regist load-more m-t-30">
            <#if page.pages == page.pageNum>
                <span>没有更多了...</span>
            <#else>
                <a id="loadMoreRegist" href="javascript:void(0);" onclick="loadMoreRegistAndAudit();"><i class="zmdi zmdi-refresh-alt"></i> Load More...</a>
                <a id="loadingRegist" style="display: none;background-color: transparent;"><span class="mui-spinner"></span></a>
            </#if>
        </div>

        <!-- 分页属性 &ndash;&gt;
        <input type="hidden" id="pageCountRegist" value="${page.pages!}">
        <input type="hidden" id="currentPageRegist" value="${page.pageNum!}">
        <input type="hidden" id="tabType" value="regist">
        <input type="hidden" id="msg" value="${msg!}">

        <br>
        <br>
        <br>-->

        <a id="onMenuShareAppMessage" href="javascript:void(0);"><i class="zmdi zmdi-refresh-alt"></i> Load More...</a>

    </div>

    <#include "/weixin/include/h5_footer_res_js.ftl"/>
    <script src="${contextPath!}/res/weixin/js/h5/h5_course_list.js" type="text/javascript"></script>

    <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js" type="text/javascript"></script>

    <script src="${contextPath!}/res/weixin/js/h5/weixin_util.js" type="text/javascript"></script>

    <script type="text/javascript">

        weixinConfig('${appid}',${timestamp},'${noncestr}','${signature}');

        /*wx.checkJsApi({
            jsApiList: [
                'getNetworkType',
                'previewImage'
            ],
            success: function (res) {
                alert(JSON.stringify(res));
            }
        });*/


        // 2. 分享接口
        // 2.1 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
        document.querySelector('#onMenuShareAppMessage').onclick = function () {

            // 注意：此 Demo 使用 2.7 版本支付接口实现，建议使用此接口时参考微信支付相关最新文档。
            wx.chooseWXPay({
                timestamp: 1414723227,
                nonceStr: 'noncestr',
                package: 'addition=action_id%3dgaby1234%26limit_pay%3d&bank_type=WX&body=innertest&fee_type=1&input_charset=GBK&notify_url=http%3A%2F%2F120.204.206.246%2Fcgi-bin%2Fmmsupport-bin%2Fnotifypay&out_trade_no=1414723227818375338&partner=1900000109&spbill_create_ip=127.0.0.1&total_fee=1&sign=432B647FE95C7BF73BCD177CEECBEF8D',
                signType: 'SHA1', // 注意：新版支付接口使用 MD5 加密
                paySign: 'bd5b1933cda6e9548862944836a9b52e8c9a2b69'
            });
        };



    </script>


</body>

</html>