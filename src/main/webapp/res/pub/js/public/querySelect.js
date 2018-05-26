/**
 * Created by YClimb on 2016/9/19.
 */

var isCompany = $('#companyId').length > 0;
var isVendor = $('#vendorId').length > 0;
var isWare = $('#wareId').length > 0;
var isToWare = $('#toWareId').length > 0;
if(isCompany){
	var myData_1027 = eval('(' + $("#myData_1027").text() + ')');
}
if(isVendor){
	var myData_1004 = eval('(' + $("#myData_1004").text() + ')');
}
if(isWare){
	var myData_1005 = eval('(' + $("#myData_1005").text() + ')');
}
if(isToWare){
	var myData_1005 = eval('(' + $("#myData_1005").text() + ')');
}
jQuery(document).ready(function(){
	if(isCompany){ getmagicSuggest_1027();}
	if(isVendor){  getmagicSuggest();}
	if(isWare){  getmagicSuggest_1005();}
	if(isToWare){ getmagicSuggest_to1005();}
});


/**
 * 获取门店下拉列表方法
 */
//读取下拉框的 值，健
function getmagicSuggest_1027(){
    ms1 = $('#magicsuggest_1027').magicSuggest({
        width: '80%',//宽度
        placeholder: '请选择',
        style:'float:left;width:100%;',
        allowFreeEntries: false,   //这个参数很重要，如果你不需要用户自已创建标签，则用这个
        data: myData_1027.data,
        //selectionStacked: false ,
        selectionStacked: true ,
        //selectionPosition: 'bottom',//标签的位置，如果想让他就在文本框里，则不用写这个参数
        //selectionRenderer: function(data){ //想让标签里显示啥就看这个
        //    return data.name + ' (<b>' + data.nb + '</b>)';
        //}
        maxSelectionRenderer: function(data){ return ""},
        noSuggestionText: '',
        maxSelection:1 //单选按照 0取值
    });

    $(ms1).on('selectionchange', function(e, cb, s){
        var object =cb.getSelection()[0];
        console.log(object);
        if(undefined==object){$("#companyId").val("");}else{
            $("#companyId").val(object.id);
            if ($("#organId")) {
                $("#organId").val(object.id);
            }
            if ($("#companyName")) {
                $("#companyName").val(object.name);
            }
        }
    });
    getStoredCallback_1027(ms1);
}
//获取查询条件回显
function getStoredCallback_1027(ms1){
    var bl = $('#companyId').val();
    if(bl == ''||0==bl) return;
    var array = bl.split(",");
    //设置延迟，否则取不到数据
    setTimeout(function (){
        ms1.setValue(array);
    }, 200);
}

/**
 * 获取门店下拉列表方法
 */
function getmagicSuggest_organ_1027(name){
    ms1 = $('#magicsuggest_1027_' + name).magicSuggest({
        width: '80%',//宽度
        placeholder: '请选择',
        style:'float:left;width:100%;',
        allowFreeEntries: false,   //这个参数很重要，如果你不需要用户自已创建标签，则用这个
        data: myData_1027.data,
        //selectionStacked: false ,
        selectionStacked: true ,
        //selectionPosition: 'bottom',//标签的位置，如果想让他就在文本框里，则不用写这个参数
        //selectionRenderer: function(data){ //想让标签里显示啥就看这个
        //    return data.name + ' (<b>' + data.nb + '</b>)';
        //}
        maxSelectionRenderer: function(data){ return ""},
        noSuggestionText: '',
        maxSelection:1 //单选按照 0取值
    });

    $(ms1).on('selectionchange', function(e, cb, s){
        var object =cb.getSelection()[0];
        console.log(object);
        alert(object);
        if(undefined==object){$("#" + name).val("");}else{
            $("#" + name).val(object.id);
            if ($("#companyName")) {
                $("#companyName").val(object.name);
            }
        }
    });
    getStoredCallback_organ_1027(ms1, name);
}

/**
 * 获取查询条件回显
 */
function getStoredCallback_organ_1027(ms1, name){
    var bl = $('#' + name).val();
    if(bl == ''||0==bl) return;
    var array = bl.split(",");
    //设置延迟，否则取不到数据
    setTimeout(function (){
        ms1.setValue(array);
    }, 200);
}


/**
 * 获取供应商下拉列表
 */
