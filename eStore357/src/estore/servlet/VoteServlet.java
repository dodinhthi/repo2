package estore.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import estore.util.XImage;
import estore.util.XProperties;

/**
 * Servlet này được sử dụng để sinh hình kết quả bầu ch�?n
 * 
 * @version 1.1
 * @author Nguyễn Nghiệm, nghiemn@fpt.edu.vn
 */
@SuppressWarnings("serial")
@WebServlet("/vote.gif")
public class VoteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		XProperties app = (XProperties) getServletContext().getAttribute("app");
		
		int exe = app.getInt("exe", 1);
		int ver = app.getInt("ver", 1);
		int goo = app.getInt("goo", 1);
		int fai = app.getInt("fai", 1);
		int bad = app.getInt("bad", 1);

		// tạo ảnh 130x130
		BufferedImage image = XImage.createVote(exe, ver, goo, fai, bad);
				
		// xuất ảnh gửi về client dạng gif
		resp.setContentType("image/gif");
		ImageIO.write(image, "gif", resp.getOutputStream());
		resp.getOutputStream().close();
	}
}
