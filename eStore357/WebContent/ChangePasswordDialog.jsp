<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	
	<script type="text/javascript">
	$(function(){
		/*--định nghĩa hộp thoại--*/
		$("#change-password-dialog").dialog({
			width:400, autoOpen:false, modal:true,
			hide:"explode", show:"explode", buttons:{
				"Đổi mật khẩu": function(){
					id = $("#change-password-id").val();
					pw1 = $("#change-password-pw1").val();
					pw21 = $("#change-password-pw21").val();
					pw22 = $("#change-password-pw22").val();
					if(pw21 != pw22){
						alert("Nhập lại mật khẩu mới không đúng !");
						return;
					}
					/*--tìm mật khẩu bằng ajax--*/
					$.ajax({
						url:"changePassword",
						data:{id:id, pwcu:pw1, pwmoi:pw21},
						success:function(response){
							alert(response);
							if(response.indexOf("thành công") > 0){
								$("#change-password-dialog").dialog("close");
							}
						}
					});
				},
				"Đóng": function(){
					$("#change-password-dialog").dialog("close");
				}
			}
		});
		/*--mở hộp thoại --*/
		$("#lnkChangePassword").click(function(){
			$("#change-password-dialog").dialog("open");
		});
	});
	</script>
	<!-- Khai báo mã HTML của hộp thoại đổi mật khẩu -->
	<div id="change-password-dialog" title="Đổi mật khẩu" style="display:none">
		Mã đăng nhập:<br>
		<input id="change-password-id" style="width:360px" value="${user.id}" readonly>
		Mật khẩu cũ:<br>
		<input type="password" id="change-password-pw1" style="width:360px">
		Mật khẩu mới:<br>
		<input type="password" id="change-password-pw21" style="width:360px">
		Nhập lại mật khẩu mới:<br>
		<input type="password" id="change-password-pw22" style="width:360px">
	</div>