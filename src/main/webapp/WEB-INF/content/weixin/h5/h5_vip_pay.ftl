<!DOCTYPE html>
<#include "/weixin/include/taglibs.ftl"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="format-detection" content="telephone=no">
    <title>购买会员 - 微课堂</title>
    <script src="${contextPath!}/res/weixin/js/weiketang/jquery.min.js?v=2.3.3"></script>
    <script src="${contextPath!}/res/weixin/js/weiketang/common.js?v=2.3.3"></script>
    <link rel="stylesheet" href="${contextPath!}/res/weixin/js/weiketang/weui.min.css?v=2.3.3" />
    <link rel="stylesheet" href="${contextPath!}/res/weixin/js/weiketang/font-awesome.min.css?v=2.3.3"/>
    <link rel="stylesheet" href="${contextPath!}/res/weixin/js/weiketang/index.css?v=2.3.3"/>
</head>
<body>
<script type="text/javascript">

    // 用户授权登陆
    getBaseInfo('${(weixinUser.id)}' ,'vipPay.do');

</script>
<div class="tabbar tabbar_wrap page_wrap">
    <div class="weui_tab">
        <link href="${contextPath!}/res/weixin/js/weiketang/qunService.css?v=2.3.3" rel="stylesheet" />
        <link href="${contextPath!}/res/weixin/js/weiketang/lesson.css?v=2.3.3" rel="stylesheet" />

        <div class="content-inner">
            <div class="video-wrap clearfix">
                <div id="videoarea" style="position:relative;text-align: center;margin-top: 10%;">
                    <img src="http://ketang.hbgstzgl.com/uplimages/tmp/vip.jpg">
                </div>
            </div>

            <footer>
                <a href="#"></a>
            </footer>
        </div>

        <ul class="d-buynow">
            <li style="width: 100%;" class="btn-buy" id="buy_now"><a href="javascript:;" class="buy buy_now blue"><p class="num" style="padding-top:22px;"><em class="money"></em>立即购买</p></a></li>
        </ul>

        <script type="text/javascript">

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
                                        setTimeout("location.href = '${contextPath!}/weixin/h5/main.do'", 1000);
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
                                setTimeout("location.href = '${contextPath!}/weixin/h5/main.do'", 1000);
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
</html>