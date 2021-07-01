package estore.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UploadAction extends ActionSupport{
	
	@Action(value="/nicUpload")
	public void nicUpload() throws Exception {
		ServletContext application = ServletActionContext.getServletContext();
		File nicImages = new File(application.getRealPath("nicImages"));
		if(!nicImages.isDirectory()){
			nicImages.mkdirs();
		}
		nicImage.renameTo(new File(nicImages, nicImageFileName));
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String uri = application.getContextPath() + "/nicImages/" + nicImageFileName;
		out.print("<script>top.nicUploadButton.statusCb({ done:1, width:'300', url:'" + uri + "'});</script>");
	}
	
	File nicImage;
	String nicImageFileName, nicImageContentType;
	
	public File getNicImage() {
		return nicImage;
	}
	public void setNicImage(File nicImage) {
		this.nicImage = nicImage;
	}
	public String getNicImageFileName() {
		return nicImageFileName;
	}
	public void setNicImageFileName(String nicImageFileName) {
		this.nicImageFileName = nicImageFileName;
	}
	public String getNicImageContentType() {
		return nicImageContentType;
	}
	public void setNicImageContentType(String nicImageContentType) {
		this.nicImageContentType = nicImageContentType;
	}
}
