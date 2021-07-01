<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertTemplate template="Layout.jsp">
	<tiles:putAttribute name="head">
	<!-- CSS và JavaScript của riêng trang này -->
	<style>
		input[name=pageno]{
			width:40px; height:40px;
			border-radius:20px;
			font-size: 10px;
			padding:0px;
		}
		form[action=products]{text-align: center;}
	</style>
	<style id="transfer-class">
		.cart-transfer{
			background: url(?);
			background-size:100% 100%;
		}
	</style>
	<script>
	 $(function(){
		transferClass = $("#transfer-class").html();
		
		$(".add-to-cart").click(function(){
			id = $(this).attr("data-id");
			$.ajax({
				url:"addToCart",
				data:{id:id},
				success:function(response){
					eval(response);
				}
			});
			
			// thay the ? bang duong dan cua anh duoc chon
			transferCss = transferClass.replace("?", $("#" + id).attr("src"));
			// cap nhat noi dung the <style id="transfer-class">
			$("#transfer-class").html(transferCss);
			
			options = {to:"#cart-image", className:"cart-transfer"}
			$("#" + id).effect("transfer", options, 500, function(){
				$("#cart-image").effect("bounce", 200);
			});
			
			return false;
		});
	}); 
	</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	
	<!-- Phân trang -->
	<form action="products">
		<input type="submit" name="pageno" value="|<<">
		<input type="submit" name="pageno" value="<<">
		<input type="submit" name="pageno" value=">>">
		<input type="submit" name="pageno" value=">>|">
	</form>
	
	<!-- danh sách hàng hóa -->
	
	<s:iterator value="products">
		<!--mặt hàng 1-->
		<div class="nn-box nn-box-col">
			<div class="nn-box-col-name">${name}</div>
			<a href="details?id=${id}">
			<img id="${id}" class="nn-box-image" src="images/products/${image}"/>
			</a>
			<div class="nn-box-col-price">$${unitPrice}</div>
			<div class="nn-box-actions">
				<img src="images/icons/Favourites.png"/>
				<img class="send-product" src="images/icons/Letter.png"/>
				<a class="add-to-cart" data-id="${id}" href="#">
				<img src="images/icons/Add to basket.png"/>
				</a>
			</div>
			<s:if test="special">
				<img class="nn-box-icon" src="images/special_icon.gif"/>
			</s:if>
			<s:elseif test="latest">
				<img class="nn-box-icon" src="images/new_icon.gif"/>
			</s:elseif>
			<s:elseif test="discount > 0">
				<img class="nn-box-icon" src="images/promo_icon.gif"/>
			</s:elseif>
		</div>
		<!--/mặt hàng 1-->
	</s:iterator>
	
	<s:include value="SendProductDialog.jsp"/>
	
	</tiles:putAttribute>
</tiles:insertTemplate>

