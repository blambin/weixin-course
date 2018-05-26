
$(function () {
	/**
	 * 此方法调用之后页面显示分页栏
	 * yipan added 2016年3月3日 14:09:00
	 */
    $("#pagination").page("form1");

    /**
     * 提示消息
     */
    var returnMsg = $("#msg").val();
    if(returnMsg != null && returnMsg != undefined && returnMsg.length > 0){
        showMsg("提示信息", returnMsg);
    }
});

/**
 * 公共js方法类
 * add by 2015-12-07
 */

/**
 * 在找开的页面中 打开新的tab
 */
function openNewTab(_href,_tabName){
	try{
		window.parent.openTab(_href,_tabName);
	}catch(e){
		console.log("openNewTab>>打开新的Tab出错了..."+"[href:"+_href+"]"); 
	}
}

/**
 * 关闭tab 当前tab
 */
function closeTab(){
	try{
		window.parent.closeTab();
	}catch(e){
		console.log("closeTab error"); 
	}
}

/**
 * 关闭tab名为tabName的tab
 * @param _tabName tab名称
 */
function closeTabByName(_tabName){
	$.each($("a.J_menuTab", parent.document), function(i, n){
		var html = n.innerHTML;
		var title = html.substr(0, html.indexOf("<i"));
		if(title == _tabName + " "){
			$(n).find("i").trigger("click");
			return true;
		}
	});
}

/*
 * 关闭弹出层
 */
function closeLayer(){
	try{
		window.parent.closeLayer();
	}catch(e){
		console.log("closeTab error"); 
	}
}
/**
 * //objE为form表单     
 * 用法  onclick="clearForm(this.form)" 
 * 例子：<input type="button" onclick="clearForm(this.form)" value="清空" class="btn btn-primary btn-sm zd-btn-pd1">
 * @param objE
 */
function clearForm(objE){
    $(objE).find(':input').each(  
        function(){  
            switch(this.type){  
                case 'passsword':  
                case 'select-multiple':  
                case 'select-one':  
                case 'text':  
                $(this).val('');  
                    break;  
                case 'textarea':  
                    $(this).val('');  
                    break;  
                case 'checkbox':  
                case 'radio':  
                    this.checked = false;  
            }  
        }     
    );  
} 

/**
 * 弹出信息 相当于alert 
 * 调用方式 return showMsg('123');
 */
function showMsg(msg,yes){
	layer.alert(msg,{title:'提示信息',shadeClose:false,scrollbar:false,end:function(){
		if(yes){
			yes();
		}
	}}); 
	return false;
}

/**
 * 询问信息 相当于 confirm
 * 调用方式 return confirm('确定要删除?',function(){alert(1)},function(){alert(2)});
 * @param msg 消息内容
 * @param yes 确定回调
 * @param no  取消回调
 */
function confMsg(msg,yes,no){
    layer.confirm(msg,{title:'询问信息',shadeClose:false,scrollbar:false},function(index){
		layer.close(index);
		if(yes){
			yes();
		}
	},function(index){
		layer.close(index);
		if(no){
			no();
		}
	}); 
}