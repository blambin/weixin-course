/**
 * Function to setup a CascadeSelect
 * @author 陈继龙 使用回调方法代替OnChange，下拉框选中后处理相应的业务。
 * @update 20160324
 */
/**
 * 在页面使用如下代码，
 * ------------------- begin -------------需要注意括号自己调整------
 <script type="text/javascript">
 <!--
 /*
 从数据库生成，形式如下[[parent, text, value], [parent, text, value]]
 var aryCertType = <@cascadeselect type="2" codeType="998" />  //数据 产生

 window.onload = function(){
form = document.forms[0];
form.certType.setAttribute("subElement", "certName");
form.certName.setAttribute("subElement", "certName1");
form.certName1.setAttribute("subElement", "certName2");
form.certName2.setAttribute("subElement", "certName3");

//form.certType.setAttribute("selectedValue", 739);
form.certType.setAttribute("defaultText", "请选择");
form.certType.setAttribute("defaultValue", "");

//form.certName.setAttribute("selectedValue", 746);
form.certName.setAttribute("defaultText", "请选择");
form.certName.setAttribute("defaultValue", "");

//form.certName1.setAttribute("selectedValue", 7471);
form.certName1.setAttribute("defaultText", "请选择");
form.certName1.setAttribute("defaultValue", "");

form.certName2.setAttribute("selectedValue", 74710);
form.certName2.setAttribute("defaultText", "请选择");
form.certName2.setAttribute("defaultValue", "");

form.certName3.setAttribute("selectedValue", 7471101);
form.certName3.setAttribute("defaultText", "请选择");
form.certName3.setAttribute("defaultValue", "");

/*
定义自己的回调函数 在你每次选择后促发，和onchange一样。 安装需要定义
*/
//var callbackfun=function(v){//方法为v及选中的对象;
///	alert(v.value+"-----"+v.text);
//};
//setupCascadeSelect(form.certType, 0, aryCertType, false,callbackfun);
//setupCascadeSelect(form.certType, 0, aryCertType,false);
//}
/*
 </script>

 <div >
 <form name="form1" method="post" action="">
 <select name="certType">
 </select>
 <select name="certName" >
 </select>
 <select name="certName1">
 </select>
 <select name="certName2">
 </select>
 <select name="certName3" onchange="alert('xx');" >
 </select>
 </form>
 *</div>
 **/
// ---------------------end-------------------- 
/**
 * setupCascadeSelect
 * @param cascadeSelect select object
 * @param parent data index of parent
 * @param nodes array contains data with structure [[parent, text, value],[],...[]]
 * @param [optional] is onchange
 */
function setupCascadeSelect(cascadeSelect, parent, nodes, isOnChange, callback) {
    if (isOnChange == null) {
        isOnChange = false;
    }
    cascadeSelect.onchange = function () {
        if (typeof(callback) === "function") {
            callback(this.options[this.selectedIndex]);
        }
        setupCascadeSelect(this, this.options[this.selectedIndex].value, nodes, true, callback);
    };
    cascadeSelect.getAttr = function (attrName) {
        return this[attrName] ? this[attrName] : this.getAttribute(attrName);
    };
    cascadeSelect.getElementById = function (id) {
        return this.form.elements[id] ? this.form.elements[id] : document.getElementById(id);
    };
    cascadeSelect.setDisplayStyle = function (value) {
        if (!this.multiple) {
            this.style.display = value;
        }
        var subElement = this.getElementById(this.getAttr("subElement"));
        if (subElement != undefined) {
            subElement.setDisplayStyle = this.setDisplayStyle;
        }
    };
    nodes.getChildNodesByParent = function (parent) {
        var childNodes = new Array();
        if (parent + "" == "") {
            return childNodes;
        }
        for (var i = 0; i < nodes.length; i++) {
            if (nodes[i][0] != undefined && nodes[i][0] == parent) {
                childNodes[childNodes.length] = nodes[i];
            }
        }
        return childNodes;
    };
    if (!isOnChange) {
        cascadeSelect.options.length = 0;
        var defaultText = cascadeSelect.getAttr("defaultText");
        var defaultValue = cascadeSelect.getAttr("defaultValue");
        var selectedValue = cascadeSelect.getAttr("selectedValue");
        if (defaultText != undefined && defaultValue != undefined) {
            cascadeSelect.options[cascadeSelect.options.length] = new Option(defaultText, defaultValue);
        }
        // alert(selectedValue + "--2");
        var childNodes = nodes.getChildNodesByParent(parent);
        for (var i = 0; i < childNodes.length; i++) {
            cascadeSelect.options[cascadeSelect.options.length] = new Option(childNodes[i][1], childNodes[i][2]);
            if (selectedValue != undefined && selectedValue == childNodes[i][2]) {
                cascadeSelect.selectedIndex = cascadeSelect.options.length - 1;
            }
        }
    }
    if (cascadeSelect.options.length > 0) {
        cascadeSelect.setDisplayStyle("");
        var subElement = cascadeSelect.getElementById(cascadeSelect.getAttr("subElement"));
        if (subElement != undefined) {
            // 直辖市特殊处理，如果是直辖市，则显示所在区县
            var cityId = cascadeSelect.options[cascadeSelect.selectedIndex].value;
            var specialCity = ['11', '50', '12', '31'];
            if ($.inArray(cityId, specialCity) > -1) {
                cityId = cityId + '01';
            }
            // alert(cityId);
            setupCascadeSelect(subElement, cityId, nodes, false, callback);
        }
    } else {
        cascadeSelect.setDisplayStyle("none");
    }
}
/**
 * 以类的方法实现连动
 */
