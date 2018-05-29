<!DOCTYPE html>
<html lang="en">
<head>
    <!--[if lt IE 8]>
    <script>
        alert('不支持IE6-8，请使用谷歌、火狐等浏览器\n或360、QQ等国产浏览器的极速模式浏览本页面！');
    </script>
    <![endif]-->
    <#include "/pub/header_res.ftl"/>
    <title>管理员</title>
    <link href="/res/pub/material_admin/css/image-view.css" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav onmouseleave="hideLayer();" class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i></div>
            <div class="sidebar-collapse"">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                <span class="text-muted text-xs block">时间：${.now?date}</span>
                                <span class="text-muted text-xs block">姓名：${(user.name)!''}</span>
                                </span>
                            </a>
                        </div>
                        <div class="logo-element"></div>
                    </li>

                    <li>
                        <a href="#" id="group_2"><i class="fa fa-bars"></i><span class="nav-label">基础管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="${contextPath}/weixin/base/queryBaseList.do" data-index="1">数据配置列表</a></li>
                        </ul>
                    </li>
                    
	                <li>
	                    <a href="#" id="group_1"><i class="fa fa-bars"></i><span class="nav-label">用户管理</span><span class="fa arrow"></span></a>
	                    <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="${contextPath}/weixin/user/queryUserList.do" data-index="1">用户列表</a></li>
	                    </ul>
	                </li>

                    <li>
                        <a href="#" id="group_2"><i class="fa fa-bars"></i><span class="nav-label">课程管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="${contextPath}/weixin/course/addCourseHtml.do" data-index="1">新增课程</a></li>
                            <li><a class="J_menuItem" href="${contextPath}/weixin/course/queryCourseList.do" data-index="2">课程列表</a></li>
                        </ul>
                    </li>

                    <li>
                        <a href="#" id="group_2"><i class="fa fa-bars"></i><span class="nav-label">微信用户管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="${contextPath}/weixin/user/queryUserPromoterList.do" data-index="1">微信用户列表</a></li>
                        </ul>
                    </li>

					<li style="display: none;">
						<a id="openTab" class="J_menuItem" href="#" data-index="99000"></a>
					</li>
					
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1"  style="overflow-x:hidden" >
            <div class="row content-tabs" style="position:relative">
			<div class="navbar-header" style="position:absolute; z-index:1000;"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary "  style="margin:0 ; padding:9px 13px 11px 12px;background-color:#fff; border:#f2f2f2; color:#999;border-radius:0; border-right:1px solid #eee; " href="#"><i class="fa fa-angle-double-left fa-lg"></i></a></div>
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="${contextPath}/indexPage.do">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <button class="roll-nav roll-right dropdown J_tabClose"><span class="dropdown-toggle" data-toggle="dropdown">关闭操作<span class="caret"></span></span>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
                    </ul>
                </button>
                <a href="#" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main" >
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="" frameborder="0" data-id="" seamless></iframe>
            </div>
            <div class="footer">
            </div>
        </div>
        <!--右侧部分结束-->

    </div>

    <!-- image viewer -->
    <div id="image-browse" class="image-browse">
        <div class="image-body">
            <button type="button" class="close">&times;</button>
            <img id="image-file"  />
        </div>
        <div class="image-foot"></div>
    </div>
    <#include "/pub/footer_res.ftl"/>
    <script type="text/javascript">
    //打开tab 标签
    function openTab(_href,_tabName){
    	if(_tabName==undefined){
    		_tabName="新Tab";
    	}
    	if(_tabName.length>10){
    		_tabName=_tabName.substr(0,10);
    	}
    	var $openTab=jQuery("#openTab");
    	//已存在
    	if($openTab.data(_href)){
    		$openTab.attr("href",_href).text(_tabName);
    		$openTab.trigger("click");
    	}
    	//不存在
    	else{
    		//href index tabName
    		$openTab.data(_href,_href);
    		$openTab.attr("href",_href).attr("data-index",(Number($openTab.attr("data-index"))+1)).text(_tabName);
    		$openTab.trigger("click");
    	}
    }
    //关闭当前活动页
    function closeTab(){
    	jQuery(".page-tabs-content").find("a").each(function(){
    		$this=jQuery(this);
    		if($this.hasClass("active")){
    			$this.find("i").trigger("click");
    		}
    	});
    }
    //初始化
    jQuery(function(){
    	jQuery(".navbar-header").find("a").first().on("click",function(){
    		if(jQuery(this).html()=='<i class="fa fa-angle-double-left fa-lg"></i>'){
    			jQuery(this).html('<i class="fa fa-angle-double-right fa-lg"></i>');
    		}else{
    			jQuery(this).html('<i class="fa fa-angle-double-left fa-lg"></i>');
    		}
    	});

        // 图片查看关闭
        $("#image-browse .close").click(function(){
            $("#image-browse").css("display", "none");
        });
    });
    function hideLayer(){
    	jQuery(".nav-header").trigger("click");
    }
    
    </script>
	
</body>
</html>