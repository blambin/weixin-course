<#-- SELECT 标签 type 0:标准型 1:自定义型 -->
<#macro select codeType="" type="0" fieldId="" fieldName="" defValue=""  whereCase="" paramName="" paramValue="" haveHead="true" headName="请选择"  props="" ><#if type=='0'><select id="${fieldId!''}" name="${fieldName!''}"  ${props!' '}>${htmlUtil('select','${type}','${codeType}','${defValue}','${whereCase}','${haveHead}','${headName}')}</select><#elseif type=='1'><select id="${fieldId!''}" name="${fieldName!''}"  ${props!' '}>${htmlUtil('select','${type}','${codeType}','${defValue}','${paramName}','${paramValue}','${haveHead}','${headName}')}</select></#if></#macro>

<#-- 分页 标签 -->
<#macro pages url="" pageCount="0" currentPage="1" >
<input type="hidden" id="currenPage" name="currenPage" value="1">
<div id="pagination" class="text-center" url="${url}" pageCount="${pageCount}" currentPage="${currentPage}" ></div>
</#macro>

<#-- 机构选择 标签 -->
<#macro organ showLevel=""  defValue="" fieldId="" fieldName="" showAreaId='_organTag_'  props="" status="">
${htmlUtil('organ','${showLevel}','${defValue}','${fieldId}','${fieldName}','${showAreaId}','${props}','${status}')}
</#macro>

<#-- token -->
<#macro token >
<input type="hidden"  name="_token_"  value="${_token_!''}">
</#macro>

<#macro cascadeselect codeType="" type="2" defValue="" >${htmlUtil('cascadeselect','${type}','${codeType}','${defValue}')}</#macro>
<#-- 工程webPath -->
<#macro webPath >${htmlUtil('webPath')}</#macro>
<#macro star >
    <label class="col-sm-1 control-label" style="color: red">*</label>
</#macro>
<#macro queryselect codeType="" type="0" defValue="" whereCase="" paramName="" paramValue=""  >${htmlUtil('queryselect','${type}','${codeType}','${defValue}','${whereCase}','${paramName}','${paramValue}')}</#macro>
