
$(function(){
    /**
     * 查询回显
     */
    backShowBSM();
});


/***
 * 查询时车辆品牌信息联动到系列
 */
var brandChange = function (dom) {
    // 品牌改变事件，获取系列
	var selectedValue = $(dom).find("option:selected").val();
	
    var	carSeriesId=$("#seriesId");
    carSeriesId.empty();
    var	carModelId=$("#modelId");
    if (!selectedValue) {carSeriesId.html("");carModelId.html(""); return}
    $.ajax({
        type   : "POST",
        url    : BASE_PATH + '/business/agreement/series.do',
        data   : {"id": selectedValue},
        async  : false,  //同步请求
        success: function (data) {
            if(data.code == 1) {
            	var html = "<option value=''>请选择</option>";
                var list = data.data;
                for(var i = 0; i < list.length; i++) {
                    html += '<option value="' + list[i].id + '">' + list[i].seriesName + '</option>';
                }
                carSeriesId.html(html);
            } else {
                layer.alert("后台服务异常，获取系列失败");
                carSeriesId.html("");
            }
        }
    });
};

/***
 * 查询时车辆系列信息联动到型号
 */
var seriesChange = function (dom) {
    // 系列改变事件，获取型号
    var selectedValue = $(dom).find("option:selected").val();
    $("#seriesName").val($(dom).find("option:selected").text());
    var carModelId =$("#modelId");  
    carModelId.empty();
    if (!selectedValue) {carModelId.html(""); return}
    $.ajax({
        type   : "POST",
        url    : BASE_PATH + '/business/agreement/model.do',
        async  : false,  //同步请求
        data   : {id: selectedValue},
        success: function (data) {
            if(data.code == 1) {
                var html = "<option value=''>请选择</option>";
                var list = data.data;
                for(var i = 0; i < list.length; i++) {
                    html += '<option value="' + list[i].modelId + '">' + list[i].modelName + '</option>';
                }
                carModelId.html(html);
            } else {
                layer.alert("后台服务异常，获取型号失败");
                carModelId.html("");
            }
        }
    });
};

//var modelChange = function (dom) {
//	$("#modelName").val($(dom).find("option:selected").text());
//};

/**
 * 查询回显品牌、系列、车型
 * added by yipan 2016年7月19日 13:09:51
 */
function backShowBSM() {

    brandChange($("#brandId"));

    $("#seriesId").val($("#seriesIdSearch").val());

    seriesChange($("#seriesId"));

    $("#modelId").val($("#modelIdSearch").val());

    modelChange($("#modelId"));
}


/***
 * 查询车辆型号信息联动到颜色 add by zhangxinfang 2016年10月25日11:47:20
 */
var modelChange = function (dom) {
    // 系列改变事件，获取型号
    var selectedValue = $(dom).find("option:selected").val();
    $("#modelName").val($(dom).find("option:selected").text());
    var carColorId =$("#colorId");
    carColorId.empty();
    if (!selectedValue) {carColorId.html(""); return}
    $.ajax({
        type   : "POST",
        url    : BASE_PATH + '/business/agreement/color.do',
        async  : false,  //同步请求
        data   : {id: selectedValue},
        success: function (data) {
            if(data.code == 1) {
                var html = "<option value=''>请选择</option>";
                var list = data.data;
                for(var i = 0; i < list.length; i++) {
                    html += '<option value="' + list[i].id + '">' + list[i].colorName + '</option>';
                }
                carColorId.html(html);
            } else {
                layer.alert("后台服务异常，获取型号失败");
                carColorId.html("");
            }
        }
    });
};

var colorChange = function (dom) {
	$("#colorName").val($(dom).find("option:selected").text());
};
