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

		<title>用户登录</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/index.css">
	</head>

	<body>
		<div id="u">
			<!-- <span id="user"><strong class="un">igtw</strong>
			</span>|
			<a href="/home/show/home" name="tj_supper" class="last">新版首页</a> -->
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
				<form name="f" action="u" method="POST">
					<div>
					<span>用户名：</span>
					<span class="s_ipt_wr"><input type="text" name="username" id="kw"
							maxlength="100" class="s_ipt">
					</span></div>
					<div>
					<span>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
					<span class="s_ipt_wr"><input type="password" name="password" id="kw"
							maxlength="100" class="s_ipt">
					</span></div>
					<input type="submit" value="登录" class="s_btn">
					<!-- <input type="submit" value="登录" class="s_btn"
							onmousedown="this.className='s_btn s_btn_h'"
							onmouseout="this.className='s_btn'"> -->
					<div><span style="color: red;margin-left: 193px;">${msg}</span></div>
				</form>
			</div>
			<p id="lm"></p>
			<p id="cp" style="padding: 10px 0;">
				<%=Constants.getInstance().getAppInfo().getCopyright() %>
			</p>
		</div>
	</body>
</html>
