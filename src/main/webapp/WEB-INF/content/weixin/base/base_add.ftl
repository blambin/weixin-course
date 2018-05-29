<!DOCTYPE html>
<html lang="en">
<head>
    <#include "/pub/header_res.ftl"/>
    <link href="${contextPath}/res/pub/css/plugins/iCheck/custom.css" rel="stylesheet">
    <title>新增数据配置</title>
</head>

<body class="gray-bg">
    <form id="submit_form" action="${contextPath}/weixin/base/modifyBase.do" method="post" name="form1" class="form-horizontal" enctype="multipart/form-data">
        <div class="container-fluid">
            <div class="panel panel-default" style="margin-top: 1px;">
                <div class="panel-heading">新增数据配置</div>
                <div class="panel-body" style="width:100%">
                    <div class="col-sm-12">
                        <div class="float-e-margins">
                            <div class="ibox-content" style="padding:0 0 0 0">
                                <div class="form-group ">
                                    <label class="col-sm-2 control-label label_padding"><label style="color:red;">*</label>配置名称:</label>
                                    <div class="col-sm-4 ">
                                        <input type="text" id="name" name="name" value="${(findObj.name)!''}" class="form-control" required='required' >
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label class="col-sm-2 control-label label_padding"><label style="color:red;">*</label>配置类型:</label>
                                    <div class="col-sm-4 ">
                                        <input type="text" id="type" name="type" value="${(findObj.type)!''}" class="form-control" required='required' >
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label class="col-sm-2 control-label label_padding"><label style="color:red;">*</label>配置值:</label>
                                    <div class="col-sm-4 ">
                                        <input type="text" id="value" name="value" value="${(findObj.value)!''}" class="form-control" required='required' >
                                    </div>
                                </div>
                                <div class="col-sm-4 col-sm-offset-8">
                                    <input type="hidden" id="id" name="id" value="${(findObj.id)!''}">
                                    <input class="btn btn-primary btn-sm zd-btn-pd1" type="submit" value="提交" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <#include "/pub/footer_res_detail.ftl"/>

    <!-- js引用 -- start -->
    <script type="application/javascript">

    </script>
    <!-- js引用 -- start -->

</body>

</html>