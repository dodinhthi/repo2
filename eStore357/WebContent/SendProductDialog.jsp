<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div id="send-dialog" title="Gửi thông tin hàng cho bạn bè / người thân">
	Người gửi:<br>
	<input id="sender">
	Người nhận:<br>
	<input id="receiver">
	Hàng hóa:<br>
	<input id="product">
	Tiêu đề:<br>
	<input id="subject">
	Nội dung:<br>
	<textarea id="content" rows="5"></textarea>
</div>

<style>
#send-dialog input, #send-dialog textarea{
	width:555px;
}
</style>

<script>
$(function(){
	$(".send-product").click(function(){
		$("#send-dialog").dialog("open");
	});
	
	$("#send-dialog").dialog({
		width:600, modal:true, autoOpen:false, 
		hide:"explode", show:"explode", buttons:{
			"Gửi": function(){
				a = $("#send-dialog #sender").val();
				b = $("#send-dialog #receiver").val();
				c = $("#send-dialog #subject").val();
				d = $("#send-dialog #content").val();
				e = $("#send-dialog #product").val();
				$.ajax({
					type:"post",
					url:"send",
					data:{sender:a, receiver:b, 
						subject:c, content:d, product:e},
					success:function(response){
						alert(response);
					}
				});
			},
			"Đóng": function(){
				$("#send-dialog").dialog("close");
			}
		}
	});
})
</script>