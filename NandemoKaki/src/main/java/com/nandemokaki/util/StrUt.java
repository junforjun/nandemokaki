package com.nandemokaki.util;

public class StrUt {

	/**
	 * 数値判定Util
	 * @param str 文字列
	 * @return
	 * 		false : 文字
	 * 		true  : 数字
	 */
	public static boolean isNumber(String str) {

		try {
			Integer.parseInt(str);
		} catch (Exception e) {
			return false;
		}

		return true;
	}
}