//读取下拉框的 值，健 
function getmagicSuggest(){
	ms1 = $('#magicsuggestVendorId').magicSuggest({
        width: '80%',//宽度
        placeholder: '请选择',
        style:'float:left;width:100%;',
        allowFreeEntries: false,   //这个参数很重要，如果你不需要用户自已创建标签，则用这个
        data: myData_1004.data,
        //selectionStacked: false , 
        selectionStacked: true ,
        //selectionPosition: 'bottom',//标签的位置，如果想让他就在文本框里，则不用写这个参数
        //selectionRenderer: function(data){ //想让标签里显示啥就看这个
        //    return data.name + ' (<b>' + data.nb + '</b>)';
        //}
        maxSelectionRenderer: function(data){ return ""},
        noSuggestionText: '',
        maxSelection:1 //单选按照 0取值 
    });

    $(ms1).on('selectionchange', function(e, cb, s){
    	var object =cb.getSelection()[0];  
    	if(undefined==object){
    		$("#vendorId").val("");
    	}else{
    		$("#vendorId").val(object.id);
    	}
//    		getBrand();
//    		getPayType();
    });
    getStoredCallback(ms1);
}
//获取查询条件回显
function getStoredCallback(ms1){ 
	var bl = $('#vendorId').val();
	if(bl == ''||0==bl) return;
	var array = bl.split(","); 
	//设置延迟，否则取不到数据
	setTimeout(function (){
		ms1.setValue(array);
	}, 200);
}


/**
 * 获取仓库下拉列表方法
 */
//读取下拉框的 值，健 
function getmagicSuggest_1005(){
	ms1 = $('#magicsuggest_1005').magicSuggest({
        width: '80%',//宽度
        placeholder: '请选择',
        style:'float:left;width:100%;',
        allowFreeEntries: false,   //这个参数很重要，如果你不需要用户自已创建标签，则用这个
        data: myData_1005.data,
        //selectionStacked: false , 
        selectionStacked: true ,
        //selectionPosition: 'bottom',//标签的位置，如果想让他就在文本框里，则不用写这个参数
        //selectionRenderer: function(data){ //想让标签里显示啥就看这个
        //    return data.name + ' (<b>' + data.nb + '</b>)';
        //}
        maxSelectionRenderer: function(data){ return ""},
        noSuggestionText: '',
        maxSelection:1 //单选按照 0取值 
});

$(ms1).on('selectionchange', function(e, cb, s){
	var object =cb.getSelection()[0];  
	console.log(object);
	if(undefined==object){$("#wareId").val("");}else{
		$("#wareId").val(object.id);}
		//$("#wareName").val(object.name);
	});
	getStoredCallback_1005(ms1);
}
//获取查询条件回显
function getStoredCallback_1005(ms1){ 
	var bl = $('#wareId').val();
	if(bl == ''||0==bl) return;
	var array = bl.split(","); 
	//设置延迟，否则取不到数据
	setTimeout(function (){
		ms1.setValue(array);
	}, 200);
}


/**
 * 调拨仓库下拉列表
 */
//读取下拉框的 值，健
function getmagicSuggest_to1005() {
    ms1 = $('#magicsuggest_to1005').magicSuggest({
        width: '80%',//宽度
        placeholder: '请选择',
        style: 'float:left;width:100%;',
        allowFreeEntries: false,   //这个参数很重要，如果你不需要用户自已创建标签，则用这个
        data: myData_1005.data,
        selectionStacked: true,
        maxSelectionRenderer: function (data) {
            return ""
        },
        noSuggestionText: '',
        maxSelection: 1 //单选按照 0取值
    });
    $(ms1).on('selectionchange', function (e, cb, s) {
        var object = cb.getSelection()[0];
        console.log(object);
        if (undefined == object) {
            $("#toWareId").val("");
        } else {
            $("#toWareId").val(object.id);
        }
        //$("#wareName").val(object.name);
        toWareIdChange();
    });
    getStoredCallback_to1005(ms1);
}

//获取查询条件回显
function getStoredCallback_to1005(ms1) {
    var bl = $('#toWareId').val();
    if (bl == '' || 0 == bl) return;
    var array = bl.split(",");
    //设置延迟，否则取不到数据
    setTimeout(function () {
        ms1.setValue(array);
    }, 200);
}
