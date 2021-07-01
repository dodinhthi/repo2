<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertTemplate template="Layout.jsp">
	<tiles:putAttribute name="head">
	    <!-- include the Tools -->
		<%-- <script src="scroller/jquery.tools.min.js"></script> --%>
		<link rel="stylesheet" type="text/css" href="scroller/hscroller.css">
		<script>
		 $(function() {

		    $(".hscroller").scrollable({ 
				mousewheel: true, 
				circular: true, 
				speed: 1000}).autoscroll();
		}); 
		//set server
		$(function() {
			$.ajax({
				url:"layServer",
				 type:"POST",
				 traditional:true,
			    success:function(res,status,xhr){

			    	alert("day la response "+res);
			    	 document.getElementById("switch").value=res;
			    	//location.reload();
				}
			});
		});
		
		function chuyenServer()
		{
			 var ex = document.getElementById("switch").value;
			 $.ajax({
					url:"chuyenServer",
					data:{chuyen:ex},
					error:function(e)
					{
						alert("loi");
					},
				    success:function(response){
				    	alert("ffffffffffffffffff");
				    	location.reload();
					}
				});
		}
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	
<div class="hscroller">
  	<div>		
		<s:iterator value="randomProducts">
		<a href="details?id=${id}">
			<img src="images/products/${image}">
		</a>
		</s:iterator>
	</div>
</div>
        
	      <%--  loai mmmmmmmmmmmm	<select name="loai" id="switch" onchange="chuyenServer()" onloadstart="layserver()" >
			<option value="1" >server1</option>
			<option value="2" >server2</option>
			</select> --%>
			<s:action name="getSer"></s:action>
			<s:select  id="switch" style="width:360px"  label="server" onchange="chuyenServer()"
			 list="#request.server" listKey="ma" listValue="ten" />
		<hr>
		
		<s:iterator value="specialProducts">
			<!--trình bày hàng hóa theo hàng-->
				
			<div class="nn-box nn-box-row">
				<img class="nn-box-image" src="images/products/${image}"/>
				<ul style="display:inline-block;">
					<li>Name: ${name}</li>
					<li>Category: ${category.name}</li>
					<li>Supplier: ${supplier.name}</li>
					<li>Unit price: $ ${unitPrice}</li>
					<li>Unit description: ${unitBrief}</li>
					<li>Sales off: ${discount*100}%</li>
				</ul>
				<img class="nn-box-icon" src="images/new_icon.gif"/>
				<div class="nn-box-actions">
					<img src="images/icons/Favourites.png"/>
					<img src="images/icons/Letter.png"/>
					<img src="images/icons/Add to basket.png"/>
				</div>
			</div>
			<!--/trình bày hàng hóa theo hàng-->
		</s:iterator>
	
		
		
	</tiles:putAttribute>
</tiles:insertTemplate>