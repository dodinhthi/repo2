package estore.action;

import java.sql.Date;
import java.util.List;

import nhatnghe.jws.BankService;
import nhatnghe.jws.BankServiceProxy;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import estore.entity.Customer;
import estore.entity.Order;
import estore.entity.OrderDetail;
import estore.entity.Product;
import estore.model.ShoppingCart;
import estore.util.XHibernate;
import estore.util.XWeb;

@Results(value={
	@Result(name="orders", location="/OrderList.jsp"),
	@Result(name="orderDetails", location="/OrderDetails.jsp"),
	@Result(name="input", location="/Checkout.jsp")
})
@SuppressWarnings("serial")
public class OrderAction extends ActionSupport implements ModelDriven<Order>{

	@Action(value="/orderDetails")
	public String orderDetails() throws Exception {
		XWeb.authenticate();
		
		Session hs = XHibernate.openSession();
		hs.refresh(model);
		return "orderDetails";
	}
	
	@Action(value="/myOrders")
	public String myOrders() throws Exception {
		XWeb.authenticate();
		return "orders";
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getOrders() {
		Customer user = (Customer) XWeb.getSession("user");
		String hql = "FROM Order WHERE customer.id='"+user.getId()+"'";
		Session hs = XHibernate.openSession();
		Query q = hs.createQuery(hql);
		return q.list();
	}
	
	@Action(value="/purchase")
	public String purchase() throws Exception {

		XWeb.authenticate();
		
		Session hs = XHibernate.openSession();
		Transaction t = hs.beginTransaction();
		try {
			/*--1. insert 1 bản ghi vào Order--*/
			model.setOrderDate(new Date(System.currentTimeMillis()));
			hs.save(model);
			
			/*--2. insert giỏ hàng vào OrderDetail--*/
			ShoppingCart cart = ShoppingCart.getCart();
			for(Product p : cart.getItems().values()){
				OrderDetail od = new OrderDetail();
				od.setProduct(p);
				od.setOrder(model);
				od.setUnitPrice(p.getUnitPrice());
				od.setDiscount(p.getDiscount());
				od.setQuantity(p.getQuantity());
				
				hs.save(od);
			}
			t.commit();
			
			// thanh toán với ngân hàng ảo ở đây
			BankService ws = new BankServiceProxy();
			String id = model.getCustomer().getId();
			double money = cart.getTotalAmount();
			ws.purchase(id, money);
		} 
		catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		}
		/*--3. thanh toán (giảm số tiền trong tài khoản)--*/
		return "orders";
	}
	
	Order model = new Order();
	@Override
	public Order getModel() {
		return model;
	}

}
