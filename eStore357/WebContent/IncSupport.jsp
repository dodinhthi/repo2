<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<div class="art-box art-vmenublock">
	<div class="art-box-body art-vmenublock-body">
		<div class="art-bar art-vmenublockheader">
			<h3 class="t">
				<img src="images/icons/Call.png">Hỗ trợ trực tuyến</h3>
		</div>
		<div class="art-box art-vmenublockcontent">
			<div class="art-box-body art-vmenublockcontent-body">
			<s:iterator value="#request.webMasters">
				<a href="ymsgr:sendIM?${email}">
					<img title="${name}"
						src="http://opi.yahoo.com/online?u=${email}&m=g&t=2">
				</a>
			</s:iterator>
				<div class="cleared"></div>
			</div>
		</div>
		<div class="cleared"></div>
	</div>
</div>

