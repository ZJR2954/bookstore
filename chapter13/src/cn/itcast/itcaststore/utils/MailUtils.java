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

//���ʼ�����
public class MailUtils {
	public static void sendMail(String userEmail,String emailMsg) throws AddressException, MessagingException {
		Properties props= new Properties();
		//�����ʼ�Э��
		props.setProperty("mail.transport.protoco", "SMTP");
		//���÷�������ķ�����
		props.setProperty("mail.host","smtp.qq.com");
		//����smtp�������Ƿ��û���֤
		props.setProperty("mail.smtp.auth", "true");
		//����У��������
		Authenticator auth=new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("zjr2954@qq.com","udbgqtmqbycwbcca");
			}
		};
		//ʵ�����ʼ��Ự
		Session session=Session.getInstance(props,auth);		
		
		Message message=new MimeMessage(session);
		//���÷�����
		message.setFrom(new InternetAddress("zjr2954@qq.com"));
		//�����ռ���
		message.setRecipient(RecipientType.TO, new InternetAddress(userEmail));
		//�����ʼ�����
		message.setSubject("�û������ʼ�!");
		message.setContent(emailMsg,"text/html;charset=utf-8");
		//����
		Transport.send(message);
		
	}
}
