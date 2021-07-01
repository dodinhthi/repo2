package estore.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import estore.entity.Product;
import estore.model.ShoppingCart;
import estore.util.XWeb;

@Results(value={
	@Result(name="products", type="redirectAction", params={"actionName", "products"}),
	@Result(name="cart", location="/CartManager.jsp"),
	@Result(name="checkout", location="/Checkout.jsp")
})
@SuppressWarnings("serial")
public class CartManagerAction extends ActionSupport{

	@Action(value="/manageCart")
	public String manageCart() throws Exception {
		ShoppingCart cart = ShoppingCart.getCart();
		if(XWeb.getParameter("btnUpdate") != null){
			Map<Integer, Product> items =  cart.getItems();
			for(Product p : items.values()){
				int id = p.getId();
				int quantity = XWeb.getIntParameter("qty" + id, 0);
				cart.update(id, quantity);
			}
		}
		else if(XWeb.getParameter("btnClear") != null){
			cart.clear();
		}
		else if(XWeb.getParameter("btnContinue") != null){
			return "products";
		}
		else if(XWeb.getParameter("btnCheckout") != null){
			XWeb.authenticate();
			return "checkout";
		}
		return "cart";
	}

	@Action(value="/viewCart")
	public String viewCart() throws Exception {
		return "cart";
	}

	@Action(value="/addToCart")
	public void addToCart() throws Exception {
		int id = XWeb.getIntParameter("id", 0);
		ShoppingCart cart = ShoppingCart.getCart();
		cart.add(id);
		String html = String.format("fnUpdateCartInfo(%s, %s)", 
				cart.getItemCount(), cart.getTotalAmount());
		XWeb.print(html);
	}
}
