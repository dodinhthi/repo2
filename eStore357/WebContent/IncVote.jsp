<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

	<div class="art-box art-block">
		<div class="art-box-body art-block-body">
			<div class="art-bar art-blockheader">
				<h3 class="t"><img src="images/icons/Card file.png">Bình chọn Website</h3>
			</div>
			<div class="art-box art-blockcontent">
				<div class="art-box-body art-blockcontent-body">
					<table id="vote-form" width="100%">
					<tr>
						<td width="20"><input type="radio" name="rdoVote" value="0" id="V0"/></td>
						<td><label for="V0">Tuyệt vời</label></td>
					</tr>
					<tr>
						<td><input type="radio" name="rdoVote" value="1" id="V1"/></td>
						<td><label for="V1">Rất tốt</label></td>
					</tr>
					<tr>
						<td><input type="radio" name="rdoVote" value="2" id="V2" checked/></td>
						<td><label for="V2">Tốt</label></td>
					</tr>
					<tr>
						<td><input type="radio" name="rdoVote" value="3" id="V3"/></td>
						<td><label for="V3">Thường</label></td>
					</tr>
					<tr>
						<td><input type="radio" name="rdoVote" value="4" id="V4"/></td>
						<td><label for="V4">Tồi</label></td>
					</tr>
					<tr>
						<td colspan="2" align="right"><a id="lnkVote" href="#">Gửi</a></td>
					</tr>
					</table>
					<div id="vote-image"></div>
					<div class="cleared"></div>
				</div>
			</div>
			<div class="cleared"></div>
		</div>
	</div>
<script>
$(function(){
	$("#lnkVote").click(function(){
		
		for(var i=0;i<=4;i++){
			var chk = $("#V"+i).attr("checked");
			if(chk == true || chk == 'checked'){
				v = i;
			}
		}
		
		$.ajax({
			url:"vote",
			data:{vote:v},
			success: function(response) {
				$("<img/>").attr("src", "vote2.gif").load(function(){
					$("#vote-form").hide();
					$(this).hide()
						.appendTo("#vote-image").show("fade", 3000);
				});
			}
		});
		return false;
	});
});
</script>	
	
	