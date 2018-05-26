
/*公用js*/
var clickFlag = true;

var uploadImages;
var deleteImages;

$(function() {
	// 隐藏滚动条
	hideProcessBar();
	$("#pagination").page("form1");

	uploadImages = new Array();
	deleteImages = new Array();

	// 注册图片预览点击事件
	$("body").delegate(".image-thumb img", "click", function(){
		showImage($(this).attr("src"));
	});

	// 注册TAB页点击事件
	$(".tab-head li").each(function(){
		var current_li = $(this);
		current_li.click(function(){
			var content_id = $(this).attr("data-id");
			current_li.addClass("active").siblings().removeClass("active");
			$(".tab-body div[id="+content_id+"]").addClass("active").siblings().removeClass("active");
		});
	});
});

/**############# TAB分页效果 ###############*/

var setActive = function(data_id){
	$(".tab-head li[data-id='"+data_id+"']").addClass("active").siblings().removeClass("active");
	$(".tab-body div[id='"+data_id+"']").addClass("active").siblings().removeClass("active");
}


/**############# 遮罩层滚动条 ###############*/

var showProcessBar = function(){
	var processLayer = $(window.parent.document).find("#processBar");
	processLayer.find("span").css("margin-top", "300px");
	processLayer.removeClass("layer-hide").addClass("layer-show");
}

var hideProcessBar = function(){
	$(window.parent.document).find("#processBar").removeClass("layer-show").addClass("layer-hide");
}


/**############# 图片上传与查看 ################*/

// 上传附件点击事件
var selectFile = function(uploadBtn){
	$(uploadBtn).next().click();
}

// 重新选择图片时追加到列表后
var fileListChange = function(fileInput){
	var imageViews = $(fileInput).closest("td").next().find(".preview");
	var fileList = fileInput.files;
	for (var i = 0; i < fileList.length; i++) {
		if (fileList[i].type.indexOf("image") == 0) {
			imageViews.append(createImageView(fileList[i], uploadImages.length+i));
		} else {
			showMsg("文件 " + fileList[i].name + " 不是有效的图片类型。");
			return;
		}
	}
	uploadImages = uploadImages.concat(Array.from(fileInput.files));
}

// 创建图片缩略图
var createImageView = function(image, index){
	var imageView = "<div class='image-preview'>" +
						"<div class='image-delete' onclick='removeImage(this)'></div>" +
						"<div class='image-thumb'>" +
							"<img width='188px' height='143px' data-index='" + index + "' src='" + getImgSrc(image) + "'/>" +
						"</div>" +
						"<div class='image-name'>" + image.name + "</div>" +
					"</div>";
	return imageView;
}

// 获取图片预览路径
var getImgSrc = function(image){
	var imgSrc = null ;
	if (window.createObjectURL!=undefined) {
		imgSrc = window.createObjectURL(image) ;
	} else if (window.URL!=undefined) { // FireFox
		imgSrc = window.URL.createObjectURL(image) ;
	} else if (window.webkitURL!=undefined) { // Chrome
		imgSrc = window.webkitURL.createObjectURL(image) ;
	}
	return imgSrc ;
}

// 删除图片
var removeImage = function(deltDom){
	var removeImage = $(deltDom).closest(".image-preview");
	var removeIndex = removeImage.find("img").attr("data-index");
	var removeSrc = removeImage.find("img").attr("src");

	// 删除在线图片时记录图片ID
	if(removeSrc.indexOf("http") == 0){
		var removeId = removeImage.find("input[name=='imageId']").val();
		deleteImages.push(removeId);
	} else {
		// 上传图片删除时从图片列表去掉，后边的图片索引减一位
		uploadImages.splice(removeIndex,1);
		removeImage.nextAll().each(function(){
			var nextImage = $(this).find("img");
			var newIndex = parseInt(nextImage.attr("data-index"))-1;
			nextImage.attr("data-index", newIndex);
		});
	}

	// 预览图删除
	removeImage.remove();
}

// 图片浏览
var showImage = function(cur_img_src){
	// 获取父级窗口及浏览器高度
	var parentDoc = $("body", window.top.document);
	var winHeight = parseInt($(window.top).height());
	var image_browse = parentDoc.find("#image-browse").css("height", winHeight+"px");

	// 添加图片到缩略图列表
	image_browse.find(".image-foot img").remove();
	$(".image-preview").each(function(){
		var img_src = $(this).find("img").attr("src");
		var mini_class = "image-mini";
		if (img_src == cur_img_src) {
			mini_class += "  image-show";
		}
		// 添加缩略图
		image_browse.find(".image-foot").append("<img class='" + mini_class + "' src='"+ img_src + "'/>");
		// 设置缩略图样式并注册点击事件
		image_browse.find(".image-foot img")
			.css("margin", winHeight*0.1*0.05+"px")
			.css("width", winHeight*0.1*0.9+"px")
			.css("height", winHeight*0.1*0.9+"px")
			.click(function(){
				var image = $(this);
				if (image.hasClass("image-show")) {
					return;
				} else {
					image_browse.find("img.image-mini").each(function(){
						if ($(this).hasClass("image-show")) {
							$(this).removeClass("image-show");
							return;
						}
					});
					image.addClass("image-show");
					image_browse.find("#image-file").attr("src", image.attr("src")).load(function(){
						$(this).css("height", winHeight*0.9+"px");
					});
				}
			});
	});

	// 大图样式
	image_browse.find("#image-file").attr("src", cur_img_src).load(function(){
		$(this).css("height", winHeight*0.9+"px");
	});

	// 显示图片
	image_browse.css("display", "block");
}