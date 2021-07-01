package estore.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class XImage {
	public static BufferedImage createVote(int exe, int ver, int goo, int fai, int bad) {
		int w = 130, h = 130;
		BufferedImage image = create(w, h);
		Graphics g = image.getGraphics();
		
		int total = exe + ver + goo + fai + bad;
		int start = 0, angle = 0;
		
		// mÃ u cá»§a quáº¡t execllent
		start += angle; angle = 360 * exe / total;
		g.setColor(Color.RED);
		g.fillArc(0, 0, w, h, start, angle);
		
		// mÃ u cá»§a quáº¡t very good
		start += angle; angle = 360 * ver / total;
		g.setColor(Color.BLUE);
		g.fillArc(0, 0, w, h, start, angle);
		
		// mÃ u cá»§a quáº¡t good
		start += angle; angle = 360 * goo / total;
		g.setColor(Color.GREEN);
		g.fillArc(0, 0, w, h, start, angle);
		
		// mÃ u cá»§a quáº¡t fair
		start += angle; angle = 360 * fai / total;
		g.setColor(Color.CYAN);
		g.fillArc(0, 0, w, h, start, angle);
		
		// mÃ u cá»§a quáº¡t bad
		start += angle; angle = 360 * bad / total;
		g.setColor(Color.PINK);
		g.fillArc(0, 0, w, h, start, 360-start);
		
		return image;
	}
	
	public static BufferedImage createCaptcha(String code) {
		Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.BLACK, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW};
		int w = 200, h = 70;
		BufferedImage image = create(w, h);
		Graphics g = image.getGraphics();
		
		// gieo 1000 dáº¥u cháº¥m mÃ u Ä‘á»� á»Ÿ vá»‹ trÃ­ ngáº«u nhiÃªn
		g.setColor(colors[Integer.parseInt(code) % colors.length]);
		for(int i=0;i<1000;i++){
			int x = (int)Math.round(Math.random() * w);
			int y = (int)Math.round(Math.random() * h);
			g.fillOval(x, y, 2, 2);
		}
		
		// váº½ mÃ£ captcha chá»¯ Ä‘áº­m kÃ­ch thÆ°á»›c 45
		g.setFont(Font.decode("IMPACT-BOLD-45"));
		g.drawString(code, 20, 50);
		
		// gieo 2000 dáº¥u cháº¥m mÃ u tráº¯ng á»Ÿ vá»‹ trÃ­ ngáº«u nhiÃªn
		g.setColor(Color.WHITE);
		for(int i=0;i<2000;i++){
			int x = (int)Math.round(Math.random() * w);
			int y = (int)Math.round(Math.random() * h);
			g.fillOval(x, y, 2, 2);
		}
		return image;
	}
	
	/**
	 * Tạo ảnh trống có nền trắng
	 * @param w là chiều rộng ảnh
	 * @param h là chiều cao ảnh
	 * @return ảnh được tạo ra
	 */
	public static BufferedImage create(int w, int h) {
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);
		return image;
	}
}
