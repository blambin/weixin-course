<!DOCTYPE html>
<html lang="en">
	<head>
		<#include "/pub/header_res.ftl"/>
		<#include "/pub/footer_res.ftl"/>
		 <link href="${contextPath}/res/pub/css/plugins/iCheck/custom.css" rel="stylesheet">
		 <script type="text/javascript">
		 	$(function(){
		 		location.href = '#'+$('#anchor').val();
		 	});
		 </script>
	</head>
	<body class="gray-bg">
	<input type="hidden" id="anchor" value="${mid!}"/>
	<#include "base/login/QA.ftl"/>
	
	</body>
</html>