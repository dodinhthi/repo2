<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<tiles:insertTemplate template="AdmLayout.jsp">
	<tiles:putAttribute name="head">
	<!-- CSS và JavaScript của riêng trang này -->
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	<!-- Nội dung trang -->
	<fieldset>
		<legend>Lọc theo thời gian</legend>
		<form method="post">
		<table>
		<tr>
			<td>Từ ngày</td>
			<td><input name="from" class="date" value="${param['from']}"></td>
			<td>Đến ngày</td>
			<td><input name="to" class="date" value="${param['to']}"></td>
			<td><input type="submit" value="Report"></td>
		</tr>
		</table>
		</form>
	</fieldset>
	
	<fieldset>
		<legend>Kết quả thống kê</legend>
		<table border="1" width="80%">
		<tr>
			<th>${title}</th>
			<th>Doanh số</th>
		</tr>
		<s:iterator var="array" value="reportInfo">
		<tr>
			<td>${array[0]}</td>
			<td>
				<fmt:formatNumber value="${array[1]}" 
				maxFractionDigits="3" minFractionDigits="3"/> 
			</td>
		</tr>
		</s:iterator>
		</table>
	</fieldset>
	</tiles:putAttribute>
</tiles:insertTemplate>

