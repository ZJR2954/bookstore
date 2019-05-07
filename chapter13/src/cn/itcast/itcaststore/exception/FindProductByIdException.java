package cn.itcast.itcaststore.exception;

import cn.itcast.itcaststore.dao.UserDao;

@SuppressWarnings("serial")
public class FindProductByIdException extends Exception {
	UserDao dao=new UserDao();
	public FindProductByIdException() {
		super();
	}
	public FindProductByIdException(String exceptionMsg) {
		super(exceptionMsg);
	}
}
