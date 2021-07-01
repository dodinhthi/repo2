package estore.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Query;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import estore.entity.Permission;
import estore.entity.WebMaster;
import estore.util.XHibernate;
import estore.util.XWeb;

@SuppressWarnings("serial")
public class SecurityAction extends ActionSupport implements ServletRequestAware {

	@Override
	public void setServletRequest(HttpServletRequest request) {
		if (XWeb.getSession("master") == null) {
			try {
				XWeb.sendRedirect("index.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public String getDisables() {
		WebMaster master = (WebMaster) XWeb.getSession("master");
		if (!master.isAdministrator()) {
			String uri = ServletActionContext.getRequest().getRequestURI();
			String page = uri.substring(1 + uri.lastIndexOf("/"));

			Session hs = XHibernate.openSession();
			String hql = "FROM Permission WHERE page LIKE :page";
			Query query = hs.createQuery(hql);
			query.setParameter("page", "%" + page);
			List<Permission> items = query.list();
			if (items.size() == 1) {
				return items.get(0).getDisables();
			}
		}
		return null;
	}
}
