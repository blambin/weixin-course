/**
 * Created by YClimb on 2016/9/12.
 */

var maxsize = 2 * 1024 * 1024;  // 2M
var errMsg = "上传的文件不能超过2M（<a href='http://www.tuhaokuai.com/image?b2' target='_blank'>点击此处进行在线图片压缩</a>）！！！";
var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过2M，建议使用Chrome、FireFox浏览器。";
var browserCfg = {};
var ua = window.navigator.userAgent;
if (ua.indexOf("MSIE") >= 1) {
    browserCfg.ie = true;
} else if (ua.indexOf("Firefox") >= 1) {
    browserCfg.firefox = true;
} else if (ua.indexOf("Chrome") >= 1) {
    browserCfg.chrome = true;
}

/**
 * 检查当前文件是否大于指定的大小
 * @param obj_file
 * @returns {boolean}
 * added by yipan 2016年9月12日 15:55:16
 */
function checkFile(obj_file) {
    try {
        if (obj_file.value == "") {
            showMsg("请先选择上传文件！！！");
            return false;
        }
        var filesize = 0;
        if (browserCfg.firefox || browserCfg.chrome) {
            filesize = obj_file.files[0].size;
        } else if (browserCfg.ie) {
            var fso = new ActiveXObject("Scripting.FileSystemObject");
            var f = fso.GetFile(obj_file.value);
            filesize = f.size;
        } else {
            showMsg(tipMsg);
            obj_file.value = null;
            return false;
        }
        if (filesize == -1) {
            showMsg(tipMsg);
            obj_file.value = null;
            return false;
        } else if (filesize > maxsize) {
            showMsg(errMsg);
            obj_file.value = null;
            return false;
        }
    } catch (e) {
        showMsg(e);
        obj_file.value = null;
    }
    return true;
}

/**
 * 判断图片类型
 * @param ths type="file"的javascript对象
 * @return boolean true-符合要求,false-不符合
 * added by yipan 2016年9月12日 15:55:16
 */
function checkImgType(ths){
    if (ths.value == "") {
        showMsg("请上传图片！！！");
        return false;
    } else {
        if (!/\.(jpg|jpeg|png|JPG|PNG)$/.test(ths.value)) {
            showMsg("图片类型必须是jpeg、jpg、png中的一种！！！");
            ths.value = null;
            return false;
        } else {
            return checkFile(ths);
        }
    }
}