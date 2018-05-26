function weixinConfig(appid, timestamp, noncestr, signature) {
	wx.config({
		debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        // debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		appId :appid, // 必填，公众号的唯一标识
		timestamp : timestamp, // 必填，生成签名的时间戳
		nonceStr : noncestr, // 必填，生成签名的随机串
		signature : signature,// 必填，签名，见附录1
        jsApiList: [
            'checkJsApi',
            'onMenuShareTimeline',
            'onMenuShareAppMessage',
            'onMenuShareQQ',
            'onMenuShareWeibo',
            'hideMenuItems',
            'showMenuItems',
            'hideAllNonBaseMenuItem',
            'showAllNonBaseMenuItem',
            'translateVoice',
            'startRecord',
            'stopRecord',
            'onRecordEnd',
            'playVoice',
            'pauseVoice',
            'stopVoice',
            'uploadVoice',
            'downloadVoice',
            'chooseImage',
            'previewImage',
            'uploadImage',
            'downloadImage',
            'getNetworkType',
            'openLocation',
            'getLocation',
            'hideOptionMenu',
            'showOptionMenu',
            'closeWindow',
            'scanQRCode',
            'chooseWXPay',
            'openProductSpecificView',
            'addCard',
            'chooseCard',
            'openCard'
		]
		// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
}

//获取用户的openid
function getBaseInfo(userId, state){
    if (userId === '') {
        //1.获取到code
        $appid="wx7ee19649ab15e945";
        //$appid="wx68d9985c78b0bdc8";//这里的appid是假的演示用
        $redirect_uri=encodeURI("http://ketang.hbgstzgl.com/weixin/auth/authorize.do");//这里的地址需要http://
        //$redirect_uri=encodeURI("http://192.168.31.70:8888/weixin/auth/authorize.do");//这里的地址需要http://
        $url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+$appid+"&redirect_uri="+$redirect_uri+"&response_type=code&scope=snsapi_userinfo&state=" + state + "#wechat_redirect";
        window.location.href = $url;
    }
}