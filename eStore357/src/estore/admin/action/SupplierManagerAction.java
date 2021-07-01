package estore.admin.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ModelDriven;

import estore.entity.Supplier;
import estore.model.UploadModel;
import estore.util.XHibernate;

@Results(value = { @Result(name = "input", location = "/admin/SupplierManager.jsp") })
@SuppressWarnings("serial")
public class SupplierManagerAction extends SecurityAction implements ModelDriven<Supplier> {

	@Action(value = "/admin/manageSupplier")
	public String manage() throws Exception {
		return "input";
	}

	@Action(value = "/admin/insertSupplier")
	public String insert() throws Exception {
		Session hs = XHibernate.openSession();
		Transaction t = hs.beginTransaction();
		try {
			if (upload.hasFile()) {
				upload.saveIn("../images/suppliers/");
				model.setLogo(upload.getFileFileName());
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

	@Action(value = "/admin/updateSupplier")
	public String update() throws Exception {
		Session hs = XHibernate.openSession();
		Transaction t = hs.beginTransaction();
		try {
			if (upload.hasFile()) {
				upload.saveIn("../images/suppliers/");
				model.setLogo(upload.getFileFileName());
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

	@Action(value = "/admin/deleteSupplier")
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

	@Action(value = "/admin/editSupplier")
	public String edit() throws Exception {
		Session hs = XHibernate.openSession();
		hs.refresh(model);
		return "input";
	}

	@SuppressWarnings("unchecked")
	public List<Supplier> getSuppliers() {
		String hql = "FROM Supplier";
		Session hs = XHibernate.openSession();
		Query query = hs.createQuery(hql);
		List<Supplier> items = query.list();
		return items;
	}

	Supplier model = new Supplier();

	@Override
	public Supplier getModel() {
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
