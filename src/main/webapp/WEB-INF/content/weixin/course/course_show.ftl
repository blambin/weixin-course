<!DOCTYPE html>
<html lang="en">
<head>
    <#include "/pub/header_res.ftl"/>
    <title>新增课程</title>
</head>

<body class="gray-bg">
    <form action="${contextPath}/weixin/course/modifyCourse.do" method="post" name="form1" class="form-horizontal">
        <div class="container-fluid">
            <div class="panel panel-default" style="margin-top: 1px;">
                <div class="panel-heading">新增课程</div>
                <div class="panel-body" style=" width:100%">
                    <div class="form-group ziding-ibox-modal">
                        <label class="col-sm-2 control-label model_left_z"><label style="color:red;">*</label>课程名称:</label>
                        <div class="col-sm-4 ">
                            <span>${(findObj.name)!''}</span>
                        </div>
                    </div>
                    <div class="form-group ziding-ibox-modal">
                        <label class="col-sm-2 control-label model_left_z"><label style="color:red;">*</label>课程简介:</label>
                        <div class="col-sm-4 ">
                            <textarea rows="4" cols="70" name="introduce" id="introduce" readonly/>${(findObj.introduce)!''}</textarea>
                        </div>
                    </div>
                    <div class="form-group ziding-ibox-modal">
                        <label class="col-sm-2 control-label model_left_z"><label style="color:red;">*</label>课程价格:</label>
                        <div class="col-sm-4 ">
                            <span>${(findObj.price)!''}</span>
                        </div>
                    </div>
                    <div class="form-group ziding-ibox-modal">
                        <label class="col-sm-2 control-label model_left_z"><label style="color:red;">*</label>是否免费:</label>
                        <div class="col-sm-4 ">
                            <input type="hidden" id="isFreeHidden" value="${(findObj.isFree)!''}">
                            <select class='form-control' id="isFree" name="isFree">
                                <option value="1">是</option>
                                <option value="2">否</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group ziding-ibox-modal">
                        <label class="col-sm-2 control-label model_left_z"><label style="color:red;">*</label>图片类型位置:</label>
                        <div class="col-sm-4 ">
                            <input type="hidden" id="typeLocationHidden" value="${(findObj.typeLocation)!''}">
                            <select class='form-control' id="typeLocation" name="typeLocation" >
                                <option value="0">默认</option>
                                <option value="1">焦点图</option>
                                <option value="2">推荐</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group ziding-ibox-modal" id="logoUrlDiv">
                        <label class="col-sm-2 control-label model_left_z"><label style="color:red;">*</label>logo图片:</label>
                        <div class="col-sm-4 ">
                            <#if findObj.logoUrl??>
                            <a id="imgDown" href="${access_root_path}${(findObj.logoUrl)!''}" download="" class="download-btn" ><img src="${contextPath}/res/pub/css/plugins/imageview/xiazai.png" ></a>
                            </#if>
                            <input type="file" name="logoUrl" />
                        </div>
                    </div>
                    <div class="form-group ziding-ibox-modal">
                        <label class="col-sm-2 control-label model_left_z"><label style="color:red;">*</label>课程类型:</label>
                        <div class="col-sm-4 ">
                            <input type="hidden" id="typeHidden" value="${(findObj.type)!''}">
                            <select class='form-control' id="type" name="type">
                                <option value="1">视频</option>
                                <option value="2">音频</option>
                                <option value="3">文字</option>
                            </select>
                        </div>
                    </div>
                    <#if findObj.type == '3'>
                    <div class="form-group ziding-ibox-modal" id="contentDiv">
                        <label class="col-sm-2 control-label model_left_z"><label style="color:red;">*</label>文章:</label>
                        <div class="col-sm-12 ">
                            <!-- 加载编辑器的容器 -->
                            <script id="container" name="content" type="text/plain" style="height: 500px;">

                            </script>
                            <!-- 配置文件 -->
                            <script type="text/javascript" src="${contextPath}/ueditor/ueditor.config.js"></script>
                            <!-- 编辑器源码文件 -->
                            <script type="text/javascript" src="${contextPath}/ueditor/ueditor.all.js"></script>
                            <!-- 实例化编辑器 -->
                            <script type="text/javascript">
                                var ue = UE.getEditor('container');
                                //对编辑器的操作最好在编辑器ready之后再做
                                ue.ready(function() {
                                    //设置编辑器的内容
                                    ue.setContent('${(findObj.content)!}');
                                    //获取html内容，返回: <p>hello</p>
                                    var html = ue.getContent();
                                    //获取纯文本内容，返回: hello
                                    var txt = ue.getContentTxt();
                                });
                            </script>
                        </div>
                    </div>
                    <#else>
                    <div class="form-group ziding-ibox-modal" id="urlDiv">
                        <label class="col-sm-2 control-label model_left_z"><label style="color:red;">*</label>文件上传:</label>
                        <div class="col-sm-4 ">
                            <#if findObj.url??>
                            <a id="imgDown" href="${access_root_path}${(findObj.url)!''}" download="" class="download-btn" ><img src="${contextPath}/res/pub/css/plugins/imageview/xiazai.png" ></a>
                            </#if>
                        </div>
                    </div>
                    </#if>
                    <div class="col-sm-4 col-sm-offset-8">
                        <#--<input class="btn btn-primary btn-sm zd-btn-pd1" type="submit" value="提交" />-->
                    </div>
                </div>
            </div>
        </div>
    </form>

    <#include "/pub/footer_res_detail.ftl"/>

    <!-- js引用 -- start -->
    <script type="application/javascript">

        var isFreeHidden = $("#isFreeHidden").val();
        $("#isFree").val(isFreeHidden);
        var typeLocationHidden = $("#typeLocationHidden").val();
        $("#typeLocation").val(typeLocationHidden);
        var typeHidden = $("#typeHidden").val();
        $("#type").val(typeHidden);


    </script>
    <!-- js引用 -- start -->

</body>

</html>