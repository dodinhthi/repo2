<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertTemplate template="Layout.jsp">
	<tiles:putAttribute name="head">
	<!-- CSS và JavaScript của riêng trang này -->
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	<!-- Nội dung trang -->
	<s:form action="signUp" enctype="multipart/form-data">
		<s:textfield name="id" key="signup.id"/>
		<s:password name="password" key="signup.password"/>
		<s:textfield name="fullname" key="signup.name"/>
		<s:textfield name="email" key="signup.email"/>
		<s:file name="upload.file" key="signup.photo"/>
		<tr>
			<td>&nbsp;</td>
			<td>
				<img src="captcha.gif"/>
				<br><s:text name="signup.message"/>
			</td>
		</tr>
		<tr>
			<td><s:text name="signup.captcha"/></td>
			<td>
				<input name="txtCaptcha">
				<s:actionmessage/>
			</td>
		</tr>
		<s:submit key="signup.button"/>
	</s:form>
	</tiles:putAttribute>
</tiles:insertTemplate>

