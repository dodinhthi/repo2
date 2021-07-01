package estore.action;

import java.util.Collection;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Query;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import estore.entity.Customer;
import estore.entity.Product;
import estore.model.PagerModel;
import estore.util.XHibernate;
import estore.util.XMail;
import estore.util.XWeb;

@Results(value = { @Result(name = "products", location = "/ProductList.jsp"),
		@Result(name = "details", location = "/ProductDetails.jsp"),
		@Result(name = "myProducts", location = "/MyProducts.jsp") })
@SuppressWarnings("serial")
public class ProductAction extends ActionSupport implements ModelDriven<Product> {

	@Action(value = "/send")
	public void sendProduct() throws Exception {
		String from = XWeb.getParameter("sender");
		String to = XWeb.getParameter("receiver");
		String subject = XWeb.getParameter("subject");
		String body = XWeb.getParameter("content");
		String product = XWeb.getParameter("product");
		body += "<hr> mã hàng hóa: " + product;
		XMail.send(from, to, subject, body);
		XWeb.print("Thông tin hàng hóa đã được gửi !");
	}

	@Action(value = "/getAdsImages")
	@SuppressWarnings("unchecked")
	public void getAdsImages() throws Exception {
		String hql = "FROM Product ORDER BY NewID()";
		Session hs = XHibernate.openSession();
		Query query = hs.createQuery(hql);
		query.setMaxResults(10);
		List<Product> items = query.list();

		StringBuffer images = new StringBuffer();
		for (Product p : items) {
			images.append(",'" + p.getImage() + "'");
		}
		XWeb.print("images=[" + images.substring(1) + "]");
	}

	@Action(value = "/myProducts")
	public String myProducts() throws Exception {
		XWeb.authenticate();
		return "myProducts";
	}

	@SuppressWarnings("unchecked")
	public List<Product> getMyProducts() {
		Customer user = (Customer) XWeb.getSession("user");

		String hql = "SELECT DISTINCT d.product FROM OrderDetail d " + " WHERE d.order.customer.id='" + user.getId()
				+ "'";
		Session hs = XHibernate.openSession();
		Query query = hs.createQuery(hql);
		return query.list();
	}

	Product model = new Product();

	@Override
	public Product getModel() {
		return model;
	}

	@Action(value = "/details")
	public String details() throws Exception {
		Session hs = XHibernate.openSession();
		hs.refresh(model);

		/*
		 * Ghi nhận mặt hàng đã xem vào cookie
		 */
		String id = String.valueOf(model.getId());
		String viewedIds = XWeb.getCookie("viewedIds");
		if (!viewedIds.contains(id)) {
			viewedIds += "," + id;
			int expiry = 30 * 24 * 60 * 60;
			XWeb.addCookie("viewedIds", viewedIds, expiry);
		}

		return "details";
	}

	@Action(value = "/products")
	public String products() throws Exception {
		String categoryId = XWeb.getParameter("categoryId");
		String supplierId = XWeb.getParameter("supplierId");
		String search = XWeb.getParameter("search");
		String special = XWeb.getParameter("special");

		if (categoryId != null) { // có tham số categoryId
			hql = "FROM Product p WHERE category.id=" + categoryId;
		} else if (supplierId != null) { // có tham số supplierId
			hql = "FROM Product p WHERE supplier.id='" + supplierId + "'";
		} else if (search != null) { // có tham số search
			hql = "FROM Product p WHERE name LIKE '%" + search + "%'";
		} else if (special != null) { // có tham số special
			if (special.equals("MOI")) {
				hql = "FROM Product p WHERE p.latest=true";
			} else if (special.equals("CHA")) {
				hql = "FROM Product p"; // ??? xem lai sau
			} else if (special.equals("DAC")) {
				hql = "FROM Product p WHERE p.special=true";
			} else if (special.equals("GIA")) {
				hql = "FROM Product p WHERE p.discount > 0";
			}
		}

		if (hql.length() > 0) {
			XWeb.setSession("hql", hql);
		} else if (XWeb.getSession("hql") != null) {
			hql = XWeb.getSession("hql").toString();
		} else {
			hql = "FROM Product p";
		}

		/*
		 * Phân trang
		 */
		Session hs = XHibernate.openSession();

		// đếm số bản ghi
		String hqlCount = "SELECT COUNT(p) " + hql;
		Query queryCount = hs.createQuery(hqlCount);
		long rowCount = (Long) queryCount.uniqueResult();

		// lấy PagerModel từ session
		PagerModel pager = PagerModel.getPager("products", 9);
		// cung cấp tổng số bản ghi
		pager.setRowCount((int) rowCount);

		int pageNo = 0;
		// phân tích nút phân trang được bấm
		String pageno = XWeb.getParameter("pageno");
		if (pageno != null) {
			if (pageno.equals("|<<")) {
				pageNo = 0;
			} else if (pageno.equals(">>|")) {
				pageNo = pager.getLastPageNo();
			} else if (pageno.equals("<<")) {
				pageNo = pager.getPageNo() - 1;
			} else if (pageno.equals(">>")) {
				pageNo = pager.getPageNo() + 1;
			}
		}
		// nhảy đến trang tương ứng nút được bấm
		pager.navigate(pageNo);

		return "products";
	}

	String hql = "";

	@SuppressWarnings("unchecked")
	public Collection<Product> getProducts() {
		Session hs = XHibernate.openSession();
		Query query = hs.createQuery(hql);
		if (hql.contains("ORDER BY discount")) {
			query.setMaxResults(15);
		} else {
			PagerModel pager = PagerModel.getPager("products");
			query.setFirstResult(pager.getStartRow());
			query.setMaxResults(pager.getPageSize());
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public Collection<Product> getViewedProducts() {
		String viewedIds = XWeb.getCookie("viewedIds");
		String hql = "FROM Product WHERE id IN (1" + viewedIds + ")";
		Session hs = XHibernate.openSession();
		Query query = hs.createQuery(hql);
		return query.list();
	}
}
