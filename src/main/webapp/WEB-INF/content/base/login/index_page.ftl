
<!DOCTYPE html>
<html lang="en">
	<head>
		<#include "/pub/header_res.ftl"/>
		<title>首页</title>
	</head>
	<body class="gray-bg">
		<div class="container-fluid">
			<div class="row" style="margin-top:15px;">
				<div class="col-xs-12 col-md-6" >
					<div class="panel panel-primary " style="border-color:#2f4050;">
					   <div class="panel-heading" style="background-color:#2f4050;">
					      <h3 class="panel-title">待办任务</h3>
					   </div>
					   <div id="taskRemindDiv" class="panel-body">
					   	   <table id="taskRemindTbl" class="table table-hover" style="font-size:18px;">
						      
						   </table>
					   </div>
					</div>
				</div>
				<div class="col-xs-12 col-md-6">
					<div class="panel panel-primary " style="border-color:#2f4050;">
					   <div class="panel-heading" style="background-color:#2f4050;">
					      <h3 class="panel-title">问题解答</h3>
					   </div>
					   <div class="panel-body">
					   	   <table class="table table-hover" style="font-size:12px;">
					   	   	<tr>
					   	   		<td>提车费的申请流程？</td>
					   	   		<td><a href="javascript:void(0);" onclick="openNewTab('/qaPage.do?anchor=question1','问题解答');">查看</a></td>
					   	   	</tr>
					   	   	<tr>
					   	   		<td>提车费发放后流程？</td>
					   	   		<td><a href="javascript:void(0);" onclick="openNewTab('/qaPage.do?anchor=question2','问题解答');">查看</a></td>
					   	   	</tr>
					   	   	<tr>
					   	   		<td>手续登记完之后的流程？</td>
					   	   		<td><a href="javascript:void(0);" onclick="openNewTab('/qaPage.do?anchor=question3','问题解答');">查看</a></td>
					   	   	</tr>
					   	   	<tr>
					   	   		<td>地方车务什么时候登记发票及合格证？</td>
					   	   		<td><a href="javascript:void(0);" onclick="openNewTab('/qaPage.do?anchor=question4','问题解答');">查看</a></td>
					   	   	</tr>
					   	   	<tr>
					   	   		<td>地方集采，按照车架号查不到的问题？</td>
					   	   		<td><a href="javascript:void(0);" onclick="openNewTab('/qaPage.do?anchor=question5','问题解答');">查看</a></td>
					   	   	</tr>
					   	   	<tr>
					   	   		<td>地方零采，在订单查询里面状态为采购待审批？</td>
					   	   		<td><a href="javascript:void(0);" onclick="openNewTab('/qaPage.do?anchor=question6','问题解答');">查看</a></td>
					   	   	</tr>
					   	   	<tr>
					   	   		<td>什么时候进行销售出库？</td>
					   	   		<td><a href="javascript:void(0);" onclick="openNewTab('/qaPage.do?anchor=question7','问题解答');">查看</a></td>
					   	   	</tr>
					   	   	
					   	   	<tr>
					   	   		<td></td>
					   	   		<td><a href="javascript:void(0);" onclick="openNewTab('/qaPage.do?anchor=question1','问题解答');">更多</a></td>
					   	   	</tr>
					   	   </table>
					   </div>
					</div>
				</div>
			</div>
		</div>
	<#include "/pub/footer_res_detail.ftl"/>
	<script type="text/javascript">
		jQuery(document).ready(function(){
			taskRemindInit();
		});
		
		function taskRemindInit(){
			$.ajax({
				url:"${contextPath}/business/taskRemind/getRemindList.do",
				async:false,
				success:function(data){
					$("#taskRemindTbl").html('<th>任务种类</th><th style="text-align:center;">任务数量 </th><th>操作</th>');
					$.each(data,function(){
						var html = '<tr';
						if(this.count > 0){
							html +=' style="color:red;"';
						}
						html += '><td>'+this.title+'</td>'+
						'<td align="center">'+this.count+'</td>';
						if(this.count >0){
						    html += '<td><a href="javascript:openNewTab(\''+BASE_PATH+this.url+'\',\''+this.title+'\')" >去处理</a></td></tr>';
						}else{
							html += '<td></td>';
						}
					
						//var html = '<p>'+
					      //'<a href="javascript:openNewTab(\''+BASE_PATH+this.url+'\',\''+this.title+'\')" style="font-size:20px;">'+this.title+'</a>'+
					      //'&nbsp;&nbsp;<span class="badge">'+this.count+'</span>'+
					      //'</p>';
						$("#taskRemindTbl").append(html);
					});
				}
			});
		}
		
		
		
		setInterval(taskRemindInit,600000);
	</script>
	</body>
</html>