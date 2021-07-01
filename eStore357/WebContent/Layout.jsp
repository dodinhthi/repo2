<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Online Shopping Mall</title>

	<!--JQuery-->
    <script src="jquery/js/jquery-1.7.2.min.js"></script>
	
	<script src="jquery/js/jquery.validate.js"></script>
	
	<link href="jquery/css/custom-theme/jquery-ui-1.8.21.custom.css" rel="stylesheet" type="text/css"/>
	<script src="jquery/js/jquery-ui-1.8.21.custom.min.js"></script>
	
	<!--Artisteer-->
    <link href="style.css" rel="stylesheet" type="text/css" />
    <!--[if IE 6]><link rel="stylesheet" href="style.ie6.css" type="text/css"/><![endif]-->
    <!--[if IE 7]><link rel="stylesheet" href="style.ie7.css" type="text/css"/><![endif]-->
    <script src="script.js"></script>
	
	<!--Nguyen Nghiem-->
	<link rel="stylesheet" href="nn-style.css" type="text/css"/>
	<script>
	$(function(){
		$(".date").datepicker({dateFormat:'mm/dd/yy'});
		$(".tabs").tabs();
		$(":submit,:button,:reset").button();
		
		// da ngon ngu
		
		$(".en, .vi").click(function() {
				language = $(this).attr("class");
				$.ajax({
					url : "language",
					data : {
						request_locale : language
					},
					success : function() {
						location.reload();
					}
				});
				return false;
			});
		});
	</script>
	
	<!--Head-ContentPlaceHolder-->
	<tiles:insertAttribute name="head" ignore="true"/>
	<!--/Head-ContentPlaceHolder-->
	
	<s:action name="include"/>
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
		<li><a href="home.action"><s:text name="layout.home"/></a></li>	
		<li><a href="about.action"><s:text name="layout.about"/></a></li>	
		<li><a href="contact.action"><s:text name="layout.contact"/></a></li>	
		<li><a href="feedback.action"><s:text name="layout.feedback"/></a></li>	
		<li><a href="faqs.action"><s:text name="layout.faqs"/></a></li>	
		<li>
			<a href="#"><s:text name="layout.account"/></a>
			<ul>
			<s:if test="#session.user == null">
				<s:include value="LoginDialog.jsp"/>
				<li><a id="lnkLogin" href="#">Đăng nhập</a></li>
				<li><a id="lnkForgot" href="#">Quên mật khẩu</a></li>
				<li><a href="viewSignUp">Đăng ký thành viên</a></li>
			</s:if>
			<s:else>
				<s:include value="ChangePasswordDialog.jsp"/>
				<li><a href="signOut">Đăng xuất</a></li>
				<li><a id="lnkChangePassword" href="#">Đổi mật khẩu</a></li>
				<li><a href="myAccount">Tài khoản cá nhân</a></li>
				<li><a href="myOrders">Đơn hàng</a></li>
				<li><a href="myProducts">Hàng đã mua</a></li>
			</s:else>
			</ul>
		</li>	
	</ul>
	<ul class="art-hmenu" style="float:right">
		<li><a class="en" href="?request_locale=en">English</a></li>	
		<li><a class="vi" href="?request_locale=vi">Tiếng Việt</a></li>	
	</ul>
</div>
</div>
</div>
</div>
<div class="cleared reset-box"></div>
	<div class="art-header">
        <div class="art-header-position">
            <div class="art-header-wrapper">
                <div class="cleared reset-box"></div>
                <div class="art-header-inner">
					<div class="art-headerobject"></div>
					<div class="art-logo">
						<h1 class="art-logo-name"><a href="Default.action">Java Technology Suite</a></h1>
						<h2 class="art-logo-text">The center point of the professional programming technologies</h2>
					</div>
                </div>
            </div>
        </div>
    </div>
	<!--art-header-->
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
		<tiles:insertAttribute name="body">
	
			Nội dung trang web
		</tiles:insertAttribute>
		<!--/Body-ContentPlaceHolder-->
		
	</div>
	<div class="cleared"></div>
</div>

		<div class="cleared"></div>
    </div>
</div>
                          <div class="cleared"></div>
                        </div>
<div class="art-layout-cell art-sidebar1">

<!--block:: giỏ hàng-->
<s:include value="IncCartInfo.jsp"/>
<!--block:: giỏ hàng-->

<!--block:: quảng cáo-->
<s:include value="IncAds.jsp"/>
<!--block:: quảng cáo-->

<!--navigation-->
<div class="nn-nav-left">

	<!--vmenu:: tìm kiếm-->
	<s:include value="IncSearch.jsp"/>
	<!--/vmenu::tìm kiếm-->
	
	<!--vmenu::chủng loại-->
	<s:include value="IncCategory.jsp"/>
	<!--/vmenu::chủng loại-->

	<!--vmenu::nhà cung cấp-->
	<s:include value="IncSupplier.jsp"/>
	<!--/vmenu::nhà cung cấp--->

	<!--vmenu::hàng đặc biệt-->
	<s:include value="IncSpecial.jsp"/>
	<!--/vmenu::hàng đặc biệt-->

	</div>

	<div class="nn-nav-right">

	<!--block::hàng khuyến mại-->
	<s:include value="IncPromotion.jsp"/>
	<!--/block::hàng khuyến mại-->

	<!--block::bình chọn website-->
	<s:include value="IncVote.jsp"/>
	<!--block::bình chọn website-->

	<!--vmenu::hỗ trợ trực tuyến-->
	<s:include value="IncSupport.jsp"/>
	<!--/vmenu::hỗ trợ trực tuyến-->

</div>
<!--/navigation-->

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

