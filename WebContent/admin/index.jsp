<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>Hawks网上商城后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath}/css/general.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	color: #FFF;
}

.input {
	display: block;
	border: none;
	height: 20px;
	margin-bottom: 5px;
}

.button {
	display: block;
	border: none;
	width: auto;
	height: 25px;
	background: #FFF;
	color: #825960;
}
</style>
</head>
<body style="background: #825996">
	<center style="margin-top: 150px;">
		<s:actionerror />
	</center>
	<form
		action="${pageContext.request.contextPath }/adminUser_login.action"
		method="post">
		<table style="margin-top: 80px" align="center">
			<tr>
				<td>
					<table>
						<tr>
							<td>管理员姓名：</td>
							<td><input type="text" name="username" class="input" /></td>
						</tr>
						<tr>
							<td>管理员密码：</td>
							<td><input type="password" name="password" class="input" /></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="进入管理中心" class="button" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<input type="hidden" name="act" value="signin" />
	</form>
</body>
</html>