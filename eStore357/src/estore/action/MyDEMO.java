package estore.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionSupport;

import estore.util.XWeb;

public class MyDEMO extends ActionSupport {

	@Action(value = "/rung")
	public void rung() throws IOException {
		System.out.println("chay");
		XWeb.print("ffff");

	}

	public static int server = 1;

	public static int getServer() {
		return server;
	}

	public static void setServer(int server) {
		MyDEMO.server = server;
	}

	@Action(value = "/chuyenServer")
	public void chuyenServer() {

		String chuyen = XWeb.getParameter("chuyen");
		System.out.println("day la chuyen " + chuyen);
		int chuyens = Integer.parseInt(chuyen);
		System.out.println(chuyens);
		if (chuyens == 1)
			setServer(1);
		else {
			setServer(2);
		}
		if (chuyen != null) {
			System.out.println("set xong server " + chuyen);
			ServletActionContext.getRequest().getSession().setAttribute("chuyen", chuyen);
			String server = (String) ServletActionContext.getRequest().getSession().getAttribute("chuyen");
			System.out.println("lay ra server " + server);

		} else
			ServletActionContext.getRequest().getSession().setAttribute("chuyen", 1);

	}

	/*
	 * @Action(value="/layServer") public void layServer() throws IOException {
	 * 
	 * String server=""; try { server=(String)
	 * ServletActionContext.getRequest().getSession().getAttribute("chuyen"); }
	 * catch(Exception ex) { System.out.println("bi loi"); }
	 * 
	 * 
	 * if(server==null) {
	 * 
	 * XWeb.print("1"); System.out.println("lay xong server"); }
	 * 
	 * 
	 * else { System.out.println("khong null"); XWeb.print(server);
	 * System.out.println("lay xong server "+server); }
	 * 
	 * }
	 */
	@Action(value = "getSer")
	public void getSer() {
		System.out.println("get list of serrver");
		List<ChiNhanh> s = new ArrayList<>();
		if (getServer() == 1) {
			ChiNhanh c = new ChiNhanh();
			c.setMa(1);
			c.setTen("Server 1");
			s.add(c);
			c = new ChiNhanh();
			c.setMa(2);
			c.setTen("Server 2");

			s.add(c);
		} else {
			ChiNhanh c = new ChiNhanh();
			c.setMa(2);
			c.setTen("Server 2");
			s.add(c);

			c = new ChiNhanh();
			c.setMa(1);
			c.setTen("Server 1");

			s.add(c);
		}
		ServletActionContext.getRequest().setAttribute("server", s);

	}

}
