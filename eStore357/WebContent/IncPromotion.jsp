<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<script src="scroller/jquery.tools.min.js"></script>
<link rel="stylesheet" type="text/css" href="scroller/vscroller.css">
<script>
$(function() {
    $(".vscroller").scrollable({ 
		vertical: true, 
		mousewheel: true, 
		circular: true, 
		speed: 1000}).autoscroll();
});
</script>

<div class="art-box art-block">
	<div class="art-box-body art-block-body">
		<div class="art-box art-blockcontent">
			<div class="art-box-body art-blockcontent-body nn-center">
<div class="vscroller">
	<div>			
			<s:iterator value="#request.promotions">
				<div class="nn-box nn-box-promo">
					<a href="details?id=${id}">
						<img src="images/products/${image}"></a>
				</div>
			</s:iterator>
	</div>
</div>
				<div class="cleared"></div>
			</div>
		</div>
		<div class="cleared"></div>
	</div>
</div>

