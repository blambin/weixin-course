$(function(){
	
	/** -- yipan added by 2016年3月22日 17:02:40 -- **/
	// 时间控件验证
	
	// 开始时间
	$("#dateStart").click(function(){
        laydate({istime:true,istoday:false, format:'YYYY-MM-DD hh:mm:ss'});
        $("#dateEnd").val("");
    });
	
	// 结束时间
    $("#dateEnd").click(function(){
        var minDate = $("#dateStart").val();
        var min = laydate.now();
        if(minDate != ""){
            min = minDate;
        }
        laydate({istime:true,istoday:false, min:min, format:'YYYY-MM-DD hh:mm:ss'});
    });
	
});