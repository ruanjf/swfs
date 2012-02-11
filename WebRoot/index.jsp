<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<link rel="stylesheet" type="text/css" href="css/index.css">
	</head>

	<body>
		<div id="u">
			<span id="user"><strong class="un">igtw</strong>
			</span>|
			<a href="/home/show/home" name="tj_supper" class="last">新版首页</a>
		</div>
		<div id="m">
			<div id="self"><h1>公司自定义句</h1></div>
			<p id="lg">
				<img src="http://www.baidu.com/img/baidu_sylogo1.gif" width="270"
					height="129" usemap="#mp">
				<map name="mp">
					<area shape="rect" coords="40,25,230,95"
						href="http://hi.baidu.com/baidu/" target="_blank"
						title="点此进入 百度的空间">
				</map>
			</p>
			<div id="fm">
				<form name="f" action="/s">
				<div>
					<span>用户名：</span>
					<span class="s_ipt_wr"><input type="text" name="wd" id="kw"
							maxlength="100" class="s_ipt" autocomplete="off">
					</span></div>
					<div>
					<span>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
					<span class="s_ipt_wr"><input type="text" name="wd" id="kw"
							maxlength="100" class="s_ipt" autocomplete="off">
					</span></div>
					<input type="hidden" name="rsv_bp" value="0">
					<input type="hidden" name="rsv_spt" value="3">
					<!-- 
					<span class="s_btn_wr"><input type="submit" value="百度一下"
							id="su" class="s_btn"
							onmousedown="this.className='s_btn s_btn_h'"
							onmouseout="this.className='s_btn'">
					</span> -->
					<div id="sd_1325582008950" style="display: none;"></div>
				</form>
			</div>
			<p id="lm"></p>
			<p>
				<a id="seth" onclick="h(this)"
					href="http://utility.baidu.com/traf/click.php?id=215&amp;url=http://www.baidu.com"
					onmousedown="return ns_c({'fm':'behs','tab':'homepage','pos':0})">把百度设为主页</a><a
					id="setf" onclick="fa(this)" href="javascript:void(0)"
					onmousedown="return ns_c({'fm':'behs','tab':'favorites','pos':0})">把百度加入收藏夹</a>
			</p>
			<p id="cp">
				©2012 Baidu
				<a href="/duty/">使用百度前必读</a>
				<a href="http://www.miibeian.gov.cn" target="_blank">京ICP证030173号</a>
				<img src="http://gimg.baidu.com/img/gs.gif">
			</p>
		</div>
	</body>
</html>
