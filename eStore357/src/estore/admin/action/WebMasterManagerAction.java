package estore.admin.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import estore.entity.WebMaster;
import estore.util.XHibernate;
import estore.util.XWeb;

@Results(value={
	@Result(name="input", location="/admin/WebMasterManager.jsp"),
	@Result(name="login", location="/admin/index.jsp")
})
@SuppressWarnings("serial")
public class WebMasterManagerAction 
	extends ActionSupport implements ModelDriven<WebMaster>{

	@Action(value="/admin/loginMaster")
	public String login() throws Exception {
		String password = model.getPassword();
		Session hs = XHibernate.openSession();
		try{
			hs.refresh(model);
			if(model.getPassword().equals(password)){
				XWeb.setSession("master", model);
				addActionMessage("Login successfully !");
			}
			else{
				addActionMessage("Invalid password !");
			}
		}
		catch (Exception e) {
			addActionMessage("Invalid login name !");
		}
		return "login";
	}
	
	WebMaster model = new WebMaster();
	@Override
	public WebMaster getModel() {
		return model;
	}
}
