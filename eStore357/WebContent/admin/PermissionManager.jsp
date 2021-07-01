<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertTemplate template="AdmLayout.jsp">
	<tiles:putAttribute name="head">
	<!-- CSS và JavaScript của riêng trang này -->
	
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	<!-- Nội dung trang -->
	
<div class="tabs">
	<ul>
		<li><a href="#tabForm">Permission Edition</a></li>
		<li><a href="#tabList">List of Permissions</a></li>
	</ul>

	<div id="tabForm">
	<s:actionmessage/>
	<s:form action="nanagePermission" enctype="multipart/form-data">
		<s:textfield name="id" label="Permission Id" readonly="true"/>
		<s:textfield name="page" label="Page"/>
		<s:textfield name="disables" label="Disable actions" size="44"/>
		<tr>
			<td>&nbsp;</td>
			<td>
				<s:submit value="Insert" action="insertPermission" theme="simple"/>
				<s:submit value="Update" action="updatePermission" theme="simple"/>
				<s:submit value="Delete" action="deletePermission" theme="simple"/>
			</td>
		</tr>
	</s:form>
	</div>
	
	<div id="tabList">
	<table border="1" width="100%">
	<tr>
		<th>Id</th>
		<th>Page</th>
		<th>Disable actions</th>
		<th>&nbsp;</th>
	</tr>
	<s:iterator value="permissions">
	<tr>
		<td>${id}</td>
		<td>${page}</td>
		<td>${disables}</td>
		<td><a href="editPermission?id=${id}">Edit</a></td>
	</tr>
	</s:iterator>
	</table>
	</div>
</div>	
	</tiles:putAttribute>
</tiles:insertTemplate>

