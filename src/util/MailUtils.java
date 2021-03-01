package util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	private final static String sender = "xxxx";
	private final static String senderVerfirycode = "xxxxx";

	/* public static void main(String[] args) throws Exception */ public static void sendMail(String to, String code)
			throws Exception {

		/*
		 * String to=""; String code="icudiuciubeiubvkjernvkomrionvioneirv";
		 */

		Properties properties = System.getProperties();
		// 连接协议
		properties.put("mail.transport.protocol", "smtp");
		// 验证权限
		properties.put("mail.smtp.auth", "true");
		// qq是smtp.qq.com
		properties.put("mail.smtp.host", "smtp.qq.com");
		// ssl邮箱端口
		properties.put("mail.smtp.socketFactory.port", 465);// 465
		// 设置是否使用ssl安全连接
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, senderVerfirycode); // 发件人邮件用户名、授权码
			}
		});

		session.setDebug(true);
		Message message = createsimplemail(session, to, code);
		Transport ts = session.getTransport();
		ts.connect("smtp.qq.com", sender, senderVerfirycode);
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();

	}

	private static Message createsimplemail(Session session, String to, String code)
			throws Exception, MessagingException {
		// TODO Auto-generated method stub
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(sender));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject("账号激活邮件");
		// 2.4设置邮件的正文,第一个参数用于指定发送的正文内容，第二个参数用于指定正文的文本类型
		if (code.length() == 6) {

			message.setContent("<h2>验证码：<h3 style='color:red;'>" + code + "</h3>", "text/html;charset=utf-8");

		} else {
			message.setContent(
					"<h2>这是一封Tk图书账号注册激活邮件，确认激活请点击以下超链接：</h2><h3><a href='http://localhost：8080/Tbook/Check.jsp?code="
							+ code + "'>http://localhost:8080/Tbook/Check.jsp?code=" + code + "</a></h3>",
					"text/html;charset=utf-8");
		}

		return message;
	}

}
