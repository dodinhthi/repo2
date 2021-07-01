/* begin Page */
/* Created by Artisteer v3.1.0.46558 */
// css helper
(function($) {
    var data = [
        {str:navigator.userAgent,sub:'Chrome',ver:'Chrome',name:'chrome'},
        {str:navigator.vendor,sub:'Apple',ver:'Version',name:'safari'},
        {prop:window.opera,ver:'Opera',name:'opera'},
        {str:navigator.userAgent,sub:'Firefox',ver:'Firefox',name:'firefox'},
        {str:navigator.userAgent,sub:'MSIE',ver:'MSIE',name:'ie'}];
    for (var n=0;n<data.length;n++)	{
        if ((data[n].str && (data[n].str.indexOf(data[n].sub) != -1)) || data[n].prop) {
            var v = function(s){var i=s.indexOf(data[n].ver);return (i!=-1)?parseInt(s.substring(i+data[n].ver.length+1)):'';};
            $('html').addClass(data[n].name+' '+data[n].name+v(navigator.userAgent) || v(navigator.appVersion)); break;			
        }
    }
})(jQuery);
/* end Page */

/* begin Menu */
jQuery(function () {
    if (!jQuery.browser.msie || parseInt(jQuery.browser.version) > 7) return;
    jQuery('ul.art-hmenu>li:not(:first-child)').each(function () { jQuery(this).prepend('<span class="art-hmenu-separator"> </span>'); });
    if (!jQuery.browser.msie || parseInt(jQuery.browser.version) > 6) return;
    jQuery('ul.art-hmenu li').each(function () {
        this.j = jQuery(this);
        this.UL = this.j.children('ul:first');
        if (this.UL.length == 0) return;
        this.A = this.j.children('a:first');
        this.onmouseenter = function () {
            this.j.addClass('art-hmenuhover');
            this.UL.addClass('art-hmenuhoverUL');
            this.A.addClass('art-hmenuhoverA');
        };
        this.onmouseleave = function() {
            this.j.removeClass('art-hmenuhover');
            this.UL.removeClass('art-hmenuhoverUL');
            this.A.removeClass('art-hmenuhoverA');
        };
    });
});

jQuery(function() { setHMenuOpenDirection({container: "div.art-sheet-body", defaultContainer: "#art-main", menuClass: "art-hmenu", leftToRightClass: "art-hmenu-left-to-right", rightToLeftClass: "art-hmenu-right-to-left"}); });

function setHMenuOpenDirection(menuInfo) {
    var defaultContainer = jQuery(menuInfo.defaultContainer);
    defaultContainer = defaultContainer.length > 0 ? defaultContainer = jQuery(defaultContainer[0]) : null;

    jQuery("ul." + menuInfo.menuClass + ">li>ul").each(function () {
        var submenu = jQuery(this);
        var submenuWidth = submenu.outerWidth();
        var submenuLeft = submenu.offset().left;

        var mainContainer = submenu.parents(menuInfo.container);
        mainContainer = mainContainer.length > 0 ? mainContainer = jQuery(mainContainer[0]) : null;

        var container = mainContainer || defaultContainer;
        if (container != null) {
            var containerLeft = container.offset().left;
            var containerWidth = container.outerWidth();

            if (submenuLeft + submenuWidth >=
                    containerLeft + containerWidth) 
                /* right to left */
                submenu.addClass(menuInfo.rightToLeftClass).find("ul").addClass(menuInfo.rightToLeftClass);
            if (submenuLeft <= containerLeft)
                /* left to right */
                submenu.addClass(menuInfo.leftToRightClass).find("ul").addClass(menuInfo.leftToRightClass);
        }
    });
}
/* end Menu */

/* begin MenuSubItem */
jQuery(function () {
    jQuery("ul.art-hmenu ul li").hover(function () { jQuery(this).prev().children("a").addClass("art-hmenu-before-hovered"); }, 
        function () { jQuery(this).prev().children("a").removeClass("art-hmenu-before-hovered"); });
});

