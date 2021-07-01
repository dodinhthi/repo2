package estore.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import estore.model.ConfigModel;
import estore.util.XImage;

@WebServlet("/vote2.gif")
public class TestVoteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ConfigModel cfg = (ConfigModel) getServletContext().getAttribute("config");
		
		int exe = cfg.getExe();
		int ver = cfg.getVer();
		int goo = cfg.getGoo();
		int fai = cfg.getFai();
		int bad = cfg.getBad();
		
		BufferedImage image = XImage.createVote(exe, ver, goo, fai, bad);
		
		resp.setContentType("image/gif");
		ImageIO.write(image, "gif", resp.getOutputStream());
		resp.getOutputStream().close();
	}
}
