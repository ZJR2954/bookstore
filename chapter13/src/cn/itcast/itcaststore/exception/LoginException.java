package cn.itcast.itcaststore.exception;

import cn.itcast.itcaststore.dao.UserDao;

@SuppressWarnings("serial")
public class LoginException extends Exception {
	UserDao dao=new UserDao();
	public LoginException() {
		super();
	}
	public LoginException(String exceptionMsg) {
		super(exceptionMsg);
	}
}
