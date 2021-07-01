package estore.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TestJSONAction extends ActionSupport{
	@Action(value="/testjson")
	public void testjson() throws Exception {
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		
        out.print("{\"b\":\"hello\",\"a\":1000}");
	}
}