package estore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Chứa các hàm tiện ích hỗ trợ Jdbc
 * 
 * @version 1.1
 * @author Nguyễn Nghiệm, nghiemn@fpt.edu.vn
 */
public class XJdbc {
	public static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static String dburl = "jdbc:sqlserver://localhost:1433;DatabaseName=eStore20";
	public static String userid = "sa";
	public static String password = "songlong";
	
	static{
		try{
			Class.forName(driver);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Mở kết nối đến CSDL
	 * @return kết nối
	 */
	static public Connection getConnection() {
		try{
			return DriverManager.getConnection(dburl, userid, password);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * �?óng kết nối đến CSDL
	 * @param connection là kết nối cần đóng
	 */
	static public void close(Connection connection) {
		try{
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Thực thi câu lệnh INSERT, UPDATE hay DELETE
	 * @param sql là câu lệnh sql chứa tham số
	 * @param values là danh sách giá trị truy�?n vào các tham số
	 */
	static public void executeUpdate(String sql, Object...values) {
		Connection c = getConnection();
		try{
			PreparedStatement pstmt = c.prepareStatement(sql);
			for(int i=1;i<=values.length;i++){
				pstmt.setObject(i, values[i-1]);
			}
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally{
			close(c);
		}
	}
	
	/**
	 * Thực thi câu lệnh SELECT
	 * @param sql là câu lệnh sql chứa tham số
	 * @param values là danh sách giá trị truy�?n vào các tham số
	 * @return tập kết quả
	 */
	static public ResultSet executeQuery(String sql, Object...values) {
		Connection c = getConnection();
		try{
			PreparedStatement pstmt = c.prepareStatement(sql);
			for(int i=1;i<=values.length;i++){
				pstmt.setObject(i, values[i-1]);
			}
			return pstmt.executeQuery();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Truy vấn 1 giá trị đơn
	 * @param sql là câu lệnh truy vấn chứa tham số
	 * @param values là danh sách giá trị truy�?n vào các tham số
	 * @param type kiểu giá trị nhận được
	 * @return giá trị nhận được
	 */
	@SuppressWarnings("unchecked")
	static public <T> T executeScalar(Class<T> type, String sql, Object...values) {
		try{
			ResultSet rs = executeQuery(sql, values);
			rs.next();
			T value = (T) rs.getObject(1);
			closeConnection(rs);
			return value;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * �?óng kết nối của ResultSet
	 * @param rs là ResultSet chứa kết nối cần đóng
	 */
	public static void closeConnection(ResultSet rs) {
		try{
			rs.getStatement().getConnection().close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
