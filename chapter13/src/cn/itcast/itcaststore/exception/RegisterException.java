package cn.itcast.itcaststore.exception;

//注册异常类，标识注册的异常信息
@SuppressWarnings("serial")
public class RegisterException extends Exception {
	public RegisterException() {
		super();
	}
	public RegisterException(String exceptionMsg) {
		super(exceptionMsg);
	}
}
