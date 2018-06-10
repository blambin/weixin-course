<!DOCTYPE html>
<html lang="en">
	<head>
		<#include "/pub/header_res.ftl"/>
		 <link href="${contextPath}/res/pub/css/plugins/iCheck/custom.css" rel="stylesheet">
		 <title>课程列表</title>
	</head>
	<body class="gray-bg">
	<form action="${contextPath}/weixin/course/queryCourseList.do" method="post" name="form1" class="form-horizontal">
		<div class="container-fluid">
			<div class="panel panel-default" style="margin-top: 1px;">
            <div class="panel-heading">课程列表</div>
                <div class="panel-body">
                    <div class="col-sm-12">
                    <div class="float-e-margins">
			  	 	 	 <div class="ibox-content" style="padding:0 0 0 0">
                            
                            <!-- 查询条件 -- start -->
                            <div class="form-group ">
                                <label class="col-sm-1 control-label label_padding">课程名称</label>
                                <div class="col-sm-3">
                                    <input type="text" id="name" name="name" value="${(findObj.name)!''}" class="form-control" >
                                </div>
                            	<label class="col-sm-1 control-label label_padding">课程类型</label>
                                <div class="col-sm-3">
                                    <input type="hidden" id="typeHidden" value="${(findObj.type)!''}">
									<select class='form-control' id="type" name="type" value="${(findObj.type)!''}">
                                        <option value="">请选择</option>
										<option value="1">视频</option>
                                        <option value="2">音频</option>
                                        <option value="3">文字</option>
                                        <option value="4">会员</option>
									</select>
                                </div>
                                <label class="col-sm-1 control-label label_padding">是否免费</label>
                                <div class="col-sm-3">
                                    <input type="hidden" id="isFreeHidden" value="${(findObj.isFree)!''}">
                                    <select class='form-control' id="isFree" name="isFree" value="${(findObj.isFree)!''}">
                                        <option value="">请选择</option>
                                        <option value="1">是</option>
                                        <option value="2">否</option>
                                    </select>
                                </div>
                            </div>
                            <!-- 查询条件 -- end -->
                            
                            <!-- 提交按钮 -- start -->
							<div class="form-group">
								<div class="col-sm-3 col-sm-offset-9">
                                    <input type="hidden" id="msg" value="${msg!''}">
                                    <input type="button" onclick="addCourseHtml();" value="新增课程" class="btn btn-primary btn-sm zd-btn-pd1"> &nbsp;&nbsp;
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
							<th style="text-align: center;">课程名称</th>
							<th style="text-align: center;">课程简介</th>
							<th style="text-align: center;">课程价格</th>
                            <th style="text-align: center;">是否免费</th>
                            <th style="text-align: center;">课程类型</th>
						</tr>
					</thead>

					<tbody>
					
						<!-- 按查询条件显示计划报表 -- start -->
						<#list page.list as info>				
						
						<tr>
							<td style="widtd: 10px; text-align: center;">${info_index+1}</td>
							<td style="text-align: center;">
                                <#--<a href="${contextPath}/weixin/course/queryCourseByIdHtml.do?id=${info.id!}">查看</a> &nbsp;&nbsp;|&nbsp;-->
                                <#--<a href="${contextPath}/weixin/h5/queryCoursePlayer.do?id=${info.id!}">查看</a> &nbsp;&nbsp;|&nbsp;-->
                                <a href="${contextPath}/weixin/course/modifyCourseByIdHtml.do?id=${info.id!}">修改</a>&nbsp;&nbsp;|&nbsp;
                                <a onclick="delCourse('${info.id!}')">删除</a>
							</td>
                            <td style="text-align: center;">${info.name!}</td>
                            <td style="text-align: center;">${info.introduce!}</td>
                            <td style="text-align: center;">${info.price!}</td>
							<td style="text-align: center;">
								<#if info.isFree == '1'>是<#else>否</#if>
							</td>
							<td style="text-align: center;">
								<#switch info.type>
									<#case '1'>视频<#break>
									<#case '2'>音频<#break>
									<#case '3'>文字<#break>
									<#case '4'>会员<#break>
								</#switch>
							</td>
						</tr>
						
						</#list>
						<!-- 按查询条件显示计划报表 -- start -->
											
						
					</tbody>
				</table>
			</div>
			<@pages url="${contextPath}/weixin/course/queryCourseList.do" pageCount="${page.pages}" currentPage="${page.pageNum}" />
        </div>
	</form>
	
	<!-- 页尾 -- start -->
	<#include "/pub/footer_res_detail.ftl"/>
	<!-- 页尾 -- start -->
	
	<!-- js引用 -- start -->
	<script type="application/javascript">

        var isFreeHidden = $("#isFreeHidden").val();
        $("#isFree").val(isFreeHidden);
        var typeHidden = $("#typeHidden").val();
        $("#type").val(typeHidden);

		function addCourseHtml() {
			location.href = BASE_PATH + "/weixin/course/addCourseHtml.do";
        }

        function delCourse(id) {
            confMsg("是否删除!", function submitDel(){
                $.ajax({
                    type: "POST",
                    url: BASE_PATH + "/weixin/course/delCourse.do",
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