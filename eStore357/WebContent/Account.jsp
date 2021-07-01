<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertTemplate template="Layout.jsp">
	<tiles:putAttribute name="head">
	<!-- CSS và JavaScript của riêng trang này -->
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	<!-- Nội dung trang -->
	<img src="images/customers/${photo}" 
		style="width:100px;height:100px">
		
	<s:form action="updateAccount" enctype="multipart/form-data">
		<s:textfield name="id" label="Mã đăng nhập"/>
		<s:textfield name="fullname" label="Họ và tên"/>
		<s:textfield name="email" label="Địa chỉ email"/>
		<s:file name="upload.file" label="Hình ảnh"/>
		<!-- giữ lại hình cũ khi user không muốn cập nhật hình -->
		<s:hidden name="photo"/>
		<s:submit value="Update"/>
	</s:form>
	</tiles:putAttribute>
</tiles:insertTemplate>

