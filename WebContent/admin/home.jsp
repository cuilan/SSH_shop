<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style>
body {
	scrollbar-arrow-color: #ffffff;
	scrollbar-base-color: #dee3f7;
}
</style>
<title>Hawks网上商城后台管理系统</title>
</head>
<frameset rows="103,*,55" frameborder=0 border="0" framespacing="0">
	<frame src="${pageContext.request.contextPath}/admin/top.jsp" name="topFrame" scrolling="NO" noresize />
		<frameset cols="159,*" frameborder="0" border="0" framespacing="0" />
			<frame src="${pageContext.request.contextPath}/admin/left.jsp" name="leftFrame" noresize scrolling="YES" />
			<frame src="${pageContext.request.contextPath}/admin/welcome.jsp" name="mainFrame" />
		</frameset>
	<frame src="${pageContext.request.contextPath}/admin/bottom.jsp" name="bottomFrame" scrolling="NO" noresize />
</frameset>
</html>
