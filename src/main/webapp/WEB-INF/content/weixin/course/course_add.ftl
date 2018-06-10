<!DOCTYPE html>
<html lang="en">
<head>
    <#include "/pub/header_res.ftl"/>
    <title>新增课程</title>
</head>

<body class="gray-bg">
    <form id="submit_form" action="${contextPath}/weixin/course/modifyCourse.do" method="post" name="form1" class="form-horizontal" enctype="multipart/form-data">
        <div class="container-fluid">
            <div class="panel panel-default" style="margin-top: 1px;">
                <div class="panel-heading">新增课程</div>
                <div class="panel-body" style="width:100%">
                    <div class="form-group ">
                        <label class="col-sm-2 control-label "><label style="color:red;">*</label>课程名称:</label>
                        <div class="col-sm-4 ">
                            <input type="text" id="name" name="name" value="${(findObj.name)!''}" class="form-control" required='required' >
                        </div>
                    </div>
                    <div class="form-group ">
                        <label class="col-sm-2 control-label "><label style="color:red;">*</label>课程简介:</label>
                        <div class="col-sm-4 ">
                            <textarea rows="4" cols="70" name="introduce" id="introduce" required='required' />${(findObj.introduce)!''}</textarea>
                        </div>
                    </div>
                    <div class="form-group ">
                        <label class="col-sm-2 control-label "><label style="color:red;">*</label>课程价格:</label>
                        <div class="col-sm-4 ">
                            <input type="number" min="0.01" step="0.01" class='form-control' name="price" value="${(findObj.price)!}" required='required' />
                        </div>
                    </div>
                    <div class="form-group ">
                        <label class="col-sm-2 control-label "><label style="color:red;">*</label>是否免费:</label>
                        <div class="col-sm-4 ">
                            <input type="hidden" id="isFreeHidden" value="${(findObj.isFree)!''}">
                            <select class='form-control' id="isFree" name="isFree" >
                                <option value="1">是</option>
                                <option value="2">否</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group ">
                        <label class="col-sm-2 control-label "><label style="color:red;">*</label>图片类型位置:</label>
                        <div class="col-sm-4 ">
                            <input type="hidden" id="typeLocationHidden" value="${(findObj.typeLocation)!''}">
                            <select class='form-control' id="typeLocation" name="typeLocation" >
                                <option value="0">默认</option>
                                <option value="1">焦点图</option>
                                <option value="2">推荐</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group " id="logoUrlDiv">
                        <label class="col-sm-2 control-label "><label style="color:red;">*</label>logo图片:</label>
                        <div class="col-sm-4 ">
                            <#if findObj.logoUrl??>
                            <a id="imgDown" href="${access_root_path}${(findObj.logoUrl)!''}" download="" class="download-btn" ><img src="${contextPath}/res/pub/css/plugins/imageview/xiazai.png" ></a>
                            </#if>
                            <input type="file" name="logoUrl" />
                            <label style="color:red;">（推荐宽*100px,高*80px）</label>
                        </div>
                    </div>
                    <div class="form-group ">
                        <label class="col-sm-2 control-label "><label style="color:red;">*</label>课程类型:</label>
                        <div class="col-sm-4 ">
                            <input type="hidden" id="typeHidden" value="${(findObj.type)!''}">
                            <select class='form-control' id="type" name="type" onchange="changeCourseType(this);">
                                <option value="1">视频</option>
                                <option value="2">音频</option>
                                <option value="3">文字</option>
                                <option value="4">会员</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group " id="urlDiv">
                        <label class="col-sm-2 control-label "><label style="color:red;">*</label>文件上传:</label>
                        <div class="col-sm-4 ">
                            <#if findObj.url??>
                            <a id="imgDown" href="${access_root_path}${(findObj.url)!''}" download="" class="download-btn" ><img src="${contextPath}/res/pub/css/plugins/imageview/xiazai.png" ></a>
                            </#if>
                            <input type="file" name="url" />
                            <label style="color:red;">（上传文件请不要超过50M）</label>
                        </div>
                    </div>
                    <div class="form-group " id="contentDiv" style="display: none;">
                        <label class="col-sm-2 control-label "><label style="color:red;">*</label>文章:</label>
                        <div class="col-sm-12">

                            <!-- 加载编辑器的容器 -->
                            <script id="container" name="content" type="text/plain" style="height: 500px;">
                                ${(findObj.content)!''}
                            </script>
                            <!-- 配置文件 -->
                            <script type="text/javascript" src="${contextPath}/ueditor/ueditor.config.js"></script>
                            <!-- 编辑器源码文件 -->
                            <script type="text/javascript" src="${contextPath}/ueditor/ueditor.all.js"></script>
                            <!-- 实例化编辑器 -->
                            <script type="text/javascript">
                                var ue = UE.getEditor('container');
                            </script>

                        </div>
                    </div>
                    <div class="col-sm-4 col-sm-offset-8">
                        <input type="hidden" id="id" name="id" value="${(findObj.id)!''}">
                        <input class="btn btn-primary btn-sm zd-btn-pd1" type="button" value="提交" onclick="submit_form();" />
                    </div>
                </div>
            </div>
        </div>
    </form>

    <#include "/pub/footer_res_detail.ftl"/>

    <!-- js引用 -- start -->
    <script type="application/javascript">

        var id = $("#id").val();
        if (id !== '') {
            var isFreeHidden = $("#isFreeHidden").val();
            $("#isFree").val(isFreeHidden);
            var typeLocationHidden = $("#typeLocationHidden").val();
            $("#typeLocation").val(typeLocationHidden);
            var typeHidden = $("#typeHidden").val();
            $("#type").val(typeHidden);
            initType(typeHidden);
        }

        function changeCourseType(obj) {
            var type = $(obj).val();

            initType(type);
        }

        function initType(type) {
            // 视频、音频、文字
            switch (type) {
                case "1":
                case "2":
                    $("#urlDiv").show();
                    $("#contentDiv").hide();
                    break;
                case "3":
                case "4":
                    $("#contentDiv").show();
                    $("#urlDiv").hide();
                    break;
            }
        }

        function submit_form() {
            showMsg("正在上传文件中，请稍候...");
            $("#submit_form").submit();
        }

    </script>
    <!-- js引用 -- start -->

</body>

</html>