/***
 * 查询界面使用
 */
function showMenu() {
		var organid = jQuery("#organId");
		var organOffset = jQuery("#organName").offset();
		var zTree = jQuery.fn.zTree.getZTreeObj("treeDemo");
		if(organid.val().length>0){
			var no = zTree.getNodeByParam("id", organid.val());
			if (no != null) {    
				zTree.checkNode(no, true); 
				//zTree.checkNode(no, true, true);
				zTree.expandNode(no, true, true, true);
		        zTree.selectNode(no);
			}
		}
		
           
		//zTree.checkNode( tree.getNodeByParam("id","020101" ), true ); 
		// var nodes = treeObj.getNodeByParam("id", 1, null);
		jQuery("#menuContent").css({left:organOffset.left + "px", top:organOffset.top + 25 + "px"}).slideDown("fast");
		jQuery("body").bind("mousedown", onBodyDown);
		
	}
	function hideMenu() {
		jQuery("#menuContent").fadeOut("fast");
		jQuery("body").unbind("mousedown", onBodyDown);
	};
	function onBodyDown(event) {
		if (!(event.target.id == "organName" || event.target.id == "menuContent" || jQuery(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
		
	};
	var setting = {
			key: {
				title:"t"
			},
			check: {
				enable: true, //每个节点上是否显示 CheckBox 
				chkStyle: "radio",
				//chkboxType:{ "Y" : "ps", "N" : "ps" },
				radioType: "all"  //radioType: "all"
			    
			},
			view: {
				dblClickExpand: false
				//dblClickExpand: dblClickExpand
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick,
				beforeExpand: beforeExpand,
				beforeClick: beforeClick,
				onAsyncSuccess: onAsyncSuccess,
				onAsyncError: onAsyncError,
				onCheck: onCheck,
				beforeCheck: zTreeBeforeCheck
			}
	};
	var log, className = "dark";
	function beforeClick(treeId, treeNode, clickFlag) {
		//alert("异步获取数据出现异常。"+treeNode.id);
		className = (className === "dark" ? "":"dark");
		// showLog("[ "+getTime()+" beforeClick ]&nbsp;&nbsp;" + treeNode.name );
		//return !treeNode.isParent;//当是父节点 返回false 不让选取  只让叶子节点选中
		return (treeNode.click != false);
	};
	function onClick(e, treeId, treeNode) {
		var zTree = jQuery.fn.zTree.getZTreeObj("treeDemo");
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	};
	function zTreeBeforeCheck(e, treeId, treeNode){
		
	};
	function onCheck(e, treeId, treeNode) {
		var zTree = jQuery.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getCheckedNodes(true),v = "";
		//console.log(treeNode.getCheckStatus().checked);
		if(treeNode.getCheckStatus().checked){
			jQuery("#organName").val(treeNode.name)
			jQuery("#organId").val(treeNode.id);
			
		}else{
			jQuery("#organName").val("")
			jQuery("#organId").val("");
		}
		//callback();
	};
	function onAsyncSuccess(event, treeId, treeNode, msg) {
		if (!msg || msg.length == 0) {
			return;
		}
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		totalCount = treeNode.count;
		if (treeNode.children.length < totalCount) {
			setTimeout(function() {ajaxGetNodes(treeNode);}, perTime);
		} else {
			treeNode.icon = "";
			zTree.updateNode(treeNode);
			zTree.selectNode(treeNode.children[0]);
			endTime = new Date();
			
		}
	};
	function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		alert("异步获取数据出现异常。");
		treeNode.icon = "";
		zTree.updateNode(treeNode);
	};
	function getUrl(treeId, treeNode) {
		var param = "id="+treeNode.id;
		return BASE_PATH+"/business/ordercg/queryOrgPerson.do?" + param;
	};
	function dblClickExpand(treeId, treeNode) {
		return treeNode.level > 0;
	};
	function beforeExpand(treeId, treeNode) {
		if (!treeNode.isAjaxing) {
			startTime = new Date();
			treeNode.times = 1;
			ajaxGetNodes(treeNode, "refresh");
			return true;
		} else {
			alert("zTree 正在下载数据中，请稍后展开节点。。。");
			return false;
		}
	};
	function ajaxGetNodes(treeNode, reloadType) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		if (reloadType == "refresh") {
			zTree.updateNode(treeNode);
		}
		zTree.reAsyncChildNodes(treeNode, reloadType, true);
	};
	
	jQuery(document).ready(function(){
		var zNodes;
		jQuery.ajax({
			type : "post",
			// url : BASE_PATH+"/business/ordercg/queryOrgPerson.do",
			url : BASE_PATH+"/base/organtree/queryOrganTree.do",
			data : {status:1},
			async:true,
			dataType : "text",
			success : function(data) {
				zNodes = eval(data);
				jQuery.fn.zTree.init(jQuery("#treeDemo"), setting, zNodes);
			}
		});
	});
