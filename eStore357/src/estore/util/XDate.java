package estore.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Chứa các hàm tiện ích xử lý Date
 * 
 * @version 1.1
 * @author Nguyễn Nghiệm, nghiemn@fpt.edu.vn
 */
public class XDate {
	public static final String DEFAULT_PATTERN = "MM/dd/yyyy";
	private static SimpleDateFormat formater = new SimpleDateFormat();
	
	/**
	 * Chuyển String sang Date với định dạng mặc định MM/dd/yyyy
	 * @param date chuỗi chứa ngày cần chuyển
	 * @return đối tượng ngày đã chuyển
	 */
	static public java.sql.Date parse(String date) {
		return parse(date, DEFAULT_PATTERN);
	}
	/**
	 * Chuyển String sang Date theo định dạng tùy ý
	 * @param date chuỗi chứa ngày cần chuyển
	 * @param pattern chuỗi định dạng
	 * @return đối tượng ngày đã chuyển
	 */
	static public java.sql.Date parse(String date, String pattern) {
		formater.applyPattern(pattern);
		try {
			Date d = formater.parse(date);
			return new java.sql.Date(d.getTime());
		} 
		catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Chuyển Date sang String theo định dạng mặc định MM/dd/yyyy
	 * @param date là Date cần chuyển
	 * @return chuỗi đã chuyển
	 */
	static public String format(Date date) {
		return format(date, DEFAULT_PATTERN);
	}
	/**
	 * Chuyển Date sang String theo định dạng tùy ý
	 * @param date đối tượng Date cần chuyển
	 * @param pattern chuỗi định dạng
	 * @return chuỗi đã chuyển
	 */
	static public String format(Date date, String pattern) {
		formater.applyPattern(pattern);
		return formater.format(date);
	}
	public static Date getNow() {
		return new java.sql.Date(System.currentTimeMillis());
	}
}
