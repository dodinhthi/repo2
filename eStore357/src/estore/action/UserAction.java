package estore.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import estore.entity.Customer;
import estore.model.UploadModel;
import estore.util.XHibernate;
import estore.util.XMail;
import estore.util.XWeb;

@Results(value={
	@Result(name="account", location="/Account.jsp"),
	@Result(name="home", location="/index.jsp"),
	@Result(name="input", location="/SignUp.jsp")
})
@SuppressWarnings("serial")
public class UserAction extends ActionSupport implements ModelDriven<Customer>{
	
	@Action(value="/changePassword")
	public void ajaxChangePassword() throws Exception {	
		String pwcu = XWeb.getParameter("pwcu");
		String pwmoi = XWeb.getParameter("pwmoi");
		
		Session hs = XHibernate.openSession();
		hs.refresh(model);
		if(!model.getPassword().equals(pwcu)){
			XWeb.print("Sai mật khẩu !");
		}
		else{
			model.setPassword(pwmoi);
			Transaction t = hs.beginTransaction();
			hs.update(model);
			t.commit();
			XWeb.print("Mật khẩu đã được đổi thành công !");
		}
	}
	
	@Action(value="/updateAccount")
	public String updateAccount() throws Exception {
		
		XWeb.authenticate();
		
		Session hs = XHibernate.openSession();
		Transaction t = hs.beginTransaction();
		try{
			if(upload.hasFile()){
				String fname = upload.saveIn("images/customers");
				model.setPhoto(fname);
			}
			hs.update(model);
			t.commit();
			XWeb.setSession("user", model);
		}
		catch (Exception e) {
			t.rollback();
		}
		return "account";
	}
	
	@Action(value="/myAccount")
	public String myAccount() throws Exception {
		
		XWeb.authenticate();
		
		Customer user = (Customer) XWeb.getSession("user");
		model.setId(user.getId());
		
		Session hs = XHibernate.openSession();
		hs.refresh(model);
		
		return "account";
	}
	
	@Action(value="/viewSignUp")
	public String viewSignUp() throws Exception {
		return "input";
	}
	
	@Action(value="/activate")
	public String activate() throws Exception {
		Session hs = XHibernate.openSession();
		Transaction t = hs.beginTransaction();
		try{
			hs.refresh(model);
			model.setActivated(true);
			hs.update(model);
			t.commit();
			XWeb.setSession("user", model);
		}
		catch (Exception e) {
			t.rollback();
		}
		return "account";
	}
	
	@Action(value="/signUp")
	public String signUp() throws Exception {
		
		String captcha1 = (String) XWeb.getSession("captcha");
		String captcha2 = XWeb.getParameter("txtCaptcha");
		if(!captcha1.equals(captcha2)){
			addActionMessage("Nhập mã bảo mật không đúng !");
			return "input";
		}
		
		Session hs = XHibernate.openSession();
		Transaction t = hs.beginTransaction();
		try{
			if(upload.hasFile()){
				String fileName = upload.saveIn("images/customers/");
				model.setPhoto(fileName);
			}
			hs.save(model);
			t.commit();
			XWeb.setSession("user", model);
			
			/*
			 * Tìm url của trang kích hoạt tài khoản
			 */
			String url = ServletActionContext.getRequest().getRequestURL().toString();
			url = url.substring(0, url.lastIndexOf("/"));
			url += "/activate?id="+model.getId();
			
			String subject = "Welcome to eStoreJava";
			//String url = "http://localhost:9090/eStore357/activate?id="+model.getId();
			String body = "<a href='"+url+"'>Activate</a>";
			XMail.send(model.getEmail(), subject, body);
		}
		catch (Exception e) {
			addActionError(e.getMessage());
			t.rollback();
		}
		return "account";
	}
	
	@Action(value="/forgot")
	public void ajaxForgotPassword() throws Exception {
		try{
			String email = model.getEmail();
			Session hs = XHibernate.openSession();
			hs.refresh(model);
			if(!email.equals(model.getEmail())){
				XWeb.print("Sai địa chỉ email !");
			}
			else{
				XMail.send(email, "Forgot password", 
						"Your password is " + model.getPassword());
				XWeb.print("Mật khẩu đã được gửi đến email của bạn !");
			}
		}
		catch (Exception e) {
			XWeb.print("Sai mã đăng nhập !");
		}
	}
	
	@Action(value="/signOut")
	public String signOut() throws Exception {
		
		XWeb.authenticate();
		
		XWeb.removeAttribute("user");
		return "home";
	}
	
	@Action(value="/login")
	public void ajaxLogin() throws Exception {
		try{
			String pw = model.getPassword();
			Session hs = XHibernate.openSession();
			hs.refresh(model);
			if(!pw.equals(model.getPassword())){
				// sai mật khẩu
				XWeb.print("result={status:false, message:'"+getText("login.invalid.password")+"'}");
			}
			else{
				XWeb.setSession("user", model);
				XWeb.print("result={status:true, message:'"+getText("login.success")+"'}");
			}
		}
		catch (Exception e) {
			// sai mã đăng nhập
			XWeb.print("result={status:false, message:'"+getText("login.invalid.id")+"'}");
		}
	}

	Customer model = new Customer();
	@Override
	public Customer getModel() {
		return model;
	}

	/*
	 * Upload hình của thành viên 
	 * (đăng ký và cập nhật tài khoản)
	 */
	UploadModel upload = new UploadModel();
	
	public UploadModel getUpload() {
		return upload;
	}

	public void setUpload(UploadModel upload) {
		this.upload = upload;
	}
}
