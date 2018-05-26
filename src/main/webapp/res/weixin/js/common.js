// mui 初始化
mui.init({
    swipeBack:true //启用右滑关闭功能
});

// system
$(function() {

    /**
     * 提示消息
     */
    var returnMsg = $("#msg").val();
    if(returnMsg != null && returnMsg != undefined && returnMsg.length > 0){
        mui.alert("提示信息", returnMsg);
    }

});

/**
 * tab跳转页面
 * @param jsView
 */
function showRegistLis(jsView) {
    location.href = BASE_PATH + '/h5/car/toH5CarRegistList.htm?listView=' + jsView;
}

/**
 * 初始化日期控件
 */
mui('.h5_dateInput').each(function(i, btn) {
    btn.addEventListener('tap', function() {
        var optionsJson = this.getAttribute('data-options') || '{}';
        var options = JSON.parse(optionsJson);
        var obj = this;
        var picker = new mui.DtPicker(options);
        picker.show(function(rs) {
            obj.value = rs.text;
            picker.dispose();
        });
    }, false);
});


// 选项卡点击事件
mui('.backup').on('tap', 'a', function(e) {
    location.href = this.getAttribute('href');
});

/**
 * 初始化选择控件
 * @param objPicker 填充数据data列表
 * @param objSelectId 选择控件id
 * @param objId value赋值控件ID
 */
var initSelect = function (objPicker, objSelectId, objId) {
    var objSelect = document.getElementById(objSelectId);
    var obj = document.getElementById(objId);
    objSelect.addEventListener('tap', function (event) {
        objPicker.show(function (items) {
            objSelect.value = items[0].text;
            obj.value = items[0].value;
        });
    }, false);
};

/**
 * 验证form表单
 * @param formClass form表单class
 */
var validateForm = function (formClass) {
    var check = true;
    $("." + formClass + " input").each(function () {
        // 若当前input为空，则alert提醒
        if (!this.value || this.value == "") {
            var label = this.previousElementSibling;
            mui.alert(label.innerText + "不允许为空");
            check = false;
            return false;
        }
    });
    // 校验通过，继续执行业务逻辑
    if (check) {
        document.getElementById(formClass).submit();
    }
};

/**
 * 是否显示查询输入框，默认可以为null
 * @param yesOrNo
 */
function isShowSearchBtn(yesOrNo) {

    // 如果有，则赋值之后显示or隐藏
    if (yesOrNo) {
        $("#searchIsFlag").val(yesOrNo);
    }

    // 是否显示
    var searchIsFlag = $("#searchIsFlag").val();
    if (searchIsFlag == 'yes') {
        $("#searchBtn").show();
    } else {
        $("#searchBtn").hide();
    }
}

/**
 * (ifream内部调用父级元素)是否显示查询输入框，默认可以为null
 * @param yesOrNo
 */
function isShowParentSearchBtn(yesOrNo) {

    // 如果有，则赋值之后显示or隐藏
    var searchIsFlag = window.parent.document.getElementById("searchIsFlag");
    if (yesOrNo) {
        searchIsFlag.value = yesOrNo;
    }

    // 是否显示
    if (searchIsFlag.value == 'yes') {
        $(window.parent.document.getElementById("searchBtn")).show();
    } else {
        $(window.parent.document.getElementById("searchBtn")).hide();
    }
}