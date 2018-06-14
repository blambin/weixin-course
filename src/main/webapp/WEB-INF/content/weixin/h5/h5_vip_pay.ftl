<!DOCTYPE html>
<#include "/weixin/include/taglibs.ftl"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>购买会员 - 微课堂</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <script src="${contextPath!}/res/weixin/js/weiketang/jquery.min.js?v=2.3.3"></script>
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        .vipimg{
            display: block;
            width: 100%;
        }
        .btn{
            position: fixed;
            display: block;
            font-size: 14px;
            width:100%;
            height: 40px;
            bottom: 0;
            text-align: center;
            line-height: 40px;
            color: #fff;
            background-color: rgb(221, 117, 117);
            text-decoration: none;
        }
        .list{
            position: relative;
            padding-bottom: 39px;
        }
        .list .class{
            position: relative;
            display: block;
            font-size: 13px;
            box-sizing: border-box;
            padding: 10px 20px;
            border-bottom: solid 1px rgb(207, 207, 207);
            overflow: hidden;
            text-overflow:ellipsis;
            white-space: nowrap;
        }
    </style>
</head>
<body>

<script type="text/javascript">

    // 用户授权登陆
    getBaseInfo('${(weixinUser.id)}' ,'vipPay.do');

</script>

<div class="main">
    <img class="vipimg" src="http://ketang.hbgstzgl.com/uplimages/tmp/vip.jpg" alt="">
    <div class="list">
        <#list list0 as info>
            <a class="class buy_class">${info.name!}</a>
        </#list>
    </div>
    <a class="btn" id="buy_now">成为会员</a>
</div>
</body>

<script type="text/javascript">

    $(function () {

        /**
         * 购买会员提示
         */
        $(".buy_class").click(function () {
            alert('请先购买会员');
        });

        /**
         * 立即购买
         */
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
                                    setTimeout("location.href = '${contextPath!}/weixin/h5/main.do'", 500);
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
                            setTimeout("location.href = '${contextPath!}/weixin/h5/main.do'", 500);
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
    });



</script>

</html>