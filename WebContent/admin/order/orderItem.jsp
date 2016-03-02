<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<table style="width: 95%">
	<s:iterator var="orderitem" value="list">
		<tr>
			<td><img width="40" height="50"
				src="${pageContext.request.contextPath}/<s:property value="#orderitem.product.image"/>"></td>
			<td><s:property value="#orderitem.count"/></td>
			<td><s:property value="#orderitem.subtotal"/></td>
		</tr>
	</s:iterator>
</table>