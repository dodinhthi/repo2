<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertTemplate template="Layout.jsp">
	<tiles:putAttribute name="head">
	<!-- CSS và JavaScript của riêng trang này -->
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	<!-- Nội dung trang -->
	<s:iterator value="myProducts">
		<!--mặt hàng 1-->
		<div class="nn-box nn-box-col">
			<div class="nn-box-col-name">${name}</div>
			<a href="details?id=${id}">
			<img class="nn-box-image" src="images/products/${image}"/>
			</a>
			<div class="nn-box-col-price">$${unitPrice}</div>
			<div class="nn-box-actions">
				<img src="images/icons/Favourites.png"/>
				<img src="images/icons/Letter.png"/>
				<a href="addToCart?productId=${id}">
				<img src="images/icons/Add to basket.png"/>
				</a>
			</div>
			<img class="nn-box-icon" src="images/new_icon.gif"/>
		</div>
		<!--/mặt hàng 1-->
	</s:iterator>
	</tiles:putAttribute>
</tiles:insertTemplate>

