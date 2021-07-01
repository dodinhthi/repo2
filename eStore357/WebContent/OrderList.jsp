<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertTemplate template="Layout.jsp">
	<tiles:putAttribute name="head">
	<style>
		.my-orders td, .my-orders th{
			padding:5px;
		}
	</style>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	<!-- Nội dung trang -->
	<table class="my-orders" border="1" width="100%">
	<tr>
		<th>Id</th>
		<th>Order Date</th>
		<th>Receiver</th>
		<th>Amount</th>
		<th>&nbsp;</th>
	</tr>
	<s:iterator value="orders">
	<tr>
		<td>${id}</td>
		<td>${orderDate}</td>
		<td>${receiver}</td>
		<td>${amount}</td>
		<td>
			<a href="orderDetails?id=${id}">Order details</a>
		</td>
	</tr>
	</s:iterator>
	</table>
	</tiles:putAttribute>
</tiles:insertTemplate>

