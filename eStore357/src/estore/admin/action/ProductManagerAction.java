package estore.admin.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ModelDriven;

import estore.entity.Category;
import estore.entity.Product;
import estore.entity.Supplier;
import estore.model.PagerModel;
import estore.model.UploadModel;
import estore.util.XHibernate;
import estore.util.XWeb;

@Results(value = { @Result(name = "input", location = "/admin/ProductManager.jsp") })
@SuppressWarnings("serial")
public class ProductManagerAction extends SecurityAction implements ModelDriven<Product> {

	@Action(value = "/admin/manageProduct")
	public String manage() throws Exception {
		return "input";
	}

	@Action(value = "/admin/insertProduct")
	public String insert() throws Exception {
		Session hs = XHibernate.openSession();
		Transaction t = hs.beginTransaction();
		try {
			if (upload.hasFile()) {
				upload.saveIn("../images/Products/");
				model.setImage(upload.getFileFileName());
			}
			hs.save(model);
			t.commit();
			addActionMessage("Insert successfully !");
		} catch (Exception e) {
			t.rollback();
			addActionMessage(e.getMessage());
		}
		return "input";
	}

	@Action(value = "/admin/updateProduct")
	public String update() throws Exception {
		Session hs = XHibernate.openSession();
		Transaction t = hs.beginTransaction();
		try {
			if (upload.hasFile()) {
				upload.saveIn("../images/Products/");
				model.setImage(upload.getFileFileName());
			}
			hs.update(model);
			t.commit();
			addActionMessage("Update successfully !");
		} catch (Exception e) {
			t.rollback();
			addActionMessage(e.getMessage());
		}
		return "input";
	}

	@Action(value = "/admin/deleteProduct")
	public String delete() throws Exception {
		Session hs = XHibernate.openSession();
		Transaction t = hs.beginTransaction();
		try {
			hs.delete(model);
			t.commit();
			addActionMessage("Delete successfully !");
		} catch (Exception e) {
			t.rollback();
			addActionMessage(e.getMessage());
		}
		return "input";
	}

	@Action(value = "/admin/editProduct")
	public String edit() throws Exception {
		Session hs = XHibernate.openSession();
		hs.refresh(model);
		return "input";
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
		Session hs = XHibernate.openSession();

		String hqlCount = "SELECT COUNT(p) FROM Product p";
		Long rowCount = (Long) hs.createQuery(hqlCount).uniqueResult();

		PagerModel pager = PagerModel.getPager("admprods", 10);
		pager.setRowCount(rowCount.intValue());

		String pageno = XWeb.getParameter("pageno");
		if (pageno == null) {
			pager.navigate(pager.getPageNo());
		} else if (pageno.equals("|<<")) {
			pager.navigate(0);
		} else if (pageno.equals("<<")) {
			pager.navigate(pager.getPageNo() - 1);
		} else if (pageno.equals(">>")) {
			pager.navigate(pager.getPageNo() + 1);
		} else if (pageno.equals(">>|")) {
			pager.navigate(pager.getLastPageNo());
		} else {
			pager.navigate(Integer.parseInt(pageno));
		}

		String hql = "FROM Product";
		Query query = hs.createQuery(hql);
		query.setFirstResult(pager.getStartRow());
		query.setMaxResults(pager.getPageSize());
		List<Product> items = query.list();
		return items;
	}

	@SuppressWarnings("unchecked")
	public List<Supplier> getSuppliers() {
		String hql = "FROM Supplier";
		Session hs = XHibernate.openSession();
		Query query = hs.createQuery(hql);
		List<Supplier> items = query.list();
		return items;
	}

	@SuppressWarnings("unchecked")
	public List<Category> getCategories() {
		String hql = "FROM Category";
		Session hs = XHibernate.openSession();
		Query query = hs.createQuery(hql);
		List<Category> items = query.list();
		return items;
	}

	Product model = new Product();

	@Override
	public Product getModel() {
		return model;
	}

	// upload
	UploadModel upload = new UploadModel();

	public UploadModel getUpload() {
		return upload;
	}

	public void setUpload(UploadModel upload) {
		this.upload = upload;
	}

}
