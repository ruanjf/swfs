<%@ page language="java" import="java.util.*,com.ruanjf.springMVC.commons.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/home.css">
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
                width:260,
			   	colNames:['编号','客户列表', '时间'],
			   	colModel:[
			   		{name:'companyId',index:'companyId', width:55, sorttype:"int"},
			   		{name:'name',index:'name', width:120, sorttype:"string"},
					{name:'createTime',index:'createTime',formatter:fromatdate, width:87, sorttype:"date"}		
			   	],
			   	rowNum:20,
			   	rowList:[20,40,60],
			   	pager: '#cpager',
			   	sortname: 'userId',
			    viewrecords: true,
			    sortorder: "companyId",
			    caption:"客户列表",
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
			$("#clist").jqGrid('navGrid','#cpager',{edit:false,add:false,del:false});
        });
		</script>
	</head>

	<body>
		<div id="companyList"><table id="clist"></table><div id="cpager"></div></div>
		<div id="u">
			<span style="float: left">欢迎！${user.username}，登录公司客户查询系统</span>
			<!-- <span id="user"><a href="u">个人信息</a><strong class="un"></strong></span>| -->
			<a href="u/logout">退出</a>
		</div>
		<div id="m">
			<div id="self"><h1><%=Constants.getInstance().getAppInfo().getSlogan() %></h1></div>
			<p id="lg">
				<img src="<%=Constants.getInstance().getAppInfo().getLogo() %>" width="270"
					height="129" usemap="#mp">
				<map name="mp">
					<area shape="rect" coords="40,25,230,95"
						href="<%=Constants.getInstance().getAppInfo().getIncUrl() %>" target="_blank"
						title="点此进入 公司首页">
				</map>
			</p>
			<div id="fm">
				<%@ include file="search.jsp" %>
			</div>
			<p id="lm"></p>
			<p id="cp" style="padding: 10px 0;">
				<%=Constants.getInstance().getAppInfo().getCopyright() %>
			</p>
		</div>
	</body>
</html>
