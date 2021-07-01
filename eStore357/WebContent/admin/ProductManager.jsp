<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertTemplate template="AdmLayout.jsp">
	<tiles:putAttribute name="head">
	<!-- CSS và JavaScript của riêng trang này -->
	
	<script src="nicEditor/nicEditor.js" type="text/javascript"></script>
	<script type="text/javascript">
	bkLib.onDomLoaded(function() {
		new nicEditor({ fullPanel: true, 
			uploadURI: 'nicUpload' }).panelInstance('description');
	});
	</script>
	<script>
	$(function(){
		$("#pager a").button();
	});
	</script>
	
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	<!-- Nội dung trang -->
	
<div class="tabs">
	<ul>
		<li><a href="#tabForm">Product Edition</a></li>
		<li><a href="#tabList">List of Products</a></li>
	</ul>

	<div id="tabForm">
	<s:actionmessage/>
	<s:form action="nanageProduct" enctype="multipart/form-data">
		<s:textfield name="id" label="Product Id"/>
		<s:textfield name="name" label="Name"/>
		<s:textfield name="unitPrice" label="Unit Price"/>
		<s:textfield name="unitBrief" label="Unit Brief"/>
		<s:textfield name="discount" label="Discount"/>
		<s:textfield name="quantity" label="Quantity"/>
		
		<!-- Định dạng ngày -->
		<s:date var="date" name="productDate" format="MM/dd/yyyy"/>
		<s:textfield cssClass="date" value="%{date}" name="productDate" label="Product Date"/>
		
		<s:radio list="#{true:'Yes', false:'No'}" name="available" label="Available"/>
		<s:radio list="#{true:'Yes', false:'No'}" name="special" label="Special"/>
		<s:radio list="#{true:'Yes', false:'No'}" name="latest" label="Latest"/>
		<s:hidden name="image"/>
		<s:file name="upload.file" label="Image"/>
		<s:select name="category.id" label="Category" 
			 list="categories" listKey="id" listValue="name"/>
		<s:select name="supplier.id" label="Supplier" 
			 list="suppliers" listKey="id" listValue="name"/>
		<s:textarea id="description" name="description" label="Description" rows="5" cols="55"/>
		<tr>
			<td>&nbsp;</td>
			<td>
				<s:submit id="btnInsert" value="Insert" action="insertProduct" theme="simple"/>
				<s:submit id="btnUpdate" value="Update" action="updateProduct" theme="simple"/>
				<s:submit id="btnDelete" value="Delete" action="deleteProduct" theme="simple"/>
			</td>
		</tr>
	</s:form>
	</div>
	
	<div id="tabList">
	
	<!-- Phân trang -->
	<form action="manageProduct#tabList" method="post">
		<input type="submit" name="pageno" value="|<<">
		<input type="submit" name="pageno" value="<<">
		<span id="pager">${admprods.groupNavigationBar}</span>
		<input type="submit" name="pageno" value=">>">
		<input type="submit" name="pageno" value=">>|">
	</form>
	
	<table border="1" width="100%">
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Unit Price</th>
		<th>Discount</th>
		<th>Quantity</th>
		<th>Category</th>
		<th>Supplier</th>
		<th>&nbsp;</th>
	</tr>
	<s:iterator value="Products">
	<tr>
		<td>${id}</td>
		<td>${name}</td>
		<td>${unitPrice}</td>
		<td>${discount}</td>
		<td>${quantity}</td>
		<td>${category.name}</td>
		<td>${supplier.name}</td>
		<td><a id="lnkEdit" href="editProduct?id=${id}">Edit</a></td>
	</tr>
	</s:iterator>
	</table>
	
	</div>
</div>	
	</tiles:putAttribute>
</tiles:insertTemplate>

