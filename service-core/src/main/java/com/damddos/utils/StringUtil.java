package com.damddos.utils;

public class StringUtil {
//	判断字符串是否为空
	public static boolean isEmpty(String str) {
		if(str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}
}
