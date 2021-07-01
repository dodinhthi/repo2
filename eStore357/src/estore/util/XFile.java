package estore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

/**
 * Chứa các hàm tiện ích xử lý đọc và ghi tập tin
 * 
 * @version 1.1
 * @author Nguyễn Nghiệm, nghiemn@fpt.edu.vn
 */
public class XFile {
	
	/**
	 * Đọc tập tin văn bản
	 * 
	 * @param file tập tin cần đọc
	 * @return dữ liệu đọc được
	 * @exception RuntimeException file không tồn tại hoặc không đọc được
	 */
	public static String readAllText(String file) {
		byte[] data = XFile.readAllBytes(file);
		return new String(data);
	}
	
	/**
	 * Ghi tập tin văn bản
	 * 
	 * @param file tập tin cần ghi
	 * @param text để ghi vào tập tin
	 * @exception RuntimeException ghi file bị lỗi
	 */
	public static void writeAllText(String file, String text){
		byte[] data = text.getBytes();
		XFile.writeAllBytes(file, data);
	}
	
	/**
	 * Đọc tập tin nhị phân
	 * 
	 * @param file tập tin cần đọc
	 * @return dữ liệu đọc được
	 * @exception RuntimeException file không tồn tại hoặc không đọc được
	 */
	public static byte[] readAllBytes(String file) {
		try{
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[fis.available()];
			fis.read(data);
			fis.close();
			return data;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Ghi tập tin nhị phân
	 * 
	 * @param file tập tin cần ghi
	 * @param data để ghi vào tập tin
	 * @exception RuntimeException ghi file bị lỗi
	 */
	public static void writeAllBytes(String file, byte[] data) {
		try{
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data);
			fos.close();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Đọc tập tin properties
	 * 
	 * @param file properties cần đọc
	 * @return dữ liệu đọc được
	 * @exception RuntimeException file không tồn tại hoặc không đọc được
	 */
	public static Properties readProperties(String file) {
		try{
			FileInputStream fis = new FileInputStream(file);
			Properties data = new Properties();
			data.load(fis);
			fis.close();
			return data;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Ghi tập tin properties
	 * 
	 * @param file properties cần ghi
	 * @param data để ghi vào tập tin
	 * @exception RuntimeException ghi file bị lỗi
	 */
	public static void writeProperties(String file, Properties data) {
		try{
			FileOutputStream fos = new FileOutputStream(file);
			data.store(fos, "");
			fos.close();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Đọc tập tin đối tượng
	 * 
	 * @param file đối tượng cần đọc
	 * @return dữ liệu đọc được
	 * @exception RuntimeException file không tồn tại hoặc không đọc được
	 */
	public static Object readObject(String file) {
		try{
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object data = ois.readObject();
			ois.close();
			fis.close();
			return data;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Ghi tập tin properties
	 * 
	 * @param file properties cần ghi
	 * @param data để ghi vào tập tin
	 * @exception RuntimeException ghi file bị lỗi
	 */
	public static void writeObject(String file, Object data) {
		try{
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			oos.close();
			fos.close();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Xóa file hoặc thư mục
	 * @param file là file hoặc thư mục cần xóa
	 */
	public static void deleteFile(File file) {
		if(file.isDirectory()){
			for(File f : file.listFiles()){
				deleteFile(f);
			}
		}
		file.delete();
	}
	
	/**
	 * Sao chép file hoặc thư mục
	 * @param src file hoặc thư mục nguồn
	 * @param dst thư mục chứa
	 */
	public static void copyFile(File src, File dst) {
		File newFile = new File(dst, src.getName());
		if(src.isDirectory()){
			newFile.mkdirs();
			for(File f : src.listFiles()){
				copyFile(f, newFile);
			}
		}
		else{
			writeAllBytes(newFile.getAbsolutePath(), readAllBytes(src.getAbsolutePath()));
		}
	}
	/**
	 * Di chuyển file hoặc thư mục
	 * @param src file hoặc thư mục nguồn
	 * @param dst thư mục chứa
	 */
	public static void moveFile(File src, File dst) {
		copyFile(src, dst);
		deleteFile(src);
	}
}