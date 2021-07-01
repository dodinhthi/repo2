package estore.listener;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import estore.model.ConfigModel;

@WebListener
public class AppListener implements ServletContextListener, HttpSessionListener{

	ConfigModel config = new ConfigModel();
	
	@Override
	public void contextDestroyed(ServletContextEvent e) {
		// chạy trước khi ứng dụng kết thúc
		try {
			config.store(); // lưu file cấu hình
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent e) {	
		// chạy sau khi ứng dụng bắt đầu
	  // System.out.println("loading eStore357");
		try {
			e.getServletContext().setAttribute("config", config);
			config.load(); // tải file cấu hình vào
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent e) {
		// chạy sau khi 1 phiên làm việc được tạo
		config.increaseVisitors();
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		// chạy trước khi 1 phiên làm việc hết hạng
	}

}
