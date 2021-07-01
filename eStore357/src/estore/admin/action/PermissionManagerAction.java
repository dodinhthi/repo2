package estore.admin.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ModelDriven;

import estore.entity.Permission;
import estore.util.XHibernate;

@Results(value = { @Result(name = "input", location = "/admin/PermissionManager.jsp") })
@SuppressWarnings("serial")
public class PermissionManagerAction extends SecurityAction implements ModelDriven<Permission> {

	@Action(value = "/admin/managePermission")
	public String manage() throws Exception {
		return "input";
	}

	@Action(value = "/admin/insertPermission")
	public String insert() throws Exception {
		Session hs = XHibernate.openSession();
		Transaction t = hs.beginTransaction();
		try {
			hs.save(model);
			t.commit();
			addActionMessage("Insert successfully !");
		} catch (Exception e) {
			t.rollback();
			addActionMessage(e.getMessage());
		}
		return "input";
	}

	@Action(value = "/admin/updatePermission")
	public String update() throws Exception {
		Session hs = XHibernate.openSession();
		Transaction t = hs.beginTransaction();
		try {
			hs.update(model);
			t.commit();
			addActionMessage("Update successfully !");
		} catch (Exception e) {
			t.rollback();
			addActionMessage(e.getMessage());
		}
		return "input";
	}

	@Action(value = "/admin/deletePermission")
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

	@Action(value = "/admin/editPermission")
	public String edit() throws Exception {
		Session hs = XHibernate.openSession();
		hs.refresh(model);
		return "input";
	}

	@SuppressWarnings("unchecked")
	public List<Permission> getPermissions() {
		String hql = "FROM Permission";
		Session hs = XHibernate.openSession();
		Query query = hs.createQuery(hql);
		List<Permission> items = query.list();
		return items;
	}

	Permission model = new Permission();

	@Override
	public Permission getModel() {
		return model;
	}
}
