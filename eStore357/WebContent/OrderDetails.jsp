<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertTemplate template="Layout.jsp">
	<tiles:putAttribute name="head">
	<!-- CSS và JavaScript của riêng trang này -->
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	<!-- Nội dung trang -->
	<fieldset>
		<legend>Thông tin đơn hàng</legend>
		<table>
			<tr><td>Id</td><td>:${id}</td></tr>
			<tr><td>Order date</td><td>:${orderDate}</td></tr>
			<tr><td>Required date</td><td>:${requireDate}</td></tr>
			<tr><td>Receiver</td><td>:${receiver}</td></tr>
			<tr><td>Address</td><td>:${address}</td></tr>
			<tr><td>Amount</td><td>:${amount}</td></tr>
			<tr><td>Description</td><td>:${description}</td></tr>
		</table>
	</fieldset>
	<fieldset>
		<legend>Chi tiết đơn hàng</legend>
		<table border="1" width="100%">
		<tr>
			<th>Product Id</th>
			<th>Product Name</th>
			<th>Unit Price</th>
			<th>Discount</th>
			<th>Quantity</th>
			<th>Amount</th>
		</tr>
		<s:iterator value="orderDetails">
		<tr>
			<td>${product.id}</td>
			<td>${product.name}</td>
			<td>${unitPrice}</td>
			<td>${discount}</td>
			<td>${quantity}</td>
			<td>${unitPrice*quantity*(1-discount)}</td>
		</tr>
		</s:iterator>
		</table>
	</fieldset>
	</tiles:putAttribute>
</tiles:insertTemplate>

