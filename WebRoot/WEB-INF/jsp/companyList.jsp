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
		$(function(){
			$("#clist").jqGrid({
			   	url:'u/clist',
				datatype: "json",
				height: 345,
                width:800,
			   	colNames:['编号','客户名称','客户联系人','联系人手机','联系人座机','己方负责人', '时间'],
			   	colModel:[
			   		{name:'companyId',index:'companyId', width:40, sorttype:"int"},
			   		{name:'name',index:'name',formatter:addclink, width:120, sorttype:"string"},
			   		{name:'contactPerson',index:'contactPerson', width:55, sorttype:"string"},
			   		{name:'contactPhone',index:'contactPhone', width:55, sorttype:"int"},
			   		{name:'contactTelephone',index:'contactTelephone', width:55, sorttype:"int"},
			   		{name:'principalName',index:'principalName', width:55, sorttype:"string"},
					{name:'createTime',index:'createTime',formatter:fromatdate, width:87, sorttype:"date"}		
			   	],
			   	rowNum:20,
			   	rowList:[20,40,60],
			   	pager: '#cpager',
			   	sortname: 'userId',
			    viewrecords: true,
			    //multiselect: true,
			    sortorder: "companyId",
			    jsonReader:{
			    	root:"result",  
			        page: "currpage",  
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
			$("#clist").jqGrid('navGrid','#cpager',{edit:false,add:false,del:false});
        });
		</script>
	</head>

	<body>
		<div id="u">
			<span style="float: left">欢迎！管理员，登录公司客户查询系统</span>
			<span id="user"><a href="u">个人信息</a><strong class="un"></strong>
			</span>|<a href="u/logout">退出</a>
		</div>
		<div id="m">
			<div id="tabs">
				<div id="tabs-c">
					<table id="clist"></table><div id="cpager"></div>
				</div>
			</div>
			<p id="cp" style="clear: both;padding: 10px 0;">
				<%=Constants.getInstance().getAppInfo().getCopyright() %>
			</p>
		</div>
	</body>
</html>
