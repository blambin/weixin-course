<!DOCTYPE html>
<html lang="en">
<head>
    <#include "/pub/header_res.ftl"/>
    <link href="${contextPath}/res/pub/css/plugins/iCheck/custom.css" rel="stylesheet">
    <title>微信用户列表</title>
</head>
<body class="gray-bg">
<form action="${contextPath}/weixin/user/queryUserPromoterList.do" method="post" name="form1" class="form-horizontal">
    <div class="container-fluid">
        <div class="panel panel-default" style="margin-top: 1px;">
            <div class="panel-heading">微信用户列表</div>
            <div class="panel-body">
                <div class="col-sm-12">
                    <div class="float-e-margins">
                        <div class="ibox-content" style="padding:0 0 0 0">

                            <!-- 查询条件 -- start -->
                            <div class="form-group ">
                                <label class="col-sm-1 control-label label_padding">推广用户昵称</label>
                                <div class="col-sm-3">
                                    <input type="text" id="promoterName" name="promoterName" value="${(findObj.promoterName)!''}" class="form-control" >
                                </div>
                                <label class="col-sm-1 control-label label_padding">用户昵称</label>
                                <div class="col-sm-3">
                                    <input type="text" id="nickname" name="nickname" value="${(findObj.nickname)!''}" class="form-control" >
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
                    <th style="text-align: center;">上级用户昵称</th>
                    <th style="text-align: center;">用户昵称</th>
                    <th style="text-align: center;">总收入金额</th>
                    <th style="text-align: center;">性别</th>
                    <th style="text-align: center;">国家</th>
                    <th style="text-align: center;">省份</th>
                    <th style="text-align: center;">城市</th>
                </tr>
                </thead>

                <tbody>

                <#list page.list as info>
                <tr>
                    <td style="widtd: 10px; text-align: center;">${info_index+1}</td>
                    <td style="text-align: center;">${info.promoterName!}</td>
                    <td style="text-align: center;">${info.nickname!}</td>
                    <td style="text-align: center;">${info.promoterMoney!}</td>
                    <td style="text-align: center;">${info.sexName!}</td>
                    <td style="text-align: center;">${info.country!}</td>
                    <td style="text-align: center;">${info.province!}</td>
                    <td style="text-align: center;">${info.city!}</td>
                </tr>
                </#list>

                </tbody>
            </table>
        </div>
        <@pages url="${contextPath}/weixin/user/queryUserPromoterList.do" pageCount="${page.pages}" currentPage="${page.pageNum}" />
    </div>
</form>

<!-- 页尾 -- start -->
<#include "/pub/footer_res_detail.ftl"/>
<!-- 页尾 -- start -->

<!-- js引用 -- start -->
<!-- js引用 -- start -->
</body>
</html>