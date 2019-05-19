package cn.itcast.itcaststore.utils;

import java.util.UUID;

public class FileUploadUtils {

	public static String subFileName(String fileName) {
		// TODO 自动生成的方法存根
		int index=fileName.lastIndexOf("\\");
		if(index==-1) {
			return fileName;
		}
		return fileName.substring(index+1);
	}
	
	public static String generateRandomFileName(String fileName) {
		int index=fileName.lastIndexOf(".");
		if(index != -1) {
			String ext=fileName.substring(index);
			return UUID.randomUUID().toString()+ext;
		}
		return UUID.randomUUID().toString();
	}
	
	public static String generateRandomDir(String uuidFileName) {
		int hashCode=uuidFileName.hashCode();
		int d1=hashCode & 0xf;
		int d2=(hashCode>>4) & 0xf;
		return "/" + d1 + "/" +d2;
	}

}
