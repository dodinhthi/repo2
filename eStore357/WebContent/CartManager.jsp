<%@ page contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertTemplate template="Layout.jsp">
	<tiles:putAttribute name="head">
	<!-- CSS và JavaScript của riêng trang này -->
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	<!-- Nội dung trang -->
	<form action="manageCart" method="post">
		<table width="100%" border="1">
		<tr style="background: lightgray;">
			<th>Id</th>
			<th>Name</th>
			<th>Price</th>
			<th>Discount</th>
			<th>Quantity</th>
			<th>Amount</th>
		</tr>
		<s:iterator value="#session.cart.items.values">
		<tr>
			<td><a href="details?id=${id}">
				<img src="images/products/${image}" 
				style="width:50px;height:50px;"></a></td>
			<td>${name}</td>
			<td>${unitPrice}</td>
			<td>${discount}</td>
			<td><input name="qty${id}" value="${quantity}" style="width:60px"></td>
			<td>${unitPrice*quantity*(1-discount)}</td>
		</tr>
		</s:iterator>
		</table>
		
		<!-- Các nút chức năng -->
		<input type="submit" name="btnUpdate" value="Update">
		<input type="submit" name="btnClear" value="Clear">
		<input type="submit" name="btnContinue" value="Continue">
		<input type="submit" name="btnCheckout" value="Checkout">
	</form>
	</tiles:putAttribute>
</tiles:insertTemplate>

