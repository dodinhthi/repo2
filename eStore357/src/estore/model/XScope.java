package estore.model;

import estore.entity.Customer;
import estore.util.XWeb;

public class XScope {
	/**
	 * Lấy user đã đăng nhập trong session. N
	 * @return user đã đăng nhập
	 */
	public static Customer getUser() {
		return (Customer) XWeb.getSession("user");
	}
	
	/**
	 * Lấy đối tượng chứa thông tin phân trang từ 
	 * session của trang đang được truy xuất
	 * @param id là định danh phân trang
	 * @param pageSize là số bản ghi của mỗi trang
	 * @return thông tin phân trang
	 */
	public static PagerModel getPager(String id, int pageSize) {
		PagerModel pager = getPager(id);
		pager.setPageSize(pageSize);
		return pager;
	}
	
	/**
	 * Lấy đối tượng chứa thông tin phân trang từ 
	 * session của trang đang được truy xuất
	 * @param id là định danh phân trang
	 * @return thông tin phân trang
	 */
	public static PagerModel getPager(String id) {
		PagerModel pager = (PagerModel) XWeb.getSession(id);
		if(pager == null){
			pager = new PagerModel();
			XWeb.setSession(id, pager);
		}
		return pager;
	}
	
	/**
	 * Lấy đối tượng chứa thông tin phân trang từ 
	 * session của trang đang được truy xuất
	 * @param id là định danh phân trang
	 * @param pageSize là số bản ghi của mỗi trang
	 * @param groupSize là trang trên mỗi nhóm
	 * @return thông tin phân trang
	 */
	public static PagerModel getPager(String id, int pageSize, int groupSize) {
		PagerModel pager = getPager(id, pageSize);
		pager.setGroupSize(groupSize);
		return pager;
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
