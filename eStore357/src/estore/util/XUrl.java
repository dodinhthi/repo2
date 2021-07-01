package estore.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Chứa các hàm tiện ích download tài nguyên web
 * 
 * @version 1.0
 * @author Nguyễn Nghiệm, nnghiem@yahoo.com
 */
public class XUrl {
	
	/**
	 * Tải tài nguyên và lưu vào tập tin
	 * 
	 * @param url địa chỉ tài nguyên cần tải
	 * @param file tập tin lưu trữ tài nguyên
	 * @exception RuntimeException lỗi download
	 */
	public static void downloadFile(String url, File file) {
		try{
			byte[] data = downloadBytes(url);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data);
			fos.close();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Tải tài nguyên văn bản
	 * 
	 * @param url địa chỉ tài nguyên cần tải
	 * @return nội dung tài nguyên
	 * @exception RuntimeException lỗi download
	 */
	public static String downloadString(String url) {
		byte[] data = downloadBytes(url);
		try{
			return new String(data, "utf-8");
		}
		catch (Exception e) {
			return new String(data);
		}
	}
	
	/**
	 * Tải tài nguyên nhị phân
	 * 
	 * @param url địa chỉ tài nguyên cần tải
	 * @return nội dung tài nguyên
	 * @exception RuntimeException lỗi download
	 */
	public static byte[] downloadBytes(String url) {
		try{
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			URL u = new URL(url);
			InputStream is = u.openStream();
			byte[] block = new byte[4*1024];
			while(true){
				int n = is.read(block);
				if(n <= 0){
					break;
				}
				buffer.write(block, 0, n);
			}
			return buffer.toByteArray();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		String html = downloadString("http://tuoitre.vn/Chinh-tri-Xa-hoi/517962/Bao-Son-Tinh-giat-cap-15-o-Bac-Trung-Bo.html");
		int start = html.indexOf("<!-- MAIN CONTENT -->");
		int end = html.indexOf("<!-- QC BAi VIET -->");
		String bao = html.substring(start, end);
		System.out.print(bao);
	}
}
