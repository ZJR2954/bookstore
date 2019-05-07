package cn.itcast.itcaststore.utils;

import java.util.UUID;

//提供激活参数
public class ActivateCodeUtils {
	public static String createActivateCode() {
		String code=UUID.randomUUID().toString();
		return code;
	}
}
