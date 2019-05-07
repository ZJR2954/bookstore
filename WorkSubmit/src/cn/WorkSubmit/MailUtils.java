package cn.WorkSubmit;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;

//邮件工具类
public class MailUtils {
	public static void sendMail(String emailAddress,String text,String fileUrl) throws AddressException,MessagingException{
		//创建获取邮件系统信息类的实例
		Properties props=new Properties();
		//设置邮件协议
		props.setProperty("mail.transport.protocol","SMTP");
		//设置接收方邮箱的服务器
		props.setProperty("mail.host", "smtp.qq.com");
		//设置SMTP服务器是否用户验证，一般为true
		props.setProperty("mail.smtp.auth", "true");
		//创建校验器对象
		Authenticator auth=new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("zjr2954@qq.com","udbgqtmqbycwbcca");//参数分别为发件方的邮箱和密码（授权码）
			}
		};
		//实例化邮件会话
		Session session = Session.getInstance(props,auth);
		//实例化邮件消息
		MimeMessage message=new MimeMessage(session);
		//设置邮件的发件人
		message.setFrom(new InternetAddress("zjr2954@qq.com"));
		//设置邮件的收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(emailAddress));
		//设置邮件的头字段，显示在邮件封面
		message.setSubject("你有一封邮件");		
		
		// 创建多重消息
        Multipart multipart = new MimeMultipart();
		//创建消息体部分
      	BodyPart messageBodyPart=new MimeBodyPart();    
      	//给消息体设置文本内容
      	messageBodyPart.setText(text);
        //把文本消息体添加到多重消息
        multipart.addBodyPart(messageBodyPart);
               
        // 附件部分
        if(fileUrl!=null && !fileUrl.equalsIgnoreCase("")) {
	        //重写一个消息体
	        messageBodyPart=new MimeBodyPart(); 
	        //设置要发送附件的文件名，用双斜杠开头
	        String filename = "\\test.zip";
	        //找到文件资源所在的路径，并实例化文件资源
	        FileDataSource source = new FileDataSource(fileUrl);
	        messageBodyPart.setDataHandler(new DataHandler(source));
	        messageBodyPart.setFileName(filename);    	        
	        //把文件消息体添加到多重消息
	        multipart.addBodyPart(messageBodyPart);
        }

        //设置消息体的编码格式
		message.setContent(multipart,"text/html;charset=utf-8");
		//发送邮件
		Transport.send(message);
	}
}
