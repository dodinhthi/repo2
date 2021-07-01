<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Online Shopping Mall</title>

	<!--JQuery-->
    <script src="../jquery/js/jquery-1.7.2.min.js"></script>
	<script src="../jquery/js/jquery.validate.js"></script>
	<link href="../jquery/css/custom-theme/jquery-ui-1.8.21.custom.css" rel="stylesheet" type="text/css"/>
	<script src="../jquery/js/jquery-ui-1.8.21.custom.min.js"></script>
	
	<!--Artisteer-->
    <link href="../style.css" rel="stylesheet" type="text/css" />
    <!--[if IE 6]><link rel="stylesheet" href="../style.ie6.css" type="text/css"/><![endif]-->
    <!--[if IE 7]><link rel="stylesheet" href="../style.ie7.css" type="text/css"/><![endif]-->
    <script src="../script.js"></script>
	
	<!--Nguyen Nghiem-->
	<link rel="stylesheet" href="../nn-style.css" type="text/css"/>
	<script>
	$(function(){
		$(".date").datepicker({dateFormat:'yy-mm-dd'});
		$(".tabs").tabs();
		$(":submit,:button,:reset").button();
	})
	</script>
	<script>
	$(function(){
		$("#tabList tr:even").css({background:"yellow"});
		$("#tabList tr:odd").css({background:"aqua"});
		$("#tabList tr:first").css({background:"gray"});
		$("#tabList td, #tabList th").css({padding:"5px"});
		
		// phân quyền
		//$("${disables}").click(function(){
		//	return false;
		//});
		$("${disables}").css("display", "none");
	});
	</script>
	
	<!--Head-ContentPlaceHolder-->
	<tiles:insertAttribute name="head" ignore="true"/>
	<!--/Head-ContentPlaceHolder-->
</head>
<body>
<div id="art-page-background-glare-wrapper">
    <div id="art-page-background-glare"></div>
</div>
<div id="art-main">
    <div class="cleared reset-box"></div>
<div class="art-bar art-nav">
<div class="art-nav-outer">
<div class="art-nav-wrapper">
<div class="art-nav-inner">
	<ul class="art-hmenu">
		<li>
			<a href="#">Hàng hóa</a>
			<ul>
				<li><a href="manageProduct">Quản lý hàng hóa</a></li>
				<li><a href="manageCategory">Quản lý chủng loại</a></li>
				<li><a href="manageSupplier">Quản lý nhà cung cấp</a></li>
			</ul>
		</li>	
		<li><a href="about.action">Khách hàng</a></li>	
		<li><a href="contact.action">Hóa đơn</a></li>	
		<li id="Reports">
			<a href="#">Thống kê doanh số</a>
			<ul>
				<li><a href="reportByProduct">Theo hàng hóa</a></li>
				<li><a href="reportByCategory">Theo chủng loại</a></li>
				<li><a href="reportBySupplier">Theo nhà cung cấp</a></li>
				<li><a href="#">Theo thời gian</a>
					<ul>
						<li><a href="reportByYear">Theo năm</a></li>
						<li><a href="reportByQuarter">Theo quí</a></li>
						<li><a href="reportByMonth">Theo tháng</a></li>
					</ul>
				</li>
			</ul>
		</li>	
	</ul>
	<ul class="art-hmenu" style="float:right">
		<li><a href="?request_locale=en">Tài khoản</a></li>	
		<li><a href="?request_locale=vi">Phân quyền</a></li>	
	</ul>
</div>
</div>
</div>
</div>
<div class="cleared reset-box"></div>
    <div class="cleared reset-box"></div>
    <div class="art-box art-sheet">
        <div class="art-box-body art-sheet-body">
            <div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-content">
<div class="art-box art-post">
    <div class="art-box-body art-post-body">
<div class="art-post-inner art-article">
	
	<h2 class="art-postheader">
		<!--Subject-ContentPlaceHolder-->
		Tiêu đề trang
		<!--/Subject-ContentPlaceHolder-->
	</h2>
	<div class="art-postcontent">

		<!--Body-ContentPlaceHolder-->
		<tiles:insertAttribute name="body"/>
		<!--/Body-ContentPlaceHolder-->
		
	</div>
	<div class="cleared"></div>
</div>

		<div class="cleared"></div>
    </div>
</div>
                          <div class="cleared"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="cleared"></div>
            <div class="art-footer">
                <div class="art-footer-body">
                    <a href="#" class="art-rss-tag-icon" title="RSS"></a>
					<div class="art-footer-text">
						<p>
							<a href="home.action">Trang Chủ</a> | 
							<a href="about.action">Giới Thiệu</a> | 
							<a href="contactUs.action">Liên Hệ</a> | 
							<a href="feedback.action">Góp Ý</a> | 
							<a href="faq.action">Hỏi-Đáp</a>
						</p>
						<p>Copyright © 2013. All Rights Reserved.</p>
					</div>
                    <div class="cleared"></div>
                </div>
            </div>
    		<div class="cleared"></div>
        </div>
    </div>
    <div class="cleared"></div>
</div>

</body>
</html>