jQuery(function () {
    if (!jQuery.browser.msie) return;
    var ieVersion = parseInt(jQuery.browser.version);
    if (ieVersion > 7) return;

    /* Fix width of submenu items.
    * The width of submenu item calculated incorrectly in IE6-7. IE6 has wider items, IE7 display items like stairs.
    */
    jQuery.each(jQuery("ul.art-hmenu ul"), function () {
        var maxSubitemWidth = 0;
        var submenu = jQuery(this);
        var subitem = null;
        jQuery.each(submenu.children("li").children("a"), function () {
            subitem = jQuery(this);
            var subitemWidth = subitem.outerWidth();
            if (maxSubitemWidth < subitemWidth)
                maxSubitemWidth = subitemWidth;
        });
        if (subitem != null) {
            var subitemBorderLeft = parseInt(subitem.css("border-left-width"), 10) || 0;
            var subitemBorderRight = parseInt(subitem.css("border-right-width"), 10) || 0;
            var subitemPaddingLeft = parseInt(subitem.css("padding-left"), 10) || 0;
            var subitemPaddingRight = parseInt(subitem.css("padding-right"), 10) || 0;
            maxSubitemWidth -= subitemBorderLeft + subitemBorderRight + subitemPaddingLeft + subitemPaddingRight;
            submenu.children("li").children("a").css("width", maxSubitemWidth + "px");
        }
    });

    if (ieVersion > 6) return;
    jQuery("ul.art-hmenu ul>li:first-child>a").css("border-top-width", "1px");
});
/* end MenuSubItem */

/* begin Layout */
jQuery(function () {
     var c = jQuery('div.art-content');
    if (c.length !== 1) return;
    var s = c.parent().children('.art-layout-cell:not(.art-content)');


    if (jQuery.browser.msie && parseInt(jQuery.browser.version) < 8) {
        jQuery(window).bind('resize', function() {
            var w = 0;
            c.hide();
            s.each(function() { w += this.clientWidth; });
            c.w = c.parent().width(); c.css('width', c.w - w + 'px');
            c.show();
        });
    }

    jQuery(window).trigger('resize');
});/* end Layout */

/* begin Button */
function artButtonSetup(className) {
    jQuery.each(jQuery("a." + className + ", button." + className + ", input." + className), function (i, val) {
        var b = jQuery(val);
        if (!b.parent().hasClass('art-button-wrapper')) {
            if (b.is('input')) b.val(b.val().replace(/^\s*/, '')).css('zoom', '1');
            if (!b.hasClass('art-button')) b.addClass('art-button');
            jQuery("<span class='art-button-wrapper'><span class='art-button-l'> </span><span class='art-button-r'> </span></span>").insertBefore(b).append(b);
            if (b.hasClass('active')) b.parent().addClass('active');
        }
        b.mouseover(function () { jQuery(this).parent().addClass("hover"); });
        b.mouseout(function () { var b = jQuery(this); b.parent().removeClass("hover"); if (!b.hasClass('active')) b.parent().removeClass('active'); });
        b.mousedown(function () { var b = jQuery(this); b.parent().removeClass("hover"); if (!b.hasClass('active')) b.parent().addClass('active'); });
        b.mouseup(function () { var b = jQuery(this); if (!b.hasClass('active')) b.parent().removeClass('active'); });
    });
}
jQuery(function() { artButtonSetup("art-button"); });

/* end Button */

/* begin VMenu */
jQuery(function() {
    if (!jQuery('html').hasClass('ie7')) return;
    jQuery('ul.art-vmenu li:not(:first-child),ul.art-vmenu li li li:first-child,ul.art-vmenu>li>ul').each(function () { jQuery(this).append('<div class="art-vmenu-separator"> </div><div class="art-vmenu-separator-bg"> </div>'); });
});
jQuery(function() {
    setPopupVMenuOpenDirection({container: "div.art-sheet-body", defaultContainer: "#art-main", vMenuClass: "art-vmenu", leftToRightClass: "art-vmenu-left-to-right", rightToLeftClass: "art-vmenu-right-to-left"});
    fixPopupVMenu({vMenuClass: "art-vmenu", vMenuLayoutCellClass: "art-layout-cell"});
}); 


