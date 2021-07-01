package estore.app;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import estore.util.XHibernate;

public class Test {
	public static void main(String[] args) {
		// thống kê doanh số theo hàng hóa
		String hql1 = "SELECT product.name, SUM(unitPrice*quantity*(1-discount))"
						+" FROM OrderDetail "
						+" WHERE order.orderDate BETWEEN :min AND :max "
						+" GROUP BY product.name";
		
		// thống kê doanh số theo chủng loại
		String hql2 = "SELECT product.category.name, SUM(unitPrice*quantity*(1-discount))"
				+" FROM OrderDetail "
				+" WHERE order.orderDate BETWEEN :min AND :max "
				+" GROUP BY product.category.name";
		
		// thống kê doanh số theo nhà cung cấp
		String hql3 = "SELECT product.supplier.name, SUM(unitPrice*quantity*(1-discount))"
				+" FROM OrderDetail "
				+" WHERE order.orderDate BETWEEN :min AND :max "
				+" GROUP BY product.supplier.name";
		
		// thống kê doanh số theo khách hàng
		String hql4 = "SELECT order.customer.fullname, SUM(unitPrice*quantity*(1-discount))"
				+" FROM OrderDetail "
				+" WHERE order.orderDate BETWEEN :min AND :max "
				+" GROUP BY order.customer.fullname";
		
		// thống kê doanh số theo năm
		String hql5 = "SELECT YEAR(d.order.orderDate), SUM(unitPrice*quantity*(1-discount))"
				+" FROM OrderDetail d"
				+" WHERE d.order.orderDate BETWEEN :min AND :max "
				+" GROUP BY YEAR(d.order.orderDate)";
		
		// thống kê doanh số theo tháng
		String hql6 = "SELECT MONTH(d.order.orderDate), SUM(unitPrice*quantity*(1-discount))"
				+" FROM OrderDetail d"
				+" WHERE d.order.orderDate BETWEEN :min AND :max "
				+" GROUP BY MONTH(d.order.orderDate)";
		
		// thống kê doanh số theo quí
		String hql7 = "SELECT CEILING(1.0*MONTH(d.order.orderDate)/3), SUM(unitPrice*quantity*(1-discount))"
				+" FROM OrderDetail d"
				+" WHERE d.order.orderDate BETWEEN :min AND :max "
				+" GROUP BY CEILING(1.0*MONTH(d.order.orderDate)/3)";		
		
		Session hs = XHibernate.openSession();
		Query q = hs.createQuery(hql7);
		q.setParameter("min", Date.valueOf("1996-12-31"));
		q.setParameter("max", Date.valueOf("2013-12-31"));
		List<Object[]> arrays = q.list();
		for(Object[] array : arrays){
			System.out.println(String.valueOf(array[0]));
			System.out.println(String.valueOf(array[1]));
			System.out.println();
		}
	}
}
