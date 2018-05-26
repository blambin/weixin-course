/**
 * 加载更多
 * @param loadType 加载类型
 */
function loadMoreRegistAndAudit(loadType) {

    // tab类型
    var type = $("#tabType").val();

    // 总页数／当前页数
    var pageCount;
    var currentPage = 1;

    pageCount = $("#pageCountRegist").val();
    currentPage = $("#currentPageRegist").val();
    $("#loadMoreRegist").hide();
    $("#loadingRegist").show();

    // 参数设置
    var option = {"currenPage":++currentPage};

    // 加载车辆数据
    $.ajax({
        url: BASE_PATH + "/weixin/h5/queryCourseListJson.do",
        data: option,
        async: true,
        type: "POST",
        success: function(data){
            var json = data;

            // 当前页／总页数
            currentPage = json.pageNum;
            pageCount = json.pages;

            // 循环赋值
            var tempHtml = "";
            $.each(json.list, function (j, info) {

                var tempCard;   // 临时car div
                var bgm_color;  // 上半部分背景颜色
                var btn_color;  // 小badge背景
                var btn_span;   // 小badge文字
                var h5Small = ""; // h5中待small块

                // 根据页面设置不同显示格式
                tempCard = "<div class='card' onclick=\"toRegistDetail('" + info.id + "');\">";
                bgm_color = "bgm-cyan";
                btn_color = "btn-success";
                if (info.isFree === '1') {
                    btn_span = "<small>免费</small>";
                } else {
                    btn_span = "<small>收费</small>";
                }

                switch (info.type) {
                    case '1':
                        h5Small = "视频";
                        break;

                    case '2':
                        h5Small = "音频";
                        break;

                    case '3':
                        h5Small = "文字";
                        break;

                }

                // 多个card块拼接
                tempHtml += tempCard +
                                "<div class='card-header " + bgm_color +"'>" +
                                    "<h2>" + info.name + " <small>" + info.introduce + "</small>" +
                                    "</h2>" +
                                    "<ul class='actions actions-alt'>" +
                                        "<span class='" + btn_color + " badge'>" + btn_span + "</span>" +
                                    "</ul>" +
                                "</div>" +
                                "<div class='card-body card-padding'>" +
                                    "<span>价格：" + info.price + "</span>" +
                                    "<span style='float: right;'>课程类型：" + h5Small + "</span>" +
                                "</div>" +
                            "</div>"
            });

            // 设置显示加载按钮
            // card块代码赋值到加载按钮之前
            $(".load-more-regist").before(tempHtml);

            $("#currentPageRegist").val(currentPage);
            $("#pageCountRegist").val(pageCount);
            $("#loadingRegist").hide();
            // 总页数和当前页数相同时，不加载
            if (pageCount != currentPage) {
                $("#loadMoreRegist").show();
            }

            // 无车辆记录提示信息
            if (!tempHtml) {
                mui.alert('没有查询到记录');
            }
        },
        error: function (data) {
            mui.alert(data.responseText);
        }
    });

}

/**
 * 进入登记详细页面
 * @param carId 车辆资产编号
 */
function toRegistDetail(carId) {

    if (!carId) {
        mui.alert('请选择记录！');
    }

    // 查询输入框是否显示
    // isShowParentSearchBtn('no');

    // 车务登记详情
    window.parent.location = BASE_PATH + '/h5/car/toH5CarRegistDetail.htm?carId=' + carId;
}

/**
 * 切换tab，设置隐藏值
 * @param type
 */
function changeTab(type) {
    $("#tabType").val(type);
}