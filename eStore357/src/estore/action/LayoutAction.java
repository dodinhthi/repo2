package estore.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Query;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import estore.entity.Product;
import estore.model.ConfigModel;
import estore.util.XHibernate;
import estore.util.XWeb;

@Results(value = { @Result(name = "home", location = "/Home.jsp"), @Result(name = "about", location = "/AboutUs.jsp"),
		@Result(name = "contact", location = "/ContactUs.jsp"), @Result(name = "feedback", location = "/Feedback.jsp"),
		@Result(name = "faqs", location = "/FAQs.jsp") })
@SuppressWarnings("serial")
public class LayoutAction extends ActionSupport {

	@Action(value = "/language")
	public void language() throws Exception {
		String language = XWeb.getParameter("request_locale");
		XWeb.setSession("language", language);
	}

	@Action(value = "/vote")
	public void ajaxVote() throws Exception {
		int v = Integer.parseInt(XWeb.getParameter("vote"));

		ConfigModel cfg = (ConfigModel) XWeb.getApplication("config");

		if (v == 0) {
			cfg.setExe(cfg.getExe() + 1);
		} else if (v == 1) {
			cfg.setVer(cfg.getVer() + 1);
		} else if (v == 2) {
			cfg.setGoo(cfg.getGoo() + 1);
		} else if (v == 3) {
			cfg.setFai(cfg.getFai() + 1);
		} else if (v == 4) {
			cfg.setBad(cfg.getBad() + 1);
		}

		cfg.store(); // lưu file cấu hình lại
	}

	@SuppressWarnings("unchecked")
	public List<Product> getSpecialProducts() {
		Session hs = XHibernate.openSession();

		String hql = "FROM Product WHERE special=true";
		Query query = hs.createQuery(hql);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Product> getRandomProducts() {
		Session hs = XHibernate.openSession();

		String hql = "FROM Product ORDER BY NewID()";
		Query query = hs.createQuery(hql);
		query.setMaxResults(10);
		return query.list();
	}

	@Action(value = "/home")
	public String home() throws Exception {
		return "home";
	}

	@Action(value = "/about")
	public String about() throws Exception {
		return "about";
	}

	@Action(value = "/contact")
	public String contact() throws Exception {
		return "contact";
	}

	@Action(value = "/faqs")
	public String faqs() throws Exception {
		return "faqs";
	}

	@Action(value = "/feedback")
	public String feedback() throws Exception {
		return "feedback";
	}

	@Action(value = "/include")
	public void include() throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		Session hs = XHibernate.openSession();

		String hqlCategory = "FROM Category";
		Query queryCategory = hs.createQuery(hqlCategory);
		req.setAttribute("categories", queryCategory.list());

		String hqlSupplier = "FROM Supplier";
		Query querySupplier = hs.createQuery(hqlSupplier);
		req.setAttribute("suppliers", querySupplier.list());

		String hqlPromotion = "FROM Product ORDER BY discount DESC";
		Query queryPromotion = hs.createQuery(hqlPromotion);
		queryPromotion.setMaxResults(10);
		req.setAttribute("promotions", queryPromotion.list());

		String hqlWebMaster = "FROM WebMaster WHERE email LIKE '%@yahoo.com%'";
		Query queryWebMaster = hs.createQuery(hqlWebMaster);
		req.setAttribute("webMasters", queryWebMaster.list());
	}
}
