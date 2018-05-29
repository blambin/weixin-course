<!DOCTYPE html>
<html lang="en">
	<head>
		<#include "/pub/header_res.ftl"/>
		 <link href="${contextPath}/res/pub/css/plugins/iCheck/custom.css" rel="stylesheet">
		 <title>数据配置列表</title>
	</head>
	<body class="gray-bg">
	<form action="${contextPath}/weixin/base/queryBaseList.do" method="post" name="form1" class="form-horizontal">
		<div class="container-fluid">
			<div class="panel panel-default" style="margin-top: 1px;">
            <div class="panel-heading">数据配置列表</div>
                <div class="panel-body">
                    <div class="col-sm-12">
                    <div class="float-e-margins">
			  	 	 	 <div class="ibox-content" style="padding:0 0 0 0">
                            
                            <!-- 查询条件 -- start -->
                            <div class="form-group ">
                                <label class="col-sm-1 control-label label_padding">配置名称</label>
                                <div class="col-sm-3">
                                    <input type="text" id="name" name="name" value="${(findObj.name)!''}" class="form-control" >
                                </div>
                            </div>
                            <!-- 查询条件 -- end -->
                            
                            <!-- 提交按钮 -- start -->
							<div class="form-group">
								<div class="col-sm-3 col-sm-offset-9">
                                    <input type="hidden" id="msg" value="${msg!''}">
                                    <input type="button" onclick="addBaseHtml();" value="新增数据配置" class="btn btn-primary btn-sm zd-btn-pd1"> &nbsp;&nbsp;
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
							<th style="text-align: center;">操作</th>
							<th style="text-align: center;">配置名称</th>
                            <th style="text-align: center;">配置类型</th>
							<th style="text-align: center;">配置值</th>
						</tr>
					</thead>

					<tbody>
					
						<!-- 按查询条件显示计划报表 -- start -->
						<#list page.list as info>				
						
						<tr>
							<td style="widtd: 10px; text-align: center;">${info_index+1}</td>
							<td style="text-align: center;">
                                <a href="${contextPath}/weixin/base/modifyBaseByIdHtml.do?id=${info.id!}">修改</a>&nbsp;&nbsp;|&nbsp;
                                <a onclick="delBase('${info.id!}')">删除</a>
							</td>
                            <td style="text-align: center;">${info.name!}</td>
                            <td style="text-align: center;">${info.type!}</td>
                            <td style="text-align: center;">${info.value!}</td>
						</tr>
						
						</#list>
						<!-- 按查询条件显示计划报表 -- start -->
											
						
					</tbody>
				</table>
			</div>
			<@pages url="${contextPath}/weixin/base/queryBaseList.do" pageCount="${page.pages}" currentPage="${page.pageNum}" />
        </div>
	</form>
	
	<!-- 页尾 -- start -->
	<#include "/pub/footer_res_detail.ftl"/>
	<!-- 页尾 -- start -->
	
	<!-- js引用 -- start -->
	<script type="application/javascript">

		function addBaseHtml() {
			location.href = BASE_PATH + "/weixin/base/addBaseHtml.do";
        }

        function delBase(id) {
            confMsg("是否删除!", function submitDel(){
                $.ajax({
                    type: "POST",
                    url: BASE_PATH + "/weixin/base/delBase.do",
                    data: {
                        "id" : id
					},
                    dataType: "json",
                    async: false,
                    success: function (response) {
                        showMsg(response.info);
                        setTimeout(function(){location.reload();}, 1000);
                    },
                    error: function (e) {
                        alert('Error: ' + e);
                    }
                });
			},function () {

            });
        }

	</script>
	<!-- js引用 -- start -->
	</body>
</html>