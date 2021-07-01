package estore.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import estore.util.XHibernate;
import estore.util.XHibernate2;

public class test {
	public static void main(String[] args) {
		insertOrder();
	}

	public static void cate() {
		Session session = XHibernate2.openSession();
		String hql = "from Category";
		Query q = session.createQuery(hql);
		List<Category> l = q.list();

		for (Category c : l) {
			System.out.println(c.getName());
		}

	}

	public static void insert() {
		Session session = XHibernate.openSession();
		Transaction tran = session.beginTransaction();
		Category c = new Category();
		c.setName("chi nhanh 212");
		c.setNamevn("chi nhanh 212");

		try {
			session.save(c);
			tran.commit();
			tran.rollback();
			System.out.println("thanh cong roi");

			/*
			 * System.out.println("thanh cong chi nhah 1");
			 * 
			 * session.close();
			 * 
			 * session=XHibernate2.openSession();
			 */

		} catch (Exception ex) {
			tran.rollback();
		}

	}

	public static void insertOrder() {
		List<String> namevn = new ArrayList<>();
		namevn.add("vn1");
		namevn.add("vn2");

		List<String> name = new ArrayList<>();
		namevn.add("en1");
		namevn.add("en2");

		Session session = XHibernate.openSession();
		Transaction tran = session.beginTransaction();

		try {
			String sql = "insert into Categories values(:lst1,:lst2)";
			Query query = session.createSQLQuery(sql);
			query.setParameter("lst1", "test");
			query.setParameter("lst2", "test");
			query.executeUpdate();
			tran.commit();

		} catch (Exception e) {
			tran.rollback();
			System.out.println(e.getMessage());
		}
	}

}
