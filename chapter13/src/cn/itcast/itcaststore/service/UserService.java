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
	//注册方法
	public void register(User user) throws Exception {		
		try {
			dao.addUser(user);
			//注册成功，发邮件
			String emailMsg="感谢注册网上商城，点击<a href='http://localhost:8080/chapter13/activeUser?activeCode="+
			user.getActiveCode()+"'>激活</a>后使用"+
			"<br/><br/>为了你的账户安全，请在24小时以内激活";
			MailUtils.sendMail(user.getEmail(), emailMsg);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RegisterException("注册失败!");
		}
		
	}

	public void activeUser(String activeCode) throws ActiveUserException{
		//该激活码对于的用户是否存在s
		try {
			User user=dao.findUserByActiveCode(activeCode);
			if(user==null) {
				throw new ActiveUserException("激活码不正确！");
			}
			Date registTime=user.getRegistTime();
			long time=System.currentTimeMillis()-registTime.getTime();
			if(time/(1000*60*60) > 24) {
				throw new ActiveUserException("激活码过期,请重新注册！");
			}
			if(user.getState()==1) {
				throw new ActiveUserException("不用重新激活！");
			}
			dao.activeUser(activeCode);
		}catch (SQLException e) {
			e.printStackTrace();
			throw new ActiveUserException("激活失败！");
		}
	}

	public User login(String username, String password) throws LoginException {
		// TODO 自动生成的方法存根
		try {
			User user = dao.findUserByUsernameAndPassword(username,password);
			if(user != null) {
				if(user.getState()==1) {
					return user;
				}
				throw new LoginException("用户未激活！");
			}
			throw new LoginException("用户名或密码错误！");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LoginException("登陆失败！");
		}
	}

	public void changeUser(User user,User nuser) {
		// TODO 自动生成的方法存根
		try {
			dao.changeUser(user,nuser);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	
}
