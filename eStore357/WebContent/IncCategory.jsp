<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<div class="art-box art-vmenublock">
	<div class="art-box-body art-vmenublock-body">
		<div class="art-bar art-vmenublockheader">
			<h3 class="t"><img src="images/icons/Bricks.png">Chủng loại</h3>
		</div>
		<div class="art-box art-vmenublockcontent">
			<div class="art-box-body art-vmenublockcontent-body">
				<ul class="art-vmenu">
				<s:iterator value="#request.categories">
					<li><a href="products?categoryId=${id}">
						<s:if test="#session.language=='vi'">
							${namevn}
						</s:if>
						<s:elseif test="#session.language=='en'">
							${name}
						</s:elseif>
						<s:else>
							${name}
						</s:else>
					</a></li>
				</s:iterator>
				</ul> 
				<div class="cleared"></div>
			</div>
		</div>
		<div class="cleared"></div>
	</div>
</div>

