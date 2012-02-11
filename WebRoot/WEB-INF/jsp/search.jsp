<%@ page language="java" import="java.util.*,com.ruanjf.springMVC.commons.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="sform" name="sform" action="s" method="post">
	<div>
		<span class="s_ipt_wr"><input type="text" name="key" id="key"
				maxlength="100" class="s_ipt" alt="请输入公司名称或者公司地址">
		</span>
		<span class="s_btn_wr"><input type="button" value="查询"
				id="search" class="s_btn"
				onmousedown="this.className='s_btn s_btn_h'"
				onmouseout="this.className='s_btn'">
		</span>
	</div>
</form>
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
    
    $('#search').bind('click', function() {
    	$('#sform').ajaxSubmit(options);
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
    //var queryString = $.param(formData); 
 
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
		$("#dialog-modal p").html(json.msg);
		$("#dialog-modal").dialog({
			title : "提示",
			height : 200,
			modal : true,
			buttons : {
				"关闭": function() {
					$( this ).dialog( "close" );
				}
			}
		});
    }
} 
</script>
</div>