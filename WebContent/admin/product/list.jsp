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
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>商品列表</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="right">
						<button type="button" id="add" name="add" value="添加"
							class="button_add" onclick="addProduct()">&#28155;&#21152;
						</button>

					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="10%">序号</td>
								<td align="center" width="10%">商品图片</td>
								<td align="center" width="25%">商品名称</td>
								<td align="center" width="10%">商品价格</td>
								<td align="center" width="17%">是否热门</td>
								<td width="7%" align="center">编辑</td>
								<td width="7%" align="center">删除</td>
							</tr>
							<s:iterator var="product" value="pageBean.list" status="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<%-- 序号 --%>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%"><s:property value="#status.count" /></td>
									<%-- 商品图片 --%>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%"><img alt="图片" width="40" height="45"
										src="${pageContext.request.contextPath}/<s:property value="#product.image" />">
									</td>
									<%-- 商品名称 --%>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="25%"><s:property value="#product.pname" /></td>
									<%-- 商品价格 --%>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%"><s:property value="#product.shopPrice" /></td>
									<%-- 是否热门 --%>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:if test="#product.isHot == 1">
											是
										</s:if> <s:else>
											否
										</s:else></td>
									<%-- 编辑 --%>
									<td align="center" style="HEIGHT: 22px"><a
										href="${pageContext.request.contextPath}/adminProduct_edit.action?pid=<s:property value="#product.pid"/>">
											<img
											src="${pageContext.request.contextPath}/images/i_edit.gif"
											border="0" style="CURSOR: hand">
									</a></td>
									<%-- 删除 --%>
									<td align="center" style="HEIGHT: 22px"><a
										href="${pageContext.request.contextPath}/adminProduct_delete.action?pid=<s:property value="#product.pid"/>">
											<img
											src="${pageContext.request.contextPath}/images/i_del.gif"
											width="16" height="16" border="0" style="CURSOR: hand">
									</a></td>
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
								href="${pageContext.request.contextPath}/adminProduct_findAll.action?page=1">首页</a>|
						<a
								href="${pageContext.request.contextPath}/adminProduct_findAll.action?page=<s:property value="pageBean.page-1"/>">上一页</a>|
						</s:if> <s:if test="pageBean.page != pageBean.totalPage">
							<a
								href="${pageContext.request.contextPath}/adminProduct_findAll.action?page=<s:property value="pageBean.page+1"/>">下一页</a>|
						<a
								href="${pageContext.request.contextPath}/adminProduct_findAll.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>
						</s:if>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>

