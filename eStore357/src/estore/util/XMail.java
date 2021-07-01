package estore.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Chứa các hàm tiện ích gửi email có tổ chức hàng đợi
 * 
 * @version 1.1
 * @author Nguyễn Nghiệm, nghiemn@fpt.edu.vn
 */
public class XMail
{
	static MailThread thread = new MailThread();
	
    private static String email = "kentphp1@gmail.com";
    private static String password = "songlong";
    
    private static Properties config = new Properties();
    static
    {
        config.setProperty("mail.smtp.host", "smtp.gmail.com");
        config.setProperty("mail.smtp.port", "465");
        config.setProperty("mail.smtp.starttls.enable","true");
        config.setProperty("mail.smtp.auth", "true");
        config.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        config.setProperty("mail.smtp.socketFactory.fallback", "false");
    }

    /**
     * Lấy mail session chuẩn bị gửi email
     *
     * @return mail session nhận được
     */
    public static Session getSession()
    {       
        Authenticator authenticator = new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        };
        return Session.getInstance(config, authenticator);
    }
    
    /**
     * Gửi email từ hệ thống
     * 
     * @param from email của người gửi (ví dụ: <b>Nguyen Nghiem &lt;nnghiem@yahoo.com&gt;</b> hoặc <b>nnghiem@yahoo.com</b>)
     * @param to danh sách email của người nhận, cách nhau bởi dấu phẩy hoặc chấm phẩy.
     * @param subject tiêu đề của email
     * @param body nội dung email
     * @exception RuntimeException lỗi tạo email
     */
    public static void send(String to, String subject, String body)
    {
        String from = String.format("EStore Web Master <%s>", email);
        XMail.send(from, to, subject, body);
    }
    
    /**
     * Gửi email với thông tin đơn giản
     * 
     * @param from email của người gửi (ví dụ: <b>Nguyen Nghiem &lt;nnghiem@yahoo.com&gt;</b> hoặc <b>nnghiem@yahoo.com</b>)
     * @param to danh sách email của người nhận, cách nhau bởi dấu phẩy hoặc chấm phẩy.
     * @param subject tiêu đề của email
     * @param body nội dung email
     * @exception RuntimeException lỗi tạo email
     */
    public static void send(String from, String to, String subject, String body)
    {
    	String cc = "", bcc = "", attach = "";
        XMail.send(from, to, cc, bcc, subject, body, attach);
    }
    
    /**
     * Gửi email đầy đủ thông tin
     * 
     * @param from email của người gửi (ví dụ: <b>Nguyen Nghiem &lt;nnghiem@yahoo.com&gt;</b> hoặc <b>nnghiem@yahoo.com</b>)
     * @param to danh sách email của người nhận, cách nhau bởi dấu phẩy hoặc chấm phẩy.
     * @param cc danh sách email của những người đồng nhận, cách nhau bởi dấu phẩy hoặc chấm phẩy.
     * @param bcc giống cc nhưng các email này không hiển thị trên mail.
     * @param subject tiêu đề của email
     * @param body nội dung email
     * @param attachments danh sách đường dẫn các tập tin đính kèm, cách nhau bởi dấu phẩy hoặc chấm phẩy.
     * @exception RuntimeException lỗi tạo email
     */
    public static void send(String from, String to, String cc, String bcc, String subject, String body, String attachments)
    {
    	try{
	        MimeMessage mail = new MimeMessage(getSession());
	
	        String[] addresses = from.split("[<>]");
	        String name = addresses[0].trim();
	        String email = (addresses.length > 1 ? addresses[1] : addresses[0]).trim();
	        InternetAddress fromAddress = new InternetAddress(email, name, "utf8");
	        
	        mail.setFrom(fromAddress);
	        mail.setReplyTo(new InternetAddress[]{fromAddress});
	        
	        final String toEmails = to.trim().replaceAll("[,;\\s]+", ",");
	        mail.addRecipients(Message.RecipientType.TO, toEmails);
	
	        if(cc != null && cc.trim().length() > 0)
	        {
	            final String ccEmails = cc.trim().replaceAll("[,;\\s]+", ",");
	            mail.addRecipients(Message.RecipientType.CC, ccEmails);
	        }
	
	        if(bcc != null && bcc.trim().length() > 0)
	        {
	            final String bccEmails = bcc.trim().replaceAll("[,;\\s]+", ",");
	            mail.addRecipients(Message.RecipientType.BCC, bccEmails);
	        }
	        mail.setSubject(subject, "utf8");
	        mail.setContent(body, "text/html; charset=utf8");
	        mail.setSentDate(new Date());
	
	        if(attachments != null && attachments.trim().length() > 0)
	        {
	            MimeMultipart multiPart = new MimeMultipart();
	            
	            MimeBodyPart mailBodyPart = new MimeBodyPart();
	            mailBodyPart.setContent(body, "text/html; charset=utf8");
	            multiPart.addBodyPart(mailBodyPart);
	
	            String[] paths = attachments.split("[,;]+");
	            for(String path : paths)
	            {
	                File file = new File(path.trim());
	                MimeBodyPart attachBodyPart = new MimeBodyPart();
	                FileDataSource fds = new FileDataSource(file);
	                attachBodyPart.setDataHandler(new DataHandler(fds));
	                attachBodyPart.setFileName(file.getName());
	                multiPart.addBodyPart(attachBodyPart);
	            }
	            mail.setContent(multiPart);
	        }
	        
	        //Transport.send(mail);
	        thread.queue(mail);
    	}
    	catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}

class MailThread extends Thread{
	List<MimeMessage> mails = new ArrayList<MimeMessage>();
	boolean running = false, stopped = false;
	
	public MailThread(){
		this.start();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(!stopped){
			if(mails.size() > 0){
				MimeMessage mail = mails.remove(0);
				try {
					Transport.send(mail);
					System.out.println("Sent");
				} 
				catch (MessagingException e) {
					e.printStackTrace();
				}
			}
			else{
				this.suspend();
				running = false;
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void queue(MimeMessage mail){
		mails.add(mail);
		if(running == false){
			this.resume();
			running = true;
		}
	}
	
	public void stopThread(){
		stopped = true;
	}
}