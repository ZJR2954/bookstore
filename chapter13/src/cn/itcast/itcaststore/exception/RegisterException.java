package cn.itcast.itcaststore.exception;

//ע���쳣�࣬��ʶע����쳣��Ϣ
@SuppressWarnings("serial")
public class RegisterException extends Exception {
	public RegisterException() {
		super();
	}
	public RegisterException(String exceptionMsg) {
		super(exceptionMsg);
	}
}
