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
		<li><a href="#tabForm">Supplier Edition</a></li>
		<li><a href="#tabList">List of Suppliers</a></li>
	</ul>

	<div id="tabForm">
	<s:actionmessage/>
	<s:form action="nanageSupplier" enctype="multipart/form-data">
		<s:textfield name="id" label="Supplier Id"/>
		<s:textfield name="name" label="Name"/>
		<s:textfield name="email" label="Email"/>
		<s:textfield name="phone" label="Phone Number"/>
		<s:hidden name="logo"/>
		<s:file name="upload.file" label="Logo"/>
		<tr>
			<td>&nbsp;</td>
			<td>
				<s:submit id="btnInsert" value="Insert" action="insertSupplier" theme="simple"/>
				<s:submit id="btnUpdate" value="Update" action="updateSupplier" theme="simple"/>
				<s:submit id="btnDelete" value="Delete" action="deleteSupplier" theme="simple"/>
			</td>
		</tr>
	</s:form>
	</div>
	
	<div id="tabList">
	<table border="1" width="100%">
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Email</th>
		<th>Phone</th>
		<th>Logo</th>
		<th>&nbsp;</th>
	</tr>
	<s:iterator value="suppliers">
	<tr>
		<td>${id}</td>
		<td>${name}</td>
		<td>${email}</td>
		<td>${phone}</td>
		<td>${logo}</td>
		<td><a id="lnkEdit" href="editSupplier?id=${id}">Edit</a></td>
	</tr>
	</s:iterator>
	</table>
	</div>
</div>	
	</tiles:putAttribute>
</tiles:insertTemplate>

