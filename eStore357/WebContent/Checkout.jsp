<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertTemplate template="Layout.jsp">
	<tiles:putAttribute name="head">
	<!-- CSS và JavaScript của riêng trang này -->
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	<!-- Nội dung trang -->
	<s:form action="purchase">
		<s:textfield name="customer.id" value="%{#session.user.id}" label="Khách hàng" readonly="true"/>
		<s:textfield name="requireDate" label="Ngày cần" cssClass="date"/>
		<s:textfield name="receiver" label="Người nhận"/>
		<s:textfield name="address" label="Địa chỉ nhận"/>
		<s:textfield name="amount" label="Tổng tiền" readonly="true" value="%{#session.cart.totalAmount}"/>
		<s:textarea rows="5" cols="55" name="description" label="Ghi chú"/>
		<s:submit value="Purchase"/>
	</s:form>
	<fieldset>
		<legend>Chi tiết hóa đơn</legend>
		<table border="1" width="100%">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Price</th>
			<th>Discount</th>
			<th>Quantity</th>
			<th>Amount</th>
		</tr>
		<s:iterator value="#session.cart.items.values">
		<tr>
			<td>${id}</td>
			<td>${name}</td>
			<td>${unitPrice}</td>
			<td>${discount}</td>
			<td>${quantity}</td>
			<td>${quantity*unitPrice*(1-discount)}</td>
		</tr>
		</s:iterator>
		</table>
	</fieldset>
	</tiles:putAttribute>
</tiles:insertTemplate>

