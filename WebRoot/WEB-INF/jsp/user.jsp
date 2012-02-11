<%@ page language="java" import="java.util.*,com.ruanjf.springMVC.commons.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="margin: 11px;">
<div<c:if test="${user.userId != null}"> style="float: left;"</c:if>>
<form id="uform" action="u/uedit" method="post">
<table>
<tr>
<td><span class="red">*</span>用户名:</td>
<td><input type="text" name="username" value="${user.username}" /></td>
</tr>
<tr>
<td><span class="red">*</span>密码:</td>
<td><input type="password" name="password" /></td>
</tr>
<tr>
<td><span class="red">*</span>重复密码:</td>
<td><input type="password" name="password1" /></td>
</tr>
<tr>
<td>个人说明:</td>
<td><textarea name="desc">${user.desc}</textarea></td>
</tr>
<tr>
<td><span class="red">*</span>状态:</td>
<td>
	<label><input type="radio" name="status"${user.status == "USE"?" checked='checked'":"" } value="USE">可用</label>
	<label><input type="radio" name="status"${user.status == "DISUSE"?" checked='checked'":"" } value="DISUSE">不可用</label>
</td>
</tr>
<tr>
<td colspan="2">
<input id="submit" type="button" value="保存" /><input type="hidden" name="userId" value="${user.userId}">
</td>
</tr>
</table>
</form>
</div>
<div style="float: right;"><table id="uclist"></table><div id="ucpager"></div></div>
<c:if test="${user.userId != null}">
<script type="text/javascript">
$(document).ready(function() {
	$("#uclist").jqGrid({
	   	url:'u/clist?userId=${user.userId}',
		datatype: "json",
		height: 285,
	    width:260,
	   	colNames:['编号','客户列表', '时间'],
	   	colModel:[
	   		{name:'companyId',index:'companyId', width:55, sorttype:"int"},
	   		{name:'name',index:'name', width:120, sorttype:"string"},
			{name:'createTime',index:'createTime',formatter:fromatdate, width:87, sorttype:"date"}		
	   	],
	   	rowNum:20,
	   	rowList:[20,40,60],
	   	pager: '#ucpager',
	   	sortname: 'userId',
	    viewrecords: true,
	    sortorder: "companyId",
	    caption:"“${user.username}”的客户列表",
	    jsonReader:{
	    	root:"result",  
	        page: "currpage",  
	        total: "pageCount",  
	        records: "totalCount",  
	        repeatitems: false,  
	        id: "0"  
	    },
	    prmNames : {  
		    page:"currPage",    // 表示请求页码的参数名称  
		    sort: "companyId", // 表示用于排序的列名的参数名称  
		    order: "companyId" // 表示采用的排序方式的参数名称  
		}
	});
	$("#uclist").jqGrid('navGrid','#cpager',{edit:false,add:false,del:false});
});
</script>
</c:if>

<div id="dialog-modal" title="Basic modal dialog">
	<p></p>
</div>
<script type="text/javascript">
//prepare the form when the DOM is ready 
$(document).ready(function() { 
    var options = { 
        //target:        '#output2',   // target element(s) to be updated with server response 
        beforeSubmit:  showRequest,  // pre-submit callback 
        success:       showResponse  // post-submit callback 
 
        // other available options: 
        //url:       url         // override for form's 'action' attribute 
        //type:      type        // 'get' or 'post', override for form's 'method' attribute 
        //dataType:  null        // 'xml', 'script', or 'json' (expected server response type) 
        //clearForm: true        // clear all form fields after successful submit 
        //resetForm: true        // reset the form after successful submit 
 
        // $.ajax options can be used here too, for example: 
        //timeout:   3000 
    }; 
    
    $('#submit').bind('click', function() {
    	$('#uform').ajaxSubmit(options);
    	return false; 
    });
 
    // bind to the form's submit event 
//userForm').submit(function() { 
//// inside event callbacks 'this' is the DOM element so we first 
//// wrap it in a jQuery object and then invoke ajaxSubmit 
//$(this).ajaxSubmit(options); 
//
//// !!! Important !!! 
//// always return false to prevent standard browser submit and page navigation 
//return false; 
//

}); 
 
// pre-submit callback 
function showRequest(formData, jqForm, options) { 
    // formData is an array; here we use $.param to convert it to a string to display it 
    // but the form plugin does this for you automatically when it submits the data 
    // var queryString = $.param(formData); 
 
    // jqForm is a jQuery object encapsulating the form element.  To access the 
    // DOM element for the form do this: 
    // var formElement = jqForm[0]; 
 
    //alert('About to submit: \n\n' + queryString); 
 
    // here we could return false to prevent the form from being submitted; 
    // returning anything other than false will allow the form submit to continue 
    return true; 
} 
 
// post-submit callback 
function showResponse(json, statusText, xhr, $form)  { 
    // for normal html responses, the first argument to the success callback 
    // is the XMLHttpRequest object's responseText property 
 
    // if the ajaxSubmit method was passed an Options Object with the dataType 
    // property set to 'xml' then the first argument to the success callback 
    // is the XMLHttpRequest object's responseXML property 
 
    // if the ajaxSubmit method was passed an Options Object with the dataType 
    // property set to 'json' then the first argument to the success callback 
    // is the json data object returned by the server
    //alert(responseText.code);
    //var json=eval("("+responseText+")");//转换为json对象
    //alert(json.code);
    if(json.code!=0){
    	$("#dialog-modal p").html(json.msg);
    	$( "#dialog-modal" ).dialog({
			title:"提示",
    		height: 200,
			modal: true,
			buttons: {
				"关闭": function() {
					$( this ).dialog( "close" );
				}
			}
		});
    }else{
		$("#dialog-modal p").html("保存成功！是否继续添加<br>用户登录id:"+json.msg);
		$("#dialog-modal").dialog({
			title : "提示",
			height : 200,
			modal : true,
			buttons : {
				"继续添加": function() {
					$( this ).dialog( "close" );
					window.location.reload();
				},
				"否": function() {
					$("#uform input[name='userId']").attr("value", json.msg);
					$( this ).dialog( "close" );
					window.location.href="<%=basePath%>u/"+json.msg;
				}
			}
		});
    }
}
</script>
</div>