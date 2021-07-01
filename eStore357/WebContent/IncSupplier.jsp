<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<div class="art-box art-vmenublock">
	<div class="art-box-body art-vmenublock-body">
		<div class="art-bar art-vmenublockheader">
			<h3 class="t"><img src="images/icons/Company.png">Nhà cung cấp</h3>
		</div>
		<div class="art-box art-vmenublockcontent">
			<div class="art-box-body art-vmenublockcontent-body">
				<ul class="art-vmenu">
				<s:iterator value="#request.suppliers">
					<li><a href="products?supplierId=${id}">${name}</a></li>
				</s:iterator>
				</ul> 
				<div class="cleared"></div>
			</div>
		</div>
		<div class="cleared"></div>
	</div>
</div>

