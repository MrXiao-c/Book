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
	private final static String sender = "709322612@qq.com";
	private final static String senderVerfirycode = "jhtmvsaizeyxbeef";

	/* public static void main(String[] args) throws Exception */ public static void sendMail(String to, String code)
			throws Exception {

		/*
		 * String to=""; String code="icudiuciubeiubvkjernvkomrionvioneirv";
		 */

		Properties properties = System.getProperties();
		// ����Э��
		properties.put("mail.transport.protocol", "smtp");
		// ��֤Ȩ��
		properties.put("mail.smtp.auth", "true");
		// qq��smtp.qq.com
		properties.put("mail.smtp.host", "smtp.qq.com");
		// ssl����˿�
		properties.put("mail.smtp.socketFactory.port", 465);// 465
		// �����Ƿ�ʹ��ssl��ȫ����
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, senderVerfirycode); // �������ʼ��û�������Ȩ��
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
		message.setSubject("�˺ż����ʼ�");
		// 2.4�����ʼ�������,��һ����������ָ�����͵��������ݣ��ڶ�����������ָ�����ĵ��ı�����
		if (code.length() == 6) {

			message.setContent("<h2>��֤�룺<h3 style='color:red;'>" + code + "</h3>", "text/html;charset=utf-8");

		} else {
			message.setContent(
					"<h2>����һ��Tkͼ���˺�ע�ἤ���ʼ���ȷ�ϼ����������³����ӣ�</h2><h3><a href='http://localhost��8080/Tbook/Check.jsp?code="
							+ code + "'>http://localhost:8080/Tbook/Check.jsp?code=" + code + "</a></h3>",
					"text/html;charset=utf-8");
		}

		return message;
	}

}
