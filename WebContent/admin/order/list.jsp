<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript">
	function addProduct() {
		window.location.href = "${pageContext.request.contextPath}/adminProduct_addPage.action";
	}
	function showDetail(oid) {
		var but = document.getElementById("but"+oid);
		var div1 = document.getElementById("div"+oid);
		if (but.value == "订单详情"){
			// 1.创建异步加载的对象
			var xhr = new XMLHttpRequest();
			ajaxXmlHttp();
			// 2.设置监听
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						div1.innerHTML = xhr.responseText;
					}
				}
			}
			// 3.打开连接
			xhr.open("GET", "${pageContext.request.contextPath}/adminOrder_findOrderItem.action?time=" + new Date().getTime() + "&oid=" + oid, true);
			// 4.发送
			xhr.send(null);
			but.value = "关闭";
		} else{
			div1.innerHTML = "";
			but.value="订单详情";
		}
	}
	/**
	 * 获取AJAX对象
	 * 
	 * @returns {___anonymous1373_1379}
	 */
	function ajaxXmlHttp() {
		var xmlHttp;
		try {
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
				}
			}
		}
		return xmlHttp;
	}
</script>
</head>
<body>
	<br>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/user/list.jsp"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<tbody>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>订单列表</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="5%">序号</td>
								<td align="center" width="10%">订单编号</td>
								<td align="center" width="7%">订单金额</td>
								<td align="center" width="5%">收货人</td>
								<td align="center" width="8%">电话</td>
								<td align="center" width="22%">收货地址</td>
								<td align="center" width="7%">订单状态</td>
								<td width="*" align="center">订单详情</td>
							</tr>
							<s:iterator var="order" value="pageBean.list" status="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<%-- 序号 --%>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="5%"><s:property value="#status.count" /></td>
									<%-- 订单编号 --%>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%"><s:property value="#order.oid" /></td>
									<%-- 订单金额 --%>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="7%"><s:property value="#order.total" /></td>
									<%-- 收货人 --%>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="5%"><s:property value="#order.name" /></td>
									<%-- 电话 --%>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%"><s:property value="#order.phone" /></td>
									<%-- 订单状态 --%>
									<td align="center" style="HEIGHT: 22px"><s:property
											value="#order.addr" /></td>
									<%-- 收货地址 --%>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="22%"><s:if test="#order.state == 1">
											未付款
										</s:if> <s:if test="#order.state == 2">
											已付款，<a
												href="${pageContext.request.contextPath}/adminOrder_updateState.action?oid=<s:property value="#order.oid" />"><font
												color="blue">发货</font></a>
										</s:if> <s:if test="#order.state == 3">
											已发货
										</s:if> <s:if test="#order.state == 4">
											交易完成
										</s:if></td>
									<%-- 订单详情 --%>
									<td align="center" style="HEIGHT: 22px"><input
										id="but<s:property value="#order.oid"/>" type="button"
										value="订单详情"
										onclick="showDetail(<s:property value="#order.oid"/>)" />
										<div id="div<s:property value="#order.oid"/>"></div></td>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
				<tr align="center">
					<td class="ta_01" align="center" bgColor="#AFD1F3">第 <s:property
							value="pageBean.page" />/<s:property value="pageBean.totalPage" />
						页&nbsp;&nbsp;&nbsp;&nbsp; <s:if test="pageBean.page != 1">
							<a
								href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=1">首页</a>|
						<a
								href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=<s:property value="pageBean.page-1"/>">上一页</a>|
						</s:if> <s:if test="pageBean.page != pageBean.totalPage">
							<a
								href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=<s:property value="pageBean.page+1"/>">下一页</a>|
						<a
								href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>
						</s:if>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>

