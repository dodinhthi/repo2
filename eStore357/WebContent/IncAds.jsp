<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="art-box art-block">
    <div class="art-box-body art-block-body">
		<div class="art-box art-blockcontent">
			<div class="art-box-body art-blockcontent-body">
				<div id="ads-image" style="height:150px">
				<img src="images/preview.jpg" width="100%" height="150">
				</div>
				<div class="cleared"></div>
			</div>
		</div>
		<div class="cleared"></div>
    </div>
</div>

<script>
	//images = ["1001.jpg", "1002.jpg", "1003.jpg", "1004.jpg", "1005.jpg"];
	i = 0;
	function fnLoadImage(){
		$("<img width='100%' height='150'/>").attr("src", 
			"images/products/" + images[i]).load(function(){
				$("#ads-image").html("");
				$(this).hide().appendTo("#ads-image").show("fade", 2000, function(){
					setTimeout(function(){
						i = (i + 1) % images.length;
						fnLoadImage();
					}, 2000);
				});
			});
	}
	$(function(){
		$.ajax({
			url:"getAdsImages",
			success:function(response){
				eval(response);
				fnLoadImage();
			}
		});
	});
</script>
