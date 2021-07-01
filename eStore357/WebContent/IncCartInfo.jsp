<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<script>
function fnUpdateCartInfo(soLuong, tongTien){
	$("#cart-item").html(soLuong);
	$("#cart-amount").html(tongTien);
}
</script>
<div class="art-box art-block">
    <div class="art-box-body art-block-body">
		<div class="art-box art-blockcontent">
			<div class="art-box-body art-blockcontent-body">
				<table width="100%">
				<tr>
					<td width="55">
						<a href="viewCart" title="Xem giỏ hàng !">
							<img id="cart-image" src="images/shoppingcart.gif" width="50" height="50">
						</a>
					</td>
					<td>
						<div><img src="images/postbullets.png"> <span id="cart-item">${cart.itemCount}</span> mặt hàng</div>
						<div><img src="images/postbullets.png"> $ <span id="cart-amount">${cart.totalAmount}</span></div>
						<div><img src="images/postbullets.png"> <a href="viewCart">Xem giỏ hàng</a></div>
					</td>
					<td align="center">
						<img src="images/icons/users.png"><br>
						<span style="font-weight:bold;font-size:20px;color:silver">${config['visitors']}</span>
					</td>
				</tr>
				</table>
				<div class="cleared"></div>
			</div>
		</div>
		<div class="cleared"></div>
    </div>
</div>