package estore.model;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;

import estore.entity.Product;
import estore.util.XHibernate;
import estore.util.XWeb;

/**
 * Giỏ hàng sử dụng session để lưu trữ theo phiên
 * 
 * @version 1.1
 * @author Nguyễn Nghiệm, nghiemn@fpt.edu.vn
 */
public class ShoppingCart {
	/*
	 * Chứa danh sách các sản phẩm đã chọn trong giỏ
	 */
	Map<Integer, Product> items = new HashMap<Integer, Product>();

	/**
	 * Thêm một sản phẩm vào giỏ. Nếu sản phẩm đã có 
	 * trong giỏ thì chỉ tăng số lượng lên 1
	 * @param id là mã sản phẩm cần thêm
	 * @return sản phẩm đã được thêm vào
	 */
	public Product add(Integer id) {
		Product p = items.get(id);
		if(p != null){
			p.setQuantity(p.getQuantity() + 1);
		}
		else{
			Session hsession = XHibernate.openSession();
			p = (Product) hsession.load(Product.class, id);
			p.setQuantity(1);
			items.put(id, p);
			hsession.close();
		}
		return p;
	}
	
	/**
	 * Xóa 1 sản phẩm khỏi giỏ
	 * @param id là mã sản phẩm cần xóa
	 * @return sản phẩm đã bị xóa khỏi giỏ
	 */
	public Product remove(Integer id) {
		Product p = items.remove(id);
		return p;
	}
	
	/**
	 * Cập nhật số lượng của một sản phẩm trong giỏ
	 * @param id là mã sản phẩm cần cập nhật số lượng
	 * @param quantity là số lượng mới
	 * @return sản phẩm đã được cập nhật số lượng
	 */
	public Product update(Integer id, int quantity) {
		Product p = items.get(id);
		p.setQuantity(quantity);
		return p;
	}
	
	/**
	 * Xóa sạch các sản phẩm trong giỏ
	 */
	public void clear() {
		items.clear();
	}
	
	/**
	 * Lấy danh sách các sản phẩm trong giỏ
	 * @return Map chứa danh sách các sản phẩm trong giỏ
	 */
	public Map<Integer, Product> getItems() {
		return items;
	}
	
	/**
	 * Lấy số sản phẩm trong giỏ
	 * @return số sản phẩn trong giỏ
	 */
	public int getItemCount() {
		return items.size();
	}
	
	/**
	 * Lấy tổng tiền các mặt hàng trong giỏ
	 * @return tổng tiền của giỏ hàng
	 */
	public double getTotalAmount() {
		double total = 0;
		for(Product p : items.values()){
			total += p.getQuantity()*p.getUnitPrice()*(1-p.getDiscount());
		}
		return Math.round(total*1000)/1000.0;
	}
	
	/**
	 * Lấy giỏ hàng trong session. Nếu chưa tồn tại 
	 * thì giỏ hàng mới được tạo ra va lưu vào session
	 * @return giỏ hàng
	 */
	public static ShoppingCart getCart() {
		ShoppingCart cart = (ShoppingCart) XWeb.getSession("cart");
		if(cart == null){
			cart = new ShoppingCart();
			XWeb.setSession("cart", cart);
		}
		return cart;
	}
}
