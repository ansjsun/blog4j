<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	// 已安装, 重定向到首页
	if (request.getAttribute("obj") == null) {
		System.out.println("已安装!") ;
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>编辑 - Blog4j</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/themes/admin/style/admin.css" />
</head>
<body>
	<div id="header-wrapper">
		<div id="header">
			<div id="admin-title" class="left">
				<span class="h1"><a href="http://www.ponxu.com">Blog4j安装</a>
					| </span> <span class="small">简单的, 高效的, 易于管理的个人博客!</span>
			</div>
			<div id="admin-func" class="right"></div>
			<div class="clear"></div>
		</div>
	</div>
	<div id="content-wrapper">
		<center>
			${msg }
			<form action="" method="post" onsubmit="return confirm('确定安装吗?')">
				<br>
				<table width="720" class="listt">
					<tbody>
						<tr>
							<td width="150" align="right">博客标题</td>
							<td><input type="text" name="title" class="shadowfocus"
								value="一个博客"></td>
						</tr>

						<tr>
							<td width="150" align="right">博客副标题</td>
							<td><input type="text" name="subtitle" class="shadowfocus"
								value="爱记录,爱分享,爱生活!"></td>
						</tr>

						<tr>
							<td width="150" align="right">登录用户名</td>
							<td><input type="text" name="loginname" class="shadowfocus"
								value="admin"></td>
						</tr>

						<tr>
							<td width="150" align="right">登录密码</td>
							<td><input type="text" name="loginpassword"
								class="shadowfocus" value="admin"></td>
						</tr>

						<tr>
							<td width="150" align="right">统计代码</td>
							<td><textarea name="analyticscode" class="shadowfocus">站长统计</textarea></td>
						</tr>

						<tr>
							<td width="150" align="right">评论代码</td>
							<td><textarea name="commentcode" class="shadowfocus">多说评论</textarea></td>
						</tr>

					</tbody>
				</table>
				<br> <input type="submit" class="btn2 shadowhover2"
					value="确定安装">

			</form>
		</center>
	</div>
	<!-- end content-wrapper -->
	<div id="footer"></div>

	<style>
input[type=text],textarea {
	width: 400px;
}

textarea {
	height: 100px;
}

td {
	padding: 30px;
}
</style>
</body>
</html>