<%@ page language="java" import="java.util.*,com.ruanjf.springMVC.commons.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>管理员页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/admin.css">
		<link rel="stylesheet" type="text/css" media="screen" href="css/ui-lightness/jquery-ui-1.8.16.custom.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="css/ui-lightness/ui.jqgrid.css" />
		<script src="js/jquery-1.6.2.min.js" type="text/javascript"></script>
		<script src="js/jquery.form.js" type="text/javascript"></script>
		<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
		<script src="js/jqGrid/jquery.jqGrid.js" type="text/javascript"></script>
		<script src="js/base.js" type="text/javascript"></script>
		<script type="text/javascript">
		var d = new Date(1326532719000);
		//alert(d.toLocaleString());
		$(function(){
			$( "#tabs" ).tabs(<c:if test="${type == 'user'}">{selected:1}</c:if>);
			$("#ulist").jqGrid({
			   	url:'u/ulist',
				datatype: "json",
				height: 345,
                width:300,
			   	colNames:['编号','员工名称', '时间'],
			   	colModel:[
			   		{name:'userId',index:'userId', width:55, sorttype:"int"},
			   		{name:'username',index:'username',formatter:addulink, width:160, sorttype:"string"},
					{name:'createTime',index:'createTime',formatter:fromatdate, width:87, sorttype:"date"}		
			   	],
			   	rowNum:20,
			   	//rowList:[20,40,60],
			   	pager: '#upager',
			   	sortname: 'userId',
			    viewrecords: true,
			    //multiselect: true,
			    sortorder: "userId",
			    jsonReader:{
			    	root:"result",  
			        page: "currentPage",  
			        total: "pageCount",  
			        records: "totalCount",  
			        repeatitems: false,  
			        id: "userId"
			    },
			    prmNames : {  
				    page:"currPage",    // 表示请求页码的参数名称  
				    sort: "userId", // 表示用于排序的列名的参数名称  
				    order: "userId" // 表示采用的排序方式的参数名称  
				}
			});
			$("#ulist").jqGrid('navGrid','#cpager',{edit:false,add:false,del:false,search:false,refresh:false});
			
			$("#clist").jqGrid({
			   	url:'u/clist',
				datatype: "json",
				height: 345,
                width:300,
			   	colNames:['编号','客户名称', '时间'],
			   	colModel:[
			   		{name:'companyId',index:'companyId', width:55, sorttype:"int"},
			   		{name:'name',index:'name',formatter:addclink, width:160, sorttype:"string"},
					{name:'createTime',index:'createTime',formatter:fromatdate, width:87, sorttype:"date"}		
			   	],
			   	rowNum:20,
			   	//rowList:[20,40,60],
			   	pager: '#cpager',
			   	sortname: 'userId',
			    viewrecords: true,
			    //multiselect: true,
			    sortorder: "companyId",
			    jsonReader:{
			    	root:"result",  
			        page: "currentPage",  
			        total: "pageCount",  
			        records: "totalCount",  
			        repeatitems: false,  
			        id: "companyId"  
			    },
			    prmNames : {  
				    page:"currPage",    // 表示请求页码的参数名称  
				    sort: "companyId", // 表示用于排序的列名的参数名称  
				    order: "companyId" // 表示采用的排序方式的参数名称  
				}
			});
			$("#clist").jqGrid('navGrid','#cpager',{edit:false,add:false,del:false,search:false,refresh:false});
			
			// $("#m1").click( function() {
			// 	var s;
			// 	s = $("#ulist").jqGrid('getGridParam','selarrrow');
			// 	alert(s);
			// });
			
			// 工具条
			// $( "#selectable" ).selectable();
        });
		function del(url,rel){
			$.ajax({
			  url: '<%=basePath%>'+url,
			  //cache: false,
			  success: function(json){
				//var json = eval("("+data+")"); //转换为json对象
				$("#dialog-modal p").html(json.msg);
				$("#dialog-modal").dialog({
					title : "提示",
					height : 200,
					modal : true,
					buttons : {
						"关闭" : function() {
							$(this).dialog("close");
							window.location.href='<%=basePath%>'+rel;
						}
					}
				});
			  }
			});
		}
		$(function() {
			$( "input:button" ).button();
			//$( "a", ".demo" ).click(function() { return false; });
		});
		</script>
	</head>

	<body>
		<div id="tabs">
			<ul>
				<!-- <c:if test="${type == 'user'}">
					<li><a href="#tabs-u" onclick="location.href='u/0'">员工列表</a></li>
					<li><a href="#tabs-c" onclick="location.href='u/c'">客户列表</a></li>
				</c:if> -->
					<li><a href="#tabs-c" onclick="location.href='<%=basePath%>u/c'">客户列表</a></li>
					<li><a href="#tabs-u" onclick="location.href='<%=basePath%>u/0'">员工列表</a></li>

			</ul>
			<div id="tabs-c">
				<table id="clist"></table><div id="cpager"></div>
			</div>
			<div id="tabs-u">
				<table id="ulist"></table><div id="upager"></div>
			</div>
		</div>
		<!-- <div id="tab"><table id="clist"></table><div id="pager2"></div></div> -->
		<div id="u">
			<span style="float: left">欢迎！管理员，登录公司客户查询系统</span>
			<span id="user"><a href="c/list">客户清单列表</a><a href="u">个人信息</a><strong class="un"></strong>
			</span>|<a href="u/logout">退出</a>
		</div>
		<div id="m">
			<div class="demo">
				<ol id="selectable">
					<c:if test="${type == 'user'}">
						<li class="ui-state-default"><a href="javascript:void(0)" onclick="del('u/udel/${user.id}','u/0');">删除${msg}</a></li>
						<li class="ui-state-default<c:if test="${edit == false}"> ui-selected</c:if>">
							<a href="javascript:void(0)" onclick="location.href='<%=basePath%>u/0'">添加${msg}</a>
						</li>
					</c:if>
					<c:if test="${type == 'company'}">
						<li class="ui-state-default"><a href="javascript:void(0)" onclick="del('u/cdel/${company.id}','u/c');">删除${msg}</a></li>
						<li class="ui-state-default<c:if test="${edit == false}"> ui-selected</c:if>">
							<a href="javascript:void(0)" onclick="location.href='<%=basePath%>u/c'">添加${msg}</a>
						</li>
					</c:if>
					<li class="ui-state-default<c:if test="${edit == true}"> ui-selected</c:if>">修改${msg}</li>
				</ol>
			</div>
			<c:if test="${type == 'user'}">
				<%@ include file="user.jsp" %>
			</c:if>
			<c:if test="${type == 'company'}">
				<%@ include file="company.jsp" %>
			</c:if>
			<div id="companyInfo" style="display: none;">
				<p></p>
			</div>
			<p id="cp" style="clear: both;padding: 10px 0;">
				<%=Constants.getInstance().getAppInfo().getCopyright() %>
			</p>
		</div>
	</body>
</html>
