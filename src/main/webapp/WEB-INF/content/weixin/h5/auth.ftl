<!DOCTYPE html>
<#include "/weixin/include/taglibs.ftl"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>微信获取用户信息</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
</head>
<body>
<script src="${contextPath!}/res/weixin/js/h5/weixin_util.js" type="text/javascript"></script>
<script type="text/javascript">
    //页面自动跳转地址（非静默授权链接） 参数appid为公众号的id redirect_uri为微信回调接口 state为可携带的参数(可选，这里写的是回调接口处理完跳转到指定页面) 其余参数不变
    // window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx68d9985c78b0bdc8&redirect_uri=yclimb.free.ngrok.cc&response_type=code&scope=snsapi_userinfo&state=index.html#wechat_redirect";
    getBaseInfo('main.do');

</script>
</body>
</html>