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

/**
 * Servlet này được sử dụng để sinh hình captcha
 * 
 * @version 1.1
 * @author Nguyễn Nghiệm, nghiemn@fpt.edu.vn
 */
@WebServlet("/captcha.gif")
public class CaptchaServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// Sinh mã ngẫu nhiên
		String code = String
				.valueOf(Math.round(100000 + Math.random() * 899999));
		BufferedImage image = XImage.createCaptcha(code);
		req.getSession().setAttribute("captcha", code);

		// xuất ảnh captcha về client
		resp.setContentType("image/gif");
		ImageIO.write(image, "gif", resp.getOutputStream());
		resp.getOutputStream().close();
	}
}
