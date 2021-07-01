package estore.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@SuppressWarnings("serial")
public class XProperties extends Properties{

	public synchronized void load(String fileName) throws IOException {
		FileInputStream fis = new FileInputStream(fileName);
		super.load(fis);
		fis.close();
	}
	public void store(String fileName) throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		super.store(fos, "XProperties");
		fos.close();
	}
	
	public int increase(String name) {
		int value = getInt(name, 1) + 1;
		setInt(name, value);
		return value;
	}
	
	public int getInt(String name) {
		return getInt(name, -1);
	}
	public int getInt(String name, int defaultValue) {
		return Integer.parseInt(getProperty(name, String.valueOf(defaultValue)));
	}
	public void setInt(String name, int value) {
		setProperty(name, String.valueOf(value));
	}
	
	public double getDouble(String name) {
		return getDouble(name, -1);
	}
	public double getDouble(String name, double defaultValue) {
		return Double.parseDouble(getProperty(name, String.valueOf(defaultValue)));
	}
	public void setDouble(String name, double value) {
		setProperty(name, String.valueOf(value));
	}
}
