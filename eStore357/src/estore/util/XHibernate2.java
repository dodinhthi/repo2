package estore.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * Chứa các hàm tiện ích hỗ trợ làm việc hibernate
 * 
 * @version 1.1
 * @author Nguyễn Nghiệm, nghiemn@fpt.edu.vn
 */
public class XHibernate2 {
	/*
	 * Tải file cấu hình
	 */
	static Configuration configuration = new AnnotationConfiguration().configure("chinhanh2.cfg.xml");
	static SessionFactory factory = configuration.buildSessionFactory();
	
	/**
	 * Lấy hibernate session để làm việc với CSDL
	 * @return hibernate session
	 */
	static public Session openSession() {
		return factory.openSession();
	}
}
