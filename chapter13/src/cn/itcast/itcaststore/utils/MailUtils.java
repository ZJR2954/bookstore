package cn.itcast.itcaststore.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//发邮件功能
public class MailUtils {
	public static void sendMail(String userEmail,String emailMsg) throws AddressException, MessagingException {
		Properties props= new Properties();
		//设置邮件协议
		props.setProperty("mail.transport.protoco", "SMTP");
		//设置发送邮箱的服务器
		props.setProperty("mail.host","smtp.qq.com");
		//设置smtp服务器是否用户验证
		props.setProperty("mail.smtp.auth", "true");
		//创建校验器对象
		Authenticator auth=new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("zjr2954@qq.com","udbgqtmqbycwbcca");
			}
		};
		//实例化邮件会话
		Session session=Session.getInstance(props,auth);		
		
		Message message=new MimeMessage(session);
		//设置发件人
		message.setFrom(new InternetAddress("zjr2954@qq.com"));
		//设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(userEmail));
		//设置邮件主题
		message.setSubject("用户激活邮件!");
		message.setContent(emailMsg,"text/html;charset=utf-8");
		//发送
		Transport.send(message);
		
	}
}
