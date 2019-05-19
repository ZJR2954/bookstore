package cn.itcast.itcaststore.service;


import java.sql.SQLException;
import java.util.Date;

import javax.security.auth.login.LoginException;

import cn.itcast.itcaststore.dao.UserDao;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.exception.ActiveUserException;
import cn.itcast.itcaststore.exception.RegisterException;
import cn.itcast.itcaststore.utils.MailUtils;

public class UserService {
	UserDao dao=new UserDao();
	//ע�᷽��
	public void register(User user) throws Exception {		
		try {
			dao.addUser(user);
			//ע��ɹ������ʼ�
			String emailMsg="��лע�������̳ǣ����<a href='http://localhost:8080/chapter13/activeUser?activeCode="+
			user.getActiveCode()+"'>����</a>��ʹ��"+
			"<br/><br/>Ϊ������˻���ȫ������24Сʱ���ڼ���";
			MailUtils.sendMail(user.getEmail(), emailMsg);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RegisterException("ע��ʧ��!");
		}
		
	}

	public void activeUser(String activeCode) throws ActiveUserException{
		//�ü�������ڵ��û��Ƿ����s
		try {
			User user=dao.findUserByActiveCode(activeCode);
			if(user==null) {
				throw new ActiveUserException("�����벻��ȷ��");
			}
			Date registTime=user.getRegistTime();
			long time=System.currentTimeMillis()-registTime.getTime();
			if(time/(1000*60*60) > 24) {
				throw new ActiveUserException("���������,������ע�ᣡ");
			}
			if(user.getState()==1) {
				throw new ActiveUserException("�������¼��");
			}
			dao.activeUser(activeCode);
		}catch (SQLException e) {
			e.printStackTrace();
			throw new ActiveUserException("����ʧ�ܣ�");
		}
	}

	public User login(String username, String password) throws LoginException {
		// TODO �Զ����ɵķ������
		try {
			User user = dao.findUserByUsernameAndPassword(username,password);
			if(user != null) {
				if(user.getState()==1) {
					return user;
				}
				throw new LoginException("�û�δ���");
			}
			throw new LoginException("�û������������");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LoginException("��½ʧ�ܣ�");
		}
	}

	public void changeUser(User user,User nuser) {
		// TODO �Զ����ɵķ������
		try {
			dao.changeUser(user,nuser);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

	
}