function setPopupVMenuOpenDirection(vMenuInfo) {
    var defaultContainer = jQuery(vMenuInfo.defaultContainer);
    defaultContainer = defaultContainer.length > 0 ? defaultContainer = jQuery(defaultContainer[0]) : null;

    jQuery("ul." + vMenuInfo.vMenuClass).each(function () {
        var vmenu = jQuery(this);
        var submenu = vmenu.find("ul:first");
        if (submenu.length > 0) {
            submenu = jQuery(submenu[0]);
            var submenuWidth = submenu.outerWidth();

            var vmenuLeft = vmenu.offset().left;
            var vmenuWidth = vmenu.outerWidth();

            var mainContainer = vmenu.parents(vMenuInfo.container);
            mainContainer = mainContainer.length > 0 ? mainContainer = jQuery(mainContainer[0]) : null;

            var container = mainContainer || defaultContainer;
            if (container != null) {
                var containerLeft = container.offset().left;
                var containerWidth = container.outerWidth();

                if (vmenuLeft + vmenuWidth + submenuWidth >=
                    containerLeft + containerWidth) {
                    /* right to left */
                    vmenu.find("ul").addClass(vMenuInfo.rightToLeftClass);
                } else {
                    /* left to right */
                    vmenu.find("ul").addClass(vMenuInfo.leftToRightClass);
                }
            }
        }
    });
}

function fixPopupVMenu(fixVMenuInfo)
{
    if (!jQuery.browser.msie) return;
    var ieVersion = parseInt(jQuery.browser.version);

    if (ieVersion > 8) return; 

    /* Add last-child class to emulate :last-child in IE6-7-8*/
    jQuery("ul." + fixVMenuInfo.vMenuClass + ", ul." + fixVMenuInfo.vMenuClass + " ul").each(function() {
	    jQuery(this).children("li").last().addClass("last-child").children("a").addClass("last-child");
    });

    if (ieVersion > 7) return;
    
    /* Fix z-index for submenus.
     * z-index is ignored in IE6-7 if the the absolute element is displayed on the absolute layer's parent siblings.
     */
    jQuery("ul." + fixVMenuInfo.vMenuClass).each(function() {
        var container = jQuery(this);
        if (container.parents("." + fixVMenuInfo.vMenuLayoutCellClass).length > 0) {
            while (true) {
                if (container.css("position") == "relative" || container.css("position") == "") 
                    container.css("z-index", "10000");
                if (container.hasClass(fixVMenuInfo.vMenuLayoutCellClass)) break;
                container = container.parent();
            }
        }
    });

    /* Fix width of submenu items.
     * The width of submenu item calculated incorrectly in IE6-7. IE6 has wider items, IE7 display items like stairs.
     */
    jQuery.each(jQuery("ul." + fixVMenuInfo.vMenuClass + " ul"), function() {
        var maxSubitemWidth = 0;
        var submenu = jQuery(this);
        var subitem = null;
        jQuery.each(submenu.children("li").children("a"), function () {
            subitem = jQuery(this); 
            var subitemWidth = subitem.outerWidth();
            if (maxSubitemWidth < subitemWidth) 
                maxSubitemWidth = subitemWidth;
        });
        if (subitem != null) {
            var subitemBorderLeft = parseInt(subitem.css("border-left-width"), 10) || 0;
            var subitemBorderRight = parseInt(subitem.css("border-right-width"), 10) || 0;
            var subitemPaddingLeft = parseInt(subitem.css("padding-left"), 10) || 0;
            var subitemPaddingRight = parseInt(subitem.css("padding-right"), 10) || 0;
            maxSubitemWidth -= subitemBorderLeft + subitemBorderRight + subitemPaddingLeft + subitemPaddingRight;

            submenu.children("li").children("a").css("width", maxSubitemWidth + "px");
        }
    });

    if (ieVersion > 6) return;
    /* Add first-child class to emulate :first-child in IE6 */
    jQuery("ul." + fixVMenuInfo.vMenuClass + ", ul." + fixVMenuInfo.vMenuClass + " ul").each(function() {
	    jQuery(this).children("li").first().addClass("first-child").children("a").addClass("first-child");
    });

    /* Add events mouseenter and mouseleave for IE6 because it doesn't understand hover for li. */
    jQuery.each(jQuery("ul." + fixVMenuInfo.vMenuClass + " li"), function(i, val) {
        val.j = jQuery(val);
        val.UL = val.j.children("ul:first");
        if (val.UL.length == 0) return;
        val.A = val.j.children("a:first");
        this.onmouseenter = function() {
            this.j.addClass("art-vmenuhover");
            this.UL.addClass("art-vmenuhoverUL");
            this.A.addClass("art-vmenuhoverA");
        };
        this.onmouseleave = function() {
            this.j.removeClass("art-vmenuhover");
            this.UL.removeClass("art-vmenuhoverUL");
            this.A.removeClass("art-vmenuhoverA");
        };
    });
}
/* end VMenu */



