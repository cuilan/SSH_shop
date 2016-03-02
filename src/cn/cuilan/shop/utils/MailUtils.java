package cn.cuilan.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 邮件发送工具类
 * 
 * @author 翠兰123
 * @date 2015年3月23日
 */
public class MailUtils {

	/**
	 * 发送邮件的方法
	 * 
	 * @param to
	 *            收件人
	 * @param code
	 *            激活码
	 */
	public static void sendMail(String to, String code) {
		/**
		 * 1.获得一个Session对象 2.创建一个代表邮件的对象Message. 3.发送邮件Transport
		 */
		// 1.获得一个Session对象
		Properties properties = new Properties();
		properties.setProperty("mail.host", "localhost");
		
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("stx@cuilan.com", "123456");
			}

		});

		// 2.创建一个代表邮件的对象Message
		Message message = new MimeMessage(session);
		try {
			// 设置发件人
			message.setFrom(new InternetAddress("stx@cuilan.com"));
			// 设置收件人
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 设置标题
			message.setSubject("来自双体系Hawks购物商城官方激活邮件");
			// 设置邮件正文
			message.setContent(
					"<h1>双体系Hawks购物商城官方激活邮件！</h1><br><h3>点击下面的超链接完成激活操作！</h3><br>"
							+ "<a href='http://127.0.0.1:8080/SSH_shop/user_active.action?code="
							+ code
							+ "'>http://127.0.0.1:8080/SSH_shop/user_active.action?code="
							+ code + "</a>", "text/html;charset=UTF-8");
			// 3.发送
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		sendMail("419475937@qq.com", "1111111111111111");
	}
	
}
