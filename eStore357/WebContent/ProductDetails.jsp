<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertTemplate template="Layout.jsp">
	<tiles:putAttribute name="head">
	<!-- CSS và JavaScript của riêng trang này -->
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	
	<!-- Thông tin chi tiết hàng hóa -->
	<table>
	<tr>
		<td>
			<img src="images/products/${image}" 
				style="width:300px;height:300px;">
		</td>
		<td>
			<ul>
				<li>Id: ${id}</li>
				<li>Name: ${name}</li>
				<li>Unit Price: ${unitPrice}</li>
				<li>Discount: ${discount}</li>
				<li>Unit Brief: ${unitBrief}</li>
				<li>Category: ${category.name}</li>
				<li>Supplier: ${supplier.name}</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			${description}
		</td>
	</tr>
	</table>
	
	<div class="tabs">
		<ul>
			<li><a href="#Tab1">Hàng cùng loại</a></li>
			<li><a href="#Tab2">Hàng cùng nhà cung cấp</a></li>
			<li><a href="#Tab3">Hàng đã xem</a></li>
		</ul>
		<div id="Tab1">
			<div class="nn-hang-da-xem">
			<s:iterator value="category.products">
				<a href="details?id=${id}" title="${name}">
					<img src="images/products/${image}"/>
				</a>
			</s:iterator>
			</div>
		</div>
		<div id="Tab2">
			<div class="nn-hang-da-xem">
			<s:iterator value="supplier.products">
				<a href="details?id=${id}" title="${name}">
					<img src="images/products/${image}"/>
				</a>
			</s:iterator>
			</div>
		</div>
		<div id="Tab3">
			<div class="nn-hang-da-xem">
			<s:iterator value="viewedProducts">
				<a href="details?id=${id}" title="${name}">
					<img src="images/products/${image}"/>
				</a>
			</s:iterator>
			</div>
		</div>
	</div>
	</tiles:putAttribute>
</tiles:insertTemplate>

