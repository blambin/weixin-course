/**
 * Created by yclimb on 2017/3/20.
 */
$(function(){

});

/**
 * 登记下次验车时间和牌照
 * @param carId
 */
function toRegistNextDateAndLicense(carId) {
    location.href = BASE_PATH + '/h5/car/toH5CarRegistList.htm?listView=h5CarLicenseEdit&attr.carId='+carId;
}
/**
 * 保险登记
 * @param carId
 */
function toRegistInsurance(carId) {
    location.href = BASE_PATH + '/h5/car/toH5CarRegistList.htm?listView=h5CarInsuranceEdit&attr.carId='+carId;
}

/**
 * 进入图片登录列表
 * @param carId 资产编号
 */
function toRegistImgList(carId) {
    location.href = BASE_PATH + '/h5/car/toH5CarRegistImg.htm?carId=' + carId + '&random='+Math.random();
}

/**
 * 提交审核
 * @param carId 资产编号
 */
function auditCarRegist(carId) {

    // 验证是否通过
    var flag = true;

    // 验证是否可以提交审核
    $(".float-right").each(function (i, n) {
        mui.alert('【' + n.nextElementSibling.innerText + '】信息不全，请填写完全之后再提交');
        flag = false;
        return false;
    });

    if (flag && checkIsPickup()) {
        // 提交审核
        mui.confirm('是否确认提交审核，确认？', '温馨提示', ['取消', '确定'], function (e) {
            if (e.index == 1) {
                window.parent.location = BASE_PATH + '/h5/car/modifyCarStatus.htm?carId=' + carId;
            }
        });
    }

}

/**
 * 车务手续登记时：
 * 1：如果没有申请提车费，提示暂不能提交
 * 2：如果提车费申请未审核完，提示暂不能提交。
 * 3：只有待放款和放款成功的才可以提交
 */
var checkIsPickup = function() {
    // 是否验证成功
    var isFlag = true;
    // 是否二手车
    var isNew = $("#isNew").val();
    // 如果是二手车，则不申请提车费且不判断审核状态
    if (isNew != 1) {
        return isFlag;
    }
    // 车辆在调拔过程中不能申请提车费。（调拔出库时，仓储状态为调拔。）
    var whStatus = $("#whStatus").val();
    if (whStatus == '50') {
        return isFlag;
    }
    // 是否申请提车费
    var isPickup = $("#isPickup").val();
    if (isPickup == 2) {
        // 提车费需求审批状态
        var approvalStatus = $("#approvalStatus").val();
        if (approvalStatus != 4 && approvalStatus != 5) {
            mui.alert("提车费申请未审核通过，暂不能提交");
            isFlag = false;
        }
    } else {
        mui.alert("提车费未申请，暂不能提交");
        isFlag = false;
    }

    return isFlag;
};