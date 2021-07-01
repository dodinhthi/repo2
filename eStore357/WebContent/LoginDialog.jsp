<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	
	<script type="text/javascript">
	$(function(){
		/*--định nghĩa hộp thoại--*/
		$("#login-dialog").dialog({
			width:400, autoOpen:false, modal:true,
			hide:"explode", show:"explode", buttons:{
				"Đăng nhập": function(){
					id = $("#login-id").val();
					pw = $("#login-pw").val();
					/*--đăng nhập bằng ajax--*/
					$.ajax({
						url:"login",
						data:{id:id, password:pw},
						success:function(response){
							alert(result.message);
							if(result.status == true){
								location.reload();
							}
						},
						dataType: 'script'
					});
					
				},
				"Đóng": function(){
					$("#login-dialog").dialog("close");
				}
			}
		});
		/*--mở hộp thoại --*/
		$("#lnkLogin").click(function(){
			$("#login-dialog").dialog("open");
		});
	});
	</script>

	<!-- Khai báo mã HTML của hộp thoại đăng nhập -->
	<div id="login-dialog" title="Đăng nhập" style="display:none">
		Mã đăng nhập:<br>
		<input id="login-id" style="width:360px">
		Mật khẩu:<br>
		<input type="password" id="login-pw" style="width:360px">
	</div>
	
	<!-- ================================================ -->
	
	<script type="text/javascript">
	$(function(){
		/*--định nghĩa hộp thoại--*/
		$("#forgot-dialog").dialog({
			width:400, autoOpen:false, modal:true,
			hide:"explode", show:"explode", buttons:{
				"Nhận mật khẩu": function(){
					id = $("#forgot-id").val();
					em = $("#forgot-email").val();
					/*--tìm mật khẩu bằng ajax--*/
					$.ajax({
						url:"forgot",
						data:{id:id, email:em},
						success:function(response){
							alert(response);
							if(response.indexOf("đã được gửi") > 0){
								$("#forgot-dialog").dialog("close");
							}
						}
					});
				},
				"Đóng": function(){
					$("#forgot-dialog").dialog("close");
				}
			}
		});
		/*--mở hộp thoại --*/
		$("#lnkForgot").click(function(){
			$("#forgot-dialog").dialog("open");
		});
	});
	</script>
	<!-- Khai báo mã HTML của hộp thoại quên mật khẩu -->
	<div id="forgot-dialog" title="Quên mật khẩu" style="display:none">
		Mã đăng nhập:<br>
		<input id="forgot-id" style="width:360px">
		Địa chỉ email:<br>
		<input id="forgot-email" style="width:360px">
	</div>