package cn.itcast.itcaststore.utils;

import java.util.UUID;

//�ṩ�������
public class ActivateCodeUtils {
	public static String createActivateCode() {
		String code=UUID.randomUUID().toString();
		return code;
	}
}
