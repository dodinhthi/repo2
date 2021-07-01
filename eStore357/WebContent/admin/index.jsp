<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertTemplate template="AdmLayout.jsp">
	<tiles:putAttribute name="head">
	<!-- CSS và JavaScript của riêng trang này -->
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	<!-- Nội dung trang -->
	
	<div class="art-box art-block" style="width:500px">
		<div class="art-box-body art-block-body">
			<div class="art-bar art-blockheader">
				<h3 class="t"><img src="../images/icons/Card file.png">Login</h3>
			</div>
			<div class="art-box art-blockcontent">
				<div class="art-box-body art-blockcontent-body">
					<s:actionmessage/>
					<s:form action="loginMaster">
						<s:textfield name="id" label="Login Name"/>
						<s:password name="password" label="Password"/>
						<s:submit value="Login"/>
					</s:form>
					<div class="cleared"></div>
				</div>
			</div>
			<div class="cleared"></div>
		</div>
	</div>

	</tiles:putAttribute>
</tiles:insertTemplate>


	
