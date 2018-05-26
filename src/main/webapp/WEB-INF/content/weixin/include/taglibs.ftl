<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="0">
<#include "/pub/tag.ftl"/>
<script type="text/javascript">
    BASE_PATH="${contextPath}";
    BASE_IMG_PATH = "${access_root_path}";
</script>


<#--<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>-->

<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js" type="text/javascript"></script>
<script src="${contextPath!}/res/weixin/js/h5/weixin_util.js" type="text/javascript"></script>

<script type="text/javascript">

    weixinConfig('${appid}',${timestamp},'${noncestr}','${signature}');

    wx.ready(function(){
        var shareData = {
            title: "首页 - 微课堂",
            desc: "秦晋科技微课堂是以...",
            link: "http://ketang.hbgstzgl.com/weixin/h5/main.do?promoter_id=${(weixinUser.id)!}",
            //link: "http://192.168.31.70:8888/weixin/h5/main.do?promoter_id=${(weixinUser.id)!}",
            imgUrl: "http://pic32.photophoto.cn/20140707/0006019627979225_b.jpg",
            trigger: function (res) {},
            complete: function (res) {},
            success: function (res) {},
            cancel: function (res) {},
            fail: function (res) {}
        };
        wx.onMenuShareTimeline(shareData);
        wx.onMenuShareAppMessage(shareData);
        wx.onMenuShareQQ(shareData);
        wx.onMenuShareWeibo(shareData);
        wx.onMenuShareQZone(shareData);
    });

</script>