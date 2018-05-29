<!DOCTYPE html>
<html lang="en">
	<head>
		<#include "/pub/header_res.ftl"/>
		 <link href="${contextPath}/res/pub/css/plugins/iCheck/custom.css" rel="stylesheet">
		 <title>用户列表</title>
	</head>
	<body class="gray-bg">
	<form action="${contextPath}/weixin/user/queryUserList.do" method="post" name="form1" class="form-horizontal">
		<div class="container-fluid">
			<div class="panel panel-default" style="margin-top: 1px;">
            <div class="panel-heading">用户列表</div>
                <div class="panel-body">
                    <div class="col-sm-12">
                    <div class="float-e-margins">
			  	 	 	 <div class="ibox-content" style="padding:0 0 0 0">
                            
                            <!-- 查询条件 -- start -->
                            <div class="form-group ">
                                <label class="col-sm-1 control-label label_padding">姓名</label>
                                <div class="col-sm-3">
                                    <input type="text" id="name" name="name" value="${(findObj.name)!''}" class="form-control" >
                                </div>
                            	<label class="col-sm-1 control-label label_padding">账号</label>
                                <div class="col-sm-3">
                                    <input type="text" id="loginAccount" name="loginAccount" value="${(findObj.loginAccount)!''}" class="form-control" >
                                </div>
                                <label class="col-sm-1 control-label label_padding">手机号码</label>
                                <div class="col-sm-3">
                                    <input type="text" id="mobile" name="mobile" value="${(findObj.mobile)!''}" class="form-control" >
                                </div>
                            </div>
                            <!-- 查询条件 -- end -->
                            
                            <!-- 提交按钮 -- start -->
							<div class="form-group">
								<div class="col-sm-3 col-sm-offset-9">
									<input type="submit" value="查 询" class="btn btn-primary btn-sm zd-btn-pd1">
								</div>
							</div>
							<!-- 提交按钮 -- end -->
                            
                           </div>
						</div>	
                    </div>
                </div>
            </div>


			<div class="panel panel-default table-responsive ziding-td">
				<table
					class="table table-condensed table-bordered table-striped table-hover">
					<thead>
						<tr>
							<th style="width: 10px; text-align: center;">序号</th>
							<th style="text-align: center;">用户名称</th>
							<th style="text-align: center;">登陆账号</th>
							<th style="text-align: center;">性别</th>
							<th style="text-align: center;">出生日期</th>
                            <th style="text-align: center;">手机号码</th>
						</tr>
					</thead>

					<tbody>
					
						<!-- 按查询条件显示计划报表 -- start -->
						<#list page.list as info>				
						
						<tr>
							<td style="widtd: 10px; text-align: center;">${info_index+1}</td>
							<td style="text-align: center;">${info.name!}</td>
							<td style="text-align: center;">${info.loginAccount!}</td>
							<td style="text-align: center;">${info.sex!}</td>
							<td style="text-align: center;">${info.birthday?datetime}</td>
							<td style="text-align: center;">${info.mobile!}</td>
						</tr>
						
						</#list>
						<!-- 按查询条件显示计划报表 -- start -->
											
						
					</tbody>
				</table>
			</div>
			<@pages url="${contextPath}/weixin/user/queryUserList.do" pageCount="${page.pages}" currentPage="${page.pageNum}" />
        </div>
	</form>
	
	<!-- 页尾 -- start -->
	<#include "/pub/footer_res_detail.ftl"/>
	<!-- 页尾 -- start -->
	
	<!-- js引用 -- start -->
	<!-- js引用 -- start -->
	</body>
</html>