function CascadeSelect(element, parent, nodes, callback) {

    this.form = element.form;
    this.nodes = nodes;
    this.attributes = {
        "subElement": "subElement",
        "defaultText": "defaultText",
        "defaultValue": "defaultValue",
        "selectedValue": "selectedValue"
    }
    this.build(element, parent, false, callback);
}
CascadeSelect.prototype.getElementById = function (id) {
    return this.form.elements[id] ? this.form.elements[id] : document.getElementById(id);
};
CascadeSelect.prototype.getAttribute = function (element, attrName) {
    return element[attrName] ? element[attrName] : element.getAttribute(attrName);
};
CascadeSelect.prototype.getChildNodesByParent = function (parent) {
    var childNodes = new Array();
    if (parent + "" == "") {
        return childNodes;
    }
    for (var i = 0; i < this.nodes.length; i++) {
        if (this.nodes[i][0] != undefined && this.nodes[i][0] == parent) {
            childNodes[childNodes.length] = this.nodes[i];
        }
    }
    return childNodes;
};
CascadeSelect.prototype.setDisplayStyle = function (element, value) {
    var cs = this;
    if (!element.multiple) {
        element.style.display = value;
    }
    var subElement = this.getElementById(this.getAttribute(element, this.attributes["subElement"]));
    if (subElement != undefined) {
        subElement.setDisplayStyle = function () {
            cs.setDisplayStyle;
        }
    }
};
CascadeSelect.prototype.build = function (element, parent, isOnChange, callback) {
    var cs = this;
    element.onchange = function () {
        if (typeof(callback) === "function") {
            callback(this.options[this.selectedIndex]);
        }
        cs.build(this, this.options[this.selectedIndex].value, true, callback);
    }
    if (!isOnChange) {
        element.options.length = 0;
        var defaultText = this.getAttribute(element, this.attributes["defaultText"]);
        var defaultValue = this.getAttribute(element, this.attributes["defaultValue"]);
        var selectedValue = this.getAttribute(element, this.attributes["selectedValue"]);
        alert(selectedValue);
        if (defaultText != undefined && defaultValue != undefined) {
            element.options[element.options.length] = new Option(defaultText, defaultValue);
        }
        var childNodes = this.getChildNodesByParent(parent);
        for (var i = 0; i < childNodes.length; i++) {
            element.options[element.options.length] = new Option(childNodes[i][1], childNodes[i][2]);
//alert("childNodes[i][2]:" + childNodes[i][2]);
            if (selectedValue != undefined && selectedValue == childNodes[i][2]) {
                alert(selectedValue);  //在有默认值时
                element.selectedIndex = element.options.length - 1;
            }
        }
    }
    if (element.options.length > 0) {
        this.setDisplayStyle(element, "");
        var subElement = this.getElementById(this.getAttribute(element, this.attributes["subElement"]));
        if (subElement != undefined) {
            this.build(subElement, element.options[element.selectedIndex].value, false, callback);
        }
    } else {
        this.setDisplayStyle(element, "none");
    }
};
//-->