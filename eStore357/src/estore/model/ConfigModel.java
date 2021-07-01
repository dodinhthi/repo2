package estore.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigModel extends Properties{
	String fileName = "Config.xml";
	
	public void store() throws IOException {
		this.storeToXML(new FileOutputStream(fileName), "");
	}
	
	public void load() throws IOException {
		this.loadFromXML(new FileInputStream(fileName));
	}
	
	public int getExe() {
		String exe = this.getProperty("exe", "1");
		return Integer.parseInt(exe);
	}
	public void setExe(int exe) {
		this.setProperty("exe", String.valueOf(exe));
	}
	
	public int getVer() {
		String ver = this.getProperty("ver", "1");
		return Integer.parseInt(ver);
	}
	public void setVer(int ver) {
		this.setProperty("ver", String.valueOf(ver));
	}
	
	public int getGoo() {
		String goo = this.getProperty("goo", "1");
		return Integer.parseInt(goo);
	}
	public void setGoo(int goo) {
		this.setProperty("goo", String.valueOf(goo));
	}
	
	public int getFai() {
		String fai = this.getProperty("fai", "1");
		return Integer.parseInt(fai);
	}
	public void setFai(int fai) {
		this.setProperty("fai", String.valueOf(fai));
	}
	
	public int getBad() {
		String bad = this.getProperty("bad", "1");
		return Integer.parseInt(bad);
	}
	public void setBad(int bad) {
		this.setProperty("bad", String.valueOf(bad));
	}
	
	////
	
	public int getVisitors() {
		System.out.println("hit");
		String visitors = this.getProperty("visitors", "100000");
		return Integer.parseInt(visitors);
	}
	
	public void increaseVisitors(){
		int v = getVisitors();
		this.setProperty("visitors", String.valueOf(v + 1));
	}
}
