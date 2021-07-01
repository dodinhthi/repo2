package estore.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

/**
 * Chứa các hàm tiện ích làm việc với các đối tượng web hữu ích
 * 
 * @version 1.2
 * @author Nguyễn Nghiệm, nghiemn@fpt.edu.vn
 */
public class XWeb {
	/*=======================NHÓM CHIA SẺ TRONG SCOPE=======================*/
	/**
	 * Đọc attribute từ request object
	 * @param attribute tên attribute cần lấy
	 * @return giá trị của attribute
	 */
	public static Object getRequest(String attribute) {
		return ServletActionContext.getRequest().getAttribute(attribute);
	}
	/**
	 * Ghi attribute vào request
	 * @param attribute là tên attribute
	 * @param value là giá trị của attribute
	 */
	public static void setRequest(String attribute, Object value) {
		ServletActionContext.getRequest().setAttribute(attribute, value);
	}
	/**
	 * Đọc attribute từ session object
	 * @param attribute tên attribute cần lấy
	 * @return giá trị của attribute
	 */
	public static Object getSession(String attribute) {
		return ServletActionContext.getRequest().getSession().getAttribute(attribute);
	}
	/**
	 * Ghi attribute vào session
	 * @param attribute là tên attribute
	 * @param value là giá trị của attribute
	 */
	public static void setSession(String attribute, Object value) {
		ServletActionContext.getRequest().getSession().setAttribute(attribute, value);
	}
	/**
	 * Lấy giá trị của attribute từ application object
	 * @param attribute tên attribute cần lấy
	 * @return giá trị của attribute
	 */
	public static Object getApplication(String attribute) {
		return ServletActionContext.getServletContext().getAttribute(attribute);
	}
	/**
	 * Ghi attribute vào application
	 * @param attribute là tên attribute
	 * @param value là giá trị của attribute
	 */
	public static void setApplication(String attribute, Object value) {
		ServletActionContext.getServletContext().setAttribute(attribute, value);
	}
	/**
	 * Xóa attribute khỏi tất cả các scope
	 * @param name là tên attribute cần xóa
	 */
	public static void removeAttribute(String name) {
		ServletActionContext.getRequest().removeAttribute(name);
		ServletActionContext.getRequest().getSession().removeAttribute(name);
		ServletActionContext.getServletContext().removeAttribute(name);
	}
	/**
	 * Tìm attribute trong tất cả các scope.
	 * Trình tự tìm kiếm request->session->application->valueStack
	 * @param name là tên attribute cần tìm
	 * @return giá trị của attribute tìm thấy
	 */
	public static Object findAttribute(String name) {
		if(getRequest(name) != null){
			return getRequest(name);
		}
		if(getSession(name) != null){
			return getSession(name);
		}
		return getApplication(name);
	}
	/*=======================NHÓM THAM SỐ VÀ COOKIE=======================*/
	/**
	 * Đọc tham số rừ request
	 * @param name là tên tham số
	 * @return giá trị tham số
	 */
	public static String getParameter(String name) {
		return ServletActionContext.getRequest().getParameter(name);
	}
	/**
	 * Đọc tham số rừ request và chuyển đổi sang số nguyên
	 * @param name là tên tham số
	 * @return giá trị tham số
	 */
	public static int getIntParameter(String name, int initValue) {
		String value = ServletActionContext.getRequest().getParameter(name);
		return value == null ? initValue : Integer.parseInt(value);
	}
	/**
	 * Đọc tham số rừ request và chuyển đổi sang số thực
	 * @param name là tên tham số
	 * @return giá trị tham số
	 */
	public static double getDoubleParameter(String name, double initValue) {
		String value = ServletActionContext.getRequest().getParameter(name);
		return value == null ? initValue : Double.parseDouble(value);
	}
	/**
	 * Đọc mảng chứa các giá trị của tham số từ request
	 * @param name là tên tham số
	 * @return mảng các giá trị tham số
	 */
	public static String[] getParameterValues(String name) {
		return ServletActionContext.getRequest().getParameterValues(name);
	}
	/**
	 * Lấy giá trị cookie gửi từ client
	 * @param name là tên cookie
	 * @return giá trị của cookie hoặc null nếu không tìm thấy cookie
	 */
	public static String getCookie(String name) {
		return getCookie(name, "");
	}
	/**
	 * Lấy giá trị cookie gửi từ client
	 * @param name là tên cookie
	 * @param defaultValue là giá trị mặc định khi không tìm thấy cookie
	 * @return giá trị của cookie hoặc giá trị mặc định nếu không tìm thấy cookie
	 */
	public static String getCookie(String name, String defaultValue) {
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		for(Cookie cookie : cookies){
			if(cookie.getName().equals(name)){
				return cookie.getValue();
			}
		}
		return defaultValue;
	}
	/**
	 * Tạo và gửi cookie về client để lưu giữ
	 * @param name là tên cookie
	 * @param value là giá trị cookie
	 * @param expiry là thời hạng cookie tồn tại trên máy client
	 * @return Cookie đã được gửi về client
	 */
	public static Cookie addCookie(String name, String value, int expiry) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(expiry);
		ServletActionContext.getResponse().addCookie(cookie);
		return cookie;
	}
	/*=======================NHÓM HÀM TIỆN ÍCH KHÁC=======================*/
	/**
	 * Gửi HTML về client
	 * @param html là mã html được gửi về client
	 * @throws IOException lỗi gửi dữ liệu
	 */
	public static PrintWriter print(String html) throws IOException {
		HttpServletResponse resp =  ServletActionContext.getResponse();
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(html);
		return out;
	}
	/**
	 * Chuyển hường sang url khác
	 * @param url là url đích
	 * @throws IOException không tìm thấy url mới
	 */
	public static void sendRedirect(String url) throws IOException{
		HttpServletResponse resp =  ServletActionContext.getResponse();
		resp.sendRedirect(url);
	}
	/**
	 * Chuyển đổi đường dẫn logic sang vật lý
	 * @param path là đường đẫn logic
	 * @return đường dẫn vật lý
	 */
	public static String getRealPath(String path) {
		return ServletActionContext.getServletContext().getRealPath(path);
	}
	/**
	 * Lấy địa chỉ cơ sở chứa trang web hiện tại
	 * @return địa chỉ tuyệt đối
	 */
	public static String getBaseUrl() {
		String url = ServletActionContext.getRequest().getRequestURL().toString();
		return url.substring(0, 1 + url.lastIndexOf("/"));
	}
	
	public static void authenticate() throws IOException {
		if(XWeb.getSession("user") == null){
			ServletActionContext.getResponse()
				.sendRedirect("AccessDenied.jsp");
		}
	}
}
