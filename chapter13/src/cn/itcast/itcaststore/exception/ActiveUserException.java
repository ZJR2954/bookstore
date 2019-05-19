package cn.itcast.itcaststore.exception;

import cn.itcast.itcaststore.dao.UserDao;

@SuppressWarnings("serial")
public class ActiveUserException extends Exception {
	UserDao dao=new UserDao();
	public ActiveUserException() {
		super();
	}
	public ActiveUserException(String exceptionMsg) {
		super(exceptionMsg);
	}
}
