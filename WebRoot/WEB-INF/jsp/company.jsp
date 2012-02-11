<%@ page language="java"
	import="java.util.*,com.ruanjf.springMVC.commons.*"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="margin: 10px;">
	<form id="cform" action="u/cedit" method="post">
		<table>
			<tr>
				<td>
					<span class="red">*</span>公司名称：
				</td>
				<td>
					<input type="text" name="name" width="10" value="${company.name}" /><span class="red">注：不包含地址</span>
				</td>
			</tr>
			<tr>
				<td>
					<span class="red">*</span>搜索关键字：
				</td>
				<td>
					<textarea name="searchAddress" rows="4">${company.searchAddress}</textarea><span class="red">注：关键字之间用逗号或者空格分开<span class="red">
				</td>
			</tr>
			<tr>
				<td>
					<span class="red">*</span>公司地址：
				</td>
				<td><%@ include file="address.jsp"%><!-- <br/>详细地址： --><input type="text" name="address" value="${company.address}" />
				</td>
			</tr>
			<tr>
				<td>
					<span class="red">*</span>公司联系人：
				</td>
				<td>
					<input type="text" name="contactPerson" value="${company.contactPerson}" />
				</td>
			</tr>
			<tr>
				<td>
					联系人手机：
				</td>
				<td>
					<input type="text" name="contactPhone" value="${company.contactPhone}" />
				</td>
			</tr>
			<tr>
				<td>
					联系人座机：
				</td>
				<td>
					<input type="text" name="contactTelephone" value="${company.contactTelephone}" />
				</td>
			</tr>
			<tr>
				<td>
					<span class="red">*</span>己方负责人：
				</td>
				<td>
					<select name="principal">
						<option value="">请选择</option>
					<c:forEach items="${users}" var="user">
         				<option value="${user.userId}" <c:if test="${company.principal==user.userId}">selected="selected"</c:if> >${user.username}</option>
					</c:forEach> 
					</select>
				</td>
			</tr>
			<tr>
				<td>
					备注：
				</td>
				<td>
					<textarea name="desc" rows="4">${company.desc}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input id="submit" type="button" value="保存" />
					<input type="hidden" name="companyId" value="${company.companyId}">
				</td>
			</tr>
		</table>
	</form>
	<div id="dialog-modal" title="Basic modal dialog">
		<p></p>
	</div>
	<script type="text/javascript">
	//prepare the form when the DOM is ready 
	$(document).ready(function() {
		var options = {
			//target:        '#output2',   // target element(s) to be updated with server response 
			beforeSubmit : showRequest, // pre-submit callback 
			success : showResponse
		// post-submit callback 

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
			$('#cform').ajaxSubmit(options);
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
	function showResponse(json, statusText, xhr, $form) {
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
		if (json.code != 0) {
			$("#dialog-modal p").html(json.msg);
			$("#dialog-modal").dialog({
				title : "提示",
				height : 200,
				modal : true,
				buttons : {
					"关闭" : function() {
						$(this).dialog("close");
					}
				}
			});
		} else {
			$("#dialog-modal p").html("保存成功！是否继续添加");
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
						$("#cform input[name='companyId']").attr("value", json.msg);
						$( this ).dialog( "close" );
						window.location.href="<%=basePath%>u/c/"+json.msg;
					}
				}
			});
		}
	}
</script>
</div>