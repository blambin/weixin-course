<!DOCTYPE html>
<html lang="en">
<head>
	<#include "/pub/header_res.ftl"/>
	<title>管理员</title>
	<link href="${contextPath}/res/pub/css/login.min.css" rel="stylesheet">

</head>
<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
                <div class="signin-info">
                    <div class="logopanel m-b">
                        <h1>管理员后台管理</span></h1>
                    </div>
                    <div class="m-b"></div>
                    <h4>欢迎使用 </h4>
                    <ul class="m-b">
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 图片管理</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 报表管理</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 建议使用google浏览器</li>
                    </ul>
                </div>
            </div>
            <div class="col-sm-5">
                <form role="form"  method="post" action="${contextPath}/login.do" >
                <h4 class="no-margins">用户登录</h4>
                    <p class="m-t-md text-danger"  id="errorTip"></p>
                    <input type="text" class="form-control uname" style="color:#000000" placeholder="用户名"  name="loginAccount" id="username" value="" required/>
                    <input type="password" class="form-control pword m-b"  style="color:#000000" placeholder="密码"  name="password" value="" required/>
                    <button type="submit" class="btn btn-success btn-block">登录</button>
                </form>
            </div>
        </div>
    </div>
    
	<#include "/pub/footer_res.ftl"/>
	<script type="text/javascript">

		function toSupplier() {
			location = BASE_PATH + "/business/supplier/getSupplierList.do";
		}
	</script>
</body>
</html>