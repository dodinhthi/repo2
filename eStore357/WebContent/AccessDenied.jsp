<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertTemplate template="Layout.jsp">
	<tiles:putAttribute name="head">
	<!-- CSS và JavaScript của riêng trang này -->
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<!-- Nội dung trang -->
		<h1>Access Denied</h1>
		<h4>Vui lòng đăng nhập trước khi sử dụng chức năng này !</h4>
		<a href="javascript:history.back()">Trở về trang trước</a>
	</tiles:putAttribute>
</tiles:insertTemplate>

