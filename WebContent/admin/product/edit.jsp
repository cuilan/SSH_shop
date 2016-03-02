<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath}/css/Style1.css"
	type="text/css" rel="stylesheet">
</HEAD>

<body>
	<form id="userAction_save_do" name="Form1"
		action="${pageContext.request.contextPath}/adminProduct_update.action"
		method="post" enctype="multipart/form-data">
		<%--隐藏字段 --%>
		<input type="hidden" name="pid" value="<s:property value="model.pid"/>"/>
		<input type="hidden" name="image" value="<s:property value="model.image"/>"/>
		&nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26"><strong><strong>编辑商品</strong></strong></td>
			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					商品名称：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="pname" value="<s:property value="model.pname"/>" class="bg" /></td>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					所属二级分类：</td>
				<td class="ta_01" bgColor="#ffffff"><select
					name="categorySecond.csid">
						<s:iterator var="cs" value="csList">
							<option value="<s:property value="#cs.csid"/>"
								<s:if test="#cs.csid == model.categorySecond.csid">selected</s:if>><s:property
									value="#cs.csname" /></option>
						</s:iterator>
				</select></td>
			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					商品市场价格：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="marketPrice" value="<s:property value="model.marketPrice"/>"
					class="bg" /></td>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					商品商城价格：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="shopPrice" value="<s:property value="model.shopPrice"/>"
					class="bg" /></td>
			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					是否热门：</td>
				<td class="ta_01" bgColor="#ffffff" colspan="3"><select
					name="isHot">
						<option value="1" <s:if test="model.isHot == 1">selected</s:if>>是</option>
						<option value="0" <s:if test="model.isHot == 0">selected</s:if>>否</option>
				</select></td>
			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					商品图片：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="file"
					name="upload" /></td>
			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					商品描述：</td>
				<td class="ta_01" bgColor="#ffffff"><textarea rows="5"
						cols="38" name="pdesc"><s:property value="model.pdesc" /></textarea></td>
			</tr>
			<%--提交 --%>
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4">
					<button type="submit" value="确定" class="button_ok">&#30830;&#23450;</button>
					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>

					<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font> <input
					class="button_ok" type="button" onclick="history.go(-1)" value="返回" />
					<span id="Label1"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
</HTML>