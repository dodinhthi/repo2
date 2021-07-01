<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertTemplate template="Layout.jsp">
	<tiles:putAttribute name="head">
	<!-- CSS và JavaScript của riêng trang này -->
	<script>
	$(function(){
		$(":button").click(function(){
			$.ajax({
				url:"testjson",
				cache:false,
				success:function(result){
					alert(result.b);
				}
			});
		});
	});
	</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	<!-- Nội dung trang -->
	<input type="button" value="OK">
	</tiles:putAttribute>
</tiles:insertTemplate>

