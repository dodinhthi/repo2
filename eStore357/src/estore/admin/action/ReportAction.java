package estore.admin.action;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Query;
import org.hibernate.Session;

import estore.util.XHibernate;
import estore.util.XWeb;

@Results(value = { @Result(name = "report", location = "/admin/Report.jsp") })
@SuppressWarnings("serial")
public class ReportAction extends SecurityAction {
	String hql = "";
	String title = "";

	@Action(value = "/admin/reportByProductByExcel")
	public void reportByProductByExcel() throws Exception {
		title = "Hàng hóa";
		hql = "SELECT product.name, SUM(unitPrice*quantity*(1-discount))" + " FROM OrderDetail "
				+ " WHERE order.orderDate BETWEEN :min AND :max " + " GROUP BY product.name";

		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("application/vnd.ms-excel");
		PrintWriter out = resp.getWriter();
		out.println("Product Name\tRevenue\tTax");

		List<Object[]> items = getReportInfo();
		int i = 2;
		for (Object[] item : items) {
			String name = (String) item[0];
			Double revenue = (Double) item[1];
			out.println(name + "\t" + revenue + "\t=0.1*B" + i);
			i++;
		}
	}

	@Action(value = "/admin/reportByProduct")
	public String reportByProduct() throws Exception {
		title = "Hàng hóa";
		hql = "SELECT product.name, SUM(unitPrice*quantity*(1-discount))" + " FROM OrderDetail "
				+ " WHERE order.orderDate BETWEEN :min AND :max " + " GROUP BY product.name";
		return "report";
	}

	@Action(value = "/admin/reportByCategory")
	public String reportByCategory() throws Exception {
		title = "Chủng loại";
		hql = "SELECT product.category.name, SUM(unitPrice*quantity*(1-discount))" + " FROM OrderDetail "
				+ " WHERE order.orderDate BETWEEN :min AND :max " + " GROUP BY product.category.name";
		return "report";
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getReportInfo() {
		Date min = Date.valueOf("1900-01-01");
		Date max = Date.valueOf("2099-12-31");
		try {
			min = Date.valueOf(XWeb.getParameter("from"));
		} catch (Exception e) {
		}
		try {
			max = Date.valueOf(XWeb.getParameter("to"));
		} catch (Exception e) {
		}

		Session hs = XHibernate.openSession();
		Query q = hs.createQuery(hql);
		q.setParameter("min", min);
		q.setParameter("max", max);
		List<Object[]> reportInfo = q.list();

		return reportInfo;
	}

	public String getTitle() {
		return title;
	}
}